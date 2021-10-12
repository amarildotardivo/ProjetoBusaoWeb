package br.com.horariodobusao.ProjetoBusao.model;

import br.com.horariodobusao.ProjetoBusao.model.annotation.*;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, updatable = true, length = 100)
    @NotBlank(message = "Nome obrigatório.")
    @Length(min = 3, message = "Nome deve ter no mínimo 3 caracteres.")
    @Length(max = 100, message = "Nome deve ter no máximo 100 caracteres.")
    private String nome;
    
    @Column(nullable = false, updatable = true, unique = true, length = 100)
    @NotBlank(message = "Email obrigatório.")
    @Length(min = 10, message = "Email deve ter no mínimo 10 caracteres.")
    @Length(max = 100, message = "Email deve ter no máximo 100 caracteres.")
    @EmailValidation
    private String email;
    
    @Column(nullable = false, updatable = true, unique = true, length = 100)
    @NotBlank(message = "Senha obrigatória.")
    @Length(min = 8, message = "Senha deve ter no mínimo 10 caracteres.")
    @Length(max = 100, message = "Senha deve ter no máximo 100 caracteres.")
    @PassValidation
    private String senha;
    
    @Column(nullable = true, updatable = true, unique = true, length = 14)
    @Length(min = 13, max = 14, message = "Telefone deve ter 13 ou 14 caracteres (Ex.: Residencial: (22)9999-9999 ou Celular: (22)99999-9999.")
    private String telefone;
    
    @Column(nullable = false, unique = true, updatable = true, length = 14)
    @NotBlank(message = "CPF obrigatório.")
    @CPF(message = "CPF inválido.")
    private String cpf;
    
    @Column(nullable = true, updatable = true, unique = false, length = 200)
    @Length(max = 200, message = "Endereço deve ter no máximo 200 caracteres.")
    private String endereco;
    
    

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.nome);
        hash = 73 * hash + Objects.hashCode(this.email);
        hash = 73 * hash + Objects.hashCode(this.senha);
        hash = 73 * hash + Objects.hashCode(this.telefone);
        hash = 73 * hash + Objects.hashCode(this.cpf);
        hash = 73 * hash + Objects.hashCode(this.endereco);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
