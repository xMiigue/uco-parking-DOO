package co.edu.uco.ucoparking.negocio.casouso.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.dto.DepartamentoDTO;
import co.edu.uco.ucoparking.entidad.DepartamentoEntidad;
import co.edu.uco.ucoparking.negocio.assembler.dto.impl.DepartamentoDTOAssembler;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.DepartamentoEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.DepartamentoNegocio;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;

public class DepartamentoNegocioImpl implements DepartamentoNegocio {

    @Override
    public void crear(final FabricaDAO fabrica, final DepartamentoDTO dto) {
        // TODO: agregar validaciones de negocio
        DepartamentoDominio dominio = DepartamentoDTOAssembler.obtenerInstancia().ensamblarDominio(dto);
        DepartamentoEntidad entidad = DepartamentoEntidadAssembler.obtenerInstancia().ensamblarEntidad(dominio);
        fabrica.obtenerDepartamentoDAO().crear(entidad);
    }

    @Override
    public DepartamentoDTO recuperarPorId(final FabricaDAO fabrica, final UUID id) {
        DepartamentoEntidad entidad = fabrica.obtenerDepartamentoDAO().recuperarPorId(id);
        DepartamentoDominio dominio = DepartamentoEntidadAssembler.obtenerInstancia().ensamblarDominio(entidad);
        return DepartamentoDTOAssembler.obtenerInstancia().ensamblarDTO(dominio);
    }

    @Override
    public List<DepartamentoDTO> recuperarTodos(final FabricaDAO fabrica, final DepartamentoDTO filtro) {
        DepartamentoDominio filtroDominio = DepartamentoDTOAssembler.obtenerInstancia().ensamblarDominio(filtro);
        DepartamentoEntidad filtroEntidad = DepartamentoEntidadAssembler.obtenerInstancia().ensamblarEntidad(filtroDominio);
        List<DepartamentoEntidad> entidades = fabrica.obtenerDepartamentoDAO().recuperarTodos(filtroEntidad);
        List<DepartamentoDTO> dtos = new ArrayList<>();
        for (DepartamentoEntidad entidad : entidades) {
            DepartamentoDominio dominio = DepartamentoEntidadAssembler.obtenerInstancia().ensamblarDominio(entidad);
            dtos.add(DepartamentoDTOAssembler.obtenerInstancia().ensamblarDTO(dominio));
        }
        return dtos;
    }

    @Override
    public void actualizar(final FabricaDAO fabrica, final DepartamentoDTO dto) {
        // TODO: agregar validaciones de negocio
        DepartamentoDominio dominio = DepartamentoDTOAssembler.obtenerInstancia().ensamblarDominio(dto);
        DepartamentoEntidad entidad = DepartamentoEntidadAssembler.obtenerInstancia().ensamblarEntidad(dominio);
        fabrica.obtenerDepartamentoDAO().actualizar(entidad);
    }

    @Override
    public void eliminar(final FabricaDAO fabrica, final UUID id) {
        // TODO: agregar validaciones de negocio
        fabrica.obtenerDepartamentoDAO().eliminar(id);
    }
}
