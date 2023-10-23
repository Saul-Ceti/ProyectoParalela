package com.erner.proyectoparalela;

import javax.swing.JTextArea;

public class Deportista {

    private String nombre;
    private String genero;
    private int edad;
    private String deporte;
    private float altura;
    private float peso;
    private float fuerza;
    private float velocidad;

    public Deportista(String nombre, String genero, int edad, String deporte, float altura, float peso, float fuerza, float velocidad) {
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.deporte = deporte;
        this.altura = altura;
        this.peso = peso;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
    }

    // Getters y setters para los atributos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getFuerza() {
        return fuerza;
    }

    public void setFuerza(float fuerza) {
        this.fuerza = fuerza;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }
}
