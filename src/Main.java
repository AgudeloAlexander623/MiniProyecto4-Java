import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoPreguntados());
    }
}

class JuegoPreguntados {
    private JFrame frame;
    private Jugador jugador;
    private BancoPregunta banco;
    private int indicePreguntaActual;
    private boolean pistaUsada;
    private Timer temporizador;
    private int tiempoRestante;
    private boolean pausado;
    private ArrayList<Administrador> administradores;
    
    public JuegoPreguntados() {
        banco = new BancoPregunta();
        pistaUsada = false;
        pausado = false;
        administradores = new ArrayList<>();
        // Administrador por defecto
        administradores.add(new Administrador("root", "1234"));
        GUI_inicio();
    }

    private void GUI_inicio() {
        if (frame != null) {
            frame.dispose();
        }
        
        frame = new JFrame("Preguntados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(52, 152, 219));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        
        // Título del juego
        JLabel titulo = new JLabel("¡Preguntados  :D  ¡");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        
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
        
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        // Botón iniciar juego
        JButton btnIniciar = new JButton("INICIAR JUEGO");
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciar.setMaximumSize(new Dimension(250, 40));
        btnIniciar.setBackground(new Color(46, 204, 113));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setFocusPainted(false);
        btnIniciar.addActionListener(e -> GUI_ingresoNombre());
        
        panel.add(btnIniciar);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Botón administrador
        JButton btnAdmin = new JButton("👤 ADMINISTRADOR");
        btnAdmin.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdmin.setMaximumSize(new Dimension(250, 40));
        btnAdmin.setBackground(new Color(231, 76, 60));
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setFocusPainted(false);
        btnAdmin.addActionListener(e -> GUI_loginAdmin());
        
        panel.add(btnAdmin);
        
        frame.add(panel);
        frame.setVisible(true);
    }
    
    
    private void GUI_loginAdmin() {
        frame.getContentPane().removeAll();
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(231, 76, 60));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        JLabel titulo = new JLabel("Panel de Administrador");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        // Label Username
        JLabel lblUsuario = new JLabel("Username:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        
        // Input Username
        JTextField txtUsuario = new JTextField(15);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsuario.setMaximumSize(new Dimension(280, 35));
        txtUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(txtUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Label Password
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 16));
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblPassword);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        
        // Input Password
        JPasswordField txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setMaximumSize(new Dimension(280, 35));
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(txtPassword);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Contador de intentos
        final int[] intentos = {3};
        
