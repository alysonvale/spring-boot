package hd.systems.bank.entidades.auxiliar;

public class AuxTransferenciaRetorno {

    private String nome_cliente;
    private String conta;
    private Double valor_tranferido;
    private String nome_detinatario;
    private String conta_destinario;

    public AuxTransferenciaRetorno(String nome_cliente, String conta, Double valor_tranferido, String nome_detinatario, String conta_destinario) {
        super();
        this.nome_cliente = nome_cliente;
        this.conta = conta;
        this.valor_tranferido = valor_tranferido;
        this.nome_detinatario = nome_detinatario;
        this.conta_destinario = conta_destinario;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Double getValor_tranferido() {
        return valor_tranferido;
    }

    public void setValor_tranferido(Double valor_tranferido) {
        this.valor_tranferido = valor_tranferido;
    }

    public String getNome_detinatario() {
        return nome_detinatario;
    }

    public void setNome_detinatario(String nome_detinatario) {
        this.nome_detinatario = nome_detinatario;
    }

    public String getConta_destinario() {
        return conta_destinario;
    }

    public void setConta_destinario(String conta_destinario) {
        this.conta_destinario = conta_destinario;
    }


}
