package co.edu.uco.ucoparking.datos.fabrica.sqlserver;

import co.edu.uco.ucoparking.datos.dao.CiudadDAO;
import co.edu.uco.ucoparking.datos.dao.DepartamentoDAO;
import co.edu.uco.ucoparking.datos.dao.PaisDAO;
import co.edu.uco.ucoparking.datos.dao.sqlserver.CiudadSqlServerDAO;
import co.edu.uco.ucoparking.datos.dao.sqlserver.DepartamentoSqlServerDAO;
import co.edu.uco.ucoparking.datos.dao.sqlserver.PaisSqlServerDAO;
import co.edu.uco.ucoparking.datos.fabrica.FabricaDAO;

public final class SqlServerFabricaDAO extends FabricaDAO {

    public SqlServerFabricaDAO() {
        super();
    }

    @Override
    public PaisDAO obtenerPaisDAO() {
        return new PaisSqlServerDAO(conexion);
    }

    @Override
    public DepartamentoDAO obtenerDepartamentoDAO() {
        return new DepartamentoSqlServerDAO(conexion);
    }

    @Override
    public CiudadDAO obtenerCiudadDAO() {
        return new CiudadSqlServerDAO(conexion);
    }
}
