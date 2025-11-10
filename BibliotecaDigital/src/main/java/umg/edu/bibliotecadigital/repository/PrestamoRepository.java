package umg.edu.bibliotecadigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umg.edu.bibliotecadigital.model.Prestamo;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByFechaDevolucionRealIsNullAndFechaDevolucionEsperadaBefore(LocalDate fecha);
}
