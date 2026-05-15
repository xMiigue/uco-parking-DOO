package co.edu.uco.ucoparking.negocio.assembler.dto.impl;

import co.edu.uco.ucoparking.dto.DepartamentoDTO;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.DTOAssembler;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorObjeto;

public final class DepartamentoDTOAssembler implements DTOAssembler<DepartamentoDominio, DepartamentoDTO> {

    private static final DepartamentoDTOAssembler INSTANCIA = new DepartamentoDTOAssembler();

    private DepartamentoDTOAssembler() {
        super();
    }

    public static final DepartamentoDTOAssembler obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public DepartamentoDominio ensamblarDominio(final DepartamentoDTO dto) {
        var deptoAEnsamblar = ManejadorObjeto.retornarValorPorDefecto(dto, new DepartamentoDTO.Builder().build());
        var paisDominio = new PaisDominio.Builder()
                .id(deptoAEnsamblar.getPais().getId())
                .nombre(deptoAEnsamblar.getPais().getNombre())
                .build();
        return new DepartamentoDominio.Builder()
                .id(deptoAEnsamblar.getId())
                .nombre(deptoAEnsamblar.getNombre())
                .pais(paisDominio)
                .build();
    }

    @Override
    public DepartamentoDTO ensamblarDTO(final DepartamentoDominio dominio) {
        var deptoAEnsamblar = ManejadorObjeto.retornarValorPorDefecto(dominio, new DepartamentoDominio.Builder().build());
        var paisDTO = new PaisDTO.Builder()
                .id(deptoAEnsamblar.getPais().getId())
                .nombre(deptoAEnsamblar.getPais().getNombre())
                .build();
        return new DepartamentoDTO.Builder()
                .id(deptoAEnsamblar.getId())
                .nombre(deptoAEnsamblar.getNombre())
                .pais(paisDTO)
                .build();
    }
}
