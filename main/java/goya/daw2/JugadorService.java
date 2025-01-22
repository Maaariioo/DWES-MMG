package goya.daw2;

import goya.daw2.Jugador;
import goya.daw2.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public Jugador obtenerOCrearJugador(String nombre) {
        return jugadorRepository.findByNombre(nombre)
                .orElseGet(() -> jugadorRepository.save(new Jugador(nombre)));
    }
}
