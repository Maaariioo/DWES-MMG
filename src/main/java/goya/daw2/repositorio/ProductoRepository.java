package goya.daw2.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import goya.daw2.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	List<Producto> findByNombreContainingIgnoreCase(String nombre);

	List<Producto> findByPrecioGreaterThan(double precio);

	List<Producto> findByPrecioLessThan(double precio);

	List<Producto> findByPrecioBetween(double precioMin, double precioMax);

	List<Producto> findByCantidadLessThan(int cantidad);

	List<Producto> findByCantidadGreaterThan(int cantidad);

	List<Producto> findAllByOrderByPrecioAsc();

	List<Producto> findAllByOrderByPrecioDesc();
	
	
	
}
