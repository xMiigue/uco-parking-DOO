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

import co.edu.uco.ucoparking.controlador.respuesta.RespuestaExito;
import co.edu.uco.ucoparking.dto.CiudadDTO;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.ActualizarCiudadFachada;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.ConsultarCiudadPorIdFachada;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.ConsultarCiudadesPorFiltroFachada;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.EliminarCiudadFachada;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.RegistrarNuevaCiudadFachada;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.impl.ActualizarCiudadFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.impl.ConsultarCiudadPorIdFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.impl.ConsultarCiudadesPorFiltroFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.impl.EliminarCiudadFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.impl.RegistrarNuevaCiudadFachadaImpl;

@RestController
@RequestMapping("/api/v1/ciudades")
public class CiudadControlador {

    @PostMapping
    public ResponseEntity<RespuestaExito<String>> registrarNuevaCiudad(@RequestBody final CiudadDTO dto) {
        RegistrarNuevaCiudadFachada fachada = new RegistrarNuevaCiudadFachadaImpl();
        fachada.ejecutar(dto);
        return new ResponseEntity<>(
                RespuestaExito.crear("La ciudad se ha registrado exitosamente.", ""),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaExito<String>> actualizarCiudad(@PathVariable final UUID id,
            @RequestBody final CiudadDTO dto) {
        final CiudadDTO dtoConId = new CiudadDTO.Builder()
                .id(id).nombre(dto.getNombre()).departamento(dto.getDepartamento()).build();
        ActualizarCiudadFachada fachada = new ActualizarCiudadFachadaImpl();
        fachada.ejecutar(dtoConId);
        return new ResponseEntity<>(
                RespuestaExito.crear("La ciudad se ha actualizado exitosamente.", ""),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaExito<String>> eliminarCiudad(@PathVariable final UUID id) {
        EliminarCiudadFachada fachada = new EliminarCiudadFachadaImpl();
        fachada.ejecutar(id);
        return new ResponseEntity<>(
                RespuestaExito.crear("La ciudad se ha eliminado exitosamente.", ""),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaExito<CiudadDTO>> consultarCiudadPorId(@PathVariable final UUID id) {
        ConsultarCiudadPorIdFachada fachada = new ConsultarCiudadPorIdFachadaImpl();
        final CiudadDTO resultado = fachada.ejecutar(id);
        return new ResponseEntity<>(
                RespuestaExito.crear("La ciudad se ha consultado exitosamente.", resultado),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RespuestaExito<List<CiudadDTO>>> consultarCiudades() {
        ConsultarCiudadesPorFiltroFachada fachada = new ConsultarCiudadesPorFiltroFachadaImpl();
        final List<CiudadDTO> resultado = fachada.ejecutar(new CiudadDTO.Builder().build());
        return new ResponseEntity<>(
                RespuestaExito.crear("Ciudades consultadas exitosamente.", resultado),
                HttpStatus.OK);
    }

}
