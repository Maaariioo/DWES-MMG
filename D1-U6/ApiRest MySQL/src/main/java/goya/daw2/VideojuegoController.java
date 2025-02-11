package goya.daw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoService videojuegoService;

    @GetMapping
    public ResponseEntity<List<Videojuego>> getAllVideojuegos() {
        List<Videojuego> videojuegos = videojuegoService.findAll();
        return ResponseEntity.ok(videojuegos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> getVideojuegoById(@PathVariable Long id) {
        return videojuegoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createVideojuego(@RequestBody Videojuego videojuego) {
        if (videojuegoService.existsByNombre(videojuego.getNombre())) {
            return ResponseEntity.badRequest().body("Error: El videojuego ya existe.");
        }
        Videojuego savedVideojuego = videojuegoService.save(videojuego);
        return ResponseEntity.created(URI.create("/api/videojuegos/" + savedVideojuego.getId()))
                .body(savedVideojuego);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVideojuego(@PathVariable Long id, @RequestBody Videojuego videojuego) {
        if (!videojuegoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        videojuego.setId(id);
        return ResponseEntity.ok(videojuegoService.save(videojuego));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchVideojuego(@PathVariable Long id, @RequestBody Videojuego videojuego) {
        Optional<Videojuego> existingVideojuego = videojuegoService.findById(id);
        if (!existingVideojuego.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Videojuego updatedVideojuego = existingVideojuego.get();
        if (videojuego.getNombre() != null) updatedVideojuego.setNombre(videojuego.getNombre());
        if (videojuego.getPlataforma() != null) updatedVideojuego.setPlataforma(videojuego.getPlataforma());
        if (videojuego.getAnio() != 0) updatedVideojuego.setAnio(videojuego.getAnio());

        return ResponseEntity.ok(videojuegoService.save(updatedVideojuego));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideojuego(@PathVariable Long id) {
        if (!videojuegoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        videojuegoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}