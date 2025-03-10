package goya.daw2.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "jugadores")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Puntuacion> puntuaciones;

    @Column(nullable = false)
    private String casa;

    public Jugador() {}

    public Jugador(String nombre, String casa) {
        this.nombre = nombre;
        this.casa = casa;
    }

    public Long getId() {
        return id;
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

    public List<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(List<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }
}
