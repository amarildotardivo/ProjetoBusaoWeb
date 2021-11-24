package br.com.horariodobusao.ProjetoBusao.repository;

import br.com.horariodobusao.ProjetoBusao.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
    
}
