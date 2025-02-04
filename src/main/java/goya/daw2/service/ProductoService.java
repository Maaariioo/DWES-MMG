package goya.daw2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import goya.daw2.modelo.Producto;
import goya.daw2.repositorio.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

        // Guardar un producto
        public void guardarProducto(Producto producto) {
            productoRepository.save(producto);
        }

        // Obtener todos los productos
        public List<Producto> obtenerTodosLosProductos() {
            return productoRepository.findAll();
        }

        // Buscar productos por nombre
        public List<Producto> obtenerProductosPorNombre(String nombre) {
            return productoRepository.findByNombreContainingIgnoreCase(nombre);
        }

        // Obtener productos por precio mayor a un valor
        public List<Producto> obtenerProductosPorPrecioMayor(double precio) {
            return productoRepository.findByPrecioGreaterThan(precio);
        }

        // Obtener productos con cantidad menor a un valor
        public List<Producto> obtenerProductosConStockBajo(int cantidad) {
            return productoRepository.findByCantidadLessThan(cantidad);
        }

        // Ordenar productos por precio ascendente
        public List<Producto> obtenerProductosOrdenadosPorPrecioAsc() {
            return productoRepository.findAllByOrderByPrecioAsc();
        }

        // Ordenar productos por precio descendente
        public List<Producto> obtenerProductosOrdenadosPorPrecioDesc() {
            return productoRepository.findAllByOrderByPrecioDesc();
   }
}
