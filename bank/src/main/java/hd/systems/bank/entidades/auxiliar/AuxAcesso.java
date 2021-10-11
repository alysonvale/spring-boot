package hd.systems.bank.entidades.auxiliar;

import java.util.Objects;

public class AuxAcesso {

    private String acesso;

    // TODO AUTO-GENERATED CONSTRUCTOR STUB
    public AuxAcesso() {}

    public AuxAcesso(String acesso){
        super();
        this.acesso = acesso;
    }

    public String getAcesso(){return acesso;}

    public void setAcesso(String acesso){this.acesso = acesso;}

    @Override
    public String toString(){return "auxAcesso [acesso=" + acesso +"]";}

    @Override
    public int hashCode() {
        return Objects.hash(acesso);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof AuxAcesso))
            return false;
        AuxAcesso other = (AuxAcesso) obj;
        return Objects.equals(acesso, other.acesso);
    }

}
