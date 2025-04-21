package com.challenge.poscacheservice.service;

import com.challenge.poscacheservice.model.CostoEntrePuntos;
import com.challenge.poscacheservice.repository.CostoEntrePuntosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostoEntrePuntosService {

    private final CostoEntrePuntosRepository costoEntrePuntosRepository;

    @Autowired
    public CostoEntrePuntosService(CostoEntrePuntosRepository costoEntrePuntosRepository) {
        this.costoEntrePuntosRepository = costoEntrePuntosRepository;
    }

    public Optional<CostoEntrePuntos> buscarPorId(String id) {
        return costoEntrePuntosRepository.findById(id);
    }

    //

    public CostoEntrePuntos guardar(CostoEntrePuntos costo) {
        return costoEntrePuntosRepository.save(costo);
    }

    public void eliminarPorId(String id) {
        costoEntrePuntosRepository.deleteById(id);
    }

    public Iterable<CostoEntrePuntos> listarTodos() {
        return costoEntrePuntosRepository.findAll();
    }
}
