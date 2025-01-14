package goya.daw2;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

public class Usuario {

    @NotEmpty(message = "El nombre no puede estar vacío.")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres.")
    private String nombre;

    @NotNull
    private Map<String, Integer> puntuaciones;

    public Usuario() {
        this.puntuaciones = new HashMap<>();
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.puntuaciones = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, Integer> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(Map<String, Integer> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public void sumarPuntos(String categoria, int puntos) {
        this.puntuaciones.put(categoria, this.puntuaciones.getOrDefault(categoria, 0) + puntos);
    }

    public String obtenerClasificacion() {
        return this.puntuaciones.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Sin clasificación");
    }
}