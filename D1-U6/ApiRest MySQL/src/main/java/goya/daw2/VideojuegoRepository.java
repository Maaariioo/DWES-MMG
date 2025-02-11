package goya.daw2;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
    Optional<Videojuego> findByNombreIgnoreCase(String nombre);
}