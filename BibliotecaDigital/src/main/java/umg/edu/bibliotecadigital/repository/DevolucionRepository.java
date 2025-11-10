package umg.edu.bibliotecadigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umg.edu.bibliotecadigital.model.Devolucion;

public interface DevolucionRepository extends JpaRepository<Devolucion, Long> { }
