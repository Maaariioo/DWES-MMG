package goya.daw2;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Persona {

	@NotEmpty(message = "El nombre no puede estar vacio")
	String nombre;
	@Min(value = 1, message = "La edad debe ser mayor a 0")
	int edad;
	@NotEmpty(message = "Selecciona tu nivel de estudios")
	String estudios;
	@NotEmpty(message = "Selecciona tus aficiones")
	String aficiones[];
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getEstudios() {
		return estudios;
	}
	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}
	public String[] getAficiones() {
		return aficiones;
	}
	public void setAficiones(String[] aficiones) {
		this.aficiones = aficiones;
	}
	
}
