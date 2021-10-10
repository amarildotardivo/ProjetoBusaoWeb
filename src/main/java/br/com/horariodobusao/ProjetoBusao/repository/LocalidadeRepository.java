package br.com.horariodobusao.ProjetoBusao.repository;

import br.com.horariodobusao.ProjetoBusao.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Long>{
    
}
