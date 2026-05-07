package co.edu.uco.ucoparking.datos.dao.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.dao.CiudadDAO;
import co.edu.uco.ucoparking.datos.mapeador.CiudadMapper;
import co.edu.uco.ucoparking.datos.sql.CiudadSql;
import co.edu.uco.ucoparking.entidad.CiudadEntidad;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingDatosException;

public final class CiudadSqlServerDAO implements CiudadDAO {

    private final Connection conexion;

    public CiudadSqlServerDAO(final Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void crear(final CiudadEntidad entidad) {
        try (PreparedStatement ps = conexion.prepareStatement(CiudadSql.CREAR)) {
            ps.setObject(1, entidad.getId());
            ps.setString(2, entidad.getNombre());
            ps.setObject(3, entidad.getIdDepartamento());
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla ciudad.", e.getMessage(), e);
        }
    }

    @Override
    public CiudadEntidad recuperarPorId(final UUID id) {
        try (PreparedStatement ps = conexion.prepareStatement(CiudadSql.RECUPERAR_POR_ID)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return CiudadMapper.obtenerInstancia().mapear(rs);
                }
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla ciudad.", e.getMessage(), e);
        }
        return new CiudadEntidad.Builder().build();
    }

    @Override
    public List<CiudadEntidad> recuperarTodos(final CiudadEntidad filtro) {
        final List<CiudadEntidad> resultado = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(CiudadSql.RECUPERAR_TODOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(CiudadMapper.obtenerInstancia().mapear(rs));
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla ciudad.", e.getMessage(), e);
        }
        return resultado;
    }

    @Override
    public void actualizar(final CiudadEntidad entidad) {
        try (PreparedStatement ps = conexion.prepareStatement(CiudadSql.ACTUALIZAR)) {
            ps.setString(1, entidad.getNombre());
            ps.setObject(2, entidad.getIdDepartamento());
            ps.setObject(3, entidad.getId());
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla ciudad.", e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(final UUID id) {
        try (PreparedStatement ps = conexion.prepareStatement(CiudadSql.ELIMINAR)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla ciudad.", e.getMessage(), e);
        }
    }
}
