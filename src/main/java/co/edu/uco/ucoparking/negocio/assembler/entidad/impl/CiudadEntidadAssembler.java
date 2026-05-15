package co.edu.uco.ucoparking.negocio.assembler.entidad.impl;

import co.edu.uco.ucoparking.entidad.CiudadEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.EntidadAssembler;
import co.edu.uco.ucoparking.negocio.dominio.CiudadDominio;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorObjeto;

public final class CiudadEntidadAssembler implements EntidadAssembler<CiudadDominio, CiudadEntidad> {

    private static final CiudadEntidadAssembler INSTANCIA = new CiudadEntidadAssembler();

    private CiudadEntidadAssembler() {
        super();
    }

    public static final CiudadEntidadAssembler obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public CiudadEntidad ensamblarEntidad(final CiudadDominio dominio) {
        var ciudadAEnsamblar = ManejadorObjeto.retornarValorPorDefecto(dominio, new CiudadDominio.Builder().build());
        return new CiudadEntidad.Builder()
                .id(ciudadAEnsamblar.getId())
                .nombre(ciudadAEnsamblar.getNombre())
                .idDepartamento(ciudadAEnsamblar.getDepartamento().getId())
                .build();
    }

    @Override
    public CiudadDominio ensamblarDominio(final CiudadEntidad entidad) {
        var ciudadAEnsamblar = ManejadorObjeto.retornarValorPorDefecto(entidad, new CiudadEntidad.Builder().build());
        var departamento = new DepartamentoDominio.Builder()
                .id(ciudadAEnsamblar.getIdDepartamento())
                .build();
        return new CiudadDominio.Builder()
                .id(ciudadAEnsamblar.getId())
                .nombre(ciudadAEnsamblar.getNombre())
                .departamento(departamento)
                .build();
    }
}
