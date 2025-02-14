package goya.daw2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {

	@Autowired
	private PeliculaRepository peliculaR;
	
	public List<Pelicula> obtenerTodas(){
		
		return peliculaR.findAll();
		
	}
	
	public Optional<Pelicula> findbyId(Long id){
		
		return peliculaR.findById(id);
		
	}

	public boolean yaExiste(String titulo) {
		
		return peliculaR.findByTituloIgnoreCase(titulo).isPresent();
	}

	public Pelicula save(Pelicula pelicula) {
		
		return peliculaR.save(pelicula);
	}

	public void deleteById(Long id) {

		peliculaR.deleteById(id);
		
	}
}
