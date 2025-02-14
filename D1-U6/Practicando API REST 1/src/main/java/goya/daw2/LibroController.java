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
@RequestMapping("/api/libros")
public class LibroController {

	@Autowired
	private LibroService libroService;

	@GetMapping
	public ResponseEntity<List<Libro>> getAllLibros() {
		List<Libro> libros = libroService.findAll();
		return ResponseEntity.ok(libros);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Libro> getLibroById(@PathVariable Long id){
		
		return libroService.findbyId(id)
				//si esta te lo devuelve ok si no not found + build para que pueda estar vacio
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	public ResponseEntity<?> crearLibro(@RequestBody Libro libro){
		
		//si el libro que quiere meter el usuario existe ya el titulo te devuelve badRequest
		if (libroService.existsbyNombre(libro.getTitulo())) {
			
			return ResponseEntity.badRequest().body("El libro ya existe");
		}
		//si no parsea los datos del curl y te devuelve en consola el libro creado
		Libro libroGuardado = libroService.save(libro);
		return ResponseEntity.created(URI.create("/api/libros" + libroGuardado.getId()))
				.body(libroGuardado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro){
		
		if (!libroService.findbyId(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		libro.setId(id);
		return ResponseEntity.ok(libroService.save(libro));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchLibro(@PathVariable Long id, @RequestBody Libro libro){
		Optional<Libro> libroExistente = libroService.findbyId(id);
		if(!libroExistente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Libro libroActualizado = libroExistente.get();
		if(libro.getTitulo() != null) libroActualizado.setTitulo(libro.getTitulo());
		if(libro.getAutor() != null) libroActualizado.setAutor(libro.getAutor());
		if(libro.getAnio_publicacion() != 0) libroActualizado.setAnio_publicacion(libro.getAnio_publicacion());
		
		return ResponseEntity.ok(libroService.save(libroActualizado));
				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarLibro(@PathVariable Long id){
		
		if(!libroService.findbyId(id).isPresent()) {
			
			return ResponseEntity.notFound().build();
		}
		libroService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
}
