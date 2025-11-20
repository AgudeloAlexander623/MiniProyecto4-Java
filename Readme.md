# 🎮 JUEGO DE PREGUNTADOS

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

---

## 👨‍💻 INFORMACIÓN DEL AUTOR

**Nombre:** Jessid Alexander Agudelo Herrera  
**Código de Estudiante:** 202478460  
**Institución:** Universidad Del Valle  
**Programa:** Tegnologo En Sistemas  
**Curso:** Programación Orientada a Eventos  
**Fecha:** Noviembre 2025

---

## 📋 DESCRIPCIÓN DEL PROYECTO

Este proyecto es un **Juego de Preguntados** desarrollado completamente en Java utilizando la biblioteca Swing para la interfaz gráfica. El juego está diseñado como un sistema interactivo de preguntas y respuestas que pone a prueba los conocimientos del usuario en diferentes categorías.

### 🎯 Finalidad del Proyecto

Este proyecto fue creado con los siguientes objetivos:

1. **Académico:** Aplicar los conceptos de **Programación Orientada a Objetos (POO)** y **Programación Orientada a Eventos (POE)** aprendidos en el curso.

2. **Práctico:** Desarrollar un sistema completo que integre:
   - Interfaz gráfica de usuario (GUI)
   - Gestión de datos (CRUD)
   - Lógica de negocio
   - Manejo de eventos
   - Sistema de autenticación
   - Temporizadores y controles de juego

3. **Educativo:** Crear una herramienta interactiva que pueda ser utilizada como material de estudio, permitiendo a los usuarios aprender mientras juegan.

4. **Demostrativo:** Evidenciar el dominio de conceptos avanzados de programación como:
   - Encapsulamiento y abstracción
   - Herencia y polimorfismo (en la estructura de clases)
   - Manejo de colecciones (ArrayList)
   - Interfaces gráficas con Swing
   - Manejo de eventos (ActionListener, MouseListener)
   - Temporizadores (Timer)
   - Validación de datos

---

## 🚀 CARACTERÍSTICAS PRINCIPALES

### ✨ Funcionalidades del Juego

- **Sistema de Preguntas por Categorías**
  - Ciencia (6 preguntas)
  - Historia (6 preguntas)
  - Deportes (6 preguntas)
  - Geografía (6 preguntas)
  - Total: 24 preguntas

- **Sistema de Puntuación**
  - Respuesta correcta: +10 puntos
  - Uso de pista: -5 puntos
  - Pausar juego: -5 puntos
  - Puntuación inicial: 0 puntos

- **Sistema de Pistas**
  - Cada pregunta incluye una pista
  - Costo: 5 puntos
  - Solo se puede usar una vez por pregunta
  - Validación de puntos suficientes

- **Temporizador**
  - 30 segundos por pregunta
  - Contador regresivo visual
  - Cambio de color según tiempo restante:
    - Verde/Amarillo: >20 segundos
    - Naranja: 10-20 segundos
    - Rojo: <10 segundos
  - Al agotarse el tiempo, pasa automáticamente a la siguiente pregunta

- **Sistema de Pausa**
  - Pausa el temporizador
  - Costo: 5 puntos
  - Se puede reanudar en cualquier momento

- **Opción de Salir**
  - Disponible en cualquier momento durante el juego
  - Muestra confirmación antes de salir
  - Guarda estadísticas en consola
  - Muestra mensaje con puntos obtenidos

### 🔐 Panel de Administrador

- **Sistema de Autenticación**
  - Login con usuario y contraseña
  - Credenciales por defecto:
    - Usuario: `root`
    - Contraseña: `1234`
  - Máximo 3 intentos
  - Mensaje de error personalizado

- **CRUD Completo de Preguntas**
  - **Create:** Agregar nuevas preguntas
  - **Read:** Ver preguntas por categoría
  - **Update:** Modificar preguntas existentes
  - **Delete:** Eliminar preguntas

