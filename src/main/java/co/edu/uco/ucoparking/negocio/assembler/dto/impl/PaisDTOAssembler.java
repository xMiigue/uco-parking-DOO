package co.edu.uco.ucoparking.negocio.assembler.dto.impl;

import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.DTOAssembler;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;

public final class PaisDTOAssembler implements DTOAssembler<PaisDominio, PaisDTO> {

    private static final PaisDTOAssembler INSTANCIA = new PaisDTOAssembler();

    private PaisDTOAssembler() {
        super();
    }

    public static PaisDTOAssembler obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public PaisDominio ensamblarDominio(final PaisDTO dto) {
        var a = UtilObjeto.retornarValorPorDefecto(dto, new PaisDTO.Builder().build());
        return new PaisDominio.Builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .build();
    }

    @Override
    public PaisDTO ensamblarDTO(final PaisDominio dominio) {
        var a = UtilObjeto.retornarValorPorDefecto(dominio, new PaisDominio.Builder().build());
        return new PaisDTO.Builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .build();
    }
}
