package com.challenge.poscacheservice.controller;

import com.challenge.poscacheservice.dto.CostoEntrePuntosRequestDTO;
import com.challenge.poscacheservice.model.CostoEntrePuntos;
import com.challenge.poscacheservice.service.CostoEntrePuntosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<CostoEntrePuntos> crearCosto(@RequestBody CostoEntrePuntosRequestDTO dto) {
        CostoEntrePuntos creado = costoEntrePuntosService.guardarCosto(dto.getIdOrigen(), dto.getIdDestino(), dto.getCosto());
        return ResponseEntity.ok(creado);
    }

}
