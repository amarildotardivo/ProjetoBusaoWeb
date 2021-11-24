package br.com.horariodobusao.ProjetoBusao.model;

import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import javax.validation.constraints.*;

@Entity
public class Funcionario extends Pessoa{
    
    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Linha> linhas = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, message = "Funcionário deve ter no mínimo 1 permissão!")
    private List<Permissao> permissoes = new ArrayList<>();

    public List<Linha> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    
}
