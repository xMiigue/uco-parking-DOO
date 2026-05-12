package co.edu.uco.ucoparking.negocio.assembler.dto.impl;

import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.DTOAssembler;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorObjeto;

public final class PaisDTOAssembler implements DTOAssembler<PaisDominio, PaisDTO> {

    private static final PaisDTOAssembler INSTANCIA = new PaisDTOAssembler();

    private PaisDTOAssembler() {
        super();
    }

    public static final PaisDTOAssembler obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public PaisDominio ensamblarDominio(final PaisDTO dto) {
        var paisAEnsamblar = ManejadorObjeto.retornarValorPorDefecto(dto, new PaisDTO.Builder().build());
        return new PaisDominio.Builder()
                .id(paisAEnsamblar.getId())
                .nombre(paisAEnsamblar.getNombre())
                .build();
    }

    @Override
    public PaisDTO ensamblarDTO(final PaisDominio dominio) {
        var paisAEnsamblar = ManejadorObjeto.retornarValorPorDefecto(dominio, new PaisDominio.Builder().build());
        return new PaisDTO.Builder()
                .id(paisAEnsamblar.getId())
                .nombre(paisAEnsamblar.getNombre())
                .build();
    }
}
