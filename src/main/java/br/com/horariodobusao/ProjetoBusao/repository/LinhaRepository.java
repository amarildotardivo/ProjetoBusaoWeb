package br.com.horariodobusao.ProjetoBusao.repository;

import br.com.horariodobusao.ProjetoBusao.model.Linha;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

@Repository
public interface LinhaRepository extends JpaRepository<Linha, Long>{
    @Query("SELECT l FROM Linha l WHERE l.nomeLinha = :nome")
    public void findByLinha(String nome);
}
