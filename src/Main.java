/**
 * ========================================
 * JUEGO DE PREGUNTADOS
 * ========================================
 * Autor: Jessid alexander agudelo herrera
 * Código: 202478460
 * 
 * DESCRIPCIÓN:
 * Juego de preguntas y respuestas con múltiples categorías.
 * El jugador debe responder preguntas para acumular puntos.
 * 
 * CARACTERÍSTICAS:
 * - 5 ventanas de interfaz gráfica
 * - Sistema de puntos
 * - Sistema de pistas (costo: 5 puntos)
 * - 4 categorías con múltiples preguntas
 * - Programación Orientada a Objetos (POO)
 * - Programación Orientada a Eventos (POE)
 * ========================================
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase principal que inicia la aplicación
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoPreguntados());
    }
}

/**
 * ========================================
 * CLASE: JuegoPreguntados
 * ========================================
 * Controlador principal del juego
 * 
 * RESPONSABILIDADES:
 * - Gestionar el flujo entre ventanas
 * - Controlar el estado del juego
 * - Manejar la lógica de las 5 ventanas
 * 
 * ATRIBUTOS:
 * - frame: Ventana principal del juego
 * - jugador: Objeto que representa al jugador actual
 * - banco: Banco de preguntas
 * - indicePreguntaActual: Índice de la pregunta actual
 * - pistaUsada: Bandera para controlar si se usó pista
 * 
 * MÉTODOS:
 * - GUI_inicio(): Pantalla de bienvenida
 * - GUI_ingresoNombre(): Captura nombre del jugador
 * - GUI_seleccionCategoria(): Selección de categoría
 * - GUI_pregunta(): Muestra pregunta y opciones
 * - GUI_resultado(): Muestra resultado final
 * ========================================
 */
class JuegoPreguntados {
    private JFrame frame;
    private Jugador jugador;
    private BancoPregunta banco;
    private int indicePreguntaActual;
    private boolean pistaUsada;
    
    /**
     * Constructor: Inicializa el juego y muestra la pantalla de inicio
     */
    public JuegoPreguntados() {
        banco = new BancoPregunta();
        pistaUsada = false;
        GUI_inicio();
    }
    
