package br.com.horariodobusao.ProjetoBusao.service;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository repo;
    
    public List<Administrador> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        
        return repo.findAll(p).toList();
    }
    
    public List<Administrador> findAll(){
        return repo.findAll();
    }
    
    public Administrador findById(Long id){
        Optional<Administrador> result = repo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Administrador não encontrado.");
        }
        return result.get();
    }
    
    public Administrador save(Administrador adm){
        //verifica se cpf ou email estão cadastrados
        verificaCpfEmailCadastrado(adm.getCpf(), adm.getEmail());
        
        try{
            return repo.save(adm);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar Administrador. " + e);
        }
    }
    
    public Administrador update(Administrador adm, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        //Verifica se funcionario já existe
        Administrador obj = findById(adm.getId());
        
        //Verifica alteração de senha
        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        
        try{
            adm.setCpf(obj.getCpf());
            return repo.save(adm);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar Administrador. " + e);
        }
    }
    
    public void delete(Long id){
        
        Administrador obj = findById(id);
        
        verificaQuantidadePessoas();
        
        try{            
            repo.delete(obj);
            
        }catch(Exception ex){
            throw new RuntimeException("Falha ao deletar o Administrador.");
        }
    }
    
    private void verificaCpfEmailCadastrado(String cpf, String email){
        List<Administrador> result = repo.findByCpfOrEmail(cpf, email);
        if(!result.isEmpty()){
            throw new RuntimeException("CPF ou EMAIL já cadastrados.");
        }
    }
    
    private void alterarSenha(Administrador obj, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        if(!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()){
            if(senhaAtual.equals(obj.getSenha())){
                throw new RuntimeException("Senha atual está incorreta!");
            }
            if(!novaSenha.equals(confirmarNovaSenha)){
                throw new RuntimeException("Nova Senha e Confirmar Nova Senha, não são iguais!");
            }
            obj.setSenha(novaSenha);
        }
    } 
    
    //verifica se a quantidade de administradores é menor que 2, pois deve existir sempre 1 administrador
    private void verificaQuantidadePessoas(){     
        
        List<Administrador> adms = findAll();
        
        if(adms.size() < 2){
            throw new RuntimeException("Não é possível excluir o Administrador, pois só existe 1 Administrador. Crie outro Administrador para realizar esta exclusão.");
        }
    }
}
