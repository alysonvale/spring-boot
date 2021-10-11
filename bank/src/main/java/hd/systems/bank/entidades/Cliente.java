package hd.systems.bank.entidades;

import java.text.ParseException;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import hd.systems.bank.entidades.excecao.EntitiesException;

import tools.Criptografar;

@Entity
public class Cliente {

    @Id
    @Column(nullable  = false)
    private String login;
    @Column(nullable = false)
    private String nome_cliente;
    @Column
    private String telefone_cliente;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private int codigo_banco;
    @Column(nullable = false)
    private String nome_banco;
    @Column(nullable = false)
    private String conta;
    @Column(nullable = false)
    private Double saldo;


    @OneToOne
    private Acesso acesso;

    // DATA DE ACESSO
    private Instant dt_acesso;

    public Cliente() {
        super();
    }

    // CONSTRUTOR
    public Cliente(String nome_cliente, String telefone_cliente, String login, String senha, int codigo_banco,
                   String nome_banco, String conta, Double saldo) throws Exception {
        this.setNome_cliente(nome_cliente);
        this.setTelefone_cliente(telefone_cliente);
        this.setLogin(login);
        this.setSenha(senha);
        this.setCodigo_banco(codigo_banco);
        this.setNome_banco(nome_banco);
        this.setConta(conta);
        this.setSaldo(saldo);
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        if (nome_cliente.length() >= 3) {

            this.nome_cliente = nome_cliente;
        } else {
            throw new EntitiesException("Nome do cliente Invalido: " + nome_cliente);
        }
    }

    public String getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(String telefone_cliente) {
        if (telefone_cliente.length() >= 11) {

            this.telefone_cliente = telefone_cliente;
        } else {
            throw new EntitiesException("Telefone do cliente Incorreto: " + telefone_cliente);
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login.length() >= 10) {

            this.login = login;
        } else {
            throw new EntitiesException("Login curto: " + login);
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        if (senha.length() >= 3) {
            this.senha = Criptografar.gerarHashMD5(senha);
        } else {
            throw new EntitiesException("Senha muito curta:" + telefone_cliente);
        }
    }

    public int getCodigo_banco() {
        return codigo_banco;
    }

    public String getNome_banco() {
        return nome_banco;
    }

    public void setNome_banco(String nome_banco) {
        if (senha.length() >= 3) {
            this.nome_banco = nome_banco;
        } else {
            throw new EntitiesException("Digite o nome do banco:" + nome_banco);
        }
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        if (conta.length() != 20) {
            throw new EntitiesException("Conta incorreta: " + conta + "o numero da conta deve conter 20 digitos:");
        }
        this.conta = conta;
    }

    public void setCodigo_banco(int codigo_banco) {
        if (senha.length() >= 3) {
            this.codigo_banco = codigo_banco;
        } else {
            throw new EntitiesException("Digite o código do banco:" + codigo_banco);
        }
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public Instant getDt_acesso() {
        if (dt_acesso == null) {
            return null;
        }
        return dt_acesso;
    }

    public void setDt_acesso(Instant data) throws ParseException {
        dt_acesso = data;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Cliente))
            return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(acesso, other.acesso) && codigo_banco == other.codigo_banco
                && Objects.equals(conta, other.conta) && Objects.equals(dt_acesso, other.dt_acesso)
                && Objects.equals(login, other.login) && Objects.equals(nome_banco, other.nome_banco)
                && Objects.equals(nome_cliente, other.nome_cliente) && Objects.equals(saldo, other.saldo)
                && Objects.equals(senha, other.senha) && Objects.equals(telefone_cliente, other.telefone_cliente);
    }

    @Override
    public String toString() {
        return "Cliente [login=" + login + ", nome_cliente=" + nome_cliente + ", telefone_cliente=" + telefone_cliente
                + ", senha=" + senha + ", codigo_banco=" + codigo_banco + ", nome_banco=" + nome_banco + ", conta="
                + conta + ", saldo=" + saldo + ", acesso=" + acesso + ", dt_acesso=" + dt_acesso + "]";
    }

}
