package hd.systems.bank.recursos;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hd.systems.bank.entidades.Acesso;
import hd.systems.bank.entidades.Cliente;
import hd.systems.bank.entidades.Caixa;
import hd.systems.bank.entidades.auxiliar.AuxAcesso;
import hd.systems.bank.entidades.auxiliar.AuxCliente;
import hd.systems.bank.entidades.auxiliar.AuxConsultaCliente;
import hd.systems.bank.entidades.auxiliar.AuxLogin;
import hd.systems.bank.entidades.auxiliar.AuxTransferencia;
import hd.systems.bank.entidades.auxiliar.AuxTransferenciaRetorno;
import hd.systems.bank.services.AcessoServico;
import hd.systems.bank.services.ClienteServico;
import hd.systems.bank.services.CaixaServico;
import hd.systems.bank.services.excecoes.AcessoExcecao;
import hd.systems.bank.services.excecoes.LoginExcecao;
import hd.systems.bank.services.excecoes.RecursoNaoEncontradoExcecao;
import hd.systems.bank.services.excecoes.TempoExpiradoException;
import hd.systems.bank.services.excecoes.TransferenciaExcecao;

import tools.Criptografar;

@RestController
@RequestMapping(value = "/banco")
public class OperacoesController {

    @Autowired
    private AcessoServico acessoServico;
    
    @Autowired
	private ClienteServico clienteServico;
    
    @Autowired
	private CaixaServico caixaServico;
    
    @PostMapping("/loginBanco")
    @ResponseBody
    AuxAcesso loginBanco(@RequestBody AuxLogin login) throws Exception {

        Acesso novoAcesso = new Acesso(null, null, null, 0, 0);
        Cliente cliente = clienteServico.findById(login.getLogin());
        Caixa caixa = caixaServico.findById(login.getCaixa());

        if (cliente.getSenha().equals(Criptografar.gerarHashMD5(login.getSenha()))
                && cliente.getLogin() == login.getLogin() && cliente.getConta().equals(login.getConta())) {
            if (caixa != null) {
                novoAcesso.setCaixa(login.getCaixa());
                novoAcesso.setCliente(cliente);
                Instant now = Instant.now();
                novoAcesso.setTempoInicial(now.toEpochMilli());
                novoAcesso.setTempoFinal();
                novoAcesso.setToken(Criptografar.gerartoken(novoAcesso.getCliente().getLogin()));
                cliente.setAcesso(novoAcesso);
                cliente.setDt_acesso(now);
                acessoServico.insert(novoAcesso);
                AuxAcesso auxAcesso = new AuxAcesso();
                auxAcesso.setAcesso(novoAcesso.getToken());
                return auxAcesso;
            } else {
                throw new LoginExcecao("Acesso não pode ser nulo!");
            }

        } else {
            throw new RecursoNaoEncontradoExcecao(
                    //PROBLEMA EXCEÇÃO ERRO 300 – CONTA, ISUARIO OU SENHA INVALIDA
                    cliente.getLogin());
        }

    }

    @GetMapping("/consultarSaldo")
    ResponseEntity<Object> consultarSaldo(@RequestBody AuxAcesso acesso) {
        Cliente cliente = validarAcesso(acesso).getCliente();
        Locale.setDefault(Locale.US);
        return ResponseEntity.ok().body(String.format("{ \"saldo\": \"%.2f\"}", cliente.getSaldo()));

    }


    @GetMapping("/consultarDadosConta")
    ResponseEntity<Object> consultarDadosConta(@RequestBody AuxAcesso acesso) {
        Cliente cliente = validarAcesso(acesso).getCliente();
        if (cliente != null) {
            AuxConsultaCliente retorno = new AuxConsultaCliente(cliente.getCodigo_banco(), cliente.getNome_banco(),
                    cliente.getConta(), cliente.getNome_cliente(), cliente.getTelefone_cliente());
            return ResponseEntity.ok().body(retorno);
        } else {
            throw new AcessoExcecao();
        }
    }

    @PutMapping("/alterarDadosConta")
    void alterarCliente(@RequestBody AuxCliente dados) {
        AuxAcesso acesso = new AuxAcesso(dados.getAcesso());
        Cliente cliente = (validarAcesso(acesso).getCliente());
        cliente.setNome_cliente(dados.getNome_cliente());
        cliente.setTelefone_cliente(dados.getTelefone_cliente());

        clienteServico.update(cliente.getLogin(), cliente);
    }


    private Acesso validarAcesso(AuxAcesso acesso) {
        List<Acesso> listaAcesso = acessoServico.findAll();
        String acc = acesso.getAcesso();
        Acesso achouAcesso = null;

        for (Acesso obj : listaAcesso) {
            String token = obj.getToken();
            if (acc.equals(token)) {
                achouAcesso = obj;
            }
        }
        if (achouAcesso == null) {
            throw new AcessoExcecao();
        }

        Instant now = Instant.now();
        if (now.toEpochMilli() > achouAcesso.getTempoFinal()) {
            throw new TempoExpiradoException();
        }

        List<Cliente> clientes = clienteServico.findAll();
        Cliente achouCliente = null;

        for (Cliente cliente : clientes) {
            if (cliente.getAcesso() != null) {
                String tokenCliente = cliente.getAcesso().getToken();
                if (acc.equals(tokenCliente)) {
                    achouCliente = cliente;
                }
            }
        }

        if (achouCliente == null) {
            throw new AcessoExcecao();
        }

        return achouAcesso;

    }


    @PostMapping("/transferencia")
    ResponseEntity<AuxTransferenciaRetorno> transferencia(@RequestBody AuxTransferencia auxTranferencia) {
        Cliente cliente = validarAcesso(auxTranferencia.getAcesso()).getCliente();
        DecimalFormat deci = new DecimalFormat("0.00");
        deci.setRoundingMode(RoundingMode.DOWN);
        auxTranferencia.setValor(Double.valueOf(deci.format(auxTranferencia.getValor().doubleValue())));
        if (auxTranferencia.getValor() > 0) {
            if (cliente.getSaldo() >= auxTranferencia.getValor()) {
                Cliente cliente_destino = auxTranferencia.contaDestino(this.clienteServico.findAll(),
                        auxTranferencia.getConta_destino());
                if (cliente_destino != null) {
                    clienteServico.debitarSaldo(cliente.getLogin(), auxTranferencia.getValor());
                    clienteServico.receberSaldo(cliente_destino.getLogin(), auxTranferencia.getValor());
                    AuxTransferenciaRetorno retorno = new AuxTransferenciaRetorno(cliente.getNome_cliente(),
                            cliente.getConta(), auxTranferencia.getValor(), cliente_destino.getNome_cliente(),
                            cliente_destino.getConta());
                    return ResponseEntity.ok().body(retorno);
                } else {
                    throw new TransferenciaExcecao("CLIENTE NÃO ENCONTRADO");
                }
            } else {
                throw new TransferenciaExcecao("SALDO INSUFICIÊNTE");
            }
        }
        throw new TransferenciaExcecao("TRANSAÇÃO NÃO REALIZADA, DIGITE UM VALOR MAIOR QUE 0:");
    }


}
