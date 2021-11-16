package br.com.horariodobusao.ProjetoBusao.controller.apirest;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.service.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/apirest/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService service;

    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page, 
            @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        return ResponseEntity.ok(service.findAll(page, size));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Administrador adm){
        adm.setId(null);
        service.save(adm);
        return ResponseEntity.status(HttpStatus.CREATED).body(adm);
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Administrador adm){
        adm.setId(id);
        service.update(adm, "", "", "");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(path = "/{id}/alterarsenha")
    public ResponseEntity alterarSenha(
            @PathVariable("id") Long id,
            @RequestParam(name = "senhaAtual", defaultValue = "", required = true) String senhaAtual,
            @RequestParam(name = "novaSenha", defaultValue = "", required = true) String novaSenha,
            @RequestParam(name = "confirmarNovaSenha", defaultValue = "", required = true) String confirmarNovaSenha){
        
        Administrador adm = service.findById(id);
        service.update(adm, senhaAtual, novaSenha, confirmarNovaSenha);
        
        return ResponseEntity.ok().build();
    }
}
