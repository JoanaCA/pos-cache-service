package com.challenge.poscacheservice.service;

import com.challenge.poscacheservice.graph.Conexion;
import com.challenge.poscacheservice.model.CostoEntrePuntos;
import com.challenge.poscacheservice.repository.CostoEntrePuntosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Servicio que encapsula la lógica de negocio relacionada con los
 * costos entre puntos de venta almacenados en Redis.
 */
@Service
public class CostoEntrePuntosService {

    private final CostoEntrePuntosRepository costoEntrePuntosRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param costoEntrePuntosRepository repositorio para acceder a los costos en Redis
     */
    @Autowired
    public CostoEntrePuntosService(CostoEntrePuntosRepository costoEntrePuntosRepository) {
        this.costoEntrePuntosRepository = costoEntrePuntosRepository;
    }

    /**
     * Busca un costo por su ID compuesto.
     *
     * @param id ID compuesto en formato "origen_destino" (ej: "1_2")
     * @return Optional con el costo si existe
     */
    public Optional<CostoEntrePuntos> buscarPorId(String id) {
        return costoEntrePuntosRepository.findById(id);
    }


    public CostoEntrePuntos guardar(CostoEntrePuntos costo) {
        return costoEntrePuntosRepository.save(costo);
    }

    public void eliminarPorId(String id) {
        costoEntrePuntosRepository.deleteById(id);
    }

    public Iterable<CostoEntrePuntos> listarTodos() {
        return costoEntrePuntosRepository.findAll();
    }

    /**
     * Guarda un nuevo costo entre dos puntos, generando su ID compuesto.
     *
     * @param idOrigen ID del punto de origen
     * @param idDestino ID del punto de destino
     * @param costo valor del costo
     * @return el objeto guardado en Redis
     */
    public CostoEntrePuntos guardarCosto(Long idOrigen, Long idDestino, Integer costo) {
        String id = generarIdCompuesto(idOrigen, idDestino);
        CostoEntrePuntos nuevoCosto = new CostoEntrePuntos(id, idOrigen, idDestino, costo);
        return costoEntrePuntosRepository.save(nuevoCosto);
    }

    /**
     * Genera un ID compuesto normalizado (siempre menor primero).
     *
     * @param idA un punto
     * @param idB otro punto
     * @return ID compuesto (ej: "1_2" o "2_3")
     */
    private String generarIdCompuesto(Long idA, Long idB) {
        return (idA < idB ? idA + "_" + idB : idB + "_" + idA);
    }

    /**
     * Devuelve todas las conexiones directas (adyacentes) a un punto de venta.
     *
     * @param idOrigen ID del punto desde el cual buscar
     * @return lista de conexiones directas
     */
    public List<CostoEntrePuntos> buscarConexionesDirectas(Long idOrigen) {
        Iterable<CostoEntrePuntos> todosLosCostos = costoEntrePuntosRepository.findAll();
        List<CostoEntrePuntos> conexionesDirectas = new ArrayList<>();

        for (CostoEntrePuntos costo : todosLosCostos) {
            if (costo.getIdOrigen().equals(idOrigen) || costo.getIdDestino().equals(idOrigen)) {
                conexionesDirectas.add(costo);
            }
        }

        return conexionesDirectas;
    }
}
