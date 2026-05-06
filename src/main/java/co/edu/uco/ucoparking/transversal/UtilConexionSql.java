package co.edu.uco.ucoparking.transversal;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

public final class UtilConexionSql {

    private UtilConexionSql() {
        super();
    }

    public static void verificarConexionNoNula(final Connection conexion) {
        if (UtilObjeto.esNulo(conexion)) {
            throw UcoParkingException.crear(
                    MensajesEnum.ERROR_USUARIO_CONEXION_SQL_NULA.getContenido(),
                    MensajesEnum.ERROR_TECNICO_CONEXION_SQL_NULA.getContenido());
        }
    }

    public static void verificarConexionAbierta(final Connection conexion) {
        verificarConexionNoNula(conexion);
        try {
            if (conexion.isClosed()) {
                throw UcoParkingException.crear(
                        MensajesEnum.ERROR_USUARIO_CONEXION_SQL_CERRADA.getContenido(),
                        MensajesEnum.ERROR_TECNICO_CONEXION_SQL_CERRADA.getContenido());
            }
        } catch (final UcoParkingException excepcion) {
            throw excepcion;
        } catch (final SQLException excepcion) {
            throw UcoParkingException.crear(excepcion,
                    MensajesEnum.ERROR_USUARIO_VALIDANDO_ESTADO_CONEXION.getContenido(),
                    MensajesEnum.ERROR_TECNICO_VALIDANDO_ESTADO_CONEXION.getContenido());
        }
    }

    public static void verificarTransaccionIniciada(final Connection conexion) {
        verificarConexionAbierta(conexion);
        try {
            if (conexion.getAutoCommit()) {
                throw UcoParkingException.crear(
                        MensajesEnum.ERROR_USUARIO_TRANSACCION_NO_INICIADA.getContenido(),
                        MensajesEnum.ERROR_TECNICO_TRANSACCION_NO_INICIADA.getContenido());
            }
        } catch (final UcoParkingException excepcion) {
            throw excepcion;
        } catch (final SQLException excepcion) {
            throw UcoParkingException.crear(excepcion,
                    MensajesEnum.ERROR_USUARIO_VALIDANDO_ESTADO_CONEXION.getContenido(),
                    MensajesEnum.ERROR_TECNICO_VALIDANDO_ESTADO_CONEXION.getContenido());
        }
    }

    public static void verificarTransaccionNoIniciada(final Connection conexion) {
        verificarConexionAbierta(conexion);
        try {
            if (!conexion.getAutoCommit()) {
                throw UcoParkingException.crear(
                        MensajesEnum.ERROR_USUARIO_TRANSACCION_YA_INICIADA.getContenido(),
                        MensajesEnum.ERROR_TECNICO_TRANSACCION_YA_INICIADA.getContenido());
            }
        } catch (final UcoParkingException excepcion) {
            throw excepcion;
        } catch (final SQLException excepcion) {
            throw UcoParkingException.crear(excepcion,
                    MensajesEnum.ERROR_USUARIO_VALIDANDO_ESTADO_CONEXION.getContenido(),
                    MensajesEnum.ERROR_TECNICO_VALIDANDO_ESTADO_CONEXION.getContenido());
        }
    }

    public static Connection establecerConexion(final Connection conexion) {
        verificarConexionAbierta(conexion);
        return conexion;
    }
}
