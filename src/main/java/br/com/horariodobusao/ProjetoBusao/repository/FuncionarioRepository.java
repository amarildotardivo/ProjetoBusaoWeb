package br.com.horariodobusao.ProjetoBusao.repository;

import br.com.horariodobusao.ProjetoBusao.model.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

public interface FuncionarioRepository extends org.springframework.data.jpa.repository.JpaRepository<Funcionario, Long>{
    @Query("SELECT a FROM Funcionario a WHERE a.cpf = :cpf OR a.email = :email")
    public List<Funcionario> findByCpfOrEmail(@Param("cpf") String cpf, @Param("email") String email);
    
    public Funcionario findByEmail(String email);
}
