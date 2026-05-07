package co.edu.uco.ucoparking.negocio.assembler.entidad.impl;

import co.edu.uco.ucoparking.entidad.DepartamentoEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.EntidadAssembler;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.UtilObjeto;

public final class DepartamentoEntidadAssembler implements EntidadAssembler<DepartamentoDominio, DepartamentoEntidad> {

    private static final DepartamentoEntidadAssembler INSTANCIA = new DepartamentoEntidadAssembler();

    private DepartamentoEntidadAssembler() {
        super();
    }

    public static final DepartamentoEntidadAssembler obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public DepartamentoEntidad ensamblarEntidad(final DepartamentoDominio dominio) {
        var deptoAEnsamblar = UtilObjeto.obtenerValorDefecto(dominio, new DepartamentoDominio.Builder().build());
        return new DepartamentoEntidad.Builder()
                .id(deptoAEnsamblar.getId())
                .nombre(deptoAEnsamblar.getNombre())
                .idPais(deptoAEnsamblar.getPais().getId())
                .build();
    }

    @Override
    public DepartamentoDominio ensamblarDominio(final DepartamentoEntidad entidad) {
        var deptoAEnsamblar = UtilObjeto.obtenerValorDefecto(entidad, new DepartamentoEntidad.Builder().build());
        var pais = new PaisDominio.Builder()
                .id(deptoAEnsamblar.getIdPais())
                .build();
        return new DepartamentoDominio.Builder()
                .id(deptoAEnsamblar.getId())
                .nombre(deptoAEnsamblar.getNombre())
                .pais(pais)
                .build();
    }
}
