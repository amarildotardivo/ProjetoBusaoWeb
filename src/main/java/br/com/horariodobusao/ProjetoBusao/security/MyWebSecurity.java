package br.com.horariodobusao.ProjetoBusao.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;

@EnableWebSecurity
public class MyWebSecurity extends WebSecurityConfigurerAdapter{
    @Autowired
    private FuncionarioDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/apirest/**")
                .and()
                .authorizeRequests()
                .antMatchers("/apirest/**").hasRole("ADMIN")
                .antMatchers("/funcionarios/meusdados/**").hasAnyRole("ADMIN", "FUNC")
                .antMatchers("/funcionarios").hasRole("ADMIN")
                .antMatchers("/funcionarios/**").hasRole("ADMIN")
                .antMatchers("/**").hasAnyRole("ADMIN", "FUNC")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .usernameParameter("email");
        
    }
    
    

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
}
