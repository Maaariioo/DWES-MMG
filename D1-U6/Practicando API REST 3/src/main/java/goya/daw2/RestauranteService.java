package goya.daw2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteR;
	
	public List<Restaurante> todos(){
		
		return restauranteR.findAll();
		
	}
	
	public Optional<Restaurante> findById(Long id){
		
		return restauranteR.findById(id);
	}
	
	public boolean existe(String nombre) {
		
		return restauranteR.findByNombreIgnoreCase(nombre).isPresent();
	}
	
	public Restaurante save(Restaurante restaurante) {
		
		return restauranteR.save(restaurante);
				
	}
	
	public void borra(Long id) {
		
		restauranteR.deleteById(id);
		
	}

}
