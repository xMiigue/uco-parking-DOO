package co.edu.uco.ucoparking.negocio.assembler.entidad.impl;

import co.edu.uco.ucoparking.entidad.CiudadEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.EntidadAssembler;
import co.edu.uco.ucoparking.negocio.dominio.CiudadDominio;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;

public final class CiudadEntidadAssembler implements EntidadAssembler<CiudadDominio, CiudadEntidad> {

    private static final CiudadEntidadAssembler INSTANCIA = new CiudadEntidadAssembler();

    private CiudadEntidadAssembler() {
        super();
    }

    public static CiudadEntidadAssembler obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public CiudadEntidad ensamblarEntidad(final CiudadDominio dominio) {
        var a = UtilObjeto.retornarValorPorDefecto(dominio, new CiudadDominio.Builder().build());
        return new CiudadEntidad.Builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .idDepartamento(a.getDepartamento().getId())
                .build();
    }

    @Override
    public CiudadDominio ensamblarDominio(final CiudadEntidad entidad) {
        var a = UtilObjeto.retornarValorPorDefecto(entidad, new CiudadEntidad.Builder().build());
        var departamento = new DepartamentoDominio.Builder()
                .id(a.getIdDepartamento())
                .build();
        return new CiudadDominio.Builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .departamento(departamento)
                .build();
    }
}
