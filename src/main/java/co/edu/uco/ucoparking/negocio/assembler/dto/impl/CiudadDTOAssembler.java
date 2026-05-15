package co.edu.uco.ucoparking.negocio.assembler.dto.impl;

import co.edu.uco.ucoparking.dto.CiudadDTO;
import co.edu.uco.ucoparking.dto.DepartamentoDTO;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.DTOAssembler;
import co.edu.uco.ucoparking.negocio.dominio.CiudadDominio;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;

public final class CiudadDTOAssembler implements DTOAssembler<CiudadDominio, CiudadDTO> {

    private static final CiudadDTOAssembler INSTANCIA = new CiudadDTOAssembler();

    private CiudadDTOAssembler() {
        super();
    }

    public static CiudadDTOAssembler obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public CiudadDominio ensamblarDominio(final CiudadDTO dto) {
        var a = UtilObjeto.retornarValorPorDefecto(dto, new CiudadDTO.Builder().build());
        var paisDominio = new PaisDominio.Builder()
                .id(a.getDepartamento().getPais().getId())
                .nombre(a.getDepartamento().getPais().getNombre())
                .build();
        var departamentoDominio = new DepartamentoDominio.Builder()
                .id(a.getDepartamento().getId())
                .nombre(a.getDepartamento().getNombre())
                .pais(paisDominio)
                .build();
        return new CiudadDominio.Builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .departamento(departamentoDominio)
                .build();
    }

    @Override
    public CiudadDTO ensamblarDTO(final CiudadDominio dominio) {
        var a = UtilObjeto.retornarValorPorDefecto(dominio, new CiudadDominio.Builder().build());
        var paisDTO = new PaisDTO.Builder()
                .id(a.getDepartamento().getPais().getId())
                .nombre(a.getDepartamento().getPais().getNombre())
                .build();
        var departamentoDTO = new DepartamentoDTO.Builder()
                .id(a.getDepartamento().getId())
                .nombre(a.getDepartamento().getNombre())
                .pais(paisDTO)
                .build();
        return new CiudadDTO.Builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .departamento(departamentoDTO)
                .build();
    }
}
