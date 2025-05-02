package com.challenge.poscacheservice.repository;

import com.challenge.poscacheservice.model.CostoEntrePuntos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder a los datos de costos entre puntos de venta
 * almacenados en Redis. Hereda operaciones CRUD básicas.
 */
@Repository
public interface CostoEntrePuntosRepository extends CrudRepository<CostoEntrePuntos, String> {

}
