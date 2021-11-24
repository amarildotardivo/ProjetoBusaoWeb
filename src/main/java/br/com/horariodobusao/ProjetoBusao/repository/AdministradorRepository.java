
package br.com.horariodobusao.ProjetoBusao.repository;

import br.com.horariodobusao.ProjetoBusao.model.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;


public interface AdministradorRepository extends org.springframework.data.jpa.repository.JpaRepository<Administrador, Long>{
    @Query("SELECT a FROM Administrador a WHERE a.cpf = :cpf OR a.email = :email")
    public List<Administrador> findByCpfOrEmail(@Param("cpf") String cpf, @Param("email") String email);

    public Administrador findByEmail(String email);
}