- **Crear Nuevo Administrador**
  - Sistema de preguntas de seguridad
  - Requiere responder correctamente 2 de 3 preguntas:
    1. ¿Cómo se llama el creador del juego? (Alexander agudelo)
    2. ¿Cuál es el nombre del perro del creador? (thanos)
    3. ¿Cuál es la edad del creador del juego? (21)
  - Creación de nuevas credenciales de administrador

---

## 🏗️ ARQUITECTURA DEL PROYECTO

### 📦 Estructura de Clases (POO)

El proyecto está organizado siguiendo los principios de la Programación Orientada a Objetos:

```
Main.java
├── JuegoPreguntados (Clase Principal)
│   ├── Atributos:
│   │   - frame: JFrame
│   │   - jugador: Jugador
│   │   - banco: BancoPregunta
│   │   - indicePreguntaActual: int
│   │   - pistaUsada: boolean
│   │   - temporizador: Timer
│   │   - tiempoRestante: int
│   │   - pausado: boolean
│   │   - administradores: ArrayList<Administrador>
│   │
│   └── Métodos:
│       - GUI_inicio()
│       - GUI_loginAdmin()
│       - GUI_crearAdmin()
│       - GUI_panelAdmin()
│       - GUI_ingresoNombre()
│       - GUI_seleccionCategoria()
│       - GUI_pregunta()
│       - GUI_resultado()
│
├── Jugador
│   ├── Atributos:
│   │   - nombre: String
│   │   - puntos: int
│   │
│   └── Métodos:
│       - getNombre(): String
│       - getPuntos(): int
│       - sumarPuntos(int): void
│       - restarPuntos(int): void
│
├── Pregunta
│   ├── Atributos:
│   │   - texto: String
│   │   - opciones: ArrayList<String>
│   │   - respuestaCorrecta: String
│   │   - pista: String
│   │
│   └── Métodos:
│       - getTexto(): String
│       - getOpciones(): ArrayList<String>
│       - getRespuestaCorrecta(): String
│       - getPista(): String
│       - verificarRespuesta(String): boolean
│
├── BancoPregunta
│   ├── Atributos:
│   │   - preguntas: ArrayList<Pregunta>
│   │
│   └── Métodos:
│       - setCategoria(String): void
│       - getPreguntas(): ArrayList<Pregunta>
│       - agregarPregunta(...): void
│       - eliminarPregunta(int): void
│
└── Administrador
    ├── Atributos:
    │   - usuario: String
    │   - password: String
    │
    └── Métodos:
        - autenticar(String, String): boolean
        - getUsuario(): String
```

### 🎯 Programación Orientada a Eventos (POE)

El juego utiliza eventos de Java Swing:

- **ActionListener:** Manejo de clics en botones
- **MouseListener:** Efectos hover en botones
- **Timer:** Temporizador de 30 segundos

---

## 🖥️ VENTANAS DEL JUEGO

El juego cuenta con **8 ventanas principales:**

### 1️⃣ **GUI_inicio()**
- Pantalla de bienvenida
- Muestra información del creador
- Opciones: Iniciar Juego | Administrador

### 2️⃣ **GUI_loginAdmin()**
- Login de administrador
- Inputs para usuario y contraseña
- Sistema de 3 intentos
- Opciones: Entrar | Crear Administrador | Volver

### 3️⃣ **GUI_crearAdmin()**
- Preguntas de seguridad
- Validación de respuestas
- Creación de nuevo administrador

### 4️⃣ **GUI_panelAdmin()**
- CRUD completo de preguntas
- Lista de preguntas con scroll
- Opciones: Ver | Agregar | Modificar | Eliminar | Volver

### 5️⃣ **GUI_ingresoNombre()**
- Captura del nombre del jugador
- Validación de campo no vacío

### 6️⃣ **GUI_seleccionCategoria()**
- Selección de categoría
- Saludo personalizado
- 4 categorías disponibles

