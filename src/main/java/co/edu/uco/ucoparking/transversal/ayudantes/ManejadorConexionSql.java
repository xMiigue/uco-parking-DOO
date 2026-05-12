package co.edu.uco.ucoparking.transversal.ayudantes;

import java.sql.Connection;
import java.sql.SQLException;

import co.edu.uco.ucoparking.transversal.excepciones.ExcepcionBaseUcoParking;

public final class ManejadorConexionSql {

    private ManejadorConexionSql() {
        super();
    }

    public static void verificarConexionNoNula(final Connection conexion) {
        if (ManejadorObjeto.esNulo(conexion)) {
            throw ExcepcionBaseUcoParking.crear(
                    MensajesEnum.ERROR_USUARIO_CONEXION_SQL_NULA.getContenido(),
                    MensajesEnum.ERROR_TECNICO_CONEXION_SQL_NULA.getContenido());
        }
    }

    public static void verificarConexionAbierta(final Connection conexion) {
        verificarConexionNoNula(conexion);
        try {
            if (conexion.isClosed()) {
                throw ExcepcionBaseUcoParking.crear(
                        MensajesEnum.ERROR_USUARIO_CONEXION_SQL_CERRADA.getContenido(),
                        MensajesEnum.ERROR_TECNICO_CONEXION_SQL_CERRADA.getContenido());
            }
        } catch (final ExcepcionBaseUcoParking excepcion) {
            throw excepcion;
        } catch (final SQLException excepcion) {
            throw ExcepcionBaseUcoParking.crear(excepcion,
                    MensajesEnum.ERROR_USUARIO_VALIDANDO_ESTADO_CONEXION.getContenido(),
                    MensajesEnum.ERROR_TECNICO_VALIDANDO_ESTADO_CONEXION.getContenido());
        }
    }

    public static void verificarTransaccionIniciada(final Connection conexion) {
        verificarConexionAbierta(conexion);
        try {
            if (conexion.getAutoCommit()) {
                throw ExcepcionBaseUcoParking.crear(
                        MensajesEnum.ERROR_USUARIO_TRANSACCION_NO_INICIADA.getContenido(),
                        MensajesEnum.ERROR_TECNICO_TRANSACCION_NO_INICIADA.getContenido());
            }
        } catch (final ExcepcionBaseUcoParking excepcion) {
            throw excepcion;
        } catch (final SQLException excepcion) {
            throw ExcepcionBaseUcoParking.crear(excepcion,
                    MensajesEnum.ERROR_USUARIO_VALIDANDO_ESTADO_CONEXION.getContenido(),
                    MensajesEnum.ERROR_TECNICO_VALIDANDO_ESTADO_CONEXION.getContenido());
        }
    }

    public static void verificarTransaccionNoIniciada(final Connection conexion) {
        verificarConexionAbierta(conexion);
        try {
            if (!conexion.getAutoCommit()) {
                throw ExcepcionBaseUcoParking.crear(
                        MensajesEnum.ERROR_USUARIO_TRANSACCION_YA_INICIADA.getContenido(),
                        MensajesEnum.ERROR_TECNICO_TRANSACCION_YA_INICIADA.getContenido());
            }
        } catch (final ExcepcionBaseUcoParking excepcion) {
            throw excepcion;
        } catch (final SQLException excepcion) {
            throw ExcepcionBaseUcoParking.crear(excepcion,
                    MensajesEnum.ERROR_USUARIO_VALIDANDO_ESTADO_CONEXION.getContenido(),
                    MensajesEnum.ERROR_TECNICO_VALIDANDO_ESTADO_CONEXION.getContenido());
        }
    }

    public static Connection establecerConexion(final Connection conexion) {
        verificarConexionAbierta(conexion);
        return conexion;
    }
}
