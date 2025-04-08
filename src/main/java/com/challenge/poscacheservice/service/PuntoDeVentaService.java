package com.challenge.poscacheservice.service;

import com.challenge.poscacheservice.model.PuntoDeVenta;
import com.challenge.poscacheservice.repository.PuntoDeVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PuntoDeVentaService {

    private final PuntoDeVentaRepository puntoDeVentaRepository;

    @Autowired
    public PuntoDeVentaService(PuntoDeVentaRepository puntoDeVentaRepository) {
        this.puntoDeVentaRepository = puntoDeVentaRepository;
    }

    public PuntoDeVenta crear(PuntoDeVenta puntoDeVenta) {
        return puntoDeVentaRepository.save(puntoDeVenta);
    }

    public Iterable<PuntoDeVenta> listarTodos() {
        return puntoDeVentaRepository.findAll();
    }

    public Optional<PuntoDeVenta> buscarPorId(Long id) {
        return puntoDeVentaRepository.findById(id);
    }

    public PuntoDeVenta actualizar(PuntoDeVenta puntoDeVenta) {
        return puntoDeVentaRepository.save(puntoDeVenta);
    }

    public void eliminar(Long id) {
        puntoDeVentaRepository.deleteById(id);
    }
}
