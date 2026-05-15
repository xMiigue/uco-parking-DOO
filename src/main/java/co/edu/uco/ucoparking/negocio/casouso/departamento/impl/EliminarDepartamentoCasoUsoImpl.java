package co.edu.uco.ucoparking.negocio.casouso.departamento.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.negocio.casouso.departamento.EliminarDepartamentoCasoUso;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class EliminarDepartamentoCasoUsoImpl implements EliminarDepartamentoCasoUso {

    private final FabricaDAO fabricaDAO;

    public EliminarDepartamentoCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public void ejecutar(final UUID id) {
        if (UtilObjeto.esNulo(id) || UtilUUID.esUUIDPorDefecto(id)) {
            throw UcoParkingException.crear("El identificador del departamento es obligatorio para eliminarlo.");
        }
        if (UtilObjeto.esNulo(fabricaDAO.obtenerDepartamentoDAO().consultarPorId(id))) {
            throw UcoParkingException.crear("No se encontró el departamento con el identificador indicado.");
        }
        fabricaDAO.obtenerDepartamentoDAO().eliminar(id);
    }
}
