package hd.systems.bank.entidades.auxiliar;

import java.util.List;

import hd.systems.bank.entidades.Cliente;

public class AuxTransferencia {

    private AuxAcesso acesso;
    private Double valor;
    private String conta_destino;

    public AuxAcesso getAcesso() {
        return acesso;
    }

    public void setAcesso(AuxAcesso acesso) {
        this.acesso = acesso;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getConta_destino() {
        return conta_destino;
    }

    public void setConta_destino(String conta_destino) {
        this.conta_destino = conta_destino;
    }

    // RETORNAR O CLIENTE NA LISTA
    public Cliente contaDestino(List<Cliente> clientes, String conta_destino) {
        for (Cliente c : clientes) {
            if (c.getConta().equals(conta_destino)) {
                return c;
            }
        }
        return null;
    }

}
