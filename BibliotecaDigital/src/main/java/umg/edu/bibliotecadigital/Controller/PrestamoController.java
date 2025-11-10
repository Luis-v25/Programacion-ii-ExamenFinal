package umg.edu.bibliotecadigital.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umg.edu.bibliotecadigital.model.Prestamo;
import umg.edu.bibliotecadigital.model.Usuario;
import umg.edu.bibliotecadigital.model.Libro;
import umg.edu.bibliotecadigital.service.PrestamoService;
import umg.edu.bibliotecadigital.service.UsuarioService;
import umg.edu.bibliotecadigital.service.LibroService;

@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final UsuarioService usuarioService;
    private final LibroService libroService;

    @PostMapping("/{usuarioId}/{libroId}")
    public ResponseEntity<Prestamo> prestar(@PathVariable Long usuarioId, @PathVariable Long libroId) {

        Usuario u = usuarioService.buscarPorId(usuarioId);
        Libro l = libroService.obtenerPorId(libroId); // ← CAMBIO AQUÍ

        Prestamo p = prestamoService.realizarPrestamo(u, l);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.buscarPorId(id));
    }
}
