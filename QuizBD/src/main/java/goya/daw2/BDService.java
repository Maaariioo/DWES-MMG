package goya.daw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BDService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    public List<Resultado> leerResultados() {
        return resultadoRepository.findAll();
    }

    public void guardarResultados(List<Resultado> resultados) {
        resultadoRepository.saveAll(resultados);
    }

    public List<Resultado> obtenerUltimosResultados() {
        return resultadoRepository.findFirst5ByOrderByIdDesc();
    }
}
