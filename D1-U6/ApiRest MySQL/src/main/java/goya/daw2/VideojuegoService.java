package goya.daw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VideojuegoService {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    public List<Videojuego> findAll() {
        return videojuegoRepository.findAll();
    }

    public Optional<Videojuego> findById(Long id) {
        return videojuegoRepository.findById(id);
    }

    public Optional<Videojuego> findByNombre(String nombre) {
        return videojuegoRepository.findByNombreIgnoreCase(nombre);
    }

    public Videojuego save(Videojuego videojuego) {
        return videojuegoRepository.save(videojuego);
    }

    public void deleteById(Long id) {
        videojuegoRepository.deleteById(id);
    }

    public boolean existsByNombre(String nombre) {
        return videojuegoRepository.findByNombreIgnoreCase(nombre).isPresent();
    }
}