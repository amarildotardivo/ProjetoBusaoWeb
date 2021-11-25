package br.com.horariodobusao.ProjetoBusao.controller.view;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.repository.*;
import br.com.horariodobusao.ProjetoBusao.service.*;
import java.util.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/administradores")
public class AdministradorViewController {
    @Autowired
    private AdministradorService service;
    
    @Autowired
    private PermissaoRepository permissaoRepo;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("administradores", service.findAll());
        return "administradores";
    }
    
    @GetMapping(path="/administrador")
    public String cadastro(Model model){
        model.addAttribute("administrador", new Administrador());
        model.addAttribute("permissoes", permissaoRepo.findAll());
        return "formAdministrador";
    }
    
    @PostMapping(path="/administrador")
    public String save(@Valid @ModelAttribute Administrador adm, 
            BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha,
            Model model){
        
        //Valores a serem retornados
        model.addAttribute("permissoes", permissaoRepo.findAll());
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formAdministrador";
        }
        
        if(!adm.getSenha().equals(confirmarSenha)){
            model.addAttribute("msgErros", new ObjectError("administrador", "Campos senha e confirmar senha devem ser iguais."));
            return "formAdministrador";
        }
        
        adm.setId(null);
        
        try{
            service.save(adm);
            model.addAttribute("msgSucesso", "Administrador cadastrado com sucesso!");
            model.addAttribute("administrador", new Administrador());
            return "formAdministrador";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("administrador", e.getMessage()));
            return "formAdministrador";
        }
    }
    
    @GetMapping(path="/administrador/{id}")
    public String atualizacao(@PathVariable("id") Long id, Model model){
        model.addAttribute("administrador", service.findById(id));
        model.addAttribute("permissoes", permissaoRepo.findAll());
        return "formAdministrador";
    }
    
    @PostMapping(path="/administrador/{id}")
    public String update(@Valid @ModelAttribute Administrador adm, 
            BindingResult result,
            @PathVariable("id") Long id,
            Model model){
        
        //Valores a serem retornados
        model.addAttribute("permissoes", permissaoRepo.findAll());
        
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("senha")){
                list.add(fe);
            }
        }
        
        if(!list.isEmpty()){
            model.addAttribute("msgErros", list);
            return "formAdministrador";
        }
        
        adm.setId(id);
        
        try{
            service.update(adm, "", "", "");
            model.addAttribute("msgSucesso", "Administrador atualizado com sucesso!");
            model.addAttribute("administrador", adm);
            return "formAdministrador";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("administrador", e.getMessage()));
            return "formAdministrador";
        }
    }
    
    @GetMapping(path="/{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/administradores";
    }
    
    //--------- Meus Dados -------------
    @GetMapping(path="/meusdados")
    public String getMeusDados(@AuthenticationPrincipal User user, Model model){
        Administrador adm = service.findByEmail(user.getUsername());
        model.addAttribute("administrador", adm);
        model.addAttribute("url", "administradores");
        return "formMeusDados";
    }
    
    @PostMapping(path="/meusdados")
    public String updateMeusDados(
            @Valid @ModelAttribute Administrador adm, 
            BindingResult result,
            @AuthenticationPrincipal User user,
            @RequestParam("senhaAtual") String senhaAtual,
            @RequestParam("novaSenha") String novaSenha,
            @RequestParam("confirmarNovaSenha") String confirmarNovaSenha,
            Model model){
        
        
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("senha") && !fe.getField().equals("permissoes")){
                list.add(fe);
            }
        }
        
        if(!list.isEmpty()){
            model.addAttribute("msgErros", list);
            return "formMeusDados";
        }
        
        Administrador admBD = service.findByEmail(user.getUsername());
        if(!admBD.getId().equals(adm.getId())){
            throw new RuntimeException("Acesso Negado!");
        }        
        
        try{
            adm.setPermissoes(admBD.getPermissoes());
            service.update(adm, senhaAtual, novaSenha, confirmarNovaSenha);
            model.addAttribute("msgSucesso", "Administrador atualizado com sucesso!");
            model.addAttribute("administrador", adm);
            return "formMeusDados";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("administrador", e.getMessage()));
            return "formMeusDados";
        }
    }
}
