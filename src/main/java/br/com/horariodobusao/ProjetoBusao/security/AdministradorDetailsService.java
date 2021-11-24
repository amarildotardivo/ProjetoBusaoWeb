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
public class AdministradorDetailsService implements UserDetailsService{
    @Autowired
    private AdministradorRepository repo;
    
    
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrador adm = repo.findByEmail(email);
        
        if(adm == null){
            throw new UsernameNotFoundException("Administrador não encontrado no email: " + email);
        }
        
        return new User(adm.getEmail(), adm.getSenha(), getAuthorities(adm.getPermissoes()) );
    }
    
    private List<GrantedAuthority> getAuthorities(List<Permissao> lista){
        List<GrantedAuthority> l = new ArrayList<>();
        
        for(Permissao p : lista){
            l.add(new SimpleGrantedAuthority( "ROLE_" + p.getNome() ) );
        }
        
        return l;
    }
}
