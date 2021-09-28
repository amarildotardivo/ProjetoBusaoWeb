package br.com.horariodobusao.ProjetoBusao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.*;

@Entity
public class Cidade implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, updatable = true, unique = true, length = 100)
    @NotBlank(message = "Nome obrigatório.")
    @Length(min = 3, message = "Nome deve ter no mínimo 3 caracteres.")
    @Length(max = 100, message = "Nome deve ter no máximo 100 caracteres.")
    private String nome;
    
    @OneToMany(mappedBy = "cidade")
    @JoinColumn(nullable = false)
    //Não validei a lista de localidades, pois ela já foi validada na classe Trajeto
    //Fiquei na dúvida
    private List<Localidade> localidade = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Localidade> getLocalidade() {
        return localidade;
    }

    public void setLocalidade(List<Localidade> localidade) {
        this.localidade = localidade;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cidade other = (Cidade) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
