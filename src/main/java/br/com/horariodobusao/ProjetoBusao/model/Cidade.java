package br.com.horariodobusao.ProjetoBusao.model;

import com.fasterxml.jackson.annotation.*;
import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.*;

@Entity
public class Cidade implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, updatable = true, unique = true, length = 100)
    @NotBlank(message = "Nome obrigatório.")
    @Length(min = 3, message = "Nome deve ter no mínimo 3 caracteres.")
    @Length(max = 100, message = "Nome deve ter no máximo 100 caracteres.")
    private String nome;
    
    //Não validei a lista de localidades, pois ela já foi validada na classe Trajeto
    //Fiquei na dúvida
    //@JoinColumn(nullable = false) - Retirado pois estava apresentando erro no BD
    @JsonIgnore 
    @OneToMany(mappedBy = "cidade")
    private List<Localidade> localidade = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Localidade> getLocalidade() {
        return localidade;
    }

    public void setLocalidade(List<Localidade> localidade) {
        this.localidade = localidade;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
