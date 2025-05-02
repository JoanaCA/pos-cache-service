package com.challenge.poscacheservice.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO que representa una solicitud para crear un nuevo costo entre dos puntos de venta.
 */
@Data
@Schema(description = "Datos requeridos para registrar un nuevo costo entre puntos de venta")
public class CostoEntrePuntosRequestDTO {

    @Schema(description = "ID del punto de venta de origen", example = "1")
    private Long idOrigen;

    @Schema(description = "ID del punto de venta de destino", example = "2")
    private Long idDestino;

    @Schema(description = "Costo numérico entre los dos puntos", example = "10")
    private Integer costo;
}
