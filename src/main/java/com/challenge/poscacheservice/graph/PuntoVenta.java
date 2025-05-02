package com.challenge.poscacheservice.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un punto de venta dentro del grafo de la aplicación.
 * <p>
 * Cada instancia modela un nodo del grafo con un identificador único y un nombre
 * descriptivo. Se emplea como parte de la estructura de datos de adyacencia para
 * calcular rutas de costo mínimo entre puntos de venta.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PuntoVenta {

    /**
     * Identificador único del punto de venta.
     * <p>
     * Se utiliza como clave en la lista de adyacencia del grafo y para referenciar
     * de forma inequívoca cada nodo en los algoritmos de búsqueda de ruta.
     * </p>
     */
    private Long id;

    /**
     * Nombre descriptivo del punto de venta.
     * <p>
     * Puede ser una etiqueta como "CABA", "GBA_1", "Salta", etc., que ayuda a
     * interpretar mejor los resultados de la ruta y a mostrar información legible
     * al usuario.
     * </p>
     */
    private String nombre;
}
