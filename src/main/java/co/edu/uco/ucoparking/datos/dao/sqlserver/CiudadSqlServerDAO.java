package co.edu.uco.ucoparking.datos.dao.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.dao.CiudadDAO;
import co.edu.uco.ucoparking.datos.dao.sql.SQLDAO;
import co.edu.uco.ucoparking.datos.mapeador.CiudadMapper;
import co.edu.uco.ucoparking.datos.sql.CiudadSql;
import co.edu.uco.ucoparking.entidad.CiudadEntidad;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingDatosException;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class CiudadSqlServerDAO extends SQLDAO implements CiudadDAO {

    public CiudadSqlServerDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(final CiudadEntidad entidad) {
        try (PreparedStatement ps = getConexion().prepareStatement(CiudadSql.CREAR)) {
            ps.setObject(1, entidad.getId());
            ps.setString(2, entidad.getNombre());
            ps.setObject(3, entidad.getIdDepartamento());
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla ciudad.", e.getMessage(), e);
        }
    }

    @Override
    public List<CiudadEntidad> consultarTodos() {
        final List<CiudadEntidad> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(CiudadSql.CONSULTAR_TODOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(CiudadMapper.obtenerInstancia().mapear(rs));
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla ciudad.", e.getMessage(), e);
        }
        return resultado;
    }

    @Override
    public CiudadEntidad consultarPorId(final UUID id) {
        try (PreparedStatement ps = getConexion().prepareStatement(CiudadSql.CONSULTAR_POR_ID)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return CiudadMapper.obtenerInstancia().mapear(rs);
                }
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla ciudad.", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<CiudadEntidad> consultarPorFiltro(final CiudadEntidad filtro) {
        final StringBuilder sql = new StringBuilder(CiudadSql.CONSULTAR_TODOS);
        final List<Object> parametros = new ArrayList<>();

        if (filtro != null) {
            if (!filtro.getNombre().isEmpty()) {
                sql.append(" AND nombre = ?");
                parametros.add(filtro.getNombre());
            }
            if (!UtilUUID.esUUIDPorDefecto(filtro.getIdDepartamento())) {
                sql.append(" AND id_departamento = ?");
                parametros.add(filtro.getIdDepartamento());
            }
        }

        final List<CiudadEntidad> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.add(CiudadMapper.obtenerInstancia().mapear(rs));
                }
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla ciudad.", e.getMessage(), e);
        }
        return resultado;
    }

    @Override
    public void actualizar(final UUID id, final CiudadEntidad entidad) {
        try (PreparedStatement ps = getConexion().prepareStatement(CiudadSql.ACTUALIZAR)) {
            ps.setString(1, entidad.getNombre());
            ps.setObject(2, entidad.getIdDepartamento());
            ps.setObject(3, id);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla ciudad.", e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(final UUID id) {
        try (PreparedStatement ps = getConexion().prepareStatement(CiudadSql.ELIMINAR)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla ciudad.", e.getMessage(), e);
        }
    }
}
