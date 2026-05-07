package co.edu.uco.ucoparking.negocio.fachada;

import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.controlador.dto.Respuesta;
import co.edu.uco.ucoparking.dto.CiudadDTO;

public interface CiudadFachada {

    Respuesta<Void> crear(CiudadDTO dto);

    Respuesta<CiudadDTO> recuperarPorId(UUID id);

    Respuesta<List<CiudadDTO>> recuperarTodos(CiudadDTO filtro);

    Respuesta<Void> actualizar(CiudadDTO dto);

    Respuesta<Void> eliminar(UUID id);
}
