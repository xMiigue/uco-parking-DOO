package co.edu.uco.ucoparking.negocio.casouso.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.PaisDTOAssembler;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.PaisEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.PaisNegocio;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.entidad.PaisEntidad;

public class PaisNegocioImpl implements PaisNegocio {

    @Override
    public void crear(final FabricaDAO fabrica, final PaisDTO dto) {
        // TODO: agregar validaciones de negocio
        PaisDominio dominio = PaisDTOAssembler.obtenerInstancia().ensamblarDominio(dto);
        PaisEntidad entidad = PaisEntidadAssembler.obtenerInstancia().ensamblarEntidad(dominio);
        fabrica.obtenerPaisDAO().crear(entidad);
    }

    @Override
    public PaisDTO recuperarPorId(final FabricaDAO fabrica, final UUID id) {
        PaisEntidad entidad = fabrica.obtenerPaisDAO().recuperarPorId(id);
        PaisDominio dominio = PaisEntidadAssembler.obtenerInstancia().ensamblarDominio(entidad);
        return PaisDTOAssembler.obtenerInstancia().ensamblarDTO(dominio);
    }

    @Override
    public List<PaisDTO> recuperarTodos(final FabricaDAO fabrica, final PaisDTO filtro) {
        PaisDominio filtroDominio = PaisDTOAssembler.obtenerInstancia().ensamblarDominio(filtro);
        PaisEntidad filtroEntidad = PaisEntidadAssembler.obtenerInstancia().ensamblarEntidad(filtroDominio);
        List<PaisEntidad> entidades = fabrica.obtenerPaisDAO().recuperarTodos(filtroEntidad);
        List<PaisDTO> dtos = new ArrayList<>();
        for (PaisEntidad entidad : entidades) {
            PaisDominio dominio = PaisEntidadAssembler.obtenerInstancia().ensamblarDominio(entidad);
            dtos.add(PaisDTOAssembler.obtenerInstancia().ensamblarDTO(dominio));
        }
        return dtos;
    }

    @Override
    public void actualizar(final FabricaDAO fabrica, final PaisDTO dto) {
        // TODO: agregar validaciones de negocio
        PaisDominio dominio = PaisDTOAssembler.obtenerInstancia().ensamblarDominio(dto);
        PaisEntidad entidad = PaisEntidadAssembler.obtenerInstancia().ensamblarEntidad(dominio);
        fabrica.obtenerPaisDAO().actualizar(dominio.getId(), entidad);
    }

    @Override
    public void eliminar(final FabricaDAO fabrica, final UUID id) {
        // TODO: agregar validaciones de negocio
        fabrica.obtenerPaisDAO().eliminar(id);
    }
}
