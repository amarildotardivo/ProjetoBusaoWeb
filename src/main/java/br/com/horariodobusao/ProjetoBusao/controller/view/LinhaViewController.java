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
@RequestMapping(path="/linhas")
public class LinhaViewController {
    @Autowired
    private LinhaService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("linhas", service.findAll());
        return "linhas";
    }
    
    @GetMapping(path = "/linha")
    public String cadastro(Model model){
        model.addAttribute("linha", new Linha());
        model.addAttribute("tipoOpcao", TipoOpcaoEnum.values());
        return "formLinha";
    }
    
    @PostMapping(path="/linha")
    public String save(@Valid @ModelAttribute Linha linha, 
            BindingResult result,
            Model model){
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formFuncionario";
        }
        
        linha.setId(null);
        
        try{
            service.save(linha);
            model.addAttribute("msgSucesso", "Linha cadastrada com sucesso!");
            model.addAttribute("linha", new Linha());
            return "formLinha";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("linha", e.getMessage()));
            return "formLinha";
        }
    }
    
    @GetMapping(path="/linha/{id}")
    public String atualizacao(@PathVariable("id") Long id, Model model){
        model.addAttribute("linha", service.findById(id));
        return "formLinha";
    }
    
    @PostMapping(path="/linha/{id}")
    public String update(@Valid @ModelAttribute Linha linha, 
            BindingResult result,
            @PathVariable("id") Long id,
            Model model){
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formLinha";
        }
        
        linha.setId(id);
        
        try{
            service.update(linha);
            model.addAttribute("msgSucesso", "Cidade atualizada com sucesso!");
            model.addAttribute("linha", linha);
            return "formLinha";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("linha", e.getMessage()));
            return "formLinha";
        }
    }
    
    @GetMapping(path="/{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/linhas";
    }
}
