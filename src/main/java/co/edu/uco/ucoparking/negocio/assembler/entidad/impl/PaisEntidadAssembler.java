package co.edu.uco.ucoparking.negocio.assembler.entidad.impl;

import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.EntidadAssembler;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.UtilObjeto;

public final class PaisEntidadAssembler implements EntidadAssembler<PaisDominio, PaisEntidad> {

    private static final PaisEntidadAssembler INSTANCIA = new PaisEntidadAssembler();

    private PaisEntidadAssembler() {
        super();
    }

    public static final PaisEntidadAssembler obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public PaisEntidad ensamblarEntidad(final PaisDominio dominio) {
        // Protege contra nulos usando el valor por defecto de PaisDominio
        var paisAEnsamblar = UtilObjeto.obtenerValorDefecto(dominio, new PaisDominio.Builder().build());
        return new PaisEntidad.Builder()
                .id(paisAEnsamblar.getId())
                .nombre(paisAEnsamblar.getNombre())
                .build();
    }

    @Override
    public PaisDominio ensamblarDominio(final PaisEntidad entidad) {
        // Protege contra nulos usando el valor por defecto de PaisEntidad
        var paisAEnsamblar = UtilObjeto.obtenerValorDefecto(entidad, new PaisEntidad.Builder().build());
        return new PaisDominio.Builder()
                .id(paisAEnsamblar.getId())
                .nombre(paisAEnsamblar.getNombre())
                .build();
    }
}
