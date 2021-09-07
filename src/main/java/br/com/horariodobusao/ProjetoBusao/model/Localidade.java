package br.com.horariodobusao.ProjetoBusao.model;

import java.util.*;
import java.io.Serializable;

public class Localidade implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int id;
    private List<Cidade> cidades;
    private String horario;
    
    private Trajeto trajeto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cidade> getCidade() {
        return cidades;
    }

    public void setCidade(List<Cidade> cidade) {
        this.cidades = cidade;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Trajeto getTrajeto() {
        return trajeto;
    }

    public void setTrajeto(Trajeto trajeto) {
        this.trajeto = trajeto;
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
