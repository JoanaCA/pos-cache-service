package com.challenge.poscacheservice.controller;

import com.challenge.poscacheservice.model.PuntoDeVenta;
import com.challenge.poscacheservice.service.PuntoDeVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/puntos-venta")
public class PuntoDeVentaController {

    private final PuntoDeVentaService puntoDeVentaService;

    @Autowired
    public PuntoDeVentaController(PuntoDeVentaService puntoDeVentaService) {
        this.puntoDeVentaService = puntoDeVentaService;
    }

    @PostMapping
    public ResponseEntity<PuntoDeVenta> crear(@RequestBody PuntoDeVenta puntoDeVenta) {
        return ResponseEntity.ok(puntoDeVentaService.crear(puntoDeVenta));
    }

    @GetMapping
    public ResponseEntity<Iterable<PuntoDeVenta>> listarTodos() {
        return ResponseEntity.ok(puntoDeVentaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoDeVenta> buscarPorId(@PathVariable Long id) {
        Optional<PuntoDeVenta> resultado = puntoDeVentaService.buscarPorId(id);
        return resultado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuntoDeVenta> actualizar(@PathVariable Long id, @RequestBody PuntoDeVenta puntoDeVenta) {
        puntoDeVenta.setId(id);
        return ResponseEntity.ok(puntoDeVentaService.actualizar(puntoDeVenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        puntoDeVentaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
