package co.edu.uco.ucoparking.negocio.assembler.dto.impl;

import co.edu.uco.ucoparking.dto.CiudadDTO;
import co.edu.uco.ucoparking.dto.DepartamentoDTO;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.DTOAssembler;
import co.edu.uco.ucoparking.negocio.dominio.CiudadDominio;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.UtilObjeto;

public final class CiudadDTOAssembler implements DTOAssembler<CiudadDominio, CiudadDTO> {

    private static final CiudadDTOAssembler INSTANCIA = new CiudadDTOAssembler();

    private CiudadDTOAssembler() {
        super();
    }

    public static final CiudadDTOAssembler obtenerInstancia() {
        return INSTANCIA;
    }

    @Override
    public CiudadDominio ensamblarDominio(final CiudadDTO dto) {
        var ciudadAEnsamblar = UtilObjeto.obtenerValorDefecto(dto, new CiudadDTO.Builder().build());
        var paisDominio = new PaisDominio.Builder()
                .id(ciudadAEnsamblar.getDepartamento().getPais().getId())
                .nombre(ciudadAEnsamblar.getDepartamento().getPais().getNombre())
                .build();
        var departamentoDominio = new DepartamentoDominio.Builder()
                .id(ciudadAEnsamblar.getDepartamento().getId())
                .nombre(ciudadAEnsamblar.getDepartamento().getNombre())
                .pais(paisDominio)
                .build();
        return new CiudadDominio.Builder()
                .id(ciudadAEnsamblar.getId())
                .nombre(ciudadAEnsamblar.getNombre())
                .departamento(departamentoDominio)
                .build();
    }

    @Override
    public CiudadDTO ensamblarDTO(final CiudadDominio dominio) {
        var ciudadAEnsamblar = UtilObjeto.obtenerValorDefecto(dominio, new CiudadDominio.Builder().build());
        var paisDTO = new PaisDTO.Builder()
                .id(ciudadAEnsamblar.getDepartamento().getPais().getId())
                .nombre(ciudadAEnsamblar.getDepartamento().getPais().getNombre())
                .build();
        var departamentoDTO = new DepartamentoDTO.Builder()
                .id(ciudadAEnsamblar.getDepartamento().getId())
                .nombre(ciudadAEnsamblar.getDepartamento().getNombre())
                .pais(paisDTO)
                .build();
        return new CiudadDTO.Builder()
                .id(ciudadAEnsamblar.getId())
                .nombre(ciudadAEnsamblar.getNombre())
                .departamento(departamentoDTO)
                .build();
    }
}
