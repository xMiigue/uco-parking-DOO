package co.edu.uco.ucoparking.negocio.casouso.departamento.impl;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.entidad.DepartamentoEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.DepartamentoEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.departamento.ConsultarDepartamentosPorFiltroCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.DepartamentoDominio;

public final class ConsultarDepartamentosPorFiltroCasoUsoImpl implements ConsultarDepartamentosPorFiltroCasoUso {

    private final FabricaDAO fabricaDAO;

    public ConsultarDepartamentosPorFiltroCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public List<DepartamentoDominio> ejecutar(final DepartamentoDominio filtro) {
        final DepartamentoEntidad entidadFiltro = DepartamentoEntidadAssembler.obtenerInstancia().ensamblarEntidad(
                filtro != null ? filtro : new DepartamentoDominio.Builder().build());
        return fabricaDAO.obtenerDepartamentoDAO().consultarPorFiltro(entidadFiltro).stream()
                .map(DepartamentoEntidadAssembler.obtenerInstancia()::ensamblarDominio)
                .collect(Collectors.toList());
    }
}
