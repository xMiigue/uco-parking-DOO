package co.edu.uco.ucoparking.negocio.casouso.ciudad.impl;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.entidad.CiudadEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.CiudadEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.ciudad.RegistrarNuevaCiudadCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.CiudadDominio;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilTexto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class RegistrarNuevaCiudadCasoUsoImpl implements RegistrarNuevaCiudadCasoUso {

    private final FabricaDAO fabricaDAO;

    public RegistrarNuevaCiudadCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public void ejecutar(final CiudadDominio datos) {
        validar(datos);
        final CiudadEntidad entidad = CiudadEntidadAssembler.obtenerInstancia().ensamblarEntidad(
                new CiudadDominio.Builder()
                        .id(UtilUUID.generarNuevoUUID())
                        .nombre(datos.getNombre())
                        .departamento(datos.getDepartamento())
                        .build());
        fabricaDAO.obtenerCiudadDAO().crear(entidad);
    }

    private void validar(final CiudadDominio datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw UcoParkingException.crear("Los datos de la ciudad son obligatorios.");
        }
        if (UtilTexto.esNula(datos.getNombre())) {
            throw UcoParkingException.crear("El nombre de la ciudad es obligatorio.");
        }
        if (datos.getNombre().length() > 100) {
            throw UcoParkingException.crear("El nombre de la ciudad no puede superar los 100 caracteres.");
        }
        if (UtilObjeto.esNulo(datos.getDepartamento()) || UtilUUID.esUUIDPorDefecto(datos.getDepartamento().getId())) {
            throw UcoParkingException.crear("El departamento de la ciudad es obligatorio.");
        }
        final CiudadEntidad filtro = CiudadEntidadAssembler.obtenerInstancia().ensamblarEntidad(
                new CiudadDominio.Builder()
                        .nombre(datos.getNombre())
                        .departamento(new DepartamentoDominio.Builder().id(datos.getDepartamento().getId()).build())
                        .build());
        if (!fabricaDAO.obtenerCiudadDAO().consultarPorFiltro(filtro).isEmpty()) {
            throw UcoParkingException.crear("Ya existe una ciudad con el nombre indicado en ese departamento.");
        }
    }
}
