package goya.daw2;

import jakarta.validation.constraints.NotBlank;

public class Usuario {

    @NotBlank(message = "Debes poner tu nombre")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
