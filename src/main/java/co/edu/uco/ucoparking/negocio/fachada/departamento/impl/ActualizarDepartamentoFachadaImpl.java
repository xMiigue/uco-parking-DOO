package co.edu.uco.ucoparking.negocio.fachada.departamento.impl;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.DepartamentoDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.DepartamentoDTOAssembler;
import co.edu.uco.ucoparking.negocio.casouso.departamento.impl.ActualizarDepartamentoCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.fachada.departamento.ActualizarDepartamentoFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class ActualizarDepartamentoFachadaImpl implements ActualizarDepartamentoFachada {

    @Override
    public void ejecutar(final DepartamentoDTO datos) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            fabricaDAO.iniciarTransaccion();
            final DepartamentoDominio dominio = DepartamentoDTOAssembler.obtenerInstancia().ensamblarDominio(datos);
            new ActualizarDepartamentoCasoUsoImpl(fabricaDAO).ejecutar(dominio);
            fabricaDAO.confirmarTransaccion();
        } catch (final UcoParkingException e) {
            fabricaDAO.cancelarTransaccion();
            throw e;
        } catch (final Exception e) {
            fabricaDAO.cancelarTransaccion();
            throw UcoParkingException.crear(e, "Error inesperado al actualizar el departamento.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
