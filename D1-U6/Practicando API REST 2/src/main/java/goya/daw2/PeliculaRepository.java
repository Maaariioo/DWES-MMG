package goya.daw2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
	Optional<Pelicula>findByTituloIgnoreCase(String titulo);
}
