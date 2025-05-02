package com.challenge.poscacheservice.controller;

import com.challenge.poscacheservice.dto.LowestCostResponse;
import com.challenge.poscacheservice.service.RutaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    /**
     * Constructor con inyección de dependencias por constructor.
     * @param rutaService servicio que contiene la lógica del grafo y cálculo de rutas
     */
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
    @Operation(
            summary = "Obtener ruta mínima",
            description = "Calcula el camino con menor costo entre dos puntos de venta por sus IDs"
    )
    @GetMapping("/minimo")
    public ResponseEntity<LowestCostResponse> obtenerRutaMinima(
            @Parameter(description = "ID del punto de venta de origen", example = "1")
            @RequestParam Long origenId,

            @Parameter(description = "ID del punto de venta de destino", example = "5")
            @RequestParam Long destinoId) {

        LowestCostResponse resultado = rutaService.encontrarRutaMinima(origenId, destinoId);
        return ResponseEntity.ok(resultado);
    }
}
