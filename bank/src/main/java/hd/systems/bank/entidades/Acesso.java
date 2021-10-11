package hd.systems.bank.entidades;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    @OneToMany(mappedBy = "cliente" )
    @JsonIgnore
    private Cliente cliente;
    private String token;
    private Integer caixa;
    private Long tempoInicial;
    private Long tempoFinal;

    public Acesso(){super();}

    public Acesso(Cliente cliente, String token, Integer caixa, long tempoInicial, long tempoFinal) {
        super();
        this.cliente = cliente;
        this.token = token;
        this.caixa = caixa;
        this.tempoInicial = tempoInicial;
        this.tempoFinal = tempoFinal;

    }

    public Long getId(){return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return  token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getCaixa() {
        return caixa;
    }

    public void setCaixa(Integer caixa) {
        this.caixa = caixa;
    }

    public Long getTempoInicial() {
        return tempoInicial;
    }

    public void setTempoInicial(Long tempoInicial) {
        this.tempoInicial = tempoInicial;
    }

    public Long getTempoFinal() {
        return tempoFinal;
    }

    public void setTempoFinal() {
        this.tempoFinal = getTempoInicial() + 900000 ; //900000 Representa 15 minutos
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {this.cliente = cliente;}

    @Override
    public int hashCode() {
        return Objects.hash(caixa, cliente, id, tempoFinal, tempoInicial, token);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Acesso))
            return false;
        Acesso other = (Acesso) obj;
        return Objects.equals(caixa, other.caixa) && Objects.equals(cliente, other.cliente)
                && Objects.equals(id, other.id) && Objects.equals(tempoFinal, other.tempoFinal)
                && Objects.equals(tempoInicial, other.tempoInicial) && Objects.equals(token, other.token);
    }

    @Override
    public String toString() {
        return "Acesso [id=" + id + ", cliente=" + cliente + ", token=" + token + ", caixa=" + caixa + ", tempoInicial="
                + tempoInicial + ", tempoFinal=" + tempoFinal + "]";
    }



}
