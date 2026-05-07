package co.edu.uco.ucoparking.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.controlador.dto.Respuesta;
import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.DepartamentoDTO;
import co.edu.uco.ucoparking.negocio.casouso.impl.DepartamentoNegocioImpl;
import co.edu.uco.ucoparking.negocio.fachada.DepartamentoFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public class DepartamentoFachadaImpl implements DepartamentoFachada {

    @Override
    public Respuesta<Void> crear(final DepartamentoDTO dto) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            new DepartamentoNegocioImpl().crear(fabrica, dto);
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
    public Respuesta<DepartamentoDTO> recuperarPorId(final UUID id) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            DepartamentoDTO resultado = new DepartamentoNegocioImpl().recuperarPorId(fabrica, id);
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
    public Respuesta<List<DepartamentoDTO>> recuperarTodos(final DepartamentoDTO filtro) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            List<DepartamentoDTO> resultado = new DepartamentoNegocioImpl().recuperarTodos(fabrica, filtro);
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
    public Respuesta<Void> actualizar(final DepartamentoDTO dto) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            new DepartamentoNegocioImpl().actualizar(fabrica, dto);
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
            new DepartamentoNegocioImpl().eliminar(fabrica, id);
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
