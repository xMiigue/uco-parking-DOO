package co.edu.uco.ucoparking.negocio.casouso.departamento.impl;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.entidad.DepartamentoEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.DepartamentoEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.departamento.ActualizarDepartamentoCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilTexto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class ActualizarDepartamentoCasoUsoImpl implements ActualizarDepartamentoCasoUso {

    private final FabricaDAO fabricaDAO;

    public ActualizarDepartamentoCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public void ejecutar(final DepartamentoDominio datos) {
        validar(datos);
        final DepartamentoEntidad entidad = DepartamentoEntidadAssembler.obtenerInstancia().ensamblarEntidad(datos);
        fabricaDAO.obtenerDepartamentoDAO().actualizar(datos.getId(), entidad);
    }

    private void validar(final DepartamentoDominio datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw UcoParkingException.crear("Los datos del departamento son obligatorios.");
        }
        if (UtilUUID.esUUIDPorDefecto(datos.getId())) {
            throw UcoParkingException.crear("El identificador del departamento es obligatorio para actualizar.");
        }
        if (UtilObjeto.esNulo(fabricaDAO.obtenerDepartamentoDAO().consultarPorId(datos.getId()))) {
            throw UcoParkingException.crear("No se encontró el departamento con el identificador indicado.");
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
        fabricaDAO.obtenerDepartamentoDAO().consultarPorFiltro(filtro).stream()
                .filter(d -> !d.getId().equals(datos.getId()))
                .findFirst()
                .ifPresent(d -> {
                    throw UcoParkingException.crear("Ya existe otro departamento con el nombre indicado en ese país.");
                });
    }
}
