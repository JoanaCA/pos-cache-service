package com.challenge.poscacheservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Representa la respuesta al calcular el camino más barato entre dos puntos de venta.
 * Contiene la lista ordenada de IDs del recorrido y el costo total acumulado.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta con la ruta más barata entre dos puntos de venta.")
public class LowestCostResponse {

    @Schema(
            description = "Lista de IDs de los puntos de venta en el orden del recorrido",
            example = "[1, 4, 5]"
    )
    private List<Long> ruta;

    @Schema(
            description = "Costo total para recorrer el camino",
            example = "11"
    )
    private int costoTotal;
}
