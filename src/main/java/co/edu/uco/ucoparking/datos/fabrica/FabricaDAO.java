package co.edu.uco.ucoparking.datos.fabrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.ucoparking.datos.dao.CiudadDAO;
import co.edu.uco.ucoparking.datos.dao.DepartamentoDAO;
import co.edu.uco.ucoparking.datos.dao.PaisDAO;
import co.edu.uco.ucoparking.datos.fabrica.sqlserver.SqlServerFabricaDAO;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingDatosException;

public abstract class FabricaDAO {

    private static final String URL = System.getenv("DB_URL") != null
            ? System.getenv("DB_URL")
            : "jdbc:sqlserver://localhost:1433;databaseName=UcoParking;encrypt=false;trustServerCertificate=true";
    private static final String USUARIO = System.getenv("DB_USERNAME") != null
            ? System.getenv("DB_USERNAME")
            : "sa";
    private static final String CLAVE = System.getenv("DB_PASSWORD") != null
            ? System.getenv("DB_PASSWORD")
            : "";

    protected Connection conexion;

    protected FabricaDAO() {
        super();
    }

    public abstract PaisDAO obtenerPaisDAO();

    public abstract DepartamentoDAO obtenerDepartamentoDAO();

    public abstract CiudadDAO obtenerCiudadDAO();

    public void abrirConexion() {
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al abrir la conexión con la base de datos.",
                    e.getMessage(), e);
        }
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al cerrar la conexión con la base de datos.",
                    e.getMessage(), e);
        }
    }

    public void iniciarTransaccion() {
        try {
            conexion.setAutoCommit(false);
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al iniciar la transacción.",
                    e.getMessage(), e);
        }
    }

    public void confirmarTransaccion() {
        try {
            conexion.commit();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al confirmar la transacción.",
                    e.getMessage(), e);
        }
    }

    public void cancelarTransaccion() {
        try {
            conexion.rollback();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al cancelar la transacción.",
                    e.getMessage(), e);
        }
    }

    public static FabricaDAO obtenerInstancia(final FabricaEnum tipo) {
        if (FabricaEnum.SQL_SERVER.equals(tipo)) {
            return new SqlServerFabricaDAO();
        }
        throw UcoParkingDatosException.crear(
                "Tipo de fábrica no soportado.",
                "FabricaEnum no reconocido: " + tipo,
                new Exception("FabricaEnum no reconocido: " + tipo));
    }
}
