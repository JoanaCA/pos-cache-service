package com.challenge.poscacheservice.repository;

import com.challenge.poscacheservice.model.PuntoDeVenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoDeVentaRepository extends CrudRepository<PuntoDeVenta, Long> {
}
