package co.edu.uco.ucoparking.datos.dao.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.dao.DepartamentoDAO;
import co.edu.uco.ucoparking.datos.dao.sql.SQLDAO;
import co.edu.uco.ucoparking.datos.mapeador.DepartamentoMapper;
import co.edu.uco.ucoparking.datos.sql.DepartamentoSql;
import co.edu.uco.ucoparking.entidad.DepartamentoEntidad;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingDatosException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class DepartamentoSqlServerDAO extends SQLDAO implements DepartamentoDAO {

    public DepartamentoSqlServerDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(final DepartamentoEntidad entidad) {
        try (PreparedStatement ps = getConexion().prepareStatement(DepartamentoSql.CREAR)) {
            ps.setObject(1, entidad.getId());
            ps.setString(2, entidad.getNombre());
            ps.setObject(3, entidad.getIdPais());
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla departamento.", e.getMessage(), e);
        }
    }

    @Override
    public List<DepartamentoEntidad> consultarTodos() {
        final List<DepartamentoEntidad> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(DepartamentoSql.CONSULTAR_TODOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(DepartamentoMapper.obtenerInstancia().mapear(rs));
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla departamento.", e.getMessage(), e);
        }
        return resultado;
    }

    @Override
    public DepartamentoEntidad consultarPorId(final UUID id) {
        try (PreparedStatement ps = getConexion().prepareStatement(DepartamentoSql.CONSULTAR_POR_ID)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return DepartamentoMapper.obtenerInstancia().mapear(rs);
                }
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla departamento.", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<DepartamentoEntidad> consultarPorFiltro(final DepartamentoEntidad filtro) {
        final StringBuilder sql = new StringBuilder(DepartamentoSql.CONSULTAR_TODOS);
        final List<Object> parametros = new ArrayList<>();

        if (filtro != null) {
            if (!filtro.getNombre().isEmpty()) {
                sql.append(" AND nombre = ?");
                parametros.add(filtro.getNombre());
            }
            if (!UtilUUID.esUUIDPorDefecto(filtro.getIdPais())) {
                sql.append(" AND id_pais = ?");
                parametros.add(filtro.getIdPais());
            }
        }

        final List<DepartamentoEntidad> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.add(DepartamentoMapper.obtenerInstancia().mapear(rs));
                }
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla departamento.", e.getMessage(), e);
        }
        return resultado;
    }

    @Override
    public void actualizar(final UUID id, final DepartamentoEntidad entidad) {
        try (PreparedStatement ps = getConexion().prepareStatement(DepartamentoSql.ACTUALIZAR)) {
            ps.setString(1, entidad.getNombre());
            ps.setObject(2, entidad.getIdPais());
            ps.setObject(3, id);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla departamento.", e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(final UUID id) {
        try (PreparedStatement ps = getConexion().prepareStatement(DepartamentoSql.ELIMINAR)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla departamento.", e.getMessage(), e);
        }
    }
}
