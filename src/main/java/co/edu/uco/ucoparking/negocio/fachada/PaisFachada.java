package co.edu.uco.ucoparking.negocio.fachada;

import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.controlador.dto.Respuesta;
import co.edu.uco.ucoparking.dto.PaisDTO;

public interface PaisFachada {

    Respuesta<Void> crear(PaisDTO dto);

    Respuesta<PaisDTO> recuperarPorId(UUID id);

    Respuesta<List<PaisDTO>> recuperarTodos(PaisDTO filtro);

    Respuesta<Void> actualizar(PaisDTO dto);

    Respuesta<Void> eliminar(UUID id);
}
