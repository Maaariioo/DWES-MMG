package goya.daw2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goya.daw2.model.Jugador;
import goya.daw2.repositorios.JugadorRepository;

import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public Jugador obtenerOCrearJugador(String nombre, String casa) {
        return jugadorRepository.findByNombre(nombre)
                .orElseGet(() -> jugadorRepository.save(new Jugador(nombre, casa)));
    }
}
