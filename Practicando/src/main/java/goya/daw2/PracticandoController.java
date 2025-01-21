package goya.daw2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PracticandoController {

    @GetMapping("/")
    public String mostrarFormulario(Persona persona) {
        return "index";
    }

    @PostMapping("/registrar")
    public String enviarFormulario(@Valid Persona persona, BindingResult resultado, Model model) {
        if (resultado.hasErrors()) {
            return "index";
        }
        model.addAttribute("persona", persona);
        return "enviado";
    }

    @GetMapping("/quiz")
    public String inicioQuiz(HttpSession session, Model model) {
        // Inicializa las preguntas
        List<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta("¿Cuál es la capital de Francia?", new String[]{"París", "Londres", "Madrid"}, "París"));
        preguntas.add(new Pregunta("¿Cuánto es 2+2?", new String[]{"3", "4", "5"}, "4"));
        preguntas.add(new Pregunta("¿Cuál es el océano más grande?", new String[]{"Atlántico", "Índico", "Pacífico"}, "Pacífico"));

        // Guarda preguntas y el índice en la sesión
        session.setAttribute("preguntas", preguntas);
        session.setAttribute("indice", 0);
        session.setAttribute("puntuacion", 0);

        // Carga la primera pregunta
        model.addAttribute("pregunta", preguntas.get(0));
        return "quiz";
    }

    @PostMapping("/responder")
    public String responder(HttpServletRequest request, HttpSession session, Model model) {
    	
        String respuesta = request.getParameter("respuesta");
        
        List<Pregunta> preguntas = (List<Pregunta>) session.getAttribute("preguntas");
        Integer indice = (Integer) session.getAttribute("indice");
        Integer puntuacion = (Integer) session.getAttribute("puntuacion");

        // Validaciones de null
        if (preguntas == null || indice == null || puntuacion == null) {
            // Si algún valor es nulo, redirige al inicio del quiz
            return "redirect:/quiz";
        }

        // Valida la respuesta
        if (respuesta != null && indice < preguntas.size()) {
        	
            Pregunta preguntaActual = preguntas.get(indice);
            
            if (preguntaActual.getRespuestaCorrecta().equals(respuesta)) {
                puntuacion++;
            }
        }

        // Incrementa el índice
        indice++;
        session.setAttribute("indice", indice);
        session.setAttribute("puntuacion", puntuacion);

        if (indice >= preguntas.size()) {
            // Quiz finalizado
            model.addAttribute("puntuacion", puntuacion);
            model.addAttribute("total", preguntas.size());
            return "resultado";
        }

        // Carga la siguiente pregunta
        model.addAttribute("pregunta", preguntas.get(indice));
        return "quiz";
    }
}
