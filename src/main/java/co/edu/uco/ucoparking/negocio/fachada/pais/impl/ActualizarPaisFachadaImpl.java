package co.edu.uco.ucoparking.negocio.fachada.pais.impl;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.PaisDTOAssembler;
import co.edu.uco.ucoparking.negocio.casouso.pais.impl.ActualizarPaisCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.negocio.fachada.pais.ActualizarPaisFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class ActualizarPaisFachadaImpl implements ActualizarPaisFachada {

    @Override
    public void ejecutar(final PaisDTO datos) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            fabricaDAO.iniciarTransaccion();
            final PaisDominio dominio = PaisDTOAssembler.obtenerInstancia().ensamblarDominio(datos);
            new ActualizarPaisCasoUsoImpl(fabricaDAO).ejecutar(dominio);
            fabricaDAO.confirmarTransaccion();
        } catch (final UcoParkingException e) {
            fabricaDAO.cancelarTransaccion();
            throw e;
        } catch (final Exception e) {
            fabricaDAO.cancelarTransaccion();
            throw UcoParkingException.crear(e, "Error inesperado al actualizar el país.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
