package br.com.horariodobusao.ProjetoBusao.model;

import com.fasterxml.jackson.annotation.*;
import java.util.*;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.*;

@Entity
public class Trajeto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, updatable = false, unique = true, length = 100)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Opção obrigatória.")
    private TipoOpcaoEnum opcao;
    
    @JsonIgnore
    @OneToMany(mappedBy = "trajeto", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 2, message = "O Trajeto deve ter no mínimo 2 localidades.")
    @Valid
    private List<Localidade> localidades = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Linha obrigatória.")
    @Valid
    private Linha linha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        hash = 79 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
   
    
    
}
