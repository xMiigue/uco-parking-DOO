package co.edu.uco.ucoparking.negocio.casouso.ciudad.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.negocio.casouso.ciudad.EliminarCiudadCasoUso;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class EliminarCiudadCasoUsoImpl implements EliminarCiudadCasoUso {

    private final FabricaDAO fabricaDAO;

    public EliminarCiudadCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public void ejecutar(final UUID id) {
        if (UtilObjeto.esNulo(id) || UtilUUID.esUUIDPorDefecto(id)) {
            throw UcoParkingException.crear("El identificador de la ciudad es obligatorio para eliminarla.");
        }
        if (UtilObjeto.esNulo(fabricaDAO.obtenerCiudadDAO().consultarPorId(id))) {
            throw UcoParkingException.crear("No se encontró la ciudad con el identificador indicado.");
        }
        fabricaDAO.obtenerCiudadDAO().eliminar(id);
    }
}
