package co.edu.uco.ucoparking.negocio.casouso.pais.impl;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.PaisEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.pais.ActualizarPaisCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilTexto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class ActualizarPaisCasoUsoImpl implements ActualizarPaisCasoUso {

    private final FabricaDAO fabricaDAO;

    public ActualizarPaisCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public void ejecutar(final PaisDominio datos) {
        validar(datos);
        final PaisEntidad entidad = PaisEntidadAssembler.obtenerInstancia().ensamblarEntidad(datos);
        fabricaDAO.obtenerPaisDAO().actualizar(datos.getId(), entidad);
    }

    private void validar(final PaisDominio datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw UcoParkingException.crear("Los datos del país son obligatorios.");
        }
        if (UtilUUID.esUUIDPorDefecto(datos.getId())) {
            throw UcoParkingException.crear("El identificador del país es obligatorio para actualizar.");
        }
        if (UtilObjeto.esNulo(fabricaDAO.obtenerPaisDAO().consultarPorId(datos.getId()))) {
            throw UcoParkingException.crear("No se encontró el país con el identificador indicado.");
        }
        if (UtilTexto.esNula(datos.getNombre())) {
            throw UcoParkingException.crear("El nombre del país es obligatorio.");
        }
        if (datos.getNombre().length() > 100) {
            throw UcoParkingException.crear("El nombre del país no puede superar los 100 caracteres.");
        }
        final PaisEntidad filtro = PaisEntidadAssembler.obtenerInstancia().ensamblarEntidad(
                new PaisDominio.Builder().nombre(datos.getNombre()).build());
        fabricaDAO.obtenerPaisDAO().consultarPorFiltro(filtro).stream()
                .filter(p -> !p.getId().equals(datos.getId()))
                .findFirst()
                .ifPresent(p -> {
                    throw UcoParkingException.crear("Ya existe otro país con el nombre indicado.");
                });
    }
}
