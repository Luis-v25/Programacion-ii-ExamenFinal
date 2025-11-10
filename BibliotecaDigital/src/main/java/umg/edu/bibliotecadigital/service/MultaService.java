package umg.edu.bibliotecadigital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umg.edu.bibliotecadigital.model.Multa;
import umg.edu.bibliotecadigital.model.Usuario;
import umg.edu.bibliotecadigital.repository.MultaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MultaService {

    private final MultaRepository multaRepository;

    public Multa crearMulta(Usuario usuario, double monto, String motivo) {
        Multa m = Multa.builder()
                .usuario(usuario)
                .monto(monto)
                .motivo(motivo)
                .fecha(LocalDate.now())
                .pagada(false)
                .build();
        return multaRepository.save(m);
    }

    public List<Multa> listarMultasUsuario(Long usuarioId) {
        return multaRepository.findByUsuarioId(usuarioId);
    }

    public List<Multa> listarMultasNoPagadas() {
        return multaRepository.findByPagadaFalse();
    }
}
