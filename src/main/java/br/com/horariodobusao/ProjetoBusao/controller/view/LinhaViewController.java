package br.com.horariodobusao.ProjetoBusao.controller.view;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
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
    
    
}
