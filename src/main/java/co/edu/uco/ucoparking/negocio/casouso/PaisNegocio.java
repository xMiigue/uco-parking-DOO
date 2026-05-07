package co.edu.uco.ucoparking.negocio.casouso;

import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.dto.PaisDTO;

public interface PaisNegocio {

    void crear(FabricaDAO fabrica, PaisDTO dto);

    PaisDTO recuperarPorId(FabricaDAO fabrica, UUID id);

    List<PaisDTO> recuperarTodos(FabricaDAO fabrica, PaisDTO filtro);

    void actualizar(FabricaDAO fabrica, PaisDTO dto);

    void eliminar(FabricaDAO fabrica, UUID id);
}
