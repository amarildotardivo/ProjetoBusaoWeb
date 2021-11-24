package br.com.horariodobusao.ProjetoBusao.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Administrador extends Pessoa{
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, message = "Administrador deve ter no mínimo 1 permissão!")
    private List<Permissao> permissoes = new ArrayList<>();

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }    
    
}
