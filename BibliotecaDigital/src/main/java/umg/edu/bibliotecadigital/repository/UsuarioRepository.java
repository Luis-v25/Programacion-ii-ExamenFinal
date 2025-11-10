package umg.edu.bibliotecadigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umg.edu.bibliotecadigital.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
