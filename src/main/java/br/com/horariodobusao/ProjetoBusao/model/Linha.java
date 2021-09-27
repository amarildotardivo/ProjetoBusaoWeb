package br.com.horariodobusao.ProjetoBusao.model;

import com.fasterxml.jackson.annotation.*;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.*;

@Entity
public class Linha implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @JsonIgnore
    @OneToMany(mappedBy = "linha", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, message = "A linha deve ter no mínimo 1 Trajeto.")
    private List<Trajeto> trajetos = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Funcionário obrigatório.")
    @Valid
    private Funcionario funcionario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Trajeto> getTrajeto() {
        return trajetos;
    }

    public void setTrajeto(List<Trajeto> trajeto) {
        this.trajetos = trajeto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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