### 7️⃣ **GUI_pregunta()**
- Muestra la pregunta actual
- Temporizador de 30 segundos
- Indicador de progreso
- Puntos actuales
- 4 opciones de respuesta
- Controles: Pista | Pausa | Salir

### 8️⃣ **GUI_resultado()**
- Muestra puntuación final
- Nombre del jugador
- Opciones: Jugar de Nuevo | Salir

---

## 💻 REQUISITOS DEL SISTEMA

### Software Necesario

- **Java JDK:** 8 o superior
- **IDE Recomendado:**
  - Eclipse
  - IntelliJ IDEA
  - NetBeans
  - Visual Studio Code (con extensión Java)

### Librerías Utilizadas

- `javax.swing.*` - Interfaz gráfica
- `java.awt.*` - Componentes gráficos
- `java.awt.event.*` - Manejo de eventos
- `java.util.ArrayList` - Gestión de colecciones
- `java.util.Collections` - Operaciones con colecciones
- `javax.swing.Timer` - Temporizador

---

## 🔧 INSTALACIÓN Y EJECUCIÓN

### Paso 1: Descargar el Proyecto
```bash
# Clonar el repositorio o descargar el archivo Main.java
git clone [URL_DEL_REPOSITORIO]
```

### Paso 2: Compilar el Proyecto
```bash
# Navegar a la carpeta del proyecto
cd juego-preguntados

# Compilar el archivo Java
javac Main.java
```

### Paso 3: Ejecutar el Juego
```bash
# Ejecutar la aplicación
java Main
```

### Alternativa: Usar un IDE

1. Abrir el IDE de tu preferencia
2. Crear un nuevo proyecto Java
3. Copiar el contenido de `Main.java`
4. Ejecutar la clase `Main`

---

## 🎮 GUÍA DE USO

### Para Jugadores

1. **Iniciar el Juego**
   - Clic en "INICIAR JUEGO"
   - Ingresar tu nombre
   - Seleccionar una categoría

2. **Responder Preguntas**
   - Leer la pregunta
   - Tienes 30 segundos por pregunta
   - Seleccionar una opción (A, B, C, D)
   - Ganar +10 puntos por respuesta correcta

3. **Usar Ayudas**
   - **Pista:** Clic en "💡 PISTA" (costo: -5 puntos)
   - **Pausa:** Clic en "⏸ PAUSA" (costo: -5 puntos)
   - **Salir:** Clic en "❌ SALIR" (en cualquier momento)

4. **Ver Resultados**
   - Al finalizar las 6 preguntas
   - Se muestra la puntuación final
   - Opción de jugar nuevamente

### Para Administradores

1. **Acceder al Panel**
   - Clic en "👤 ADMINISTRADOR"
   - Ingresar credenciales:
     - Usuario: `root`
     - Contraseña: `1234`

2. **Gestionar Preguntas**
   - **Ver:** Seleccionar categoría y visualizar preguntas
   - **Agregar:** Ingresar nueva pregunta con opciones y pista
   - **Modificar:** Editar pregunta existente
   - **Eliminar:** Borrar pregunta por número

3. **Crear Administrador**
   - Clic en "CREAR ADMINISTRADOR"
   - Responder preguntas de seguridad
   - Ingresar nuevo usuario y contraseña

---

## 📊 SISTEMA DE PUNTUACIÓN

| Acción | Puntos |
|--------|--------|
| 🎯 Respuesta Correcta | +10 |
| 💡 Usar Pista | -5 |
| ⏸ Pausar Juego | -5 |
| ⏱ Tiempo Agotado | 0 |
| ❌ Respuesta Incorrecta | 0 |

**Puntuación Máxima Posible:** 60 puntos (6 preguntas × 10 puntos)

---

## 🎨 PALETA DE COLORES

El juego utiliza una paleta de colores moderna y atractiva:

