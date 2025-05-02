package com.challenge.poscacheservice.controller;

import com.challenge.poscacheservice.dto.CostoEntrePuntosRequestDTO;
import com.challenge.poscacheservice.model.CostoEntrePuntos;
import com.challenge.poscacheservice.service.CostoEntrePuntosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador que expone endpoints para consultar los costos directos
 * entre puntos de venta. Los datos provienen de Redis.
 */
@RestController
@RequestMapping("/api/costos")
@Tag(name = "Costos directos", description = "Operaciones sobre los costos directos entre puntos de venta")
public class CostoEntrePuntosController {

    private final CostoEntrePuntosService costoEntrePuntosService;

    public CostoEntrePuntosController(CostoEntrePuntosService costoEntrePuntosService) {
        this.costoEntrePuntosService = costoEntrePuntosService;
    }

    /**
     * Busca un costo entre puntos por su ID compuesto.
     *
     * @param id ID del costo (formato "idOrigen_idDestino")
     * @return el costo si existe, o 404 si no se encuentra
     */
    @Operation(summary = "Buscar costo por ID", description = "Devuelve un costo directo entre dos puntos por su ID compuesto")
    @GetMapping("/{id}")
    public ResponseEntity<CostoEntrePuntos> obtenerPorId(
            @Parameter(description = "ID compuesto del costo (ej: 1_2)", example = "1_2")
            @PathVariable String id) {
        Optional<CostoEntrePuntos> resultado = costoEntrePuntosService.buscarPorId(id);

        return resultado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo costo entre dos puntos.
     *
     * @param dto DTO con los IDs de origen, destino y el costo
     * @return el costo creado
     */
    @Operation(summary = "Crear nuevo costo", description = "Registra un nuevo costo entre dos puntos de venta")
    @PostMapping
    public ResponseEntity<CostoEntrePuntos> crearCosto(@RequestBody CostoEntrePuntosRequestDTO dto) {
        CostoEntrePuntos creado = costoEntrePuntosService.guardarCosto(dto.getIdOrigen(), dto.getIdDestino(), dto.getCosto());
        return ResponseEntity.ok(creado);
    }

    /**
     * Elimina un costo por su ID compuesto.
     *
     * @param id ID del costo (formato "idOrigen_idDestino")
     * @return 204 No Content si se eliminó correctamente
     */
    @Operation(summary = "Eliminar costo por ID", description = "Elimina un costo existente entre dos puntos")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCosto(
            @Parameter(description = "ID compuesto del costo (ej: 1_2)", example = "1_2")
            @PathVariable String id) {
        costoEntrePuntosService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Devuelve todas las conexiones directas desde un punto de venta dado.
     *
     * @param idOrigen ID del punto de origen
     * @return lista de conexiones directas
     */
    @Operation(summary = "Buscar conexiones directas", description = "Devuelve los costos desde un punto hacia todos sus vecinos")
    @GetMapping("/conectados/{idOrigen}")
    public ResponseEntity<List<CostoEntrePuntos>> obtenerConexionesDirectas(
            @Parameter(description = "ID del punto de venta de origen", example = "1")
            @PathVariable Long idOrigen) {
        List<CostoEntrePuntos> conexiones = costoEntrePuntosService.buscarConexionesDirectas(idOrigen);
        return ResponseEntity.ok(conexiones);
    }

}
