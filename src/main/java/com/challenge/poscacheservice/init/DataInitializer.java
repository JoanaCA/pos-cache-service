package com.challenge.poscacheservice.init;

import com.challenge.poscacheservice.model.PuntoDeVenta;
import com.challenge.poscacheservice.repository.PuntoDeVentaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final PuntoDeVentaRepository puntoDeVentaRepository;

    public DataInitializer(PuntoDeVentaRepository puntoDeVentaRepository) {
        this.puntoDeVentaRepository = puntoDeVentaRepository;
    }

    @PostConstruct
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
}
