package co.edu.uco.ucoparking.negocio.fachada.ciudad.impl;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.CiudadDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.CiudadDTOAssembler;
import co.edu.uco.ucoparking.negocio.casouso.ciudad.impl.ConsultarCiudadesPorFiltroCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.CiudadDominio;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.ConsultarCiudadesPorFiltroFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class ConsultarCiudadesPorFiltroFachadaImpl implements ConsultarCiudadesPorFiltroFachada {

    @Override
    public List<CiudadDTO> ejecutar(final CiudadDTO filtro) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            final CiudadDominio domFiltro = CiudadDTOAssembler.obtenerInstancia().ensamblarDominio(filtro);
            final List<CiudadDominio> dominios = new ConsultarCiudadesPorFiltroCasoUsoImpl(fabricaDAO).ejecutar(domFiltro);
            return dominios.stream()
                    .map(CiudadDTOAssembler.obtenerInstancia()::ensamblarDTO)
                    .collect(Collectors.toList());
        } catch (final UcoParkingException e) {
            throw e;
        } catch (final Exception e) {
            throw UcoParkingException.crear(e, "Error inesperado al consultar las ciudades.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
