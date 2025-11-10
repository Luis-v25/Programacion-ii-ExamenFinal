package umg.edu.bibliotecadigital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umg.edu.bibliotecadigital.model.Libro;
import umg.edu.bibliotecadigital.repository.LibroRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;

    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }

    public Libro actualizarLibro(Long id, Libro libro) {
        Libro existente = obtenerPorId(id);

        existente.setTitulo(libro.getTitulo());
        existente.setAutor(libro.getAutor());
        existente.setCategoria(libro.getCategoria());
        existente.setCantidadTotal(libro.getCantidadTotal());
        existente.setCantidadDisponible(libro.getCantidadDisponible());

        return libroRepository.save(existente);
    }

    public void eliminarLibro(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Libro no encontrado con ID: " + id);
        }
        libroRepository.deleteById(id);
    }

    public void reducirStock(Libro libro) {
        if (libro.getCantidadDisponible() > 0) {
            libro.setCantidadDisponible(libro.getCantidadDisponible() - 1);
            libroRepository.save(libro);
        } else {
            throw new RuntimeException("No hay ejemplares disponibles para prestar.");
        }
    }

    public void aumentarStock(Libro libro) {
        if (libro.getCantidadDisponible() < libro.getCantidadTotal()) {
            libro.setCantidadDisponible(libro.getCantidadDisponible() + 1);
            libroRepository.save(libro);
        } else {
            throw new RuntimeException("No se puede aumentar stock, ya está al máximo.");
        }
    }
}
