package com.challenge.poscacheservice.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una arista (conexión) entre dos puntos de venta en el grafo.
 * <p>
 * Cada instancia modela el enlace desde un nodo origen hasta un nodo destino,
 * con un costo asociado que indica el peso de esa conexión. Es fundamental
 * para calcular rutas de costo mínimo usando algoritmos como Dijkstra.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conexion {


    /**
     * Identificador del punto de venta de origen.
     * <p>
     * Corresponde al nodo desde el cual parte esta conexión.
     * Se usa como clave para recorrer las adyacencias del grafo.
     * </p>
     */
    private Long origenId;

    /**
     * Identificador del punto de venta de destino.
     * <p>
     * Indica el nodo al cual llega esta conexión.
     * Junto con {@code origenId}, define la arista en el grafo.
     * </p>
     */
    private Long destinoId;

    /**
     * Costo de recorrer esta conexión.
     * <p>
     * Representa el peso o “distancia” entre los dos puntos de venta.
     * Se emplea en el cálculo de la ruta de costo mínimo para
     * sumarizar el coste total de un camino.
     * </p>
     */
    private int costo;

}
