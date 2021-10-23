package br.com.horariodobusao.ProjetoBusao.controller;

import br.com.horariodobusao.ProjetoBusao.model.*;
import br.com.horariodobusao.ProjetoBusao.service.*;
import javax.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/apirest/linhas")
public class LinhaController {
    @Autowired
    private LinhaService service;
    
    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page, 
            @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(page, size));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Linha linha){
        linha.setId(null);
        service.save(linha);
        return ResponseEntity.status(HttpStatus.CREATED).body(linha);
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Linha linha){
        linha.setId(id);
        service.update(linha);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
