package com.challenge.poscacheservice.init;

import com.challenge.poscacheservice.model.CostoEntrePuntos;
import com.challenge.poscacheservice.model.PuntoDeVenta;
import com.challenge.poscacheservice.repository.CostoEntrePuntosRepository;
import com.challenge.poscacheservice.repository.PuntoDeVentaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final PuntoDeVentaRepository puntoDeVentaRepository;
    private final CostoEntrePuntosRepository costoEntrePuntosRepository;


    public DataInitializer(PuntoDeVentaRepository puntoDeVentaRepository,
                           CostoEntrePuntosRepository costoEntrePuntosRepository) {
        this.puntoDeVentaRepository = puntoDeVentaRepository;
        this.costoEntrePuntosRepository = costoEntrePuntosRepository;
    }

    @PostConstruct
    public void precargarDatos() {
        precargarPuntosDeVenta();
        precargarCostosEntrePuntos();
    }

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

    private void precargarCostosEntrePuntos() {
        if (costoEntrePuntosRepository.count() == 0) {
            String id = generarIdCompuesto(1L, 2L);
            CostoEntrePuntos costo = new CostoEntrePuntos(id, 1L, 2L, 2);
            costoEntrePuntosRepository.save(costo);
            System.out.println("Costo 1_2 precargado en Redis");
        }
    }

    private String generarIdCompuesto(Long idA, Long idB) {
        return (idA < idB ? idA + "_" + idB : idB + "_" + idA);
    }
}
