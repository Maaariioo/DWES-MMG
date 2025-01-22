package goya.daw2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goya.daw2.model.Puntuacion;
import goya.daw2.repositorios.PuntuacionRepository;

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
