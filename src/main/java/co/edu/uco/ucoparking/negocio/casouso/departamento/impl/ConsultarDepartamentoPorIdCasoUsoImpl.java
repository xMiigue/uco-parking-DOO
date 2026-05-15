package co.edu.uco.ucoparking.negocio.casouso.departamento.impl;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.entidad.DepartamentoEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.DepartamentoEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.departamento.ConsultarDepartamentoPorIdCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class ConsultarDepartamentoPorIdCasoUsoImpl implements ConsultarDepartamentoPorIdCasoUso {

    private final FabricaDAO fabricaDAO;

    public ConsultarDepartamentoPorIdCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public DepartamentoDominio ejecutar(final UUID id) {
        if (UtilObjeto.esNulo(id) || UtilUUID.esUUIDPorDefecto(id)) {
            throw UcoParkingException.crear("El identificador del departamento es obligatorio para consultarlo.");
        }
        final DepartamentoEntidad entidad = fabricaDAO.obtenerDepartamentoDAO().consultarPorId(id);
        if (UtilObjeto.esNulo(entidad)) {
            throw UcoParkingException.crear("No se encontró el departamento con el identificador indicado.");
        }
        return DepartamentoEntidadAssembler.obtenerInstancia().ensamblarDominio(entidad);
    }
}
