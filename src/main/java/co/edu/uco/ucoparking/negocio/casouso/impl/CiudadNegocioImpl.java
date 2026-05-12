package co.edu.uco.ucoparking.negocio.casouso.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.dto.CiudadDTO;
import co.edu.uco.ucoparking.entidad.CiudadEntidad;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.CiudadDTOAssembler;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.CiudadEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.CiudadNegocio;
import co.edu.uco.ucoparking.negocio.dominio.CiudadDominio;

public class CiudadNegocioImpl implements CiudadNegocio {

    @Override
    public void crear(final FabricaDAO fabrica, final CiudadDTO dto) {
        // TODO: agregar validaciones de negocio
        CiudadDominio dominio = CiudadDTOAssembler.obtenerInstancia().ensamblarDominio(dto);
        CiudadEntidad entidad = CiudadEntidadAssembler.obtenerInstancia().ensamblarEntidad(dominio);
        fabrica.obtenerCiudadDAO().crear(entidad);
    }

    @Override
    public CiudadDTO recuperarPorId(final FabricaDAO fabrica, final UUID id) {
        CiudadEntidad entidad = fabrica.obtenerCiudadDAO().recuperarPorId(id);
        CiudadDominio dominio = CiudadEntidadAssembler.obtenerInstancia().ensamblarDominio(entidad);
        return CiudadDTOAssembler.obtenerInstancia().ensamblarDTO(dominio);
    }

    @Override
    public List<CiudadDTO> recuperarTodos(final FabricaDAO fabrica, final CiudadDTO filtro) {
        CiudadDominio filtroDominio = CiudadDTOAssembler.obtenerInstancia().ensamblarDominio(filtro);
        CiudadEntidad filtroEntidad = CiudadEntidadAssembler.obtenerInstancia().ensamblarEntidad(filtroDominio);
        List<CiudadEntidad> entidades = fabrica.obtenerCiudadDAO().recuperarTodos(filtroEntidad);
        List<CiudadDTO> dtos = new ArrayList<>();
        for (CiudadEntidad entidad : entidades) {
            CiudadDominio dominio = CiudadEntidadAssembler.obtenerInstancia().ensamblarDominio(entidad);
            dtos.add(CiudadDTOAssembler.obtenerInstancia().ensamblarDTO(dominio));
        }
        return dtos;
    }

    @Override
    public void actualizar(final FabricaDAO fabrica, final CiudadDTO dto) {
        // TODO: agregar validaciones de negocio
        CiudadDominio dominio = CiudadDTOAssembler.obtenerInstancia().ensamblarDominio(dto);
        CiudadEntidad entidad = CiudadEntidadAssembler.obtenerInstancia().ensamblarEntidad(dominio);
        fabrica.obtenerCiudadDAO().actualizar(entidad);
    }

    @Override
    public void eliminar(final FabricaDAO fabrica, final UUID id) {
        // TODO: agregar validaciones de negocio
        fabrica.obtenerCiudadDAO().eliminar(id);
    }
}
