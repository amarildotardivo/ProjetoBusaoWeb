package br.com.horariodobusao.ProjetoBusao;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.repository.*;
import java.time.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoBusaoApplication implements CommandLineRunner{
    
    @Autowired
    private AdministradorRepository admRepo;
    @Autowired
    private FuncionarioRepository funcRepo;
    @Autowired
    private LinhaRepository linhaRepo;
    @Autowired
    private TrajetoRepository trajetoRepo;
    @Autowired
    private LocalidadeRepository localRepo;
    @Autowired
    private CidadeRepository cidadeRepo;
    
    
	public static void main(String[] args) {
		SpringApplication.run(ProjetoBusaoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        
        //Administrador
        Administrador a1 = new Administrador();
        a1.setNome("Amarildo");
        a1.setEmail("amarildo@gmail.com");
        a1.setSenha("$Rauto19083");
        a1.setEndereco("Rua Lyra Reis Salles, 91 - Centro - Italva");
        a1.setCpf("814.117.733-81");
        a1.setTelefone("(22)99999-9999");
        
        admRepo.save(a1);

        //Funcionario
        Funcionario f1 = new Funcionario();
        f1.setNome("Mathes");
        f1.setEmail("mateus@gmail.com");
        f1.setSenha("$Rloucura19083");
        f1.setEndereco("Rua Lyra Reis Salles, 91 - Centro - Italva");
        f1.setCpf("869.250.850-01");
        f1.setTelefone("(22)99854-9159");
        
        //Linha
        Linha l1 = new Linha();
        l1.setFuncionario(f1);
        l1.setNomeLinha("Italva x Campos");
        
        f1.setLinhas(List.of(l1));
        
        //save Funcionario
        funcRepo.save(f1);
        
        //Trajeto
        Trajeto t1 = new Trajeto();
        t1.setLinha(l1);
        t1.setOpcao(TipoOpcaoEnum.DIRETO);
        
        //linha
        l1.setTrajetos(List.of(t1));
        
        //Localidade
        Localidade loc1 = new Localidade();
        loc1.setHorario(LocalTime.of(6, 35));
        loc1.setTrajeto(t1);  
        
        Localidade loc2 = new Localidade();
        loc2.setHorario(LocalTime.of(9, 35));
        loc2.setTrajeto(t1);    
        
        //Cidade
        Cidade c1 = new Cidade();
        c1.setNome("Italva");
        c1.setLocalidade(List.of(loc1));
        
        Cidade c2 = new Cidade();
        c2.setNome("Campos");
        c2.setLocalidade(List.of(loc2));
        
        //save cidade
        cidadeRepo.save(c1);
        cidadeRepo.save(c2);
        
        //localidade
        loc1.setCidade(c1);
        loc2.setCidade(c2);
        
        //Trajeto
        t1.setLocalidades(List.of(loc1, loc2));
        
        //save linha
        linhaRepo.save(l1);
        
        //save trajeto
        trajetoRepo.save(t1);
        
        //save localidade
        localRepo.save(loc1);
        localRepo.save(loc2);
        
        
    }

}
