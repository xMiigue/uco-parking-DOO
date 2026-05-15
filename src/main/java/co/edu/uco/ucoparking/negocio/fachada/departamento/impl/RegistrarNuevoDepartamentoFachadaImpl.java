package co.edu.uco.ucoparking.negocio.fachada.departamento.impl;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.DepartamentoDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.DepartamentoDTOAssembler;
import co.edu.uco.ucoparking.negocio.casouso.departamento.impl.RegistrarNuevoDepartamentoCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.fachada.departamento.RegistrarNuevoDepartamentoFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class RegistrarNuevoDepartamentoFachadaImpl implements RegistrarNuevoDepartamentoFachada {

    @Override
    public void ejecutar(final DepartamentoDTO datos) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            fabricaDAO.iniciarTransaccion();
            final DepartamentoDominio dominio = DepartamentoDTOAssembler.obtenerInstancia().ensamblarDominio(datos);
            new RegistrarNuevoDepartamentoCasoUsoImpl(fabricaDAO).ejecutar(dominio);
            fabricaDAO.confirmarTransaccion();
        } catch (final UcoParkingException e) {
            fabricaDAO.cancelarTransaccion();
            throw e;
        } catch (final Exception e) {
            fabricaDAO.cancelarTransaccion();
            throw UcoParkingException.crear(e, "Error inesperado al registrar el departamento.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
