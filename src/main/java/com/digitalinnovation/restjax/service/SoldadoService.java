package com.digitalinnovation.restjax.service;

import com.digitalinnovation.restjax.dto.Soldado;
import com.digitalinnovation.restjax.dto.SoldadoRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoldadoService {
    private List<Soldado> soldados;

    public SoldadoService() {
        this.soldados = new ArrayList<Soldado>();
    }

    public Soldado buscarSoldado(String cpf) {
        var soldado = soldados.stream()
                .filter(s -> s.getCpf().equals(cpf))
                .findAny();
        return soldado.orElse(null);
    }

    public Soldado criarSoldado(Soldado soldado) {
        soldados.add(soldado);
        return soldado;
    }

    public List<Soldado> buscarSoldados() {
        return soldados;
    }

    public void alterarSoldado(String cpf, SoldadoRequest soldadoRequest) {
        var soldado = buscarSoldado(cpf);
        if (soldado != null) {
            soldados = soldados.stream()
                    .map(s -> s.getCpf().equals(cpf) ? Soldado.of(soldado, soldadoRequest) : s)
                    .collect(Collectors.toList());
        }
    }

    public void excluirSoldado(String cpf) {
        var soldado = buscarSoldado(cpf);
        if (soldado != null) {
            soldados = soldados.stream()
                    .filter(s -> !s.getCpf().equals(cpf))
                    .collect(Collectors.toList());
        }
    }
}
