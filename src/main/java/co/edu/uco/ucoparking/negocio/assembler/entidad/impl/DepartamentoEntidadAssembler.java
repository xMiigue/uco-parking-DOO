package co.edu.uco.ucoparking.negocio.assembler.entidad.impl;

import co.edu.uco.ucoparking.entidad.DepartamentoEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.EntidadAssembler;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;

public final class DepartamentoEntidadAssembler implements EntidadAssembler<DepartamentoDominio, DepartamentoEntidad> {

    private static final DepartamentoEntidadAssembler INSTANCIA = new DepartamentoEntidadAssembler();

    private DepartamentoEntidadAssembler() {
        super();
    }

    public static DepartamentoEntidadAssembler obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public DepartamentoEntidad ensamblarEntidad(final DepartamentoDominio dominio) {
        var a = UtilObjeto.retornarValorPorDefecto(dominio, new DepartamentoDominio.Builder().build());
        return new DepartamentoEntidad.Builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .idPais(a.getPais().getId())
                .build();
    }

    @Override
    public DepartamentoDominio ensamblarDominio(final DepartamentoEntidad entidad) {
        var a = UtilObjeto.retornarValorPorDefecto(entidad, new DepartamentoEntidad.Builder().build());
        var pais = new PaisDominio.Builder()
                .id(a.getIdPais())
                .build();
        return new DepartamentoDominio.Builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .pais(pais)
                .build();
    }
}
