package com.challenge.poscacheservice.service;

import com.challenge.poscacheservice.dto.LowestCostResponse;
import com.challenge.poscacheservice.graph.Conexion;
import com.challenge.poscacheservice.model.PuntoDeVenta;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Servicio responsable de construir el grafo en memoria y calcular
 * la ruta con menor costo entre dos puntos de venta mediante el algoritmo de Dijkstra.
 */
@Service
public class RutaService {

    // Grafo representado como lista de adyacencia:
    // clave = id de PuntoVenta, valor = lista de Conexiones salientes
    private final Map<Long, List<Conexion>> grafo = new HashMap<>();

    /**
     * Inicializa el grafo en memoria a partir de la lista de puntos y conexiones.
     * Debe llamarse una vez, por ejemplo tras cargar tus datos iniciales.
     * @param puntos lista de puntos de venta (nodos del grafo)
     * @param conexiones lista de conexiones (aristas del grafo)
     */
    public void initGrafo(List<PuntoDeVenta> puntos, List<Conexion> conexiones) {
        // Crear una entrada vacía para cada punto
        for (PuntoDeVenta p : puntos) {
            grafo.put(p.getId(), new ArrayList<>());
        }
        // Agregar cada conexión; asumimos grafo no dirigido
        for (Conexion c : conexiones) {
            grafo.get(c.getOrigenId()).add(c);
            // arista inversa
            grafo.get(c.getDestinoId())
                    .add(new Conexion(c.getDestinoId(), c.getOrigenId(), c.getCosto()));
        }
    }

    /**
     * Ejecuta Dijkstra desde 'origenId' hasta 'destinoId' y devuelve
     * un DTO con la ruta de ids y el costo total mínimo.
     * @param origenId ID del punto de venta origen
     * @param destinoId ID del punto de venta destino
     * @return DTO con la lista ordenada de IDs que componen la ruta y el costo total
     */
    public LowestCostResponse encontrarRutaMinima(Long origenId, Long destinoId) {
        // Distancias iniciales: infinito salvo el origen
        Map<Long, Integer> dist = new HashMap<>();
        // Para reconstruir camino
        Map<Long, Long> previo = new HashMap<>();
        PriorityQueue<Nodo> pq = new PriorityQueue<>();

        for (Long nodo : grafo.keySet()) {
            dist.put(nodo, Integer.MAX_VALUE);
        }
        dist.put(origenId, 0);
        pq.add(new Nodo(origenId, 0));

        while (!pq.isEmpty()) {
            Nodo actual = pq.poll();
            if (actual.id.equals(destinoId)) break;

            // Si ya encontramos una mejor ruta a 'actual', la descartamos
            if (actual.dist > dist.get(actual.id)) continue;

            for (Conexion edge : grafo.get(actual.id)) {
                int nuevaDist = dist.get(actual.id) + edge.getCosto();
                if (nuevaDist < dist.get(edge.getDestinoId())) {
                    dist.put(edge.getDestinoId(), nuevaDist);
                    previo.put(edge.getDestinoId(), actual.id);
                    pq.add(new Nodo(edge.getDestinoId(), nuevaDist));
                }
            }
        }

        // Reconstruir la ruta desde destino hasta origen
        List<Long> ruta = new ArrayList<>();
        Long paso = destinoId;
        if (!previo.containsKey(paso) && !paso.equals(origenId)) {
            // no hay ruta
            return new LowestCostResponse(Collections.emptyList(), -1);
        }
        while (paso != null) {
            ruta.add(paso);
            paso = previo.get(paso);
        }
        Collections.reverse(ruta);
        int costoTotal = dist.get(destinoId);

        return new LowestCostResponse(ruta, costoTotal);
    }

    /**
     * Clase interna que representa un nodo con su distancia acumulada.
     * Usada en la cola de prioridad para seleccionar el nodo con menor costo.
     */
    private static class Nodo implements Comparable<Nodo> {
        Long id;
        int dist;
        Nodo(Long id, int dist) {
            this.id = id;
            this.dist = dist;
        }
        @Override
        public int compareTo(Nodo o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
