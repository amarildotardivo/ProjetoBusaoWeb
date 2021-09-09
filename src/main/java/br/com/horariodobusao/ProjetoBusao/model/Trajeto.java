package br.com.horariodobusao.ProjetoBusao.model;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Trajeto implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, updatable = false, unique = true, length = 100)
    @Enumerated(EnumType.STRING)
    private TipoOpcaoEnum opcao;
    
    private List<Localidade> localidades;
        
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
