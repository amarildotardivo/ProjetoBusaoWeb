package br.com.horariodobusao.ProjetoBusao.service;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository repo;
    
    public List<Cidade> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        
        return repo.findAll(p).toList();
    }
    
    public List<Cidade> findAll(){
        return repo.findAll();
    }
    
    public Cidade findById(Long id){
        Optional<Cidade> result = repo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Cidade não encontrada.");
        }
        return result.get();
    }
    
    public Cidade save(Cidade c){
        try{
            return repo.save(c);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar Cidade. " + e);
        }
    }
    
    public Cidade update(Cidade c){
        verificarExclusaoCidadeComLocalidade(c);
        
        try{
            return repo.save(c);
        }catch(Exception e){
            throw new RuntimeException("Falha ao atualizar Cidade. " + e);
        }
    }
    
    public void delete(Long id){
        Cidade obj = findById(id);
        
        verificarExclusaoCidadeComLocalidade(obj);
        
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao deletar Cidade. " + e);
        }
    }
    
    private void verificarExclusaoCidadeComLocalidade(Cidade c){
        List<Localidade> localidades = new ArrayList<>();
        
        for(Localidade l: localidades){
            if(l.getCidade().equals(c)){
                throw new RuntimeException("Não é possível excluir a cidade, pois está cadastrada em uma Linha.");
            }
        }
    }
}
