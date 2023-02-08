package com.br.projects.controller;

import com.br.projects.dto.MembroDto;
import com.br.projects.dto.MembrosProjetosDto;
import com.br.projects.service.MembrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/membros")
public class MembrosController {

    @Autowired
    MembrosService service;

    @PostMapping
    public ResponseEntity<Void> salvarMembro(@RequestBody MembroDto dto) {
        service.salvarMembro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/projetos")
    public MembrosProjetosDto listarMembrosProjetos() {
        return service.listarMembrosProjetos();
    }

}
