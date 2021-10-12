package br.com.horariodobusao.ProjetoBusao.service;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class LinhaService {
    @Autowired
    private LinhaRepository repo;
    
    public List<Linha> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        
        return repo.findAll(p).toList();
    }
    
    public List<Linha> findAll(){
        return repo.findAll();
    }
    
    public Linha findById(Long id){
        Optional<Linha> result = repo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Linha não encontrada.");
        }
        return result.get();
    }
    
    public Linha save(Linha l){
        try{
            return repo.save(l);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar linha. "+ l.getNomeLinha()+ ". " + e);
        }
    }
    
    public Linha update(Linha l){
        try{
            return repo.save(l);
        }catch(Exception e){
            throw new RuntimeException("Falha ao atualizar linha "+ l.getNomeLinha()+ ". " + e);
        }
    }
    
    public void delete(Long id){
        Linha obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao deletar linha "+ obj.getNomeLinha()+ ". " + e);
        }
    }
}
