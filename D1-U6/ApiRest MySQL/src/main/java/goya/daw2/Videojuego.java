package goya.daw2;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String plataforma;
    private String desarrollador;
    private int anio;

    public Videojuego() {
		super();
	}

	public Videojuego(Long id, String nombre, String plataforma, String desarrollador, int anio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.desarrollador = desarrollador;
		this.anio = anio;
	}

	// Getters y Setters
    
    public Long getId() {
        return id;
    }

    public String getDesarrollador() {
		return desarrollador;
	}

	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    // Equals y HashCode basados en el nombre (ignorando mayúsculas/minúsculas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Videojuego that = (Videojuego) o;
        return Objects.equals(nombre.toLowerCase(), that.nombre.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }
}
