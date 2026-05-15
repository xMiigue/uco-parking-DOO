package co.edu.uco.ucoparking.negocio.casouso.departamento.impl;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.entidad.DepartamentoEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.DepartamentoEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.departamento.RegistrarNuevoDepartamentoCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilTexto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class RegistrarNuevoDepartamentoCasoUsoImpl implements RegistrarNuevoDepartamentoCasoUso {

    private final FabricaDAO fabricaDAO;

    public RegistrarNuevoDepartamentoCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public void ejecutar(final DepartamentoDominio datos) {
        validar(datos);
        final DepartamentoEntidad entidad = DepartamentoEntidadAssembler.obtenerInstancia().ensamblarEntidad(
                new DepartamentoDominio.Builder()
                        .id(UtilUUID.generarNuevoUUID())
                        .nombre(datos.getNombre())
                        .pais(datos.getPais())
                        .build());
        fabricaDAO.obtenerDepartamentoDAO().crear(entidad);
    }

    private void validar(final DepartamentoDominio datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw UcoParkingException.crear("Los datos del departamento son obligatorios.");
        }
        if (UtilTexto.esNula(datos.getNombre())) {
            throw UcoParkingException.crear("El nombre del departamento es obligatorio.");
        }
        if (datos.getNombre().length() > 100) {
            throw UcoParkingException.crear("El nombre del departamento no puede superar los 100 caracteres.");
        }
        if (UtilObjeto.esNulo(datos.getPais()) || UtilUUID.esUUIDPorDefecto(datos.getPais().getId())) {
            throw UcoParkingException.crear("El país del departamento es obligatorio.");
        }
        final DepartamentoEntidad filtro = DepartamentoEntidadAssembler.obtenerInstancia().ensamblarEntidad(
                new DepartamentoDominio.Builder()
                        .nombre(datos.getNombre())
                        .pais(new PaisDominio.Builder().id(datos.getPais().getId()).build())
                        .build());
        if (!fabricaDAO.obtenerDepartamentoDAO().consultarPorFiltro(filtro).isEmpty()) {
            throw UcoParkingException.crear("Ya existe un departamento con el nombre indicado en ese país.");
        }
    }
}
