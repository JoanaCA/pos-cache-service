package com.challenge.poscacheservice.service;

import com.challenge.poscacheservice.model.PuntoDeVenta;
import com.challenge.poscacheservice.repository.PuntoDeVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio que maneja la lógica de negocio relacionada con los puntos de venta.
 * Utiliza Redis como almacenamiento en memoria.
 */
@Service
public class PuntoDeVentaService {

    private final PuntoDeVentaRepository puntoDeVentaRepository;

    @Autowired
    public PuntoDeVentaService(PuntoDeVentaRepository puntoDeVentaRepository) {
        this.puntoDeVentaRepository = puntoDeVentaRepository;
    }

    /**
     * Crea un nuevo punto de venta.
     *
     * @param puntoDeVenta punto de venta a guardar
     * @return el punto de venta guardado
     */
    public PuntoDeVenta crear(PuntoDeVenta puntoDeVenta) {
        return puntoDeVentaRepository.save(puntoDeVenta);
    }

    /**
     * Lista todos los puntos de venta almacenados.
     *
     * @return Iterable con todos los puntos de venta
     */
    public Iterable<PuntoDeVenta> listarTodos() {
        return puntoDeVentaRepository.findAll();
    }

    /**
     * Busca un punto de venta por su ID.
     *
     * @param id identificador del punto de venta
     * @return Optional con el resultado
     */
    public Optional<PuntoDeVenta> buscarPorId(Long id) {
        return puntoDeVentaRepository.findById(id);
    }

    /**
     * Actualiza los datos de un punto de venta existente.
     *
     * @param puntoDeVenta objeto actualizado
     * @return punto de venta actualizado
     */
    public PuntoDeVenta actualizar(PuntoDeVenta puntoDeVenta) {
        return puntoDeVentaRepository.save(puntoDeVenta);
    }

    /**
     * Elimina un punto de venta por su ID.
     *
     * @param id identificador del punto de venta
     */
    public void eliminar(Long id) {
        puntoDeVentaRepository.deleteById(id);
    }
}
