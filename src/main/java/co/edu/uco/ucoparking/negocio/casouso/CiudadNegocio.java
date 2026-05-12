package co.edu.uco.ucoparking.negocio.casouso;

import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.dto.CiudadDTO;

public interface CiudadNegocio {

    void crear(FabricaDAO fabrica, CiudadDTO dto);

    CiudadDTO recuperarPorId(FabricaDAO fabrica, UUID id);

    List<CiudadDTO> recuperarTodos(FabricaDAO fabrica, CiudadDTO filtro);

    void actualizar(FabricaDAO fabrica, CiudadDTO dto);

    void eliminar(FabricaDAO fabrica, UUID id);
}
