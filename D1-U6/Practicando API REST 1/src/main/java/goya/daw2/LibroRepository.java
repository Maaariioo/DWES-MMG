package goya.daw2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro,Long> {
	 Optional<Libro> findByTituloIgnoreCase(String titulo);
	 
}
