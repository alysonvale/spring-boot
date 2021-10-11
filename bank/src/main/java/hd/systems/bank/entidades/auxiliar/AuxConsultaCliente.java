package hd.systems.bank.entidades.auxiliar;

public class AuxConsultaCliente {

    private int codigo_banco;
    private String nome_banco;
    private String conta;
    private String nome_cliente;
    private String telefone_cliente;

    public AuxConsultaCliente() {}

    public AuxConsultaCliente(int codigo_banco, String nome_banco, String conta, String nome_cliente, String telefone_cliente) {
        super();
        this.codigo_banco = codigo_banco;
        this.nome_banco = nome_banco;
        this.nome_cliente = nome_cliente;
        this.telefone_cliente = telefone_cliente;
        this.conta = conta;
    }

    public int getCodigo_banco() {
        return codigo_banco;
    }

    public void setCodigo_banco(int codigo_banco) {
        this.codigo_banco = codigo_banco;
    }

    public String getNome_banco() {
        return nome_banco;
    }

    public void setNome_banco(String nome_banco) {
        this.nome_banco = nome_banco;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(String telefone_cliente) {
        this.telefone_cliente = telefone_cliente;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

}
