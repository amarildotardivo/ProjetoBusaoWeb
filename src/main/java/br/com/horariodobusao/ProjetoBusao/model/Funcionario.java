package br.com.horariodobusao.ProjetoBusao.model;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
public class Funcionario extends Pessoa{
    
    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Linha> linhas = new ArrayList<>();

    public List<Linha> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }
    
    
}
