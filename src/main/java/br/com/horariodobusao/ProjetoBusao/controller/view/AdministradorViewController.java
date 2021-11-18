package br.com.horariodobusao.ProjetoBusao.controller.view;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.service.*;
import java.util.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/administradores")
public class AdministradorViewController {
    @Autowired
    private AdministradorService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("administradores", service.findAll());
        return "administradores";
    }
    
    @GetMapping(path="/administrador")
    public String cadastro(Model model){
        model.addAttribute("administrador", new Administrador());
        return "formAdministrador";
    }
    
    @PostMapping(path="/administrador")
    public String save(@Valid @ModelAttribute Administrador adm, 
            BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha,
            Model model){
        
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
        return "formAdministrador";
    }
    
    @PostMapping(path="/administrador/{id}")
    public String update(@Valid @ModelAttribute Administrador adm, 
            BindingResult result,
            @PathVariable("id") Long id,
            Model model){
        
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
    
}
