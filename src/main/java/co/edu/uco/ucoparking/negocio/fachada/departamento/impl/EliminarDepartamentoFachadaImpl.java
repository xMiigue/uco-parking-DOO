package co.edu.uco.ucoparking.negocio.fachada.departamento.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.negocio.casouso.departamento.impl.EliminarDepartamentoCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.fachada.departamento.EliminarDepartamentoFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class EliminarDepartamentoFachadaImpl implements EliminarDepartamentoFachada {

    @Override
    public void ejecutar(final UUID id) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            fabricaDAO.iniciarTransaccion();
            new EliminarDepartamentoCasoUsoImpl(fabricaDAO).ejecutar(id);
            fabricaDAO.confirmarTransaccion();
        } catch (final UcoParkingException e) {
            fabricaDAO.cancelarTransaccion();
            throw e;
        } catch (final Exception e) {
            fabricaDAO.cancelarTransaccion();
            throw UcoParkingException.crear(e, "Error inesperado al eliminar el departamento.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
