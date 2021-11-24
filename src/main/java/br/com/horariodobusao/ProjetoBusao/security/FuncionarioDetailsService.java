package br.com.horariodobusao.ProjetoBusao.security;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Service
public class FuncionarioDetailsService implements UserDetailsService{
    
    @Autowired
    private FuncionarioRepository repo;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Funcionario funcionario = repo.findByEmail(email);
        
        if(funcionario == null){
            throw new UsernameNotFoundException("Funcionário não encontrado no email: " + email);
        }
        
        return new User(funcionario.getEmail(), funcionario.getSenha(), getAuthorities(funcionario.getPermissoes()) );
    }
    
    private List<GrantedAuthority> getAuthorities(List<Permissao> lista){
        List<GrantedAuthority> l = new ArrayList<>();
        
        for(Permissao p : lista){
            l.add(new SimpleGrantedAuthority( "ROLE_" + p.getNome() ) );
        }
        
        return l;
    }
    
}
