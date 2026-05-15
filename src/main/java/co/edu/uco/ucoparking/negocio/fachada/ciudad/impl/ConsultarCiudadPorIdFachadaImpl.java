package co.edu.uco.ucoparking.negocio.fachada.ciudad.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.CiudadDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.CiudadDTOAssembler;
import co.edu.uco.ucoparking.negocio.casouso.ciudad.impl.ConsultarCiudadPorIdCasoUsoImpl;
import co.edu.uco.ucoparking.negocio.dominio.CiudadDominio;
import co.edu.uco.ucoparking.negocio.fachada.ciudad.ConsultarCiudadPorIdFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class ConsultarCiudadPorIdFachadaImpl implements ConsultarCiudadPorIdFachada {

    @Override
    public CiudadDTO ejecutar(final UUID id) {
        FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
        try {
            fabricaDAO.abrirConexion();
            final CiudadDominio dominio = new ConsultarCiudadPorIdCasoUsoImpl(fabricaDAO).ejecutar(id);
            return CiudadDTOAssembler.obtenerInstancia().ensamblarDTO(dominio);
        } catch (final UcoParkingException e) {
            throw e;
        } catch (final Exception e) {
            throw UcoParkingException.crear(e, "Error inesperado al consultar la ciudad.", e.getMessage());
        } finally {
            fabricaDAO.cerrarConexion();
        }
    }
}
