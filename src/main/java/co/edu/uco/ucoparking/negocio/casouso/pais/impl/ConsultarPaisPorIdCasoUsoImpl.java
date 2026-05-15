package co.edu.uco.ucoparking.negocio.casouso.pais.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.PaisEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.pais.ConsultarPaisPorIdCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class ConsultarPaisPorIdCasoUsoImpl implements ConsultarPaisPorIdCasoUso {

    private final FabricaDAO fabricaDAO;

    public ConsultarPaisPorIdCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public PaisDominio ejecutar(final UUID id) {
        if (UtilObjeto.esNulo(id) || UtilUUID.esUUIDPorDefecto(id)) {
            throw UcoParkingException.crear("El identificador del país es obligatorio para consultarlo.");
        }
        final PaisEntidad entidad = fabricaDAO.obtenerPaisDAO().consultarPorId(id);
        if (UtilObjeto.esNulo(entidad)) {
            throw UcoParkingException.crear("No se encontró el país con el identificador indicado.");
        }
        return PaisEntidadAssembler.obtenerInstancia().ensamblarDominio(entidad);
    }
}
