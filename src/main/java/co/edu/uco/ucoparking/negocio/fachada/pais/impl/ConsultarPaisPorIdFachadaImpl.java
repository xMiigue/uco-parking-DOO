package co.edu.uco.ucoparking.negocio.fachada.pais.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.PaisDTOAssembler;
import co.edu.uco.ucoparking.negocio.casouso.pais.impl.ConsultarPaisPorIdCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.negocio.fachada.pais.ConsultarPaisPorIdFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class ConsultarPaisPorIdFachadaImpl implements ConsultarPaisPorIdFachada {

    @Override
    public PaisDTO ejecutar(final UUID id) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            final PaisDominio dominio = new ConsultarPaisPorIdCasoUsoImpl(fabricaDAO).ejecutar(id);
            return PaisDTOAssembler.obtenerInstancia().ensamblarDTO(dominio);
        } catch (final UcoParkingException e) {
            throw e;
        } catch (final Exception e) {
            throw UcoParkingException.crear(e, "Error inesperado al consultar el país.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
