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

    @PostMapping
    public ResponseEntity<RespuestaExito<String>> registrarNuevoDepartamento(@RequestBody final DepartamentoDTO dto) {
        RegistrarNuevoDepartamentoFachada fachada = new RegistrarNuevoDepartamentoFachadaImpl();
        fachada.ejecutar(dto);
        return new ResponseEntity<>(
                RespuestaExito.crear("El departamento se ha registrado exitosamente.", ""),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaExito<String>> actualizarDepartamento(@PathVariable final UUID id,
            @RequestBody final DepartamentoDTO dto) {
        final DepartamentoDTO dtoConId = new DepartamentoDTO.Builder()
                .id(id).nombre(dto.getNombre()).pais(dto.getPais()).build();
        ActualizarDepartamentoFachada fachada = new ActualizarDepartamentoFachadaImpl();
        fachada.ejecutar(dtoConId);
        return new ResponseEntity<>(
                RespuestaExito.crear("El departamento se ha actualizado exitosamente.", ""),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaExito<String>> eliminarDepartamento(@PathVariable final UUID id) {
        EliminarDepartamentoFachada fachada = new EliminarDepartamentoFachadaImpl();
        fachada.ejecutar(id);
        return new ResponseEntity<>(
                RespuestaExito.crear("El departamento se ha eliminado exitosamente.", ""),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaExito<DepartamentoDTO>> consultarDepartamentoPorId(@PathVariable final UUID id) {
        ConsultarDepartamentoPorIdFachada fachada = new ConsultarDepartamentoPorIdFachadaImpl();
        final DepartamentoDTO resultado = fachada.ejecutar(id);
        return new ResponseEntity<>(
                RespuestaExito.crear("El departamento se ha consultado exitosamente.", resultado),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RespuestaExito<List<DepartamentoDTO>>> consultarDepartamentos() {
        ConsultarDepartamentosPorFiltroFachada fachada = new ConsultarDepartamentosPorFiltroFachadaImpl();
        final List<DepartamentoDTO> resultado = fachada.ejecutar(new DepartamentoDTO.Builder().build());
        return new ResponseEntity<>(
                RespuestaExito.crear("Departamentos consultados exitosamente.", resultado),
                HttpStatus.OK);
    }

}
