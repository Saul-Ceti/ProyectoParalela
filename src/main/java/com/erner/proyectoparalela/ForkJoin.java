package com.erner.proyectoparalela;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ForkJoin {
    private final ForkJoinPool pool = new ForkJoinPool();

    public void ordenarDeportistasPorPeso(Deportista[] deportistas) {
        pool.invoke(new OrdenarPorPesoTask(deportistas, 0, deportistas.length));
    }

    private class OrdenarPorPesoTask extends RecursiveAction {
        private static final int UMBRAL = 10;
        private final Deportista[] deportistas;
        private final int inicio;
        private final int fin;

        OrdenarPorPesoTask(Deportista[] deportistas, int inicio, int fin) {
            this.deportistas = deportistas;
            this.inicio = inicio;
            this.fin = fin;
        }

        @Override
        protected void compute() {
            if (fin - inicio <= UMBRAL) {
                // Usa el algoritmo de ordenación por inserción para pequeños subarreglos
                insertionSort(deportistas, inicio, fin);
            } else {
                int mitad = inicio + (fin - inicio) / 2;
                OrdenarPorPesoTask tareaIzquierda = new OrdenarPorPesoTask(deportistas, inicio, mitad);
                OrdenarPorPesoTask tareaDerecha = new OrdenarPorPesoTask(deportistas, mitad, fin);
                invokeAll(tareaIzquierda, tareaDerecha);
                merge(deportistas, inicio, mitad, fin);
            }
        }
    }

    private void insertionSort(Deportista[] deportistas, int inicio, int fin) {
        for (int i = inicio + 1; i < fin; i++) {
            Deportista clave = deportistas[i];
            int j = i - 1;
            while (j >= inicio && deportistas[j].getPeso() < clave.getPeso()) {
                deportistas[j + 1] = deportistas[j];
                j = j - 1;
            }
            deportistas[j + 1] = clave;
        }
    }

    private void merge(Deportista[] deportistas, int inicio, int mitad, int fin) {
        // No se necesita un método de fusión en esta versión
    }
}
