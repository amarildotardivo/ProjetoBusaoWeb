package br.com.horariodobusao.ProjetoBusao.model;

import java.util.*;

public class Funcionario extends Pessoa{
    private List<Linha> linhas;

    public List<Linha> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }
    
    
}
