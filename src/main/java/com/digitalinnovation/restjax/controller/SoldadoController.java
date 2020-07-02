package com.digitalinnovation.restjax.controller;

import com.digitalinnovation.restjax.dto.Soldado;
import com.digitalinnovation.restjax.dto.SoldadoRequest;
import com.digitalinnovation.restjax.service.SoldadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/soldados")
public class SoldadoController {
    private final SoldadoService soldadoService;

    public SoldadoController(SoldadoService soldadoService) {
        this.soldadoService = soldadoService;
    }

    @GetMapping
    public ResponseEntity<List<Soldado>> buscarSoldados() {
        var soldados = soldadoService.buscarSoldados();
        return ResponseEntity.ok(soldados);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Soldado> buscarSoldado(@PathVariable() String cpf) {
        var soldado = soldadoService.buscarSoldado(cpf);
        return ResponseEntity.ok(soldado);
    }

    @PostMapping
    public ResponseEntity<Soldado> criarSoldado(@RequestBody Soldado soldado) {
        var soldadoEntity = soldadoService.criarSoldado(soldado);
        return ResponseEntity.status(HttpStatus.CREATED).body(soldadoEntity);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Soldado> editarSoldado(@PathVariable() String cpf,@RequestBody SoldadoRequest soldadoRequest) {
        soldadoService.alterarSoldado(cpf, soldadoRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Soldado> excluirSoldado(@PathVariable() String cpf) {
        soldadoService.excluirSoldado(cpf);
        return ResponseEntity.ok().build();
    }
}
