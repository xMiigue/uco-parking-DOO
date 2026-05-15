package co.edu.uco.ucoparking.negocio.casouso.pais.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.negocio.casouso.pais.EliminarPaisCasoUso;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class EliminarPaisCasoUsoImpl implements EliminarPaisCasoUso {

    private final FabricaDAO fabricaDAO;

    public EliminarPaisCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public void ejecutar(final UUID id) {
        if (UtilObjeto.esNulo(id) || UtilUUID.esUUIDPorDefecto(id)) {
            throw UcoParkingException.crear("El identificador del país es obligatorio para eliminarlo.");
        }
        if (UtilObjeto.esNulo(fabricaDAO.obtenerPaisDAO().consultarPorId(id))) {
            throw UcoParkingException.crear("No se encontró el país con el identificador indicado.");
        }
        fabricaDAO.obtenerPaisDAO().eliminar(id);
    }
}
