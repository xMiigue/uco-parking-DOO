package co.edu.uco.ucoparking.negocio.casouso;

import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.dto.DepartamentoDTO;

public interface DepartamentoNegocio {

    void crear(FabricaDAO fabrica, DepartamentoDTO dto);

    DepartamentoDTO recuperarPorId(FabricaDAO fabrica, UUID id);

    List<DepartamentoDTO> recuperarTodos(FabricaDAO fabrica, DepartamentoDTO filtro);

    void actualizar(FabricaDAO fabrica, DepartamentoDTO dto);

    void eliminar(FabricaDAO fabrica, UUID id);
}
