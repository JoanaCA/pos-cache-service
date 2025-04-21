package com.challenge.poscacheservice.controller;

import com.challenge.poscacheservice.model.CostoEntrePuntos;
import com.challenge.poscacheservice.service.CostoEntrePuntosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/costos")
public class CostoEntrePuntosController {

    private final CostoEntrePuntosService costoEntrePuntosService;

    public CostoEntrePuntosController(CostoEntrePuntosService costoEntrePuntosService) {
        this.costoEntrePuntosService = costoEntrePuntosService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostoEntrePuntos> obtenerPorId(@PathVariable String id) {
        Optional<CostoEntrePuntos> resultado = costoEntrePuntosService.buscarPorId(id);

        return resultado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
