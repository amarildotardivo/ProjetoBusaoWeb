package br.com.horariodobusao.ProjetoBusao.model;

import com.fasterxml.jackson.annotation.*;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Linha implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, updatable = true, length = 100)
    @NotBlank(message = "Nome da Linha  obrigatório.")
    @Length(min = 3, message = "Nome deve ter no mínimo 3 caracteres.")
    @Length(max = 100, message = "Nome deve ter no máximo 100 caracteres.")
    private String nomeLinha;
    
    @JsonIgnore
    @OneToMany(mappedBy = "linha", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, message = "A linha deve ter no mínimo 1 Trajeto.")
    @Valid
    private List<Trajeto> trajetos = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Funcionário obrigatório.")
    private Funcionario funcionario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeLinha() {
        return nomeLinha;
    }

    public void setNomeLinha(String nomeLinha) {
        this.nomeLinha = nomeLinha;
    }

    public List<Trajeto> getTrajetos() {
        return trajetos;
    }

    public void setTrajetos(List<Trajeto> trajetos) {
        this.trajetos = trajetos;
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
        hash = 17 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
