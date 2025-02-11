package goya.daw2;

import jakarta.persistence.*;

@Entity
@Table(name = "videojuego")
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) 
    private String nombre;

    @Column(nullable = false)
    private String plataforma;

    @Column(nullable = false)
    private String desarrollador;

    @Column(nullable = false)
    private int anio;

    public Videojuego() {}

    public Videojuego(String nombre, String plataforma, String desarrollador, int anio) {
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.desarrollador = desarrollador;
        this.anio = anio;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
    public String getDesarrollador() { return desarrollador; }
    public void setDesarrollador(String desarrollador) { this.desarrollador = desarrollador; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
}
