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
            throw new RuntimeException("Funcionário não encontrado.");
        }
        return result.get();
    }
    
    public Funcionario save(Funcionario f){
        //verifica se cpf ou email estão cadastrados
        verificaCpfEmailCadastrado(f.getCpf(), f.getEmail());
        
        try{
            return repo.save(f);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar Funcionário. " + e);
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
            throw new RuntimeException("Falha ao salvar Funcionário. " + e);
        }
    }
    
    public void delete(Long id){
        
        Funcionario obj = findById(id);
        
        verificaQuantidadePessoas();
        
        try{
            repo.delete(obj);
        }catch(Exception ex){
            throw new RuntimeException("Falha ao deletar o Funcionário.");
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
    
    //verifica se a quantidade de Funcionários é menor que 2, pois deve existir sempre 1 Funcionário
    private void verificaQuantidadePessoas(){     
        
        List<Funcionario> func = findAll();
        
        if(func.size() < 2){
            throw new RuntimeException("Não é possível excluir o Funcionário, pois só existe 1 Funcionário. Crie outro Funcionário para realizar esta exclusão.");
        }
    }
}
