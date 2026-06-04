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
import co.edu.uco.ucoparking.dto.DepartamentoDTO;
import co.edu.uco.ucoparking.negocio.fachada.departamento.ActualizarDepartamentoFachada;
import co.edu.uco.ucoparking.negocio.fachada.departamento.ConsultarDepartamentoPorIdFachada;
import co.edu.uco.ucoparking.negocio.fachada.departamento.ConsultarDepartamentosPorFiltroFachada;
import co.edu.uco.ucoparking.negocio.fachada.departamento.EliminarDepartamentoFachada;
import co.edu.uco.ucoparking.negocio.fachada.departamento.RegistrarNuevoDepartamentoFachada;
import co.edu.uco.ucoparking.negocio.fachada.departamento.impl.ActualizarDepartamentoFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.departamento.impl.ConsultarDepartamentoPorIdFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.departamento.impl.ConsultarDepartamentosPorFiltroFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.departamento.impl.EliminarDepartamentoFachadaImpl;
import co.edu.uco.ucoparking.negocio.fachada.departamento.impl.RegistrarNuevoDepartamentoFachadaImpl;

@RestController
@RequestMapping("/api/v1/departamentos")
public class DepartamentoControlador {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoControlador.class);

    @PostMapping
    public ResponseEntity<RespuestaExito<String>> registrarNuevoDepartamento(@RequestBody final DepartamentoDTO dto) {
        LOGGER.info("Solicitud recibida: registrar nuevo departamento.");
        RegistrarNuevoDepartamentoFachada fachada = new RegistrarNuevoDepartamentoFachadaImpl();
        fachada.ejecutar(dto);
        LOGGER.info("Departamento registrado exitosamente.");
        return new ResponseEntity<>(
                RespuestaExito.crear("El departamento se ha registrado exitosamente.", ""),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaExito<String>> actualizarDepartamento(@PathVariable final UUID id,
            @RequestBody final DepartamentoDTO dto) {
        LOGGER.info("Solicitud recibida: actualizar departamento con id {}.", id);
        final DepartamentoDTO dtoConId = new DepartamentoDTO.Builder()
                .id(id).nombre(dto.getNombre()).pais(dto.getPais()).build();
        ActualizarDepartamentoFachada fachada = new ActualizarDepartamentoFachadaImpl();
        fachada.ejecutar(dtoConId);
        LOGGER.info("Departamento con id {} actualizado exitosamente.", id);
        return new ResponseEntity<>(
                RespuestaExito.crear("El departamento se ha actualizado exitosamente.", ""),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaExito<String>> eliminarDepartamento(@PathVariable final UUID id) {
        LOGGER.info("Solicitud recibida: eliminar departamento con id {}.", id);
        EliminarDepartamentoFachada fachada = new EliminarDepartamentoFachadaImpl();
        fachada.ejecutar(id);
        LOGGER.info("Departamento con id {} eliminado exitosamente.", id);
        return new ResponseEntity<>(
                RespuestaExito.crear("El departamento se ha eliminado exitosamente.", ""),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaExito<DepartamentoDTO>> consultarDepartamentoPorId(@PathVariable final UUID id) {
        LOGGER.info("Solicitud recibida: consultar departamento por id {}.", id);
        ConsultarDepartamentoPorIdFachada fachada = new ConsultarDepartamentoPorIdFachadaImpl();
        final DepartamentoDTO resultado = fachada.ejecutar(id);
        LOGGER.info("Departamento con id {} consultado exitosamente.", id);
        return new ResponseEntity<>(
                RespuestaExito.crear("El departamento se ha consultado exitosamente.", resultado),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RespuestaExito<List<DepartamentoDTO>>> consultarDepartamentos() {
        LOGGER.info("Solicitud recibida: consultar todos los departamentos.");
        ConsultarDepartamentosPorFiltroFachada fachada = new ConsultarDepartamentosPorFiltroFachadaImpl();
        final List<DepartamentoDTO> resultado = fachada.ejecutar(new DepartamentoDTO.Builder().build());
        LOGGER.info("Consulta de departamentos completada. Registros obtenidos: {}.", resultado.size());
        return new ResponseEntity<>(
                RespuestaExito.crear("Departamentos consultados exitosamente.", resultado),
                HttpStatus.OK);
    }

}
