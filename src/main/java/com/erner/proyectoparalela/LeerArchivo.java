package com.erner.proyectoparalela;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerArchivo {
    private String filePath;

    public LeerArchivo(String filePath) {
        this.filePath = filePath;
    }

    public Deportista[] leerDeportistas() {
        List<Deportista> deportistas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String nombre = parts[0];
                    String genero = parts[1];
                    int edad = Integer.parseInt(parts[2]);
                    String deporte = parts[3];
                    float altura = Float.parseFloat(parts[4]);
                    float peso = Float.parseFloat(parts[5]);
                    float fuerza = Float.parseFloat(parts[6]);
                    float velocidad = Float.parseFloat(parts[7]);

                    Deportista deportista = new Deportista(nombre, genero, edad, deporte, altura, peso, fuerza, velocidad);
                    deportistas.add(deportista);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deportistas.toArray(new Deportista[0]);
    }
}
