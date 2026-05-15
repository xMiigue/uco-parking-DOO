package co.edu.uco.ucoparking.negocio.fachada.ciudad.impl;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.CiudadDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.CiudadDTOAssembler;
import co.edu.uco.ucoparking.negocio.casouso.ciudad.impl.RegistrarNuevaCiudadCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.CiudadDominio;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.RegistrarNuevaCiudadFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class RegistrarNuevaCiudadFachadaImpl implements RegistrarNuevaCiudadFachada {

    @Override
    public void ejecutar(final CiudadDTO datos) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            fabricaDAO.iniciarTransaccion();
            final CiudadDominio dominio = CiudadDTOAssembler.obtenerInstancia().ensamblarDominio(datos);
            new RegistrarNuevaCiudadCasoUsoImpl(fabricaDAO).ejecutar(dominio);
            fabricaDAO.confirmarTransaccion();
        } catch (final UcoParkingException e) {
            fabricaDAO.cancelarTransaccion();
            throw e;
        } catch (final Exception e) {
            fabricaDAO.cancelarTransaccion();
            throw UcoParkingException.crear(e, "Error inesperado al registrar la ciudad.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
