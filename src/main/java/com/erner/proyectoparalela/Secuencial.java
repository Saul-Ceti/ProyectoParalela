package com.erner.proyectoparalela;

import java.util.Arrays;

public class Secuencial {
    
public void ordenarDeportistasPorAltura(Deportista[] deportistas) {
    int n = deportistas.length;
    boolean intercambio;
    
    do {
        intercambio = false;
        for (int i = 1; i < n; i++) {
            if (deportistas[i - 1].getAltura() < deportistas[i].getAltura()) {
                // Intercambia los elementos si están en el orden incorrecto
                Deportista temp = deportistas[i - 1];
                deportistas[i - 1] = deportistas[i];
                deportistas[i] = temp;
                intercambio = true;
            }
        }
        n--;
    } while (intercambio);
}
    
    public void ordenarDeportistasPorPeso(Deportista[] deportistas) {
        // Ordena el arreglo de deportistas por peso de mayor a menor
        Arrays.sort(deportistas, (deportista1, deportista2) -> {
            if (deportista1.getPeso() > deportista2.getPeso()) {
                return -1;
            } else if (deportista1.getPeso() < deportista2.getPeso()) {
                return 1;
            } else {
                return 0;
            }
        });
    }
    
    public void ordenarDeportistasPorFuerza(Deportista[] deportistas) {
        // Ordena el arreglo de deportistas por fuerza de mayor a menor
        Arrays.sort(deportistas, (deportista1, deportista2) -> {
            if (deportista1.getFuerza() > deportista2.getFuerza()) {
                return -1;
            } else if (deportista1.getFuerza() < deportista2.getFuerza()) {
                return 1;
            } else {
                return 0;
            }
        });
    }
    
    public void ordenarDeportistasPorVelocidad(Deportista[] deportistas) {
        // Ordena el arreglo de deportistas por velocidad de mayor a menor
        Arrays.sort(deportistas, (deportista1, deportista2) -> {
            if (deportista1.getVelocidad() > deportista2.getVelocidad()) {
                return -1;
            } else if (deportista1.getVelocidad() < deportista2.getVelocidad()) {
                return 1;
            } else {
                return 0;
            }
        });
    }
}

