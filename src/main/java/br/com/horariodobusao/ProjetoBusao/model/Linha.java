package br.com.horariodobusao.ProjetoBusao.model;

import java.io.Serializable;
import java.util.*;

public class Linha implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int id;
    private List<Trajeto> trajeto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Trajeto> getTrajeto() {
        return trajeto;
    }

    public void setTrajeto(List<Trajeto> trajeto) {
        this.trajeto = trajeto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
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
        final Linha other = (Linha) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
