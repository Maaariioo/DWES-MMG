package goya.daw2;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller  // Define la clase como un controlador Spring MVC
public class OrdinariaController {

    // Lista de preguntas predefinidas con opciones y la opción correcta
    private List<Pregunta> preguntas = Arrays.asList(
        new Pregunta("¿Cuál es la capital de Francia?", new String[]{"Londres", "Berlín", "París"}, 2),
        new Pregunta("¿Cuál es el planeta más grande del sistema solar?", new String[]{"Saturno", "Júpiter", "Tierra"}, 1),
        new Pregunta("¿Quién pintó la Mona Lisa?", new String[]{"Leonardo da Vinci", "Picasso", "Van Gogh"}, 0),
        new Pregunta("¿Cuántos continentes hay en la Tierra?", new String[]{"5", "6", "7"}, 2),
        new Pregunta("¿Cuál es el metal más abundante en la corteza terrestre?", new String[]{"Hierro", "Aluminio", "Cobre"}, 1)
    );

    // Método para mostrar la página inicial donde el usuario ingresa su nombre
    @GetMapping("/")
    public String nombreUsuario(Model modelo, HttpSession sesion) {
        modelo.addAttribute("usuario", new Usuario());  // Se añade un objeto Usuario al modelo para el formulario
        sesion.setAttribute("preguntaActual", 0); // Reiniciar el progreso del cuestionario
        return "index"; // Devuelve la vista index.html
    }

    // Método para manejar el formulario donde el usuario ingresa su nombre
    @PostMapping("/preguntas")
    public String mostrarPreguntas(@Valid @ModelAttribute Usuario usuario, BindingResult resultado, HttpSession sesion) {
        if (resultado.hasErrors()) {
            return "index";  // Si hay errores de validación, vuelve a mostrar la página de inicio
        }

        sesion.setAttribute("nombre", usuario.getNombre());  // Guarda el nombre del usuario en la sesión
        sesion.setAttribute("preguntaActual", 0); // Inicializa la primera pregunta

        return "redirect:/preguntas";  // Redirige a la página de preguntas
    }

    // Método para mostrar la pregunta actual
    @GetMapping("/preguntas")
    public String mostrarPregunta(HttpSession sesion, Model modelo) {
        String nombre = (String) sesion.getAttribute("nombre");  // Obtiene el nombre del usuario desde la sesión
        Integer preguntaActual = (Integer) sesion.getAttribute("preguntaActual");  // Obtiene el número de la pregunta actual

        if (nombre == null || preguntaActual == null) {
            return "redirect:/";  // Si no hay usuario o no se ha comenzado el cuestionario, redirige a la página de inicio
        }

        if (preguntaActual >= preguntas.size()) {
            return "redirect:/resultado";  // Si ya se han respondido todas las preguntas, redirige a los resultados
        }

        modelo.addAttribute("nombre", nombre);  // Pasa el nombre del usuario a la vista
        modelo.addAttribute("pregunta", preguntas.get(preguntaActual));  // Pasa la pregunta actual a la vista
        modelo.addAttribute("respuesta", new Respuesta());  // Añade un objeto vacío de Respuesta para el formulario

        return "pregunta";  // Devuelve la vista pregunta.html
    }

    // Método para manejar la respuesta de la pregunta actual y avanzar a la siguiente
    @PostMapping("/siguiente")
    public String siguientePregunta(@Valid @ModelAttribute Respuesta respuesta, BindingResult resultado, HttpSession sesion, Model modelo) {
        if (resultado.hasErrors()) {
            // Si hay errores de validación en la respuesta, vuelve a mostrar la pregunta actual
            Integer preguntaActual = (Integer) sesion.getAttribute("preguntaActual");
            modelo.addAttribute("nombre", sesion.getAttribute("nombre"));
            modelo.addAttribute("pregunta", preguntas.get(preguntaActual));
            return "pregunta";
        }

        Integer preguntaActual = (Integer) sesion.getAttribute("preguntaActual");  // Obtiene la pregunta actual
        sesion.setAttribute("respuesta" + preguntaActual, respuesta.getPregunta1());  // Guarda la respuesta en la sesión
        sesion.setAttribute("preguntaActual", preguntaActual + 1);  // Avanza a la siguiente pregunta

        return "redirect:/preguntas";  // Redirige a la página de la siguiente pregunta
    }

    // Método para mostrar los resultados al finalizar el cuestionario
    @GetMapping("/resultado")
    public String mostrarResultado(HttpSession sesion, Model modelo) {
        String nombre = (String) sesion.getAttribute("nombre");  // Obtiene el nombre del usuario desde la sesión

        if (nombre == null) {
            return "redirect:/";  // Si no hay un nombre registrado, redirige a la página de inicio
        }

        modelo.addAttribute("nombre", nombre);  // Pasa el nombre a la vista
        modelo.addAttribute("preguntas", preguntas);  // Pasa las preguntas a la vista

        String[] respuestas = new String[preguntas.size()];  // Crea un array para almacenar las respuestas
        int puntuacion = 0;  // Inicializa la puntuación

        // Itera sobre las preguntas y verifica las respuestas
        for (int i = 0; i < preguntas.size(); i++) {
            respuestas[i] = (String) sesion.getAttribute("respuesta" + i);  // Recupera la respuesta de la sesión

            // Si la respuesta es correcta (compara con el índice de la respuesta correcta), suma 2 puntos
            if (respuestas[i] != null && respuestas[i].equals(String.valueOf(preguntas.get(i).getCorrecta()))) {
                puntuacion += 2;
            }
        }

        modelo.addAttribute("respuestas", respuestas);  // Pasa las respuestas al modelo
        modelo.addAttribute("puntuacion", puntuacion);  // Pasa la puntuación al modelo

        return "resultado";  // Devuelve la vista resultado.html
    }
}
