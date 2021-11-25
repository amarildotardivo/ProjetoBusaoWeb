package br.com.horariodobusao.ProjetoBusao.controller.view;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = Controller.class)
public class MyViewControllerAdvice {
    
    @ExceptionHandler(Exception.class)
    public String errorException(Exception e, Model model){
        model.addAttribute("msgErros", e.getMessage());
        return "error";
    }
}
