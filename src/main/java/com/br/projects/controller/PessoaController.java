package com.br.projects.controller;

import com.br.projects.dto.GerenteDto;
import com.br.projects.dto.PessoaDto;
import com.br.projects.dto.PessoaMembroDto;
import com.br.projects.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;


    @PostMapping
    public ResponseEntity salvarPessoa(@RequestBody PessoaDto dto) {
        pessoaService.salvarPessoa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<PessoaMembroDto> listarPessoas() {
        return pessoaService.listarPessoas();
    }

}
