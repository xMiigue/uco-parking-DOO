package co.edu.uco.ucoparking.negocio.casouso.pais.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.negocio.assembler.entidad.impl.PaisEntidadAssembler;
import co.edu.uco.ucoparking.negocio.casouso.pais.RegistrarNuevoPaisCasoUso;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilTexto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class RegistrarNuevoPaisCasoUsoImpl implements RegistrarNuevoPaisCasoUso {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrarNuevoPaisCasoUsoImpl.class);

    private final FabricaDAO fabricaDAO;

    public RegistrarNuevoPaisCasoUsoImpl(final FabricaDAO fabricaDAO) {
        super();
        this.fabricaDAO = fabricaDAO;
    }

    @Override
    public void ejecutar(final PaisDominio datos) {
        LOGGER.info("Iniciando registro de nuevo país...");
        try {
            validar(datos);
            final PaisEntidad entidad = PaisEntidadAssembler.obtenerInstancia().ensamblarEntidad(
                    new PaisDominio.Builder()
                            .id(UtilUUID.generarNuevoUUID())
                            .nombre(datos.getNombre())
                            .build());
            fabricaDAO.obtenerPaisDAO().crear(entidad);
            LOGGER.info("País registrado exitosamente con nombre: {}", datos.getNombre());
        } catch (final UcoParkingException excepcion) {
            LOGGER.warn("Error de negocio al registrar país: {}", excepcion.getMensajeUsuario());
            throw excepcion;
        } catch (final Exception excepcion) {
            LOGGER.error("Error inesperado al registrar país.", excepcion);
            throw UcoParkingException.crear(
                    "Se presentó un error inesperado al registrar el país.",
                    excepcion.getMessage());
        }
    }

    private void validar(final PaisDominio datos) {
        LOGGER.debug("Validando datos del país...");
        if (UtilObjeto.esNulo(datos)) {
            throw UcoParkingException.crear("Los datos del país son obligatorios.");
        }
        if (UtilTexto.esNula(datos.getNombre())) {
            throw UcoParkingException.crear("El nombre del país es obligatorio.");
        }
        if (datos.getNombre().length() > 100) {
            throw UcoParkingException.crear("El nombre del país no puede superar los 100 caracteres.");
        }
        final PaisEntidad filtro = PaisEntidadAssembler.obtenerInstancia().ensamblarEntidad(
                new PaisDominio.Builder().nombre(datos.getNombre()).build());
        if (!fabricaDAO.obtenerPaisDAO().consultarPorFiltro(filtro).isEmpty()) {
            throw UcoParkingException.crear("Ya existe un país con el nombre indicado.");
        }
        LOGGER.debug("Validación completada sin errores.");
    }
}