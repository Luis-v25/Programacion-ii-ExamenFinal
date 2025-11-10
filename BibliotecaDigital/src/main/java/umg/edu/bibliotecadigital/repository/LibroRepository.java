package umg.edu.bibliotecadigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umg.edu.bibliotecadigital.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> { }
