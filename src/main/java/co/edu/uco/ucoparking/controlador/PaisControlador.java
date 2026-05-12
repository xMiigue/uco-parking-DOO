package co.edu.uco.ucoparking.controlador;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.ucoparking.controlador.dto.Respuesta;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.fachada.PaisFachada;
import co.edu.uco.ucoparking.negocio.fachada.impl.PaisFachadaImpl;

@RestController
@RequestMapping("/api/paises")
public class PaisControlador {

    private final PaisFachada fachada = new PaisFachadaImpl();

    @PostMapping
    public ResponseEntity<Respuesta<Void>> crear(@RequestBody final PaisDTO dto) {
        Respuesta<Void> respuesta = fachada.crear(dto);
        return respuesta.isExitoso()
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta<PaisDTO>> recuperarPorId(@PathVariable final UUID id) {
        Respuesta<PaisDTO> respuesta = fachada.recuperarPorId(id);
        return respuesta.isExitoso()
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<PaisDTO>>> recuperarTodos() {
        Respuesta<List<PaisDTO>> respuesta = fachada.recuperarTodos(new PaisDTO.Builder().build());
        return respuesta.isExitoso()
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta<Void>> actualizar(@PathVariable final UUID id,
            @RequestBody final PaisDTO dto) {
        Respuesta<Void> respuesta = fachada.actualizar(dto);
        return respuesta.isExitoso()
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Respuesta<Void>> eliminar(@PathVariable final UUID id) {
        Respuesta<Void> respuesta = fachada.eliminar(id);
        return respuesta.isExitoso()
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }
}
