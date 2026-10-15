package pedidos360.backend.controllers;

import pedidos360.backend.models.Producto;
import pedidos360.backend.repositories.ProductoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void inicializarDatos() {
        if (repository.count() == 0) {
            repository.saveAll(List.of(
                new Producto("Vela Artesanal Violeta", "Vertida a mano en frasco de vidrio. Acabado sin rechupe.", "🕯️", 12990),
                new Producto("Sérum Facial Iluminador", "Fórmula enriquecida con vitamina C y ácido hialurónico.", "✨", 18500),
                new Producto("Crema Corporal Almendras", "Hidratación profunda con aceite natural de almendras.", "🧴", 14200),
                new Producto("Vela Aromática Eucalipto", "Aroma fresco y relajante para sesiones de meditación.", "🌿", 11500)
            ));
        }
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return repository.findAll();
    }
}