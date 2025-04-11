package com.challenge.poscacheservice.repository;

import com.challenge.poscacheservice.model.CostoEntrePuntos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostoEntrePuntosRepository extends CrudRepository<CostoEntrePuntos, String> {

}
