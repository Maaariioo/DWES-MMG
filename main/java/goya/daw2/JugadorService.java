package goya.daw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
