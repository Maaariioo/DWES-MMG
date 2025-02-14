package goya.daw2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

	Optional<Restaurante>findByNombreIgnoreCase(String nombre);
}
