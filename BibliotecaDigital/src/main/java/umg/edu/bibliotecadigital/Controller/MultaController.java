package umg.edu.bibliotecadigital.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umg.edu.bibliotecadigital.model.Multa;
import umg.edu.bibliotecadigital.service.MultaService;

import java.util.List;

@RestController
@RequestMapping("/api/multas")
@RequiredArgsConstructor
public class MultaController {

    private final MultaService multaService;

    @GetMapping("/usuario/{usuarioId}")
    public List<Multa> multasPorUsuario(@PathVariable Long usuarioId) {
        return multaService.listarMultasUsuario(usuarioId);
    }

    @GetMapping("/nopagadas")
    public List<Multa> multasNoPagadas() {
        return multaService.listarMultasNoPagadas();
    }
}
