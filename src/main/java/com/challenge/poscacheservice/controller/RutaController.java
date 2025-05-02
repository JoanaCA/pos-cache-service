package com.challenge.poscacheservice.controller;

import com.challenge.poscacheservice.dto.LowestCostResponse;
import com.challenge.poscacheservice.service.RutaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsable de exponer el cálculo de ruta mínima entre puntos de venta.
 */
@RestController
@RequestMapping("/api/costos")
public class RutaController {

    private final RutaService rutaService;

    // Inyección por constructor
    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    /**
     * Endpoint que calcula la ruta con costo mínimo entre dos puntos de venta.
     *
     * Ejemplo: GET /api/costos/minimo?origenId=1&destinoId=5
     *
     * @param origenId ID del punto de venta de origen
     * @param destinoId ID del punto de venta de destino
     * @return objeto JSON con la ruta más barata y el costo total
     */
    @GetMapping("/minimo")
    public ResponseEntity<LowestCostResponse> obtenerRutaMinima(
            @RequestParam Long origenId,
            @RequestParam Long destinoId) {

        LowestCostResponse resultado = rutaService.encontrarRutaMinima(origenId, destinoId);
        return ResponseEntity.ok(resultado);
    }
}
