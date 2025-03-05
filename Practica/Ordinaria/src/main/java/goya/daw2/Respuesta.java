package goya.daw2;

import jakarta.validation.constraints.NotBlank;

public class Respuesta {

    @NotBlank(message = "Debes seleccionar una opción antes de continuar")
    private String pregunta1; 

    public String getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }
}
