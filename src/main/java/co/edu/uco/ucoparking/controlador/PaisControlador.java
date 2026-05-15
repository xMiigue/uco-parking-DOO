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
import co.edu.uco.ucoparking.negocio.fachada.pais.impl.ActualizarPaisFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.pais.impl.ConsultarPaisPorIdFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.pais.impl.ConsultarPaisesPorFiltroFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.pais.impl.EliminarPaisFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.pais.impl.RegistrarNuevoPaisFachadaImpl;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

@RestController
@RequestMapping("/api/paises")
public class PaisControlador {

    @PostMapping
    public ResponseEntity<Respuesta<Void>> crear(@RequestBody final PaisDTO dto) {
        try {
            new RegistrarNuevoPaisFachadaImpl().ejecutar(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Respuesta.exitosaConMensaje("País registrado exitosamente.", null));
        } catch (final UcoParkingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta.fallida(e.getMensajeUsuario()));
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Respuesta.fallida("Error interno al registrar el país."));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta<PaisDTO>> consultarPorId(@PathVariable final UUID id) {
        try {
            final PaisDTO resultado = new ConsultarPaisPorIdFachadaImpl().ejecutar(id);
            return ResponseEntity.ok(Respuesta.exitosa(resultado));
        } catch (final UcoParkingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta.fallida(e.getMensajeUsuario()));
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Respuesta.fallida("Error interno al consultar el país."));
        }
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<PaisDTO>>> consultarTodos() {
        try {
            final List<PaisDTO> resultado = new ConsultarPaisesPorFiltroFachadaImpl()
                    .ejecutar(new PaisDTO.Builder().build());
            return ResponseEntity.ok(Respuesta.exitosa(resultado));
        } catch (final UcoParkingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta.fallida(e.getMensajeUsuario()));
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Respuesta.fallida("Error interno al consultar los países."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta<Void>> actualizar(@PathVariable final UUID id,
            @RequestBody final PaisDTO dto) {
        try {
            final PaisDTO dtoConId = new PaisDTO.Builder().id(id).nombre(dto.getNombre()).build();
            new ActualizarPaisFachadaImpl().ejecutar(dtoConId);
            return ResponseEntity.ok(Respuesta.exitosaConMensaje("País actualizado exitosamente.", null));
        } catch (final UcoParkingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta.fallida(e.getMensajeUsuario()));
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Respuesta.fallida("Error interno al actualizar el país."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Respuesta<Void>> eliminar(@PathVariable final UUID id) {
        try {
            new EliminarPaisFachadaImpl().ejecutar(id);
            return ResponseEntity.ok(Respuesta.exitosaConMensaje("País eliminado exitosamente.", null));
        } catch (final UcoParkingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta.fallida(e.getMensajeUsuario()));
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Respuesta.fallida("Error interno al eliminar el país."));
        }
    }
}
