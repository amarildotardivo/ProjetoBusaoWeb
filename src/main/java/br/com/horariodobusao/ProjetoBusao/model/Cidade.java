package br.com.horariodobusao.ProjetoBusao.model;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import java.util.*;

@Entity
public class Cidade implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, updatable = true, unique = true, length = 100)
    private String nome;
    
    @OneToMany
    @JoinColumn(nullable = false)
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
