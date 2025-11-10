package umg.edu.bibliotecadigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umg.edu.bibliotecadigital.model.Multa;

import java.util.List;

public interface MultaRepository extends JpaRepository<Multa, Long> {
    List<Multa> findByUsuarioId(Long usuarioId);
    List<Multa> findByPagadaFalse();
}
