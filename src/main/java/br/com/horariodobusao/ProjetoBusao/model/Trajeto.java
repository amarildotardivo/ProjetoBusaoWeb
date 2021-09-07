package br.com.horariodobusao.ProjetoBusao.model;

import java.util.*;
import java.io.Serializable;


public class Trajeto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int id;
    private List<Localidade> localidades;
    private TipoOpcaoEnum opcao;
    
    private Linha linha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Localidade> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Localidade> localidades) {
        this.localidades = localidades;
    }

    public TipoOpcaoEnum getOpcao() {
        return opcao;
    }
    
    public void setOpcao(TipoOpcaoEnum opcao) {
        this.opcao = opcao;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
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
        final Trajeto other = (Trajeto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