    /**
     * VENTANA 1: Pantalla de Inicio
     * Muestra el título del juego, información del estudiante y botón para iniciar
     */
    private void GUI_inicio() {
        frame = new JFrame("Preguntados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(52, 152, 219));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        // Título del juego
        JLabel titulo = new JLabel("¡Preguntados  :D  ¡");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        
        // Nombre del estudiante
        JLabel nombre = new JLabel("Jessid alexander agudelo herrera");
        nombre.setFont(new Font("Arial", Font.PLAIN, 18));
        nombre.setForeground(Color.WHITE);
        nombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(nombre);
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Código del estudiante
        JLabel codigo = new JLabel("202478460");
        codigo.setFont(new Font("Arial", Font.BOLD, 16));
        codigo.setForeground(new Color(255, 235, 59));
        codigo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(codigo);
        
        panel.add(Box.createRigidArea(new Dimension(0, 60)));
        
        // Botón iniciar (POE - Evento ActionListener)
        JButton btnIniciar = new JButton("INICIAR JUEGO");
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciar.setBackground(new Color(46, 204, 113));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setFocusPainted(false);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI_ingresoNombre();
            }
        });
        
        panel.add(btnIniciar);
        
        frame.add(panel);
        frame.setVisible(true);
    }
    
    /**
     * VENTANA 2: Ingreso de Nombre
     * Permite al jugador ingresar su nombre
     * Valida que el nombre no esté vacío
     */
    private void GUI_ingresoNombre() {
        frame.getContentPane().removeAll();
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(155, 89, 182));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 50, 80, 50));
        
        JLabel titulo = new JLabel("Ingresa tu nombre");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JTextField txtNombre = new JTextField(20);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNombre.setMaximumSize(new Dimension(300, 35));
        txtNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(txtNombre);
        
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Botón continuar con validación (POE)
        JButton btnContinuar = new JButton("CONTINUAR");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 14));
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setBackground(new Color(46, 204, 113));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFocusPainted(false);
        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor ingresa tu nombre", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    jugador = new Jugador(nombre);
                    GUI_seleccionCategoria();
                }
            }
        });
        
        panel.add(btnContinuar);
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
    
    /**
     * VENTANA 3: Selección de Categoría
     * Muestra las 4 categorías disponibles
     * Inicializa el banco de preguntas según la categoría seleccionada
     */
    private void GUI_seleccionCategoria() {
        frame.getContentPane().removeAll();
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(231, 76, 60));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        
        JLabel titulo = new JLabel("Selecciona una categoría");
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel saludo = new JLabel("¡Hola " + jugador.getNombre() + "!");
        saludo.setFont(new Font("Arial", Font.PLAIN, 18));
        saludo.setForeground(Color.WHITE);
        saludo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(saludo);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        String[] categorias = {"Ciencia", "Historia", "Deportes", "Geografía"};
        
        // Crear botón para cada categoría (POE)
        for (String cat : categorias) {
            JButton btnCategoria = new JButton(cat);
            btnCategoria.setFont(new Font("Arial", Font.BOLD, 14));
            btnCategoria.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCategoria.setMaximumSize(new Dimension(200, 40));
            btnCategoria.setBackground(new Color(44, 62, 80));
            btnCategoria.setForeground(Color.WHITE);
            btnCategoria.setFocusPainted(false);
            btnCategoria.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    banco.setCategoria(cat);
                    indicePreguntaActual = 0;
                    pistaUsada = false;
                    GUI_pregunta();
                }
            });
            
            panel.add(btnCategoria);
            panel.add(Box.createRigidArea(new Dimension(0, 15)));
        }
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
    
    /**
     * VENTANA 4: Pregunta
     * Muestra la pregunta actual con sus opciones
     * Incluye sistema de pistas que cuesta 5 puntos
     * Verifica respuestas y actualiza puntuación
     */
    private void GUI_pregunta() {
        if (indicePreguntaActual >= banco.getPreguntas().size()) {
            GUI_resultado();
            return;
        }
        
        frame.getContentPane().removeAll();
        
        Pregunta pregunta = banco.getPreguntas().get(indicePreguntaActual);
        pistaUsada = false;
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(52, 152, 219));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));
        
        // Panel superior con progreso y puntos
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelSuperior.setBackground(new Color(52, 152, 219));
        panelSuperior.setMaximumSize(new Dimension(450, 30));
        
        // Indicador de progreso
        JLabel lblProgreso = new JLabel("Pregunta " + (indicePreguntaActual + 1) + " de " + banco.getPreguntas().size());
        lblProgreso.setFont(new Font("Arial", Font.BOLD, 14));
        lblProgreso.setForeground(new Color(255, 235, 59));
        
        // Puntos actuales
        JLabel lblPuntos = new JLabel("Puntos: " + jugador.getPuntos());
        lblPuntos.setFont(new Font("Arial", Font.BOLD, 18));
        lblPuntos.setForeground(Color.WHITE);
        
        panelSuperior.add(lblProgreso);
        panelSuperior.add(lblPuntos);
        
        panel.add(panelSuperior);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Panel para la pregunta con borde
        JPanel panelPregunta = new JPanel();
        panelPregunta.setBackground(new Color(41, 128, 185));
        panelPregunta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panelPregunta.setMaximumSize(new Dimension(400, 150));
        
        JLabel lblPregunta = new JLabel("<html><div style='text-align: center; width: 350px;'>" + 
            pregunta.getTexto() + "</div></html>");
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 18));
        lblPregunta.setForeground(Color.WHITE);
        lblPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPregunta.add(lblPregunta);
        
        panel.add(panelPregunta);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Botón de PISTA (POE - Costo: 5 puntos)
        JButton btnPista = new JButton("💡 USAR PISTA (-5 puntos)");
        btnPista.setFont(new Font("Arial", Font.BOLD, 12));
        btnPista.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPista.setMaximumSize(new Dimension(220, 35));
        btnPista.setBackground(new Color(241, 196, 15));
        btnPista.setForeground(Color.BLACK);
        btnPista.setFocusPainted(false);
        btnPista.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Evento del botón de pista
        btnPista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pistaUsada) {
                    JOptionPane.showMessageDialog(frame, 
                        "Ya usaste la pista para esta pregunta", 
                        "Pista ya usada", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (jugador.getPuntos() < 5) {
                    JOptionPane.showMessageDialog(frame, 
                        "No tienes suficientes puntos\nNecesitas al menos 5 puntos", 
                        "Puntos insuficientes", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Consumir puntos y mostrar pista
                jugador.restarPuntos(5);
                pistaUsada = true;
                String pista = pregunta.getPista();
                
                JOptionPane.showMessageDialog(frame, 
                    "PISTA:\n" + pista + "\n\n-5 puntos", 
                    "💡 Pista", JOptionPane.INFORMATION_MESSAGE);
                
                // Actualizar puntos en pantalla
                lblPuntos.setText("Puntos: " + jugador.getPuntos());
                btnPista.setEnabled(false);
                btnPista.setText("💡 PISTA USADA");
                btnPista.setBackground(Color.GRAY);
            }
        });
        
        panel.add(btnPista);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Opciones de respuesta
        ArrayList<String> opciones = pregunta.getOpciones();
        
        for (int i = 0; i < opciones.size(); i++) {
            String opcion = opciones.get(i);
            JButton btnOpcion = new JButton((char)('A' + i) + ") " + opcion);
            btnOpcion.setFont(new Font("Arial", Font.PLAIN, 15));
            btnOpcion.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnOpcion.setMaximumSize(new Dimension(380, 50));
            btnOpcion.setBackground(Color.WHITE);
            btnOpcion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            btnOpcion.setFocusPainted(false);
            btnOpcion.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            // Efecto hover (POE - MouseListener)
            btnOpcion.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btnOpcion.setBackground(new Color(46, 204, 113));
                    btnOpcion.setForeground(Color.WHITE);
                }
                public void mouseExited(MouseEvent e) {
                    btnOpcion.setBackground(Color.WHITE);
                    btnOpcion.setForeground(Color.BLACK);
                }
            });
            
            // Evento de click en opción (POE)
            btnOpcion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (pregunta.verificarRespuesta(opcion)) {
                        jugador.sumarPuntos(10);
                        JOptionPane.showMessageDialog(frame, 
                            "¡Excelente! Respuesta correcta\n+10 puntos", 
                            "✓ Correcto", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, 
                            "Respuesta incorrecta\n\nLa respuesta correcta era:\n" + pregunta.getRespuestaCorrecta(), 
                            "✗ Incorrecto", JOptionPane.ERROR_MESSAGE);
                    }
                    indicePreguntaActual++;
                    GUI_pregunta();
                }
            });
            
            panel.add(btnOpcion);
            panel.add(Box.createRigidArea(new Dimension(0, 12)));
        }
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
    
    /**
     * VENTANA 5: Resultado Final
     * Muestra la puntuación final del jugador
     * Opciones para jugar de nuevo o salir
     */
    private void GUI_resultado() {
        frame.getContentPane().removeAll();
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(46, 204, 113));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 50, 60, 50));
        
        JLabel titulo = new JLabel("¡Juego Terminado!");
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        JLabel lblJugador = new JLabel("Jugador: " + jugador.getNombre());
        lblJugador.setFont(new Font("Arial", Font.PLAIN, 20));
        lblJugador.setForeground(Color.WHITE);
        lblJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(lblJugador);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JLabel lblPuntos = new JLabel("Puntos Finales: " + jugador.getPuntos());
        lblPuntos.setFont(new Font("Arial", Font.BOLD, 24));
        lblPuntos.setForeground(new Color(255, 235, 59));
        lblPuntos.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(lblPuntos);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        // Botón reintentar (POE)
        JButton btnReintentar = new JButton("JUGAR DE NUEVO");
        btnReintentar.setFont(new Font("Arial", Font.BOLD, 14));
        btnReintentar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReintentar.setBackground(new Color(52, 152, 219));
        btnReintentar.setForeground(Color.WHITE);
        btnReintentar.setFocusPainted(false);
        btnReintentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugador = null;
                GUI_inicio();
            }
        });
        
        panel.add(btnReintentar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Botón salir (POE)
        JButton btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setBackground(new Color(231, 76, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        panel.add(btnSalir);
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
}

/**
 * ========================================
 * CLASE: Jugador
 * ========================================
 * Representa a un jugador del juego
 * 
 * ATRIBUTOS:
 * - nombre: Nombre del jugador
 * - puntos: Puntuación acumulada
 * 
 * MÉTODOS:
 * - getNombre(): Retorna el nombre del jugador
 * - getPuntos(): Retorna los puntos actuales
 * - sumarPuntos(int): Suma puntos (respuesta correcta = +10)
 * - restarPuntos(int): Resta puntos (usar pista = -5)
 * ========================================
 */
class Jugador {
    private String nombre;
    private int puntos;
    
    /**
     * Constructor: Crea un jugador con puntos iniciales en 0
     * @param nombre Nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }
    
    /**
     * Obtiene el nombre del jugador
     * @return nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene los puntos actuales del jugador
     * @return puntos acumulados
     */
    public int getPuntos() {
        return puntos;
    }
    
    /**
     * Suma puntos al jugador (respuesta correcta)
     * @param puntos Cantidad de puntos a sumar
     */
    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }
    
    /**
     * Resta puntos al jugador (usar pista)
     * @param puntos Cantidad de puntos a restar
     */
    public void restarPuntos(int puntos) {
        this.puntos -= puntos;
        if (this.puntos < 0) {
            this.puntos = 0;
        }
    }
}

/**
 * ========================================
 * CLASE: Pregunta
 * ========================================
 * Representa una pregunta del juego
 * 
 * ATRIBUTOS:
 * - texto: Enunciado de la pregunta
 * - opciones: Lista de opciones de respuesta (4 opciones)
 * - respuestaCorrecta: Respuesta correcta
 * - pista: Ayuda para el jugador
 * 
 * MÉTODOS:
 * - getTexto(): Retorna el texto de la pregunta
 * - getOpciones(): Retorna las opciones barajadas
 * - getRespuestaCorrecta(): Retorna la respuesta correcta
 * - getPista(): Retorna la pista de la pregunta
 * - verificarRespuesta(String): Verifica si la respuesta es correcta
 * ========================================
 */
class Pregunta {
    private String texto;
    private ArrayList<String> opciones;
    private String respuestaCorrecta;
    private String pista;
    
    /**
     * Constructor: Crea una pregunta con sus opciones y pista
     * Las opciones se barajan aleatoriamente
     * 
     * @param texto Enunciado de la pregunta
     * @param opcionesArray Arreglo con las 4 opciones
     * @param respuestaCorrecta Respuesta correcta
     * @param pista Ayuda para el jugador
     */
    public Pregunta(String texto, String[] opcionesArray, String respuestaCorrecta, String pista) {
        this.texto = texto;
        this.respuestaCorrecta = respuestaCorrecta;
        this.pista = pista;
        this.opciones = new ArrayList<>();
        for (String op : opcionesArray) {
            opciones.add(op);
        }
        Collections.shuffle(opciones);
    }
    
    public String getTexto() {
        return texto;
    }
    
    public ArrayList<String> getOpciones() {
        return opciones;
    }
    
    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
    
    public String getPista() {
        return pista;
    }
    
    /**
     * Verifica si la respuesta proporcionada es correcta
     * @param respuesta Respuesta del jugador
     * @return true si es correcta, false si no
     */
    public boolean verificarRespuesta(String respuesta) {
        return respuesta.equals(respuestaCorrecta);
    }
}

/**
 * ========================================
 * CLASE: BancoPregunta
 * ========================================
 * Almacena y gestiona todas las preguntas del juego
 * 
 * ATRIBUTOS:
 * - preguntas: Lista de preguntas de la categoría actual
 * 
 * MÉTODOS:
 * - setCategoria(String): Carga las preguntas de una categoría
 * - getPreguntas(): Retorna la lista de preguntas
 * 
 * CATEGORÍAS DISPONIBLES:
 * 1. Ciencia (6 preguntas)
 * 2. Historia (6 preguntas)
 * 3. Deportes (6 preguntas)
 * 4. Geografía (6 preguntas)
 * TOTAL: 24 preguntas
 * ========================================
 */
class BancoPregunta {
    private ArrayList<Pregunta> preguntas;
    
    /**
     * Constructor: Inicializa el banco de preguntas vacío
     */
    public BancoPregunta() {
        preguntas = new ArrayList<>();
    }
    
    /**
     * Carga las preguntas según la categoría seleccionada
     * Cada pregunta incluye su pista
     * 
     * @param categoria Nombre de la categoría (Ciencia, Historia, Deportes, Geografía)
     */
    public void setCategoria(String categoria) {
        preguntas.clear();
        
        switch (categoria) {
            case "Ciencia":
                preguntas.add(new Pregunta(
                    "¿Cuál es el elemento químico más abundante en el universo?",
                    new String[]{"Hidrógeno", "Oxígeno", "Carbono", "Helio"},
                    "Hidrógeno",
                    "Es el elemento más simple, con solo un protón"
                ));
                break;
                
            case "Historia":
                preguntas.add(new Pregunta(
                    "¿En qué año comenzó la Segunda Guerra Mundial?",
                    new String[]{"1939", "1940", "1938", "1941"},
                    "1939",
                    "Fue el mismo año en que Alemania invadió Polonia"
                ));
                preguntas.add(new Pregunta(
                    "¿Quién fue el primer presidente de Estados Unidos?",
                    new String[]{"George Washington", "Thomas Jefferson", "Abraham Lincoln", "Benjamin Franklin"},
                    "George Washington",
                    "Su rostro aparece en el billete de 1 dólar"
                ));
                preguntas.add(new Pregunta(
                    "¿En qué año llegó Cristóbal Colón a América?",
                    new String[]{"1492", "1490", "1500", "1485"},
                    "1492",
                    "Es fácil recordar: 'En 1492, Colón llegó a América'"
                ));
                preguntas.add(new Pregunta(
                    "¿Quién pintó la Mona Lisa?",
                    new String[]{"Leonardo da Vinci", "Miguel Ángel", "Rafael", "Donatello"},
                    "Leonardo da Vinci",
                    "Fue un genio del Renacimiento italiano"
                ));
                preguntas.add(new Pregunta(
                    "¿En qué año cayó el Muro de Berlín?",
                    new String[]{"1989", "1990", "1985", "1991"},
                    "1989",
                    "Marcó el fin de la Guerra Fría"
                ));
                preguntas.add(new Pregunta(
                    "¿Quién fue el libertador de varios países sudamericanos?",
                    new String[]{"Simón Bolívar", "José de San Martín", "Antonio José de Sucre", "Francisco de Miranda"},
                    "Simón Bolívar",
                    "Es conocido como 'El Libertador'"
                ));
                break;
                
            case "Deportes":
                preguntas.add(new Pregunta(
                    "¿Cuántos jugadores tiene un equipo de fútbol en el campo?",
                    new String[]{"11", "10", "12", "9"},
                    "11",
                    "Incluye al portero"
                ));
                preguntas.add(new Pregunta(
                    "¿En qué deporte se usa un birdie?",
                    new String[]{"Bádminton", "Tenis", "Voleibol", "Golf"},
                    "Bádminton",
                    "Es un proyectil con plumas"
                ));
                preguntas.add(new Pregunta(
                    "¿Cuántos anillos tiene el logo de los Juegos Olímpicos?",
                    new String[]{"5", "4", "6", "7"},
                    "5",
                    "Representan los 5 continentes"
                ));
                preguntas.add(new Pregunta(
                    "¿Cuántos sets se juegan en un partido de voleibol?",
                    new String[]{"5", "3", "7", "4"},
                    "5",
                    "Se juega al mejor de 5 sets"
                ));
                preguntas.add(new Pregunta(
                    "¿Cuántos puntos vale un tiro libre en baloncesto?",
                    new String[]{"1", "2", "3", "0"},
                    "1",
                    "Se otorga después de una falta"
                ));
                preguntas.add(new Pregunta(
                    "¿Cada cuántos años se realizan los Juegos Olímpicos?",
                    new String[]{"4 años", "2 años", "3 años", "5 años"},
                    "4 años",
                    "Es una tradición que viene de la antigua Grecia"
                ));
                break;
                
            case "Geografía":
                preguntas.add(new Pregunta(
                    "¿Cuál es el río más largo del mundo?",
                    new String[]{"Amazonas", "Nilo", "Yangtsé", "Misisipi"},
                    "Amazonas",
                    "Está en América del Sur"
                ));
                preguntas.add(new Pregunta(
                    "¿Cuál es la capital de Francia?",
                    new String[]{"París", "Londres", "Berlín", "Madrid"},
                    "París",
                    "La Torre Eiffel está en esta ciudad"
                ));
                preguntas.add(new Pregunta(
                    "¿En qué continente está Egipto?",
                    new String[]{"África", "Asia", "Europa", "América"},
                    "África",
                    "Allí están las famosas pirámides"
                ));
                preguntas.add(new Pregunta(
                    "¿Cuál es el océano más grande del mundo?",
                    new String[]{"Océano Pacífico", "Océano Atlántico", "Océano Índico", "Océano Ártico"},
                    "Océano Pacífico",
                    "Cubre más de un tercio de la superficie terrestre"
                ));
                preguntas.add(new Pregunta(
                    "¿Cuál es la montaña más alta del mundo?",
                    new String[]{"Monte Everest", "K2", "Kilimanjaro", "Monte Aconcagua"},
                    "Monte Everest",
                    "Está en la cordillera del Himalaya"
                ));
                preguntas.add(new Pregunta(
                    "¿Cuál es el país más grande del mundo?",
                    new String[]{"Rusia", "Canadá", "China", "Estados Unidos"},
                    "Rusia",
                    "Abarca Europa y Asia"
                ));
                break;
        }
    }
    
    /**
     * Retorna la lista de preguntas de la categoría actual
     * @return ArrayList con las preguntas
     */
    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }
}