package com.br.projects.controller;

import com.br.projects.dto.ProjetoDto;
import com.br.projects.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity salvarProjeto(@RequestBody ProjetoDto dto) {
        projetoService.salvarProjeto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{id}")
    public ProjetoDto buscarPorId(@PathVariable Long id){
        return projetoService.bucarPorId(id);
    }

    @GetMapping(value = "/listar")
    public List<ProjetoDto> listarProjetos() {
        return projetoService.listarProjetos();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizarProjeto(@PathVariable Long id, @RequestBody ProjetoDto dto) {
        if(id != dto.getId().longValue()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(projetoService.atualizarProjeto(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProjetoDto> excluirProjeto(@PathVariable Long id) {
        projetoService.excluirProjeto(id);
        return ResponseEntity.noContent().build();
    }
}
