package co.edu.uco.ucoparking.datos.dao.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.dao.DepartamentoDAO;
import co.edu.uco.ucoparking.datos.mapeador.DepartamentoMapper;
import co.edu.uco.ucoparking.datos.sql.DepartamentoSql;
import co.edu.uco.ucoparking.entidad.DepartamentoEntidad;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingDatosException;

public final class DepartamentoSqlServerDAO implements DepartamentoDAO {

    private final Connection conexion;

    public DepartamentoSqlServerDAO(final Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void crear(final DepartamentoEntidad entidad) {
        try (PreparedStatement ps = conexion.prepareStatement(DepartamentoSql.CREAR)) {
            ps.setObject(1, entidad.getId());
            ps.setString(2, entidad.getNombre());
            ps.setObject(3, entidad.getIdPais());
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla departamento.", e.getMessage(), e);
        }
    }

    @Override
    public DepartamentoEntidad recuperarPorId(final UUID id) {
        try (PreparedStatement ps = conexion.prepareStatement(DepartamentoSql.RECUPERAR_POR_ID)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return DepartamentoMapper.obtenerInstancia().mapear(rs);
                }
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla departamento.", e.getMessage(), e);
        }
        return new DepartamentoEntidad.Builder().build();
    }

    @Override
    public List<DepartamentoEntidad> recuperarTodos(final DepartamentoEntidad filtro) {
        final List<DepartamentoEntidad> resultado = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(DepartamentoSql.RECUPERAR_TODOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(DepartamentoMapper.obtenerInstancia().mapear(rs));
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla departamento.", e.getMessage(), e);
        }
        return resultado;
    }

    @Override
    public void actualizar(final UUID id, final DepartamentoEntidad entidad) {
        try (PreparedStatement ps = conexion.prepareStatement(DepartamentoSql.ACTUALIZAR)) {
            ps.setString(1, entidad.getNombre());
            ps.setObject(2, entidad.getIdPais());
            ps.setObject(3, id);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla departamento.", e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(final UUID id) {
        try (PreparedStatement ps = conexion.prepareStatement(DepartamentoSql.ELIMINAR)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla departamento.", e.getMessage(), e);
        }
    }
}
