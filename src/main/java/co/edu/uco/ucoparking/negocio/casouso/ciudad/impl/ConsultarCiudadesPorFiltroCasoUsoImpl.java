package co.edu.uco.ucoparking.negocio.casouso.ciudad.impl;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.entidad.CiudadEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.CiudadEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.ciudad.ConsultarCiudadesPorFiltroCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.CiudadDominio;

public final class ConsultarCiudadesPorFiltroCasoUsoImpl implements ConsultarCiudadesPorFiltroCasoUso {

    private final FabricaDAO fabricaDAO;

    public ConsultarCiudadesPorFiltroCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public List<CiudadDominio> ejecutar(final CiudadDominio filtro) {
        final CiudadEntidad entidadFiltro = CiudadEntidadAssembler.obtenerInstancia().ensamblarEntidad(
                filtro != null ? filtro : new CiudadDominio.Builder().build());
        return fabricaDAO.obtenerCiudadDAO().consultarPorFiltro(entidadFiltro).stream()
                .map(CiudadEntidadAssembler.obtenerInstancia()::ensamblarDominio)
                .collect(Collectors.toList());
    }
}
