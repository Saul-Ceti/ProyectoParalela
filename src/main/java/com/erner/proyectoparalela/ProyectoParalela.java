package com.erner.proyectoparalela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ProyectoParalela extends JFrame {

    //Variables necesarias para el programa
    Deportista[] deportistas;
    Secuencial secuencial = new Secuencial();
    ForkJoin forkJoin = new ForkJoin();
    MetodoEjecutor metodoEjecutor = new MetodoEjecutor();

    public ProyectoParalela() {
        //Parámetros para el JFrame o la ventana pricipal
        setTitle("Análisis de datos deportivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        ////////////////////////////////////////////////////////////////////////

        //Campos de visualización de datos
        //Datos originales
        JTextArea originalData = new JTextArea();
        JScrollPane original = new JScrollPane(originalData);
        original.setBounds(50, 50, 850, 200);

        //Conteo de datos
        JLabel cantidadDeportistas = new JLabel("Esperando...");
        cantidadDeportistas.setBounds(450, 265, 100, 20);

        //Datos procesados
        JTextArea processData = new JTextArea();
        JScrollPane process = new JScrollPane(processData);
        process.setBounds(50, 300, 850, 200);
        ////////////////////////////////////////////////////////////////////////

        //Botones para el archivo
        //Subir archivo crudo
        JButton btnSubir = new JButton("Subir archivo");
        btnSubir.setBounds(950, 50, 200, 50);

        //Descargar archivo procesado
        JButton btnDescargar = new JButton("Descargar archivo");
        btnDescargar.setBounds(950, 110, 200, 50);
        ////////////////////////////////////////////////////////////////////////

        //Botones para procesar los datos y sus etiquetas
        //Botón Altura
        JButton btnAltura = new JButton("Altura");
        btnAltura.setBounds(950, 210, 200, 50);
        JLabel timeAltura = new JLabel("Esperando");
        timeAltura.setBounds(950, 265, 200, 10);

        //Botón Peso
        JButton btnPeso = new JButton("Peso");
        btnPeso.setBounds(950, 280, 200, 50);
        JLabel timePeso = new JLabel("Esperando");
        timePeso.setBounds(950, 335, 250, 10);

        //Botón Fuerza
        JButton btnFuerza = new JButton("Fuerza");
        btnFuerza.setBounds(950, 350, 200, 50);
        JLabel timeFuerza = new JLabel("Esperando");
        timeFuerza.setBounds(950, 405, 250, 10);

        //Botón Velocidad
        JButton btnVelocidad = new JButton("Velocidad");
        btnVelocidad.setBounds(950, 420, 200, 50);
        JLabel timeVelocidad = new JLabel("Esperando");
        timeVelocidad.setBounds(950, 475, 250, 10);
        ////////////////////////////////////////////////////////////////////////

        //Botón para limpiar
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(950, 520, 200, 50);
        ////////////////////////////////////////////////////////////////////////

        //Check box para filtrado
        //Filtrado Género
        JCheckBox chbGenero = new JCheckBox("Género");
        chbGenero.setBounds(50, 540, 100, 30);

        //Filtrado Género
        JCheckBox chbEdad = new JCheckBox("Edad");
        chbEdad.setBounds(200, 540, 100, 30);

        //Filtrado Género
        JCheckBox chbDeporte = new JCheckBox("Deporte");
        chbDeporte.setBounds(350, 540, 100, 30);

        //Grupo de opciones
        ButtonGroup filtrado = new ButtonGroup();
        filtrado.add(chbGenero);
        filtrado.add(chbEdad);
        filtrado.add(chbDeporte);
        ////////////////////////////////////////////////////////////////////////

        //Agrega los componentes al frame
        getContentPane().add(original);
        getContentPane().add(cantidadDeportistas);
        getContentPane().add(process);
        getContentPane().add(btnAltura);
        getContentPane().add(btnPeso);
        getContentPane().add(btnFuerza);
        getContentPane().add(btnVelocidad);
        getContentPane().add(btnSubir);
        getContentPane().add(btnDescargar);
        getContentPane().add(btnLimpiar);
        getContentPane().add(timeAltura);
        getContentPane().add(timePeso);
        getContentPane().add(timeFuerza);
        getContentPane().add(timeVelocidad);
        getContentPane().add(timeVelocidad);
        getContentPane().add(chbGenero);
        getContentPane().add(chbEdad);
        getContentPane().add(chbDeporte);
        ////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////
        ////////////////////////ACCIONES DE BOTONES/////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        //Limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                originalData.setText("");
                processData.setText("");
                cantidadDeportistas.setText("0");
                timeAltura.setText("0.0ms");
                timePeso.setText("0.0ms");
                timeFuerza.setText("0.0ms");
                timeVelocidad.setText("0.0ms");
            }
        });
        ////////////////////////////////////////////////////////////////////////
        // Subir archivo
        btnSubir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    // El usuario seleccionó un archivo
                    java.io.File file = fileChooser.getSelectedFile();
                    String filePath = file.getAbsolutePath();

                    // Crea una instancia de LeerArchivo con la ruta del archivo
                    LeerArchivo lector = new LeerArchivo(filePath);

                    // Llama al método leerDeportistas para obtener un arreglo de Deportista
                    deportistas = lector.leerDeportistas();
                    cantidadDeportistas.setText(Integer.toString(deportistas.length));

                    imprimirDeportistas(deportistas, originalData);
                } else {
                    // El usuario canceló la selección del archivo
                    JOptionPane.showMessageDialog(null, "Selección de archivo cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        ////////////////////////////////////////////////////////////////////////
        //Descargar archivo
        btnDescargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnAltura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long tiempo = medirTiempoEjecucion(() -> {
                    secuencial.ordenarDeportistasPorAltura(deportistas);
                });

                imprimirDeportistas(deportistas, processData);

                String tiempoFormateado = formatoTiempo(tiempo);
                timeAltura.setText("Tiempo: " + tiempoFormateado + " ms:ns");
            }
        });

        btnPeso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long tiempo = medirTiempoEjecucion(() -> {
                    forkJoin.ordenarDeportistasPorPeso(deportistas);
                });

                imprimirDeportistas(deportistas, processData);

                String tiempoFormateado = formatoTiempo(tiempo);
                timePeso.setText("Tiempo: " + tiempoFormateado + " ms:ns");
            }
        });

        btnFuerza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long tiempo = medirTiempoEjecucion(() -> {
                    metodoEjecutor.ordenarDeportistasPorFuerza(deportistas);
                });

                imprimirDeportistas(deportistas, processData);

                String tiempoFormateado = formatoTiempo(tiempo);
                timeFuerza.setText("Tiempo: " + tiempoFormateado + " ms:ns");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        ProyectoParalela proyectoApp = new ProyectoParalela();
    }

    public void imprimirDeportistas(Deportista[] deportistas, JTextArea area) {
        // Iterar a través del arreglo de deportistas
        for (Deportista deportista : deportistas) {
            // Obtén la información del deportista y agrega al JTextArea
            area.append("Nombre: " + deportista.getNombre() + "\n");
            area.append("Género: " + deportista.getGenero() + "\n");
            area.append("Edad: " + deportista.getEdad() + "\n");
            area.append("Deporte: " + deportista.getDeporte() + "\n");
            area.append("Altura: " + deportista.getAltura() + "\n");
            area.append("Peso: " + deportista.getPeso() + "\n");
            area.append("Fuerza: " + deportista.getFuerza() + "\n");
            area.append("Velocidad: " + deportista.getVelocidad() + "\n");
            area.append("\n"); // Agregar una línea en blanco entre deportistas
        }
    }

    public long medirTiempoEjecucion(Runnable runnable) {
        long startTime = System.nanoTime();
        runnable.run();
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    public String formatoTiempo(long tiempoNanosegundos) {
        long milliseconds = tiempoNanosegundos / 1_000_000;
        long nanoseconds = tiempoNanosegundos % 1_000_000;

        return String.format("%d:%06d", milliseconds, nanoseconds);
    }
}
