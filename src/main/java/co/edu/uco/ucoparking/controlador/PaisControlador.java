package co.edu.uco.ucoparking.controlador;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PaisControlador.class);

    @PostMapping
    public ResponseEntity<RespuestaExito<String>> registrarNuevoPais(@RequestBody final PaisDTO dto) {
        LOGGER.info("Solicitud recibida: registrar nuevo país.");
        RegistrarNuevoPaisFachada fachada = new RegistrarNuevoPaisFachadaImpl();
        fachada.ejecutar(dto);
        LOGGER.info("País registrado exitosamente.");
        return new ResponseEntity<>(
                RespuestaExito.crear("El país se ha registrado exitosamente.", ""),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaExito<String>> actualizarPais(@PathVariable final UUID id,
            @RequestBody final PaisDTO dto) {
        LOGGER.info("Solicitud recibida: actualizar país con id {}.", id);
        final PaisDTO dtoConId = new PaisDTO.Builder().id(id).nombre(dto.getNombre()).build();
        ActualizarPaisFachada fachada = new ActualizarPaisFachadaImpl();
        fachada.ejecutar(dtoConId);
        LOGGER.info("País con id {} actualizado exitosamente.", id);
        return new ResponseEntity<>(
                RespuestaExito.crear("El país se ha actualizado exitosamente.", ""),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaExito<String>> eliminarPais(@PathVariable final UUID id) {
        LOGGER.info("Solicitud recibida: eliminar país con id {}.", id);
        EliminarPaisFachada fachada = new EliminarPaisFachadaImpl();
        fachada.ejecutar(id);
        LOGGER.info("País con id {} eliminado exitosamente.", id);
        return new ResponseEntity<>(
                RespuestaExito.crear("El país se ha eliminado exitosamente.", ""),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaExito<PaisDTO>> consultarPaisPorId(@PathVariable final UUID id) {
        LOGGER.info("Solicitud recibida: consultar país por id {}.", id);
        ConsultarPaisPorIdFachada fachada = new ConsultarPaisPorIdFachadaImpl();
        final PaisDTO resultado = fachada.ejecutar(id);
        LOGGER.info("País con id {} consultado exitosamente.", id);
        return new ResponseEntity<>(
                RespuestaExito.crear("El país se ha consultado exitosamente.", resultado),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RespuestaExito<List<PaisDTO>>> consultarPaises() {
        LOGGER.info("Solicitud recibida: consultar todos los países.");
        ConsultarPaisesPorFiltroFachada fachada = new ConsultarPaisesPorFiltroFachadaImpl();
        final List<PaisDTO> resultado = fachada.ejecutar(new PaisDTO.Builder().build());
        LOGGER.info("Consulta de países completada. Registros obtenidos: {}.", resultado.size());
        return new ResponseEntity<>(
                RespuestaExito.crear("Países consultados exitosamente.", resultado),
                HttpStatus.OK);
    }
}
