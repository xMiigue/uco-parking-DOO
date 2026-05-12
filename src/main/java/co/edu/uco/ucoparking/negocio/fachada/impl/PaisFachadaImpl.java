package co.edu.uco.ucoparking.negocio.fachada.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.controlador.dto.Respuesta;
import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaEnum;
import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.casouso.impl.PaisNegocioImpl;
import co.edu.uco.ucoparking.negocio.fachada.PaisFachada;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public class PaisFachadaImpl implements PaisFachada {

    @Override
    public Respuesta<Void> crear(final PaisDTO dto) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            new PaisNegocioImpl().crear(fabrica, dto);
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
    public Respuesta<PaisDTO> recuperarPorId(final UUID id) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            PaisDTO resultado = new PaisNegocioImpl().recuperarPorId(fabrica, id);
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
    public Respuesta<List<PaisDTO>> recuperarTodos(final PaisDTO filtro) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            List<PaisDTO> resultado = new PaisNegocioImpl().recuperarTodos(fabrica, filtro);
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
    public Respuesta<Void> actualizar(final PaisDTO dto) {
        FabricaDAO fabrica = null;
        try {
            fabrica = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
            fabrica.abrirConexion();
            fabrica.iniciarTransaccion();
            new PaisNegocioImpl().actualizar(fabrica, dto);
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
            new PaisNegocioImpl().eliminar(fabrica, id);
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
