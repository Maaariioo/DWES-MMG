package goya.daw2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import goya.daw2.model.Puntuacion;

import java.util.List;

@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {
    List<Puntuacion> findByJugadorIdOrderByFechaDesc(Long jugadorId);
}
