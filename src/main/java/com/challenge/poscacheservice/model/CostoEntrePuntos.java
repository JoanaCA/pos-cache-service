package com.challenge.poscacheservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * Representa una conexión directa entre dos puntos de venta,
 * con un costo numérico asociado. Se almacena en Redis.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("CostoEntrePuntos")
@Schema(description = "Conexión directa entre dos puntos de venta con su costo")
public class CostoEntrePuntos implements Serializable {

    @Id
    @Schema(description = "ID compuesto del costo (formato: idOrigen_idDestino)", example = "1_2")
    private String id;

    @Schema(description = "ID del punto de venta de origen", example = "1")
    private Long idOrigen;

    @Schema(description = "ID del punto de venta de destino", example = "2")
    private Long idDestino;

    @Schema(description = "Costo del trayecto entre origen y destino", example = "5")
    private Integer costo;
}
