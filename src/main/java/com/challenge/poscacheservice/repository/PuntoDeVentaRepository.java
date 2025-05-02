package com.challenge.poscacheservice.repository;

import com.challenge.poscacheservice.model.PuntoDeVenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositorio para acceder a los datos de puntos de venta almacenados en Redis.
 * Hereda operaciones CRUD básicas como guardar, buscar, eliminar, etc.
 */
@Repository
public interface PuntoDeVentaRepository extends CrudRepository<PuntoDeVenta, Long> {
}
