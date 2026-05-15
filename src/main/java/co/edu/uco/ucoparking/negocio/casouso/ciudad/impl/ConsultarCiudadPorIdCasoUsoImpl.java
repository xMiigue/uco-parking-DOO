package co.edu.uco.ucoparking.negocio.casouso.ciudad.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.entidad.CiudadEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.CiudadEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.ciudad.ConsultarCiudadPorIdCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.CiudadDominio;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class ConsultarCiudadPorIdCasoUsoImpl implements ConsultarCiudadPorIdCasoUso {

    private final FabricaDAO fabricaDAO;

    public ConsultarCiudadPorIdCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public CiudadDominio ejecutar(final UUID id) {
        if (UtilObjeto.esNulo(id) || UtilUUID.esUUIDPorDefecto(id)) {
            throw UcoParkingException.crear("El identificador de la ciudad es obligatorio para consultarla.");
        }
        final CiudadEntidad entidad = fabricaDAO.obtenerCiudadDAO().consultarPorId(id);
        if (UtilObjeto.esNulo(entidad)) {
            throw UcoParkingException.crear("No se encontró la ciudad con el identificador indicado.");
        }
        return CiudadEntidadAssembler.obtenerInstancia().ensamblarDominio(entidad);
    }
}
