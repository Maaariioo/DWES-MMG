package goya.daw2;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculasController {
	
	@Autowired
	private PeliculaService peliculaS;

	
	@GetMapping
	public ResponseEntity<List<Pelicula>> allPeliculas() {
		
		List<Pelicula> peliculas = peliculaS.obtenerTodas();
		
		return ResponseEntity.ok(peliculas) ;
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pelicula> peliculaPorId(@PathVariable Long id) {
		
		return peliculaS.findbyId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	public ResponseEntity<?> crearPeli(@RequestBody Pelicula pelicula){
		
		if(!peliculaS.yaExiste(pelicula.getTitulo())) {
			
			return ResponseEntity.badRequest().body("Pelicula ya existe");
			
		}
		Pelicula peliculaNueva = peliculaS.save(pelicula);
		return ResponseEntity
				.created(URI.create("/api/peliculas" + peliculaNueva.getId()))
				.body(peliculaNueva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarPeli(@RequestBody Pelicula pelicula, @PathVariable Long id){
		
		if (!peliculaS.findbyId(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		pelicula.setId(id);
		return ResponseEntity.ok(peliculaS.save(pelicula));
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchLibro(@PathVariable Long id,@RequestBody Pelicula pelicula){
	
		Optional<Pelicula> peliculaExiste = peliculaS.findbyId(id);
		if (!peliculaExiste.isPresent()) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		Pelicula peliculaNueva = peliculaExiste.get();
		if(pelicula.getTitulo() != null) peliculaNueva.setTitulo(pelicula.getTitulo());
		if(pelicula.getDirector() != null) peliculaNueva.setDirector(pelicula.getDirector());
		if(pelicula.getAnio() != 0) peliculaNueva.setAnio(pelicula.getAnio());
		if(pelicula.getDuracion() != 0) peliculaNueva.setDuracion(pelicula.getDuracion());
		
		return ResponseEntity.ok(peliculaS.save(peliculaNueva));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarPeli(@PathVariable Long id){
		
		if(!peliculaS.findbyId(id).isPresent()) {
			
			return ResponseEntity.notFound().build();
			
		}
		peliculaS.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
