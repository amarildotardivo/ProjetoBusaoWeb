package br.com.horariodobusao.ProjetoBusao.model;

import java.util.*;

public class Localidade extends Trajeto{
    private int id;
    private List<Cidade> cidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cidade> getCidade() {
        return cidade;
    }

    public void setCidade(List<Cidade> cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
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
        final Localidade other = (Localidade) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
