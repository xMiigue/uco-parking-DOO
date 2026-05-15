package co.edu.uco.ucoparking.negocio.fachada.departamento.impl;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.DepartamentoDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.DepartamentoDTOAssembler;
import co.edu.uco.ucoparking.negocio.casouso.departamento.impl.ConsultarDepartamentosPorFiltroCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.fachada.departamento.ConsultarDepartamentosPorFiltroFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class ConsultarDepartamentosPorFiltroFachadaImpl implements ConsultarDepartamentosPorFiltroFachada {

    @Override
    public List<DepartamentoDTO> ejecutar(final DepartamentoDTO filtro) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            final DepartamentoDominio domFiltro = DepartamentoDTOAssembler.obtenerInstancia().ensamblarDominio(filtro);
            final List<DepartamentoDominio> dominios = new ConsultarDepartamentosPorFiltroCasoUsoImpl(fabricaDAO).ejecutar(domFiltro);
            return dominios.stream()
                    .map(DepartamentoDTOAssembler.obtenerInstancia()::ensamblarDTO)
                    .collect(Collectors.toList());
        } catch (final UcoParkingException e) {
            throw e;
        } catch (final Exception e) {
            throw UcoParkingException.crear(e, "Error inesperado al consultar los departamentos.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
