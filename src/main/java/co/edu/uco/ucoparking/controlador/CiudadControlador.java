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
import co.edu.uco.ucoparking.negocio.fachada.ciudad.impl.ActualizarCiudadFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.impl.ConsultarCiudadPorIdFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.impl.ConsultarCiudadesPorFiltroFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.impl.EliminarCiudadFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.impl.RegistrarNuevaCiudadFachadaImpl;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadControlador {

    @PostMapping
    public ResponseEntity<Respuesta<Void>> crear(@RequestBody final CiudadDTO dto) {
        try {
            new RegistrarNuevaCiudadFachadaImpl().ejecutar(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Respuesta.exitosaConMensaje("Ciudad registrada exitosamente.", null));
        } catch (final UcoParkingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta.fallida(e.getMensajeUsuario()));
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Respuesta.fallida("Error interno al registrar la ciudad."));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Respuesta<CiudadDTO>> consultarPorId(@PathVariable final UUID id) {
        try {
            final CiudadDTO resultado = new ConsultarCiudadPorIdFachadaImpl().ejecutar(id);
            return ResponseEntity.ok(Respuesta.exitosa(resultado));
        } catch (final UcoParkingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta.fallida(e.getMensajeUsuario()));
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Respuesta.fallida("Error interno al consultar la ciudad."));
        }
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<CiudadDTO>>> consultarTodos() {
        try {
            final List<CiudadDTO> resultado = new ConsultarCiudadesPorFiltroFachadaImpl()
                    .ejecutar(new CiudadDTO.Builder().build());
            return ResponseEntity.ok(Respuesta.exitosa(resultado));
        } catch (final UcoParkingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta.fallida(e.getMensajeUsuario()));
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Respuesta.fallida("Error interno al consultar las ciudades."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Respuesta<Void>> actualizar(@PathVariable final UUID id,
            @RequestBody final CiudadDTO dto) {
        try {
            final CiudadDTO dtoConId = new CiudadDTO.Builder()
                    .id(id)
                    .nombre(dto.getNombre())
                    .departamento(dto.getDepartamento())
                    .build();
            new ActualizarCiudadFachadaImpl().ejecutar(dtoConId);
            return ResponseEntity.ok(Respuesta.exitosaConMensaje("Ciudad actualizada exitosamente.", null));
        } catch (final UcoParkingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta.fallida(e.getMensajeUsuario()));
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Respuesta.fallida("Error interno al actualizar la ciudad."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Respuesta<Void>> eliminar(@PathVariable final UUID id) {
        try {
            new EliminarCiudadFachadaImpl().ejecutar(id);
            return ResponseEntity.ok(Respuesta.exitosaConMensaje("Ciudad eliminada exitosamente.", null));
        } catch (final UcoParkingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta.fallida(e.getMensajeUsuario()));
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Respuesta.fallida("Error interno al eliminar la ciudad."));
        }
    }
}
