package goya.daw2;

import goya.daw2.Jugador;
import goya.daw2.Puntuacion;
import goya.daw2.JugadorService;
import goya.daw2.PuntuacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class QuizController {

    private static final String[][] preguntas = {
        {"¿Qué prefieres?", "Coraje", "Gryffindor", "Ambición", "Slytherin", "Sabiduría", "Ravenclaw", "Lealtad", "Hufflepuff"},
        {"¿Qué animal te representa?", "León", "Gryffindor", "Serpiente", "Slytherin", "Águila", "Ravenclaw", "Tejón", "Hufflepuff"},
        {"¿Qué cualidad valoras más?", "Valentía", "Gryffindor", "Astucia", "Slytherin", "Inteligencia", "Ravenclaw", "Justicia", "Hufflepuff"},
        {"¿Cuál es tu clase favorita?", "Defensa contra las Artes Oscuras", "Gryffindor", "Pociones", "Slytherin", "Encantamientos", "Ravenclaw", "Herbología", "Hufflepuff"},
        {"¿Qué lugar prefieres?", "El Bosque Prohibido", "Gryffindor", "Las Mazmorras", "Slytherin", "La Biblioteca", "Ravenclaw", "La Cocina", "Hufflepuff"},
        {"¿Qué buscarías en una varita?", "Poder", "Slytherin", "Fuerza", "Gryffindor", "Sabiduría", "Ravenclaw", "Estabilidad", "Hufflepuff"},
        {"¿Cuál es tu meta en la vida?", "Hacer el bien", "Hufflepuff", "Ser recordado", "Gryffindor", "Tener éxito", "Slytherin", "Entender el mundo", "Ravenclaw"}
    };

    private final String[] casas = {"Gryffindor", "Slytherin", "Ravenclaw", "Hufflepuff"};

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private PuntuacionService puntuacionService;

    @GetMapping("/preguntas")
    public String mostrarPregunta(@RequestParam(value = "nombre", required = false) String nombre, HttpSession sesion, Model modelo) {
        if (sesion.getAttribute("indicePregunta") == null) {
            sesion.setAttribute("indicePregunta", 0);
            sesion.setAttribute("puntos", new int[4]);

            if (nombre != null && !nombre.isEmpty()) {
                sesion.setAttribute("nombre", nombre);
            }
        }

        int indice = (int) sesion.getAttribute("indicePregunta");
        if (indice >= preguntas.length) {
            return "redirect:/resultado";
        }

        modelo.addAttribute("pregunta", preguntas[indice]);
        return "quiz";
    }

    @PostMapping("/preguntas/siguiente")
    public String siguientePregunta(@RequestParam("respuesta") String respuesta, HttpSession sesion) {
        int indice = (int) sesion.getAttribute("indicePregunta");
        int[] puntos = (int[]) sesion.getAttribute("puntos");

        switch (respuesta) {
            case "Coraje":
            case "Gryffindor":
                puntos[0]++;
                break;
            case "Ambición":
            case "Slytherin":
                puntos[1]++;
                break;
            case "Sabiduría":
            case "Ravenclaw":
                puntos[2]++;
                break;
            case "Lealtad":
            case "Hufflepuff":
                puntos[3]++;
                break;
        }

        sesion.setAttribute("puntos", puntos);
        indice++;
        sesion.setAttribute("indicePregunta", indice);

        if (indice >= preguntas.length) {
            return "redirect:/resultado";
        }
        return "redirect:/preguntas";
    }

    @GetMapping("/resultado")
    public String mostrarResultado(HttpSession sesion, Model modelo) {
        int[] puntos = (int[]) sesion.getAttribute("puntos");

        if (puntos == null) {
            puntos = new int[4];
        }

        int max = 0;
        int casaIndex = 0;
        for (int i = 0; i < puntos.length; i++) {
            if (puntos[i] > max) {
                max = puntos[i];
                casaIndex = i;
            }
        }

        String casa = casas[casaIndex];
        modelo.addAttribute("casa", casa);
        modelo.addAttribute("descripcion", getDescripcionCasa(casa));

        String nombre = (String) sesion.getAttribute("nombre");
        if (nombre == null || nombre.isEmpty()) {
            nombre = "Usuario Anónimo";
        }

        // Obtener o crear el jugador
        Jugador jugador = jugadorService.obtenerOCrearJugador(nombre);

        // Crear y guardar puntuación
        Puntuacion nuevaPuntuacion = new Puntuacion(jugador, casa, max);
        puntuacionService.guardarPuntuacion(nuevaPuntuacion);

        sesion.removeAttribute("indicePregunta");
        sesion.removeAttribute("puntos");
        sesion.removeAttribute("nombre");

        return "resultado";
    }

    @GetMapping("/puntuaciones")
    public String listarPuntuaciones(@RequestParam(value = "nombre", required = false) String jugadorNombre, Model modelo) {
        if (jugadorNombre != null && !jugadorNombre.trim().isEmpty()) {
            Jugador jugador = jugadorService.obtenerOCrearJugador(jugadorNombre);
            List<Puntuacion> puntuaciones = puntuacionService.obtenerPuntuacionesPorJugador(jugador.getId());

            modelo.addAttribute("puntuaciones", puntuaciones);
            modelo.addAttribute("jugadorNombre", jugador.getNombre());
        } else {
            modelo.addAttribute("mensaje", "Por favor, ingresa un nombre para buscar puntuaciones.");
        }
        return "puntuaciones";
    }

    private String getDescripcionCasa(String casa) {
        switch (casa) {
            case "Gryffindor":
                return "Eres valiente y decidido. Gryffindor es la casa de los valientes de corazón.";
            case "Slytherin":
                return "Tienes ambición y astucia. Slytherin es hogar de líderes natos.";
            case "Ravenclaw":
                return "Eres sabio y creativo. Ravenclaw valora el ingenio y el conocimiento.";
            case "Hufflepuff":
                return "Eres leal y trabajador. Hufflepuff acoge a quienes son justos y fieles.";
            default:
                return "";
        }
    }
}
