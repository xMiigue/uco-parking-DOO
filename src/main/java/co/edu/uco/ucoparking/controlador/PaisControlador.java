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
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.fachada.pais.ActualizarPaisFachada;
import co.edu.uco.ucoparking.negocio.fachada.pais.ConsultarPaisPorIdFachada;
import co.edu.uco.ucoparking.negocio.fachada.pais.ConsultarPaisesPorFiltroFachada;
import co.edu.uco.ucoparking.negocio.fachada.pais.EliminarPaisFachada;
import co.edu.uco.ucoparking.negocio.fachada.pais.RegistrarNuevoPaisFachada;
import co.edu.uco.ucoparking.negocio.fachada.pais.impl.ActualizarPaisFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.pais.impl.ConsultarPaisPorIdFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.pais.impl.ConsultarPaisesPorFiltroFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.pais.impl.EliminarPaisFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.pais.impl.RegistrarNuevoPaisFachadaImpl;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisControlador {

    @PostMapping
    public ResponseEntity<RespuestaExito<String>> registrarNuevoPais(@RequestBody final PaisDTO dto) {
        RegistrarNuevoPaisFachada fachada = new RegistrarNuevoPaisFachadaImpl();
        fachada.ejecutar(dto);
        return new ResponseEntity<>(
                RespuestaExito.crear("El país se ha registrado exitosamente.", ""),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaExito<String>> actualizarPais(@PathVariable final UUID id,
            @RequestBody final PaisDTO dto) {
        final PaisDTO dtoConId = new PaisDTO.Builder().id(id).nombre(dto.getNombre()).build();
        ActualizarPaisFachada fachada = new ActualizarPaisFachadaImpl();
        fachada.ejecutar(dtoConId);
        return new ResponseEntity<>(
                RespuestaExito.crear("El país se ha actualizado exitosamente.", ""),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaExito<String>> eliminarPais(@PathVariable final UUID id) {
        EliminarPaisFachada fachada = new EliminarPaisFachadaImpl();
        fachada.ejecutar(id);
        return new ResponseEntity<>(
                RespuestaExito.crear("El país se ha eliminado exitosamente.", ""),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaExito<PaisDTO>> consultarPaisPorId(@PathVariable final UUID id) {
        ConsultarPaisPorIdFachada fachada = new ConsultarPaisPorIdFachadaImpl();
        final PaisDTO resultado = fachada.ejecutar(id);
        return new ResponseEntity<>(
                RespuestaExito.crear("El país se ha consultado exitosamente.", resultado),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RespuestaExito<List<PaisDTO>>> consultarPaises() {
        ConsultarPaisesPorFiltroFachada fachada = new ConsultarPaisesPorFiltroFachadaImpl();
        final List<PaisDTO> resultado = fachada.ejecutar(new PaisDTO.Builder().build());
        return new ResponseEntity<>(
                RespuestaExito.crear("Países consultados exitosamente.", resultado),
                HttpStatus.OK);
    }

}
