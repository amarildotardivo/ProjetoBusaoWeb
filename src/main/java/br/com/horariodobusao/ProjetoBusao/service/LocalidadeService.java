package br.com.horariodobusao.ProjetoBusao.service;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class LocalidadeService {
    @Autowired
    private LocalidadeRepository repo;
    
    public List<Localidade> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        
        return repo.findAll(p).toList();
    }
    
    public List<Localidade> findAll(){
        return repo.findAll();
    }
    
    public Localidade findById(Long id){
        Optional<Localidade> result = repo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Trajeto não encontrada.");
        }
        return result.get();
    }
    
    public Localidade save(Localidade l){
        try{
            return repo.save(l);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar Localidade. " + e);
        }
    }
    
    public Localidade update(Localidade l){
        try{
            return repo.save(l);
        }catch(Exception e){
            throw new RuntimeException("Falha ao atualizar Localidade. " + e);
        }
    }
    
    public void delete(Long id){
        Localidade obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao deletar Localidade. " + e);
        }
    }
}
