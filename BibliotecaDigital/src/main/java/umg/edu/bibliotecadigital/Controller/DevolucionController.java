package umg.edu.bibliotecadigital.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umg.edu.bibliotecadigital.model.Devolucion;
import umg.edu.bibliotecadigital.model.Prestamo;
import umg.edu.bibliotecadigital.service.DevolucionService;
import umg.edu.bibliotecadigital.service.PrestamoService;

@RestController
@RequestMapping("/api/devoluciones")
@RequiredArgsConstructor
public class DevolucionController {

    private final DevolucionService devolucionService;
    private final PrestamoService prestamoService;

    @PostMapping("/{prestamoId}")
    public ResponseEntity<Devolucion> devolver(@PathVariable Long prestamoId) {
        Prestamo p = prestamoService.buscarPorId(prestamoId);
        Devolucion d = devolucionService.registrarDevolucion(p);
        return ResponseEntity.ok(d);
    }
}
