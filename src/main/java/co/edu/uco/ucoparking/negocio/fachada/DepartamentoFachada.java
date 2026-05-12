package co.edu.uco.ucoparking.negocio.fachada;

import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.controlador.dto.Respuesta;
import co.edu.uco.ucoparking.dto.DepartamentoDTO;

public interface DepartamentoFachada {

    Respuesta<Void> crear(DepartamentoDTO dto);

    Respuesta<DepartamentoDTO> recuperarPorId(UUID id);

    Respuesta<List<DepartamentoDTO>> recuperarTodos(DepartamentoDTO filtro);

    Respuesta<Void> actualizar(DepartamentoDTO dto);

    Respuesta<Void> eliminar(UUID id);
}
