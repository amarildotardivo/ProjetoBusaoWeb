package br.com.horariodobusao.ProjetoBusao.controller.view;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.service.*;
import java.util.*;
import javax.validation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.*;
import org.springframework.validation.*;

@Controller
@RequestMapping(path="/funcionarios")
public class FuncionarioViewController {
    @Autowired
    private FuncionarioService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("funcionarios", service.findAll());
        return "funcionarios";
    }
    
    @GetMapping(path="/funcionario")
    public String cadastro(Model model){
        model.addAttribute("funcionario", new Funcionario());
        return "formFuncionario";
    }
    
    @PostMapping(path="/funcionario")
    public String save(@Valid @ModelAttribute Funcionario func, 
            BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha,
            Model model){
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formFuncionario";
        }
        
        if(!func.getSenha().equals(confirmarSenha)){
            model.addAttribute("msgErros", new ObjectError("funcionario", "Campos senha e confirmar senha devem ser iguais."));
            return "formFuncionario";
        }
        
        func.setId(null);
        
        try{
            service.save(func);
            model.addAttribute("msgSucesso", "Funcionário cadastrado com sucesso!");
            model.addAttribute("funcionario", new Funcionario());
            return "formFuncionario";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formFuncionario";
        }
    }
    
    @GetMapping(path="/funcionario/{id}")
    public String atualizacao(@PathVariable("id") Long id, Model model){
        model.addAttribute("funcionario", service.findById(id));
        return "formFuncionario";
    }
    
    @PostMapping(path="/funcionario/{id}")
    public String update(@Valid @ModelAttribute Funcionario func, 
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
            return "formFuncionario";
        }
        
        func.setId(id);
        
        try{
            service.update(func, "", "", "");
            model.addAttribute("msgSucesso", "Funcionário atualizado com sucesso!");
            model.addAttribute("funcionario", func);
            return "formFuncionario";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formFuncionario";
        }
    }
    
    @GetMapping(path="/{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/funcionarios";
    }
}
