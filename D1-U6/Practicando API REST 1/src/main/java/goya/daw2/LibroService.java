package goya.daw2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

	
	@Autowired
	private LibroRepository libroR;

	public List<Libro> findAll() {
        return libroR.findAll();
    }

	public Optional<Libro> findbyId(Long id){
		return libroR.findById(id);
	}

	public boolean existsbyNombre(String titulo) {
		return libroR.findByTituloIgnoreCase(titulo).isPresent();
	}

	public Libro save(Libro libro) {

		return libroR.save(libro);
	}

	public void deleteById(Long id) {
		libroR.deleteById(id);
	}
	
	
}
