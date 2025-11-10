package umg.edu.bibliotecadigital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umg.edu.bibliotecadigital.model.Devolucion;
import umg.edu.bibliotecadigital.model.Prestamo;
import umg.edu.bibliotecadigital.repository.DevolucionRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class DevolucionService {

    private final DevolucionRepository devolucionRepository;
    private final LibroService libroService;
    private final MultaService multaService;

    @Transactional
    public Devolucion registrarDevolucion(Prestamo prestamo) {
        if (prestamo == null) throw new RuntimeException("Préstamo inválido");

        prestamo.setDevuelto(true);
        prestamo.setFechaDevolucionReal(LocalDate.now());

        libroService.aumentarStock(prestamo.getLibro());

        Devolucion d = Devolucion.builder()
                .fechaDevolucion(LocalDate.now())
                .prestamo(prestamo)
                .build();

        long diasAtraso = ChronoUnit.DAYS.between(prestamo.getFechaDevolucionEsperada(), LocalDate.now());
        if (diasAtraso > 0) {
            double monto = diasAtraso * 1.50;
            multaService.crearMulta(prestamo.getUsuario(), monto, "Devolución tardía");
        }

        return devolucionRepository.save(d);
    }
}
