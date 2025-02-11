package goya.daw2;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @GetMapping
    public List<Videojuego> obtenerTodos() {
        return (List<Videojuego>) videojuegoRepository.findAll();
    }

    // Buscar por id o por nombre, dependiendo de lo que se pase
    @GetMapping("/{valor}")
    public ResponseEntity<Videojuego> obtenerPorIdONombre(@PathVariable String valor) {
        try {
            // Intentar convertir el valor en Long para buscar por ID
            Long id = Long.parseLong(valor);
            Optional<Videojuego> videojuego = videojuegoRepository.findById(id);
            return videojuego.map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound().build());
        } catch (NumberFormatException e) {
            // Si no es un número válido, buscar por nombre
            Optional<Videojuego> videojuego = videojuegoRepository.findByNombreIgnoreCase(valor);
            return videojuego.map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound().build());
        }
    }

    @PostMapping
    public ResponseEntity<Videojuego> crearVideojuego(@RequestBody Videojuego videojuego) {
        if (videojuego.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Videojuego nuevoVideojuego = videojuegoRepository.save(videojuego);
        return ResponseEntity.ok(nuevoVideojuego);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVideojuego(@PathVariable Long id) {
        if (!videojuegoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        videojuegoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
