package co.edu.uco.ucoparking.negocio.casouso.pais.impl;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.PaisEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.pais.ConsultarPaisesPorFiltroCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;

public final class ConsultarPaisesPorFiltroCasoUsoImpl implements ConsultarPaisesPorFiltroCasoUso {

    private final FabricaDAO fabricaDAO;

    public ConsultarPaisesPorFiltroCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public List<PaisDominio> ejecutar(final PaisDominio filtro) {
        final PaisEntidad entidadFiltro = PaisEntidadAssembler.obtenerInstancia().ensamblarEntidad(
                filtro != null ? filtro : new PaisDominio.Builder().build());
        return fabricaDAO.obtenerPaisDAO().consultarPorFiltro(entidadFiltro).stream()
                .map(PaisEntidadAssembler.obtenerInstancia()::ensamblarDominio)
                .collect(Collectors.toList());
    }
}