        // Botón ENTRAR
        JButton btnEntrar = new JButton("ENTRAR");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.setMaximumSize(new Dimension(280, 45));
        btnEntrar.setBackground(new Color(46, 204, 113));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover en botón ENTRAR
        btnEntrar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnEntrar.setBackground(new Color(39, 174, 96));
            }
            public void mouseExited(MouseEvent e) {
                btnEntrar.setBackground(new Color(46, 204, 113));
            }
        });
        
        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String password = new String(txtPassword.getPassword());
            
            if (usuario.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, 
                    "Por favor completa todos los campos",
                    "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            boolean autenticado = false;
            for (Administrador admin : administradores) {
                if (admin.autenticar(usuario, password)) {
                    autenticado = true;
                    break;
                }
            }
            
            if (autenticado) {
                JOptionPane.showMessageDialog(frame, 
                    "¡Bienvenido, " + usuario + "!",
                    "Acceso Concedido", JOptionPane.INFORMATION_MESSAGE);
                GUI_panelAdmin();
            } else {
                intentos[0]--;
                if (intentos[0] > 0) {
                    JOptionPane.showMessageDialog(frame, 
                        "Credenciales incorrectas\n\nIntentos restantes: " + intentos[0],
                        "❌ Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                    txtPassword.setText("");
                    txtUsuario.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(frame, 
                        "Ups!! . diste los datos mal !!!!",
                        "🚫 Acceso Denegado", JOptionPane.ERROR_MESSAGE);
                    System.out.println("=================================");
                    System.out.println("❌ ACCESO DENEGADO");
                    System.out.println("Usuario intentado: " + usuario);
                    System.out.println("Intentos agotados: 3/3");
                    System.out.println("=================================");
                    System.exit(0);
                }
            }
        });
        
        panel.add(btnEntrar);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Botón CREAR ADMINISTRADOR
        JButton btnCrear = new JButton("CREAR ADMINISTRADOR");
        btnCrear.setFont(new Font("Arial", Font.BOLD, 14));
        btnCrear.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCrear.setMaximumSize(new Dimension(280, 40));
        btnCrear.setBackground(new Color(52, 152, 219));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFocusPainted(false);
        btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnCrear.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnCrear.setBackground(new Color(41, 128, 185));
            }
            public void mouseExited(MouseEvent e) {
                btnCrear.setBackground(new Color(52, 152, 219));
            }
        });
        
        btnCrear.addActionListener(e -> GUI_crearAdmin());
        
        panel.add(btnCrear);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Botón VOLVER
        JButton btnVolver = new JButton("⬅ VOLVER");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 12));
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.setMaximumSize(new Dimension(280, 35));
        btnVolver.setBackground(new Color(149, 165, 166));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnVolver.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnVolver.setBackground(new Color(127, 140, 141));
            }
            public void mouseExited(MouseEvent e) {
                btnVolver.setBackground(new Color(149, 165, 166));
            }
        });
        
        btnVolver.addActionListener(e -> GUI_inicio());
        
        panel.add(btnVolver);
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void GUI_crearAdmin() {
        frame.getContentPane().removeAll();
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(155, 89, 182));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        JLabel titulo = new JLabel("Preguntas de Seguridad");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JLabel info = new JLabel("Responde al menos 2 de 3 preguntas correctamente");
        info.setFont(new Font("Arial", Font.PLAIN, 12));
        info.setForeground(new Color(255, 235, 59));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(info);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // Pregunta 1
        JLabel lblP1 = new JLabel("1. ¿Cómo se llama el creador del juego?");
        lblP1.setForeground(Color.WHITE);
        lblP1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblP1);
        
        JTextField txtP1 = new JTextField(20);
        txtP1.setMaximumSize(new Dimension(300, 30));
        txtP1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(txtP1);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Pregunta 2
        JLabel lblP2 = new JLabel("2. ¿Cuál es el nombre del perro del creador?");
        lblP2.setForeground(Color.WHITE);
        lblP2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblP2);
        
        JTextField txtP2 = new JTextField(20);
        txtP2.setMaximumSize(new Dimension(300, 30));
        txtP2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(txtP2);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Pregunta 3
        JLabel lblP3 = new JLabel("3. ¿Cuál es la edad del creador del juego?");
        lblP3.setForeground(Color.WHITE);
        lblP3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblP3);
        
        JTextField txtP3 = new JTextField(20);
        txtP3.setMaximumSize(new Dimension(300, 30));
        txtP3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(txtP3);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        JButton btnVerificar = new JButton("VERIFICAR");
        btnVerificar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVerificar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerificar.setBackground(new Color(46, 204, 113));
        btnVerificar.setForeground(Color.WHITE);
        btnVerificar.setFocusPainted(false);
        btnVerificar.addActionListener(e -> {
            int correctas = 0;
            
            // Verificar respuestas (case-insensitive)
            if (txtP1.getText().trim().equalsIgnoreCase("Alexander agudelo")) correctas++;
            if (txtP2.getText().trim().equalsIgnoreCase("thanos")) correctas++;
            if (txtP3.getText().trim().equals("21")) correctas++;
            
            if (correctas >= 2) {
                // Puede crear administrador
                String usuario = JOptionPane.showInputDialog(frame, "Ingresa el nombre de usuario:");
                if (usuario != null && !usuario.trim().isEmpty()) {
                    String password = JOptionPane.showInputDialog(frame, "Ingresa la contraseña:");
                    if (password != null && !password.trim().isEmpty()) {
                        administradores.add(new Administrador(usuario, password));
                        JOptionPane.showMessageDialog(frame, 
                            "¡Administrador creado exitosamente!",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        GUI_loginAdmin();
                    }
                }
            } else {
                // No cumple condiciones, vuelve al inicio sin mensaje
                GUI_inicio();
            }
        });
        
        panel.add(btnVerificar);
        
        frame.revalidate();
        frame.repaint();
    }
    
    private void GUI_panelAdmin() {
        frame.getContentPane().removeAll();
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(44, 62, 80));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titulo = new JLabel("Panel de Administración - CRUD Preguntas");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);
        
        // Panel central con lista de preguntas
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(new Color(44, 62, 80));
        
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        JList<String> listPreguntas = new JList<>(modeloLista);
        listPreguntas.setBackground(new Color(236, 240, 241));
        
        JScrollPane scrollPane = new JScrollPane(listPreguntas);
        panelCentro.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1, 10, 10));
        panelBotones.setBackground(new Color(44, 62, 80));
        
        JButton btnVer = new JButton("VER PREGUNTAS");
        JButton btnAgregar = new JButton("AGREGAR PREGUNTA");
        JButton btnModificar = new JButton("MODIFICAR PREGUNTA");
        JButton btnEliminar = new JButton("ELIMINAR PREGUNTA");
        JButton btnVolver = new JButton("VOLVER");
        
        // Estilizar botones
        JButton[] botones = {btnVer, btnAgregar, btnModificar, btnEliminar, btnVolver};
        for (JButton btn : botones) {
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
        }
        
        btnVer.setBackground(new Color(52, 152, 219));
        btnAgregar.setBackground(new Color(46, 204, 113));
        btnModificar.setBackground(new Color(241, 196, 15));
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnVolver.setBackground(new Color(149, 165, 166));
        
        // Eventos de botones
        btnVer.addActionListener(e -> {
            modeloLista.clear();
            String categoria = JOptionPane.showInputDialog(frame, 
                "Ingresa la categoría (Ciencia, Historia, Deportes, Geografía):");
            if (categoria != null) {
                banco.setCategoria(categoria);
                ArrayList<Pregunta> preguntas = banco.getPreguntas();
                for (int i = 0; i < preguntas.size(); i++) {
                    modeloLista.addElement((i + 1) + ". " + preguntas.get(i).getTexto());
                }
            }
        });
        
        btnAgregar.addActionListener(e -> {
            String categoria = JOptionPane.showInputDialog(frame, 
                "Categoría (Ciencia, Historia, Deportes, Geografía):");
            if (categoria == null) return;
            
            String texto = JOptionPane.showInputDialog(frame, "Texto de la pregunta:");
            if (texto == null) return;
            
            String op1 = JOptionPane.showInputDialog(frame, "Opción 1:");
            String op2 = JOptionPane.showInputDialog(frame, "Opción 2:");
            String op3 = JOptionPane.showInputDialog(frame, "Opción 3:");
            String op4 = JOptionPane.showInputDialog(frame, "Opción 4:");
            
            String correcta = JOptionPane.showInputDialog(frame, "Respuesta correcta:");
            String pista = JOptionPane.showInputDialog(frame, "Pista:");
            
            if (texto != null && op1 != null && op2 != null && op3 != null && 
                op4 != null && correcta != null && pista != null) {
                banco.agregarPregunta(categoria, texto, 
                    new String[]{op1, op2, op3, op4}, correcta, pista);
                JOptionPane.showMessageDialog(frame, "¡Pregunta agregada!");
            }
        });
        
        btnModificar.addActionListener(e -> {
            String categoria = JOptionPane.showInputDialog(frame, 
                "Categoría de la pregunta:");
            if (categoria == null) return;
            
            banco.setCategoria(categoria);
            String indiceStr = JOptionPane.showInputDialog(frame, 
                "Número de pregunta a modificar (1-" + banco.getPreguntas().size() + "):");
            
            if (indiceStr != null) {
                try {
                    int indice = Integer.parseInt(indiceStr) - 1;
                    Pregunta p = banco.getPreguntas().get(indice);
                    
                    String nuevoTexto = JOptionPane.showInputDialog(frame, 
                        "Nuevo texto:", p.getTexto());
                    if (nuevoTexto != null && !nuevoTexto.trim().isEmpty()) {
                        // Aquí iría la lógica de modificación completa
                        JOptionPane.showMessageDialog(frame, "Función de modificación disponible");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Índice inválido");
                }
            }
        });
        
        btnEliminar.addActionListener(e -> {
            String categoria = JOptionPane.showInputDialog(frame, 
                "Categoría de la pregunta:");
            if (categoria == null) return;
            
            banco.setCategoria(categoria);
            String indiceStr = JOptionPane.showInputDialog(frame, 
                "Número de pregunta a eliminar (1-" + banco.getPreguntas().size() + "):");
            
            if (indiceStr != null) {
                try {
                    int indice = Integer.parseInt(indiceStr) - 1;
                    banco.eliminarPregunta(indice);
                    JOptionPane.showMessageDialog(frame, "¡Pregunta eliminada!");
                    modeloLista.clear();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error al eliminar");
                }
            }
        });
        
        btnVolver.addActionListener(e -> GUI_inicio());
        
        panelBotones.add(btnVer);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);
        
        panel.add(panelCentro, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.EAST);
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
    
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
        
        JButton btnContinuar = new JButton("CONTINUAR");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 14));
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setBackground(new Color(46, 204, 113));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFocusPainted(false);
        btnContinuar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor ingresa tu nombre", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                jugador = new Jugador(nombre);
                GUI_seleccionCategoria();
            }
        });
        
        panel.add(btnContinuar);
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
    
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
        
        for (String cat : categorias) {
            JButton btnCategoria = new JButton(cat);
            btnCategoria.setFont(new Font("Arial", Font.BOLD, 14));
            btnCategoria.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCategoria.setMaximumSize(new Dimension(200, 40));
            btnCategoria.setBackground(new Color(44, 62, 80));
            btnCategoria.setForeground(Color.WHITE);
            btnCategoria.setFocusPainted(false);
            btnCategoria.addActionListener(e -> {
                banco.setCategoria(cat);
                indicePreguntaActual = 0;
                pistaUsada = false;
                GUI_pregunta();
            });
            
            panel.add(btnCategoria);
            panel.add(Box.createRigidArea(new Dimension(0, 15)));
        }
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
    
    private void GUI_pregunta() {
        if (indicePreguntaActual >= banco.getPreguntas().size()) {
            if (temporizador != null) temporizador.stop();
            GUI_resultado();
            return;
        }
        
        frame.getContentPane().removeAll();
        
        Pregunta pregunta = banco.getPreguntas().get(indicePreguntaActual);
        pistaUsada = false;
        pausado = false;
        tiempoRestante = 30;
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(52, 152, 219));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Panel superior con temporizador, progreso y puntos
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelSuperior.setBackground(new Color(52, 152, 219));
        panelSuperior.setMaximumSize(new Dimension(500, 40));
        
        // Temporizador
        JLabel lblTiempo = new JLabel("⏱ Tiempo: 30");
        lblTiempo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTiempo.setForeground(new Color(255, 235, 59));
        
        JLabel lblProgreso = new JLabel("Pregunta " + (indicePreguntaActual + 1) + " de " + banco.getPreguntas().size());
        lblProgreso.setFont(new Font("Arial", Font.BOLD, 14));
        lblProgreso.setForeground(new Color(255, 235, 59));
        
        JLabel lblPuntos = new JLabel("Puntos: " + jugador.getPuntos());
        lblPuntos.setFont(new Font("Arial", Font.BOLD, 18));
        lblPuntos.setForeground(Color.WHITE);
        
        panelSuperior.add(lblTiempo);
        panelSuperior.add(lblProgreso);
        panelSuperior.add(lblPuntos);
        
        panel.add(panelSuperior);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Panel para la pregunta
        JPanel panelPregunta = new JPanel();
        panelPregunta.setBackground(new Color(41, 128, 185));
        panelPregunta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panelPregunta.setMaximumSize(new Dimension(400, 120));
        
        JLabel lblPregunta = new JLabel("<html><div style='text-align: center; width: 350px;'>" + 
            pregunta.getTexto() + "</div></html>");
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        lblPregunta.setForeground(Color.WHITE);
        panelPregunta.add(lblPregunta);
        
        panel.add(panelPregunta);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Panel de botones de control
        JPanel panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelControl.setBackground(new Color(52, 152, 219));
        panelControl.setMaximumSize(new Dimension(450, 40));
        
        // Botón de PISTA
        JButton btnPista = new JButton("💡 PISTA (-5 pts)");
        btnPista.setFont(new Font("Arial", Font.BOLD, 11));
        btnPista.setBackground(new Color(241, 196, 15));
        btnPista.setForeground(Color.BLACK);
        btnPista.setFocusPainted(false);
        btnPista.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnPista.addActionListener(e -> {
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
            
            jugador.restarPuntos(5);
            pistaUsada = true;
            String pista = pregunta.getPista();
            
            JOptionPane.showMessageDialog(frame, 
                "PISTA:\n" + pista + "\n\n-5 puntos", 
                "💡 Pista", JOptionPane.INFORMATION_MESSAGE);
            
            lblPuntos.setText("Puntos: " + jugador.getPuntos());
            btnPista.setEnabled(false);
            btnPista.setText("💡 USADA");
            btnPista.setBackground(Color.GRAY);
        });
        
        // Botón de PAUSA
        JButton btnPausa = new JButton("⏸ PAUSA (-5 pts)");
        btnPausa.setFont(new Font("Arial", Font.BOLD, 11));
        btnPausa.setBackground(new Color(52, 73, 94));
        btnPausa.setForeground(Color.WHITE);
        btnPausa.setFocusPainted(false);
        btnPausa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnPausa.addActionListener(e -> {
            if (!pausado) {
                if (jugador.getPuntos() < 5) {
                    JOptionPane.showMessageDialog(frame, 
                        "No tienes suficientes puntos\nNecesitas al menos 5 puntos", 
                        "Puntos insuficientes", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Pausar
                pausado = true;
                temporizador.stop();
                jugador.restarPuntos(5);
                lblPuntos.setText("Puntos: " + jugador.getPuntos());
                btnPausa.setText("▶ REANUDAR");
                btnPausa.setBackground(new Color(46, 204, 113));
                
                JOptionPane.showMessageDialog(frame, 
                    "Juego pausado\n-5 puntos", 
                    "Pausa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Reanudar
                pausado = false;
                temporizador.start();
                btnPausa.setText("⏸ PAUSA (-5 pts)");
                btnPausa.setBackground(new Color(52, 73, 94));
            }
        });
        
        // Botón de SALIR
        JButton btnSalir = new JButton("❌ SALIR");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 11));
        btnSalir.setBackground(new Color(231, 76, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnSalir.addActionListener(e -> {
            if (temporizador != null) temporizador.stop();
            
            int confirmacion = JOptionPane.showConfirmDialog(frame, 
                "¿Estás seguro de que quieres salir de la partida?", 
                "Salir de la partida", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                System.out.println("=================================");
                System.out.println("El jugador " + jugador.getNombre() + " salió de la partida");
                System.out.println("Puntos obtenidos: " + jugador.getPuntos());
                System.out.println("=================================");
                
                JOptionPane.showMessageDialog(frame, 
                    "Saliste de la partida\n\nJugador: " + jugador.getNombre() + 
                    "\nPuntos: " + jugador.getPuntos(), 
                    "Partida Abandonada", JOptionPane.INFORMATION_MESSAGE);
                
                GUI_inicio();
            } else {
                if (pausado) {
                    // Si estaba pausado, no reiniciar el temporizador
                } else {
                    temporizador.start();
                }
            }
        });
        
        panelControl.add(btnPista);
        panelControl.add(btnPausa);
        panelControl.add(btnSalir);
        
        panel.add(panelControl);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Opciones de respuesta
        ArrayList<String> opciones = pregunta.getOpciones();
        ArrayList<JButton> botonesOpciones = new ArrayList<>();
        
        for (int i = 0; i < opciones.size(); i++) {
            String opcion = opciones.get(i);
            JButton btnOpcion = new JButton((char)('A' + i) + ") " + opcion);
            btnOpcion.setFont(new Font("Arial", Font.PLAIN, 14));
            btnOpcion.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnOpcion.setMaximumSize(new Dimension(380, 45));
            btnOpcion.setBackground(Color.WHITE);
            btnOpcion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            btnOpcion.setFocusPainted(false);
            btnOpcion.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            // Efecto hover
            btnOpcion.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    if (btnOpcion.isEnabled()) {
                        btnOpcion.setBackground(new Color(46, 204, 113));
                        btnOpcion.setForeground(Color.WHITE);
                    }
                }
                public void mouseExited(MouseEvent e) {
                    if (btnOpcion.isEnabled()) {
                        btnOpcion.setBackground(Color.WHITE);
                        btnOpcion.setForeground(Color.BLACK);
                    }
                }
            });
            
            btnOpcion.addActionListener(e -> {
                temporizador.stop();
                
                for (JButton btn : botonesOpciones) {
                    btn.setEnabled(false);
                }
                
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
            });
            
            botonesOpciones.add(btnOpcion);
            panel.add(btnOpcion);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        
        // Iniciar temporizador
        temporizador = new Timer(1000, e -> {
            if (!pausado) {
                tiempoRestante--;
                lblTiempo.setText("⏱ Tiempo: " + tiempoRestante);
                
                // Cambiar color según tiempo restante
                if (tiempoRestante <= 10) {
                    lblTiempo.setForeground(new Color(231, 76, 60));
                } else if (tiempoRestante <= 20) {
                    lblTiempo.setForeground(new Color(241, 196, 15));
                }
                
                if (tiempoRestante <= 0) {
                    temporizador.stop();
                    
                    for (JButton btn : botonesOpciones) {
                        btn.setEnabled(false);
                    }
                    
                    JOptionPane.showMessageDialog(frame, 
                        "¡Se acabó el tiempo!\n\nLa respuesta correcta era:\n" + pregunta.getRespuestaCorrecta(), 
                        "Tiempo Agotado", JOptionPane.WARNING_MESSAGE);
                    
                    indicePreguntaActual++;
                    GUI_pregunta();
                }
            }
        });
        
        temporizador.start();
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
    
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
        
        JButton btnReintentar = new JButton("JUGAR DE NUEVO");
        btnReintentar.setFont(new Font("Arial", Font.BOLD, 14));
        btnReintentar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReintentar.setBackground(new Color(52, 152, 219));
        btnReintentar.setForeground(Color.WHITE);
        btnReintentar.setFocusPainted(false);
        btnReintentar.addActionListener(e -> {
            jugador = null;
            GUI_inicio();
        });
        
        panel.add(btnReintentar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setBackground(new Color(231, 76, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(e -> {
            System.out.println("Gracias por jugar. ¡Hasta pronto!");
            System.exit(0);
        });
        
        panel.add(btnSalir);
        
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
}

class Jugador {
    private String nombre;
    private int puntos;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getPuntos() {
        return puntos;
    }
    
    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }
    
    public void restarPuntos(int puntos) {
        this.puntos -= puntos;
        if (this.puntos < 0) {
            this.puntos = 0;
        }
    }
}

class Pregunta {
    private String texto;
    private ArrayList<String> opciones;
    private String respuestaCorrecta;
    private String pista;
    
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
    
    public boolean verificarRespuesta(String respuesta) {
        return respuesta.equals(respuestaCorrecta);
    }
}


class BancoPregunta {
    private ArrayList<Pregunta> preguntas;
    
    public BancoPregunta() {
        preguntas = new ArrayList<>();
    }
    
    public void setCategoria(String categoria) {
        preguntas.clear();
        
        switch (categoria) {
            case "Ciencia":
                preguntas.add(new Pregunta(
                    "¿Cuál es el planeta más cercano al Sol?",
                    new String[]{"Venus", "Mercurio", "Marte", "Tierra"},
                    "Mercurio",
                    "Es el planeta más pequeño del sistema solar"
                ));
                preguntas.add(new Pregunta(
                    "¿Qué gas es más abundante en la atmósfera?",
                    new String[]{"Oxígeno", "Nitrógeno", "Dióxido de carbono", "Hidrógeno"},
                    "Nitrógeno",
                    "Representa aproximadamente el 78% del aire"
                ));
                preguntas.add(new Pregunta(
                    "¿Cuántos huesos tiene el cuerpo humano adulto?",
                    new String[]{"206", "195", "215", "180"},
                    "206",
                    "Los bebés nacen con más huesos que se fusionan"
                ));
                preguntas.add(new Pregunta(
                    "¿Cuál es la velocidad de la luz?",
                    new String[]{"300,000 km/s", "150,000 km/s", "500,000 km/s", "200,000 km/s"},
                    "300,000 km/s",
                    "Es la velocidad máxima en el universo"
                ));
                preguntas.add(new Pregunta(
                    "¿Qué órgano del cuerpo humano consume más energía?",
                    new String[]{"Cerebro", "Corazón", "Hígado", "Pulmones"},
                    "Cerebro",
                    "Consume el 20% de la energía del cuerpo"
                ));
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
    
    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }
    
    public void agregarPregunta(String categoria, String texto, String[] opciones, 
                                String respuestaCorrecta, String pista) {
        setCategoria(categoria);
        preguntas.add(new Pregunta(texto, opciones, respuestaCorrecta, pista));
    }
    
    public void eliminarPregunta(int indice) {
        if (indice >= 0 && indice < preguntas.size()) {
            preguntas.remove(indice);
        }
    }
}

class Administrador {
    private String usuario;
    private String password;
    
    public Administrador(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    
    public boolean autenticar(String usuario, String password) {
        return this.usuario.equals(usuario) && this.password.equals(password);
    }
    
    public String getUsuario() {
        return usuario;
    }
}