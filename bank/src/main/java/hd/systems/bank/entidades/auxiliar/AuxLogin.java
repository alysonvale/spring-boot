package hd.systems.bank.entidades.auxiliar;

import java.util.Objects;

public class AuxLogin {

    private String login;
    private String senha;
    private Integer caixa;
    private String conta;

    public AuxLogin(){}

    public AuxLogin(String login, String senha, Integer caixa, String conta) {
        super();
        this.login = login;
        this.senha = senha;
        this.caixa = caixa;
        this.conta = conta;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getCaixa() {
        return caixa;
    }

    public void setCaixa(Integer caixa) {
        this.caixa = caixa;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    @Override
    public String toString() {
        return "auxLogin [login=" + login + ", senha=" + senha + ", caixa=" + caixa + ", conta=" + conta + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(caixa, conta, login, senha);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof AuxLogin))
            return false;
        AuxLogin other = (AuxLogin) obj;
        return Objects.equals(caixa, other.caixa) && Objects.equals(conta, other.conta)
                && Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
    }


}
