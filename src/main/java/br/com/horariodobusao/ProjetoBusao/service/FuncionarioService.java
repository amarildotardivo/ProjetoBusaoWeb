package br.com.horariodobusao.ProjetoBusao.service;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repo;
    
    public List<Funcionario> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        
        return repo.findAll(p).toList();
    }
    
    public List<Funcionario> findAll(){
        return repo.findAll();
    }
    
    public Funcionario findById(Long id){
        Optional<Funcionario> result = repo.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Funcionario não encontrado.");
        }
        return result.get();
    }
    
    public Funcionario save(Funcionario f){
        //verifica se cpf ou email estão cadastrados
        verificaCpfEmailCadastrado(f.getCpf(), f.getEmail());
        
        try{
            return repo.save(f);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar Funcionario. " + e);
        }
    }
    
    public Funcionario update(Funcionario f, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        //Verifica se funcionario já existe
        Funcionario obj = findById(f.getId());
        
        //Verifica alteração de senha
        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        
        try{
            f.setCpf(obj.getCpf());
            return repo.save(f);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar Funcionario. " + e);
        }
    }
    
    public void delete(Long id){
        int cont = 0;
        
        Funcionario obj = findById(id);
        List<Funcionario> func = new ArrayList<>();
        
        for(Funcionario f: func){
            cont++;
        }
        
        try{
            if(cont > 1){
                repo.delete(obj);
            }else{
                throw new RuntimeException("Não é possível excluir o Funcionario, pois só existe 1 Funcionario. Crei outro Funcionario para realizar esta excluisão.");
            }
        }catch(Exception ex){
            throw new RuntimeException("Falha ao deletar o Funcionario.");
        }
    }
    
    private void verificaCpfEmailCadastrado(String cpf, String email){
        List<Funcionario> result = repo.findByCpfOrEmail(cpf, email);
        if(!result.isEmpty()){
            throw new RuntimeException("CPF ou EMAIL já cadastrados.");
        }
    }
    
    private void alterarSenha(Funcionario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha){
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
}
