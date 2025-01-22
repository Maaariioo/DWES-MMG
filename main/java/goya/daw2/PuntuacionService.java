package goya.daw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuntuacionService {

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    public void guardarPuntuacion(Puntuacion puntuacion) {
        puntuacionRepository.save(puntuacion);
    }

    public List<Puntuacion> obtenerPuntuacionesPorJugador(Long jugadorId) {
        return puntuacionRepository.findByJugadorIdOrderByFechaDesc(jugadorId);
    }
}
