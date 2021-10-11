package hd.systems.bank.entidades.auxiliar;

public class AuxCliente {

    private String acesso;
    private String nome_cliente;
    private String telefone_cliente;

    public AuxCliente(){}

    public AuxCliente(String acesso, String nome_cliente, String telefone_cliente) {
        super();
        this.acesso = acesso;
        this.nome_cliente = nome_cliente;
        this.telefone_cliente = telefone_cliente;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
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

}
