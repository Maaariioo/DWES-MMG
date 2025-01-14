package goya.daw2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
	List<Resultado> findFirst5ByOrderByIdDesc();
}
