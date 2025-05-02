package com.challenge.poscacheservice.init;

import com.challenge.poscacheservice.graph.Conexion;
import com.challenge.poscacheservice.model.CostoEntrePuntos;
import com.challenge.poscacheservice.model.PuntoDeVenta;
import com.challenge.poscacheservice.repository.CostoEntrePuntosRepository;
import com.challenge.poscacheservice.repository.PuntoDeVentaRepository;
import com.challenge.poscacheservice.service.RutaService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase responsable de precargar los datos iniciales necesarios en Redis y en memoria.
 */
@Component
public class DataInitializer {

    private final PuntoDeVentaRepository puntoDeVentaRepository;
    private final CostoEntrePuntosRepository costoEntrePuntosRepository;
    private final RutaService rutaService;


    public DataInitializer(PuntoDeVentaRepository puntoDeVentaRepository,
                           CostoEntrePuntosRepository costoEntrePuntosRepository,
                           RutaService rutaService) {
        this.puntoDeVentaRepository = puntoDeVentaRepository;
        this.costoEntrePuntosRepository = costoEntrePuntosRepository;
        this.rutaService = rutaService;
    }

    /**
     * Método que se ejecuta automáticamente al iniciar la aplicación.
     * Llama a la carga de puntos de venta, costos entre ellos y el grafo en memoria.
     */
    @PostConstruct
    public void precargarDatos() {
        precargarPuntosDeVenta();
        precargarCostosEntrePuntos();
        inicializarGrafoEnMemoria();
    }

    /**
     * Precarga los puntos de venta en Redis si aún no existen.
     */
    public void precargarPuntosDeVenta() {
        if (puntoDeVentaRepository.count() == 0) {
            puntoDeVentaRepository.save(new PuntoDeVenta(1L, "CABA"));
            puntoDeVentaRepository.save(new PuntoDeVenta(2L, "GBA_1"));
            puntoDeVentaRepository.save(new PuntoDeVenta(3L, "GBA_2"));
            puntoDeVentaRepository.save(new PuntoDeVenta(4L, "Santa Fe"));
            puntoDeVentaRepository.save(new PuntoDeVenta(5L, "Córdoba"));
            puntoDeVentaRepository.save(new PuntoDeVenta(6L, "Misiones"));
            puntoDeVentaRepository.save(new PuntoDeVenta(7L, "Salta"));
            puntoDeVentaRepository.save(new PuntoDeVenta(8L, "Chubut"));
            puntoDeVentaRepository.save(new PuntoDeVenta(9L, "Santa Cruz"));
            puntoDeVentaRepository.save(new PuntoDeVenta(10L, "Catamarca"));

            System.out.println("Puntos de venta precargados en Redis");
        }
    }
    /**
     * Precarga un costo entre puntos de venta si aún no existe.
     */
    private void precargarCostosEntrePuntos() {
        if (costoEntrePuntosRepository.count() == 0) {
            String id = generarIdCompuesto(1L, 2L);
            CostoEntrePuntos costo = new CostoEntrePuntos(id, 1L, 2L, 2);
            costoEntrePuntosRepository.save(costo);
            System.out.println("Costo 1_2 precargado en Redis");
        }
    }

    /**
     * Inicializa el grafo en memoria para que RutaService pueda calcular caminos mínimos.
     * Utiliza los datos cargados previamente en Redis.
     */
    private void inicializarGrafoEnMemoria() {
        // Convertir Iterable<PuntoDeVenta> a List<PuntoDeVenta>
        List<PuntoDeVenta> puntos = new ArrayList<>();
        puntoDeVentaRepository.findAll().forEach(puntos::add);

        // Convertir Iterable<CostoEntrePuntos> a List<CostoEntrePuntos>
        List<CostoEntrePuntos> costos = new ArrayList<>();
        costoEntrePuntosRepository.findAll().forEach(costos::add);

        // Convertir a conexiones del grafo
        List<Conexion> conexiones = new ArrayList<>();
        for (CostoEntrePuntos costo : costos) {
            conexiones.add(new Conexion(costo.getIdOrigen(), costo.getIdDestino(), costo.getCosto()));
        }

        // Inicializar grafo con puntos y conexiones
        rutaService.initGrafo(puntos, conexiones);
        System.out.println("Grafo en memoria inicializado con puntos y conexiones existentes.");
    }

    /**
     * Genera un ID compuesto ordenado para usar como clave entre dos puntos de venta.
     */
    private String generarIdCompuesto(Long idA, Long idB) {

        return (idA < idB ? idA + "_" + idB : idB + "_" + idA);
    }
}
