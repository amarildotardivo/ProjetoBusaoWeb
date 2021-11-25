package br.com.horariodobusao.ProjetoBusao.controller.view;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.repository.*;
import br.com.horariodobusao.ProjetoBusao.service.*;
import java.util.*;
import javax.validation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.ui.*;
import org.springframework.validation.*;

@Controller
@RequestMapping(path="/funcionarios")
public class FuncionarioViewController {
    @Autowired
    private FuncionarioService service;
    
    @Autowired
    private PermissaoRepository permissaoRepo;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("funcionarios", service.findAll());
        return "funcionarios";
    }
    
    @GetMapping(path="/funcionario")
    public String cadastro(Model model){
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("permissoes", permissaoRepo.findAll());
        return "formFuncionario";
    }
    
    @PostMapping(path="/funcionario")
    public String save(@Valid @ModelAttribute Funcionario func, 
            BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha,
            Model model){
        
        //Valores a serem retornados
        model.addAttribute("permissoes", permissaoRepo.findAll());
        
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
        model.addAttribute("permissoes", permissaoRepo.findAll());
        return "formFuncionario";
    }
    
    @PostMapping(path="/funcionario/{id}")
    public String update(@Valid @ModelAttribute Funcionario func, 
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
    
    //--------- Meus Dados -------------
    @GetMapping(path="/meusdados")
    public String getMeusDados(@AuthenticationPrincipal User user, Model model){
        Funcionario funcionario = service.findByEmail(user.getUsername());
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("url", "funcionarios");
        return "formMeusDados";
    }
    
    @PostMapping(path="/meusdados")
    public String updateMeusDados(
            @Valid @ModelAttribute Funcionario funcionario, 
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
        
        Funcionario funcionarioBD = service.findByEmail(user.getUsername());
        if(!funcionarioBD.getId().equals(funcionario.getId())){
            throw new RuntimeException("Acesso Negado!");
        }        
        
        try{
            funcionario.setPermissoes(funcionarioBD.getPermissoes());
            service.update(funcionario, senhaAtual, novaSenha, confirmarNovaSenha);
            model.addAttribute("msgSucesso", "Funcionario atualizado com sucesso!");
            model.addAttribute("funcionario", funcionario);
            return "formMeusDados";
        }catch(Exception e){
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formMeusDados";
        }
    }
}
