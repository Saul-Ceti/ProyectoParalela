package com.erner.proyectoparalela;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MetodoEjecutor {

    public void ordenarDeportistasPorFuerza(Deportista[] deportistas) {
        // Crea un ExecutorService con un número fijo de hilos
        ExecutorService executor = Executors.newFixedThreadPool(4); // Por ejemplo, 4 hilos

        int batchSize = deportistas.length / 4; // Divide los deportistas en 4 lotes
        Future<Deportista[]>[] futures = new Future[4];

        // Divide el trabajo en lotes y crea tareas para ordenar por fuerza
        for (int i = 0; i < 4; i++) {
            int startIndex = i * batchSize;
            int endIndex = (i == 3) ? deportistas.length : (i + 1) * batchSize;

            Callable<Deportista[]> task = () -> {
                Arrays.sort(deportistas, startIndex, endIndex, (deportista1, deportista2) -> {
                    if (deportista1.getFuerza() > deportista2.getFuerza()) {
                        return -1;
                    } else if (deportista1.getFuerza() < deportista2.getFuerza()) {
                        return 1;
                    } else {
                        return 0;
                    }
                });

                return Arrays.copyOfRange(deportistas, startIndex, endIndex);
            };

            futures[i] = executor.submit(task);
        }

        // Espera a que todas las tareas se completen y recopila los resultados
        try {
            for (Future<Deportista[]> future : futures) {
                future.get(); // Espera a que la tarea termine, pero no hace nada con el resultado
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Fusiona los resultados en un solo arreglo
        Deportista[] merged = new Deportista[deportistas.length];
        int index = 0;
        for (Future<Deportista[]> future : futures) {
            try {
                Deportista[] result = future.get();
                System.arraycopy(result, 0, merged, index, result.length);
                index += result.length;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Copia el resultado ordenado de nuevo al arreglo original
        System.arraycopy(merged, 0, deportistas, 0, merged.length);

        // Apaga el ExecutorService
        executor.shutdown();
    }
}
