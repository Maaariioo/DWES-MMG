package goya.daw2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "videojuegos", path = "videojuegos")
public interface VideojuegoRepository extends CrudRepository<Videojuego, Long> {
    Optional<Videojuego> findByNombreIgnoreCase(String nombre);
}
