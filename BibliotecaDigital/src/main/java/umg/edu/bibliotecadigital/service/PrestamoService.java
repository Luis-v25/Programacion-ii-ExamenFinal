package umg.edu.bibliotecadigital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umg.edu.bibliotecadigital.model.Libro;
import umg.edu.bibliotecadigital.model.Prestamo;
import umg.edu.bibliotecadigital.model.Usuario;
import umg.edu.bibliotecadigital.repository.PrestamoRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroService libroService;

    @Transactional
    public Prestamo realizarPrestamo(Usuario usuario, Libro libro) {
        if (libro.getCantidadDisponible() == null || libro.getCantidadDisponible() <= 0) {
            throw new RuntimeException("No hay ejemplares disponibles.");
        }

        Prestamo p = Prestamo.builder()
                .fechaPrestamo(LocalDate.now())
                .fechaDevolucionEsperada(LocalDate.now().plusDays(7))
                .devuelto(false)
                .usuario(usuario)
                .libro(libro)
                .build();

        libroService.reducirStock(libro);
        return prestamoRepository.save(p);
    }

    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
    }
}
