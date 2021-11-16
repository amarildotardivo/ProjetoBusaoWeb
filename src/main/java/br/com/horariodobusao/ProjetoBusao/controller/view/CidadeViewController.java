package br.com.horariodobusao.ProjetoBusao.controller.view;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.service.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/cidades")
public class CidadeViewController {
    @Autowired
    private CidadeService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("cidades", service.findAll());
        return "cidades";
    }
    
    @GetMapping(path="/cidade")
    public String cadastro(Model model){
        model.addAttribute("cidade", new Cidade());
        return "formCidade";
    }
    
    @PostMapping(path="/cidade")
    public String save(@Valid @ModelAttribute Cidade cidade, 
            BindingResult result,
            Model model){
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formCidade";
        }
        
        cidade.setId(null);
        
        try{
            service.save(cidade);
            model.addAttribute("msgSucesso", "Cidade cadastrada com sucesso!");
            model.addAttribute("cidade", new Cidade());
            return "formCidade";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("cidade", e.getMessage()));
            return "formCidade";
        }
    }
    
    @GetMapping(path="/cidade/{id}")
    public String atualizacao(@PathVariable("id") Long id, Model model){
        model.addAttribute("cidade", service.findById(id));
        return "formCidade";
    }
    
    @PostMapping(path="/cidade/{id}")
    public String update(@Valid @ModelAttribute Cidade cidade, 
            BindingResult result,
            @PathVariable("id") Long id,
            Model model){
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formCidade";
        }
        
        cidade.setId(id);
        
        try{
            service.update(cidade);
            model.addAttribute("msgSucesso", "Cidade atualizada com sucesso!");
            model.addAttribute("cidade", cidade);
            return "formCidade";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("cidade", e.getMessage()));
            return "formCidade";
        }
    }
    
    @GetMapping(path="/{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/cidades";
    }
}
