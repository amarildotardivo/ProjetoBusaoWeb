package br.com.horariodobusao.ProjetoBusao.service;

import br.com.horariodobusao.ProjetoBusao.exception.*;
import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.*;
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
    
    public Funcionario findByEmail(String email){
        return repo.findByEmail(email);
    }
    
    public Funcionario findById(Long id){
        Optional<Funcionario> result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Funcionário não encontrado.");
        }
        return result.get();
    }
    
    public Funcionario save(Funcionario f){
        //verifica se cpf ou email estão cadastrados
        verificaCpfEmailCadastrado(f.getCpf(), f.getEmail());
        
        //verifica permissões nulas
        removePermissoesNulas(f);
        
        try{
            f.setSenha(new BCryptPasswordEncoder().encode(f.getSenha() ) );
            return repo.save(f);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar Funcionário. " + e);
        }
    }
    
    public Funcionario update(Funcionario f, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        //Verifica se funcionario já existe
        Funcionario obj = findById(f.getId());
        
        //verifica permissões nulas
        removePermissoesNulas(f);
        
        //Verifica alteração de senha
        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        
        try{
            f.setCpf(obj.getCpf());
            f.setEmail(obj.getEmail());
            f.setSenha(obj.getSenha());
            return repo.save(f);
        }catch(Exception e){
            Throwable t = e;
            while (t.getCause() != null){
                t = t.getCause();
                if(t instanceof javax.validation.ConstraintViolationException){
                    throw ((javax.validation.ConstraintViolationException) t);
                }
            }
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
        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        if(!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()){
            if(!crypt.matches(novaSenha, obj.getSenha()) ){
                throw new RuntimeException("Senha atual está incorreta!");
            }
            if(!novaSenha.equals(confirmarNovaSenha)){
                throw new RuntimeException("Nova Senha e Confirmar Nova Senha, não são iguais!");
            }
            obj.setSenha(new BCryptPasswordEncoder().encode(novaSenha) );
        }
    }
    
    //verifica se a quantidade de Funcionários é menor que 2, pois deve existir sempre 1 Funcionário
    private void verificaQuantidadePessoas(){     
        
        List<Funcionario> func = findAll();
        
        if(func.size() < 2){
            throw new RuntimeException("Não é possível excluir o Funcionário, pois só existe 1 Funcionário. Crie outro Funcionário para realizar esta exclusão.");
        }
    }
    
    public void removePermissoesNulas(Funcionario f){
        f.getPermissoes().removeIf((Permissao p) -> {
            return p.getId() == null;
        });
        
        if(f.getPermissoes().isEmpty()){
            throw new RuntimeException("Funcionário deve ter no mínimo 1 permissão!");
        }
    }
}
