package com.challenge.poscacheservice.model;

//import jakarta.persistence.Id;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * Representa un punto de venta dentro del sistema.
 * Se identifica por un ID y tiene un nombre. Se almacena en Redis.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("PuntoDeVenta")
@Schema(description = "Punto de venta con identificador y nombre")
public class PuntoDeVenta implements Serializable {

    @Id
    @Schema(description = "Identificador único del punto de venta", example = "1")
    private Long id;

    @Schema(description = "Nombre descriptivo del punto de venta", example = "CABA")
    private String nombre;
}
