package co.edu.uco.ucoparking.negocio.fachada.pais.impl;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.PaisDTOAssembler;
import co.edu.uco.ucoparking.negocio.casouso.pais.impl.ConsultarPaisesPorFiltroCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.negocio.fachada.pais.ConsultarPaisesPorFiltroFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class ConsultarPaisesPorFiltroFachadaImpl implements ConsultarPaisesPorFiltroFachada {

    @Override
    public List<PaisDTO> ejecutar(final PaisDTO filtro) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            final PaisDominio domFiltro = PaisDTOAssembler.obtenerInstancia().ensamblarDominio(filtro);
            final List<PaisDominio> dominios = new ConsultarPaisesPorFiltroCasoUsoImpl(fabricaDAO).ejecutar(domFiltro);
            return dominios.stream()
                    .map(PaisDTOAssembler.obtenerInstancia()::ensamblarDTO)
                    .collect(Collectors.toList());
        } catch (final UcoParkingException e) {
            throw e;
        } catch (final Exception e) {
            throw UcoParkingException.crear(e, "Error inesperado al consultar los países.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
