package com.challenge.poscacheservice.controller;

import com.challenge.poscacheservice.model.PuntoDeVenta;
import com.challenge.poscacheservice.service.PuntoDeVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * Controlador que expone operaciones CRUD para los puntos de venta almacenados en Redis.
 */
@RestController
@RequestMapping("/api/puntos-venta")
@Tag(name = "Puntos de venta", description = "Operaciones CRUD sobre puntos de venta")
public class PuntoDeVentaController {

    private final PuntoDeVentaService puntoDeVentaService;

    /**
     * Constructor con inyección de dependencias por constructor.
     *
     * @param puntoDeVentaService servicio con lógica de negocio para puntos de venta
     */
    @Autowired
    public PuntoDeVentaController(PuntoDeVentaService puntoDeVentaService) {
        this.puntoDeVentaService = puntoDeVentaService;
    }

    /**
     * Crea un nuevo punto de venta.
     *
     * @param puntoDeVenta objeto con nombre e ID
     * @return el punto de venta creado
     */
    @Operation(summary = "Crear punto de venta", description = "Registra un nuevo punto de venta en Redis")
    @PostMapping
    public ResponseEntity<PuntoDeVenta> crear(@RequestBody PuntoDeVenta puntoDeVenta) {
        return ResponseEntity.ok(puntoDeVentaService.crear(puntoDeVenta));
    }

    /**
     * Lista todos los puntos de venta almacenados.
     *
     * @return Iterable con todos los puntos registrados
     */
    @Operation(summary = "Listar puntos de venta", description = "Devuelve todos los puntos de venta registrados en Redis")
    @GetMapping
    public ResponseEntity<Iterable<PuntoDeVenta>> listarTodos() {
        return ResponseEntity.ok(puntoDeVentaService.listarTodos());
    }


    /**
     * Busca un punto de venta por su ID.
     *
     * @param id identificador del punto de venta
     * @return punto encontrado o 404 si no existe
     */
    @Operation(summary = "Buscar punto de venta por ID", description = "Devuelve un punto de venta por su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<PuntoDeVenta> buscarPorId(@PathVariable Long id) {
        Optional<PuntoDeVenta> resultado = puntoDeVentaService.buscarPorId(id);
        return resultado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza un punto de venta existente.
     *
     * @param id identificador del punto
     * @param puntoDeVenta objeto con el nuevo nombre
     * @return punto de venta actualizado
     */
    @Operation(summary = "Actualizar punto de venta", description = "Actualiza el nombre de un punto de venta")
    @PutMapping("/{id}")
    public ResponseEntity<PuntoDeVenta> actualizar(@PathVariable Long id, @RequestBody PuntoDeVenta puntoDeVenta) {
        puntoDeVenta.setId(id);
        return ResponseEntity.ok(puntoDeVentaService.actualizar(puntoDeVenta));
    }

    /**
     * Elimina un punto de venta por su ID.
     *
     * @param id identificador del punto a eliminar
     * @return código 204 si se eliminó correctamente
     */
    @Operation(summary = "Eliminar punto de venta", description = "Elimina un punto de venta por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        puntoDeVentaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
