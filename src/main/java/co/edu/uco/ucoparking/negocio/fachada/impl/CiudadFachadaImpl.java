package co.edu.uco.ucoparking.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.controlador.dto.Respuesta;
import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.CiudadDTO;
import co.edu.uco.ucoparking.negocio.casouso.impl.CiudadNegocioImpl;
import co.edu.uco.ucoparking.negocio.fachada.CiudadFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public class CiudadFachadaImpl implements CiudadFachada {

    @Override
    public Respuesta<Void> crear(final CiudadDTO dto) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            new CiudadNegocioImpl().crear(fabrica, dto);
            fabrica.confirmarTransaccion();
            return Respuesta.exitosa(null);
        } catch (final UcoParkingException e) {
            if (fabrica != null) fabrica.cancelarTransaccion();
            return Respuesta.fallida(e.getMensajeUsuario());
        } finally {
            if (fabrica != null) fabrica.cerrarConexion();
        }
    }

    @Override
    public Respuesta<CiudadDTO> recuperarPorId(final UUID id) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            CiudadDTO resultado = new CiudadNegocioImpl().recuperarPorId(fabrica, id);
            fabrica.confirmarTransaccion();
            return Respuesta.exitosa(resultado);
        } catch (final UcoParkingException e) {
            if (fabrica != null) fabrica.cancelarTransaccion();
            return Respuesta.fallida(e.getMensajeUsuario());
        } finally {
            if (fabrica != null) fabrica.cerrarConexion();
        }
    }

    @Override
    public Respuesta<List<CiudadDTO>> recuperarTodos(final CiudadDTO filtro) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            List<CiudadDTO> resultado = new CiudadNegocioImpl().recuperarTodos(fabrica, filtro);
            fabrica.confirmarTransaccion();
            return Respuesta.exitosa(resultado);
        } catch (final UcoParkingException e) {
            if (fabrica != null) fabrica.cancelarTransaccion();
            return Respuesta.fallida(e.getMensajeUsuario());
        } finally {
            if (fabrica != null) fabrica.cerrarConexion();
        }
    }

    @Override
    public Respuesta<Void> actualizar(final CiudadDTO dto) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            new CiudadNegocioImpl().actualizar(fabrica, dto);
            fabrica.confirmarTransaccion();
            return Respuesta.exitosa(null);
        } catch (final UcoParkingException e) {
            if (fabrica != null) fabrica.cancelarTransaccion();
            return Respuesta.fallida(e.getMensajeUsuario());
        } finally {
            if (fabrica != null) fabrica.cerrarConexion();
        }
    }

    @Override
    public Respuesta<Void> eliminar(final UUID id) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            new CiudadNegocioImpl().eliminar(fabrica, id);
            fabrica.confirmarTransaccion();
            return Respuesta.exitosa(null);
        } catch (final UcoParkingException e) {
            if (fabrica != null) fabrica.cancelarTransaccion();
            return Respuesta.fallida(e.getMensajeUsuario());
        } finally {
            if (fabrica != null) fabrica.cerrarConexion();
        }
    }
}