- **Azul Principal:** `#3498db` (RGB: 52, 152, 219)
- **Verde Éxito:** `#2ecc71` (RGB: 46, 204, 113)
- **Rojo Error:** `#e74c3c` (RGB: 231, 76, 60)
- **Amarillo Advertencia:** `#f1c40f` (RGB: 241, 196, 15)
- **Morado Secundario:** `#9b59b6` (RGB: 155, 89, 182)
- **Gris Neutral:** `#95a5a6` (RGB: 149, 165, 166)
- **Azul Oscuro:** `#2c3e50` (RGB: 44, 62, 80)

---

## 📖 CONCEPTOS DE PROGRAMACIÓN APLICADOS

### Programación Orientada a Objetos (POO)

1. **Encapsulamiento**
   - Atributos privados (`private`)
   - Métodos públicos (`public`)
   - Getters y Setters

2. **Abstracción**
   - Clases que representan entidades del mundo real
   - Métodos que ocultan la complejidad

3. **Composición**
   - `JuegoPreguntados` contiene `Jugador`
   - `BancoPregunta` contiene `ArrayList<Pregunta>`

### Programación Orientada a Eventos (POE)

1. **ActionListener**
   - Eventos de clic en botones
   - Lambda expressions: `e -> método()`

2. **MouseListener**
   - Efectos hover (mouseEntered/mouseExited)
   - Cambio de colores en tiempo real

3. **Timer**
   - Temporizador de 30 segundos
   - Actualización cada 1000ms

### Estructuras de Datos

1. **ArrayList**
   - Gestión dinámica de preguntas
   - Gestión de opciones
   - Lista de administradores

2. **Collections**
   - `Collections.shuffle()` para randomizar opciones

---

## 🐛 MANEJO DE ERRORES

El sistema incluye validaciones para:

- ✅ Campos vacíos (nombre, usuario, contraseña)
- ✅ Puntos insuficientes (pista, pausa)
- ✅ Intentos de login agotados (3 máximo)
- ✅ Índices inválidos (CRUD)
- ✅ Categorías inexistentes
- ✅ Respuestas de seguridad incorrectas

---

## 📝 DOCUMENTACIÓN DEL CÓDIGO

Cada clase y método está documentado con comentarios JavaDoc:

```java
/**
 * Descripción de la clase
 * @author Jessid Alexander Agudelo Herrera
 * @version 1.0
 */
```

---

## 🚀 FUTURAS MEJORAS

Posibles mejoras para versiones futuras:

- [ ] Base de datos para persistencia de preguntas
- [ ] Múltiples niveles de dificultad
- [ ] Sistema de ranking y leaderboard
- [ ] Modo multijugador
- [ ] Sonidos y efectos visuales
- [ ] Más categorías de preguntas
- [ ] Estadísticas detalladas del jugador
- [ ] Exportar/Importar preguntas (JSON/XML)
- [ ] Traducción a múltiples idiomas
- [ ] Modo oscuro

---

## 📄 LICENCIA

Este proyecto fue desarrollado con fines académicos y educativos.

**© 2025 Jessid Alexander Agudelo Herrera**  
Todos los derechos reservados.

---

## 📞 CONTACTO

**Estudiante:** Jessid Alexander Agudelo Herrera  
**Código:** 202478460  
**Email:** [tu-email@universidad.edu]  
**GitHub:** [tu-usuario-github]

---

## 🙏 AGRADECIMIENTOS

- A los profesores del curso de Programación Orientada a Objetos
- A la comunidad de Java y Swing
- A todos los que contribuyeron con ideas y feedback

---

## 📚 REFERENCIAS

- [Documentación de Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Java API Documentation](https://docs.oracle.com/en/java/javase/)
- [Buenas prácticas de POO](https://refactoring.guru/es/design-patterns)

---

<div align="center">

**⭐ Si te gustó este proyecto, no olvides darle una estrella ⭐**

**Made with ❤️ by Jessid Alexander Agudelo Herrera**

</div>