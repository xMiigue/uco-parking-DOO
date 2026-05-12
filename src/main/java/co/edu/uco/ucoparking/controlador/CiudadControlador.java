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
import co.edu.uco.ucoparking.dto.CiudadDTO;
import co.edu.uco.ucoparking.negocio.fachada.CiudadFachada;
import co.edu.uco.ucoparking.negocio.fachada.impl.CiudadFachadaImpl;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadControlador {

    private final CiudadFachada fachada = new CiudadFachadaImpl();

    @PostMapping
    public ResponseEntity<Respuesta<Void>> crear(@RequestBody final CiudadDTO dto) {
        Respuesta<Void> respuesta = fachada.crear(dto);
        return respuesta.isExitoso()
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta<CiudadDTO>> recuperarPorId(@PathVariable final UUID id) {
        Respuesta<CiudadDTO> respuesta = fachada.recuperarPorId(id);
        return respuesta.isExitoso()
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<CiudadDTO>>> recuperarTodos() {
        Respuesta<List<CiudadDTO>> respuesta = fachada.recuperarTodos(new CiudadDTO.Builder().build());
        return respuesta.isExitoso()
                ? ResponseEntity.ok(respuesta)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta<Void>> actualizar(@PathVariable final UUID id,
            @RequestBody final CiudadDTO dto) {
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
