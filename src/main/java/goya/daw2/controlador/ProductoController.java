package goya.daw2.controlador;

import goya.daw2.modelo.Producto;
import goya.daw2.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductoController {
    
    @Autowired
    private ProductoService productoService; // Usamos el servicio en vez del repositorio directo

    // Formulario inicial
    @GetMapping("/")
    public String cargarForm(Model model) {
        model.addAttribute("producto", new Producto());
        return "index.html";
    }

    // Guardar un producto
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto); // Usamos el servicio para guardar
        return "redirect:/listado";
    }

    // Listado de todos los productos
    @GetMapping("/listado")
    public String listarProducto(Model model) {
        List<Producto> productos = productoService.obtenerTodosLosProductos(); // Obtener todos desde el servicio
        model.addAttribute("productos", productos);
        return "listado";
    }

    // Filtrar productos por nombre (con search)
    @GetMapping("/listado/por-nombre")
    public String listarProductosPorNombre(@RequestParam(name = "nombre") String nombre, Model model) {
        List<Producto> productos = productoService.obtenerProductosPorNombre(nombre);
        model.addAttribute("productos", productos);
        return "listado";
    }

    // Filtrar productos por precio mayor a un valor específico
    @GetMapping("/listado/por-precio")
    public String listarProductosPorPrecio(@RequestParam(name = "precio") double precio, Model model) {
        List<Producto> productos = productoService.obtenerProductosPorPrecioMayor(precio); // Filtrar por precio
        model.addAttribute("productos", productos);
        return "listado";
    }

    // Filtrar productos con cantidad menor a un valor específico
    @GetMapping("/listado/bajo-stock")
    public String listarProductosConPocoStock(@RequestParam(name = "cantidad") int cantidad, Model model) {
        List<Producto> productos = productoService.obtenerProductosConStockBajo(cantidad); // Filtrar por stock
        model.addAttribute("productos", productos);
        return "listado";
    }

    // Obtener productos ordenados por precio de menor a mayor
    @GetMapping("/listado/ordenar-precio-asc")
    public String listarProductosOrdenadosPorPrecioAsc(Model model) {
        List<Producto> productos = productoService.obtenerProductosOrdenadosPorPrecioAsc(); // Ordenar por precio ascendente
        model.addAttribute("productos", productos);
        return "listado";
    }

    // Obtener productos ordenados por precio de mayor a menor
    @GetMapping("/listado/ordenar-precio-desc")
    public String listarProductosOrdenadosPorPrecioDesc(Model model) {
        List<Producto> productos = productoService.obtenerProductosOrdenadosPorPrecioDesc(); // Ordenar por precio descendente
        model.addAttribute("productos", productos);
        return "listado";
    }
}
