package co.edu.uco.ucoparking.negocio.fachada.departamento.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.DepartamentoDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.DepartamentoDTOAssembler;
import co.edu.uco.ucoparking.negocio.casouso.departamento.impl.ConsultarDepartamentoPorIdCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.fachada.departamento.ConsultarDepartamentoPorIdFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class ConsultarDepartamentoPorIdFachadaImpl implements ConsultarDepartamentoPorIdFachada {

    @Override
    public DepartamentoDTO ejecutar(final UUID id) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            final DepartamentoDominio dominio = new ConsultarDepartamentoPorIdCasoUsoImpl(fabricaDAO).ejecutar(id);
            return DepartamentoDTOAssembler.obtenerInstancia().ensamblarDTO(dominio);
        } catch (final UcoParkingException e) {
            throw e;
        } catch (final Exception e) {
            throw UcoParkingException.crear(e, "Error inesperado al consultar el departamento.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
