package com.challenge.poscacheservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO que encapsula el resultado del cálculo de la ruta de costo mínimo.
 * <p>
 * Contiene la secuencia de IDs de puntos de venta que forman el camino de menor
 * costo y el costo total asociado. Devuelve en un endpoint REST
 * tras ejecutar el algoritmo de Dijkstra.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LowestCostResponse {

    /**
     * Lista de IDs de puntos de venta que componen la ruta de menor costo.
     * <p>
     * El primer elemento es el ID del punto de origen, y el último, el del destino.
     * Entre ambos, la secuencia refleja el camino encontrado por el algoritmo.
     * </p>
     */
    private List<Long> ruta;

    /**
     * Costo acumulado total de la ruta calculada.
     * <p>
     * Suma de los pesos (costos) de cada conexión en la ruta.
     * </p>
     */
    private int costoTotal;
}
