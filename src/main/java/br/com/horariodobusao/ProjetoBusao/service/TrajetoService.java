package br.com.horariodobusao.ProjetoBusao.service;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class TrajetoService {
    @Autowired
    private TrajetoRepository repo;
    
    public List<Trajeto> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        
        return repo.findAll(p).toList();
    }
    
    public List<Trajeto> findAll(){
        return repo.findAll();
    }
    
    public Trajeto findById(Long id){
        Optional<Trajeto> result = repo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Trajeto não encontrado.");
        }
        return result.get();
    }
    
    public Trajeto save(Trajeto t){
        try{
            return repo.save(t);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar trajeto. " + e);
        }
    }
    
    public Trajeto update(Trajeto t){
        try{
            return repo.save(t);
        }catch(Exception e){
            throw new RuntimeException("Falha ao atualizar trajeto. " + e);
        }
    }
    
    public void delete(Long id){
        Trajeto obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao deletar trajeto. " + e);
        }
    }
}
