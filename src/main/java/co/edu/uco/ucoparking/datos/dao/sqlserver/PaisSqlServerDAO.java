package co.edu.uco.ucoparking.datos.dao.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.dao.PaisDAO;
import co.edu.uco.ucoparking.datos.dao.sql.SQLDAO;
import co.edu.uco.ucoparking.datos.mapeador.PaisMapper;
import co.edu.uco.ucoparking.datos.sql.PaisSql;
import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingDatosException;

public final class PaisSqlServerDAO extends SQLDAO implements PaisDAO {

    public PaisSqlServerDAO(final Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(final PaisEntidad entidad) {
        try (PreparedStatement ps = getConexion().prepareStatement(PaisSql.CREAR)) {
            ps.setObject(1, entidad.getId());
            ps.setString(2, entidad.getNombre());
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla pais.", e.getMessage(), e);
        }
    }

    @Override
    public List<PaisEntidad> consultarTodos() {
        final List<PaisEntidad> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(PaisSql.CONSULTAR_TODOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(PaisMapper.obtenerInstancia().mapear(rs));
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla pais.", e.getMessage(), e);
        }
        return resultado;
    }

    @Override
    public PaisEntidad consultarPorId(final UUID id) {
        try (PreparedStatement ps = getConexion().prepareStatement(PaisSql.CONSULTAR_POR_ID)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return PaisMapper.obtenerInstancia().mapear(rs);
                }
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla pais.", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<PaisEntidad> consultarPorFiltro(final PaisEntidad filtro) {
        final StringBuilder sql = new StringBuilder(PaisSql.CONSULTAR_TODOS);
        final List<Object> parametros = new ArrayList<>();

        if (filtro != null && !filtro.getNombre().isEmpty()) {
            sql.append(" AND nombre = ?");
            parametros.add(filtro.getNombre());
        }

        final List<PaisEntidad> resultado = new ArrayList<>();
        try (PreparedStatement ps = getConexion().prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.add(PaisMapper.obtenerInstancia().mapear(rs));
                }
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla pais.", e.getMessage(), e);
        }
        return resultado;
    }

    @Override
    public void actualizar(final UUID id, final PaisEntidad entidad) {
        try (PreparedStatement ps = getConexion().prepareStatement(PaisSql.ACTUALIZAR)) {
            ps.setString(1, entidad.getNombre());
            ps.setObject(2, id);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla pais.", e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(final UUID id) {
        try (PreparedStatement ps = getConexion().prepareStatement(PaisSql.ELIMINAR)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear("Error al acceder a la tabla pais.", e.getMessage(), e);
        }
    }
}
