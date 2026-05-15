package co.edu.uco.ucoparking.negocio.assembler.dto.impl;

import co.edu.uco.ucoparking.dto.DepartamentoDTO;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.DTOAssembler;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;

public final class DepartamentoDTOAssembler implements DTOAssembler<DepartamentoDominio, DepartamentoDTO> {

    private static final DepartamentoDTOAssembler INSTANCIA = new DepartamentoDTOAssembler();

    private DepartamentoDTOAssembler() {
        super();
    }

    public static DepartamentoDTOAssembler obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public DepartamentoDominio ensamblarDominio(final DepartamentoDTO dto) {
        var a = UtilObjeto.retornarValorPorDefecto(dto, new DepartamentoDTO.Builder().build());
        var paisDominio = new PaisDominio.Builder()
                .id(a.getPais().getId())
                .nombre(a.getPais().getNombre())
                .build();
        return new DepartamentoDominio.Builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .pais(paisDominio)
                .build();
    }

    @Override
    public DepartamentoDTO ensamblarDTO(final DepartamentoDominio dominio) {
        var a = UtilObjeto.retornarValorPorDefecto(dominio, new DepartamentoDominio.Builder().build());
        var paisDTO = new PaisDTO.Builder()
                .id(a.getPais().getId())
                .nombre(a.getPais().getNombre())
                .build();
        return new DepartamentoDTO.Builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .pais(paisDTO)
                .build();
    }
}
