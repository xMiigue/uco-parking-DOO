package co.edu.uco.ucoparking.negocio.fachada.ciudad.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.negocio.casouso.ciudad.impl.EliminarCiudadCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.EliminarCiudadFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class EliminarCiudadFachadaImpl implements EliminarCiudadFachada {

    @Override
    public void ejecutar(final UUID id) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            fabricaDAO.iniciarTransaccion();
            new EliminarCiudadCasoUsoImpl(fabricaDAO).ejecutar(id);
            fabricaDAO.confirmarTransaccion();
        } catch (final UcoParkingException e) {
            fabricaDAO.cancelarTransaccion();
            throw e;
        } catch (final Exception e) {
            fabricaDAO.cancelarTransaccion();
            throw UcoParkingException.crear(e, "Error inesperado al eliminar la ciudad.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
