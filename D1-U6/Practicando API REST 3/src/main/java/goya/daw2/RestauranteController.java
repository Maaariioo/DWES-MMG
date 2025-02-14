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
@RequestMapping("/api/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService restauranteS;

	@GetMapping
	public ResponseEntity<List<Restaurante>> todosRestaurantes(){
		
		List<Restaurante> restaurantes = restauranteS.todos();
		return ResponseEntity.ok(restaurantes);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> restaurantePorId(@PathVariable Long id){
		
		return restauranteS.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	public ResponseEntity<?>crearRestaurante(@RequestBody Restaurante restaurante) {
		
		if(restauranteS.existe(restaurante.getNombre())) {
			
			return ResponseEntity.badRequest().body("Restaurante ya existe");
			
		}
		
		Restaurante nuevoRestaurante = restauranteS.save(restaurante);
		
		return ResponseEntity.created(URI.create("/api/restaurantes" + nuevoRestaurante.getId())).body(nuevoRestaurante);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarRestaurante(@RequestBody Restaurante restaurante, @PathVariable Long id){
		
	if(!restauranteS.findById(id).isPresent()) {
			
			return ResponseEntity.notFound().build();
			
		}
	
	restaurante.setId(id);
	return ResponseEntity.ok(restauranteS.save(restaurante));
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> patchRestaurante(@PathVariable Long id, @RequestBody Restaurante restaurante){
		
		Optional<Restaurante> restauranteExistente = restauranteS.findById(id);
		if(!restauranteExistente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Restaurante nuevoRestaurante = restauranteExistente.get();
		
		if (restaurante.getNombre() != null) nuevoRestaurante.setNombre(restaurante.getNombre());
		if (restaurante.getCiudad() != null) nuevoRestaurante.setCiudad(restaurante.getCiudad());
		if (restaurante.getDireccion() != null) nuevoRestaurante.setDireccion(restaurante.getDireccion());
		if (restaurante.getTipo_comida() != null) nuevoRestaurante.setTipo_comida(restaurante.getTipo_comida());
		if (restaurante.getCapacidad() != 0) nuevoRestaurante.setCapacidad(restaurante.getCapacidad());
		if (restaurante.getCalificacion() != 0) nuevoRestaurante.setCalificacion(restaurante.getCalificacion());
		
		return ResponseEntity.ok(restauranteS.save(nuevoRestaurante));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarRestaurante(@PathVariable Long id){
		
		if(!restauranteS.findById(id).isPresent()) {
			
			return ResponseEntity.notFound().build();
		}
		
		restauranteS.borra(id);
		return ResponseEntity.noContent().build();
		
	}
}
