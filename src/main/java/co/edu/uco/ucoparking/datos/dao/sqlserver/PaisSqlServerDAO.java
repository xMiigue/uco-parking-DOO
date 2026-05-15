package co.edu.uco.ucoparking.datos.dao.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucoparking.datos.dao.PaisDAO;
import co.edu.uco.ucoparking.datos.mapeador.PaisMapper;
import co.edu.uco.ucoparking.datos.sql.PaisSql;
import co.edu.uco.ucoparking.entidad.PaisEntidad;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingDatosException;

public final class PaisSqlServerDAO implements PaisDAO {

    private final Connection conexion;

    public PaisSqlServerDAO(final Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void crear(final PaisEntidad entidad) {
        try (PreparedStatement ps = conexion.prepareStatement(PaisSql.CREAR)) {
            ps.setObject(1, entidad.getId());
            ps.setString(2, entidad.getNombre());
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla pais.", e.getMessage(), e);
        }
    }

    @Override
    public PaisEntidad recuperarPorId(final UUID id) {
        try (PreparedStatement ps = conexion.prepareStatement(PaisSql.RECUPERAR_POR_ID)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return PaisMapper.obtenerInstancia().mapear(rs);
                }
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla pais.", e.getMessage(), e);
        }
        return new PaisEntidad.Builder().build();
    }

    @Override
    public List<PaisEntidad> recuperarTodos(final PaisEntidad filtro) {
        final List<PaisEntidad> resultado = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(PaisSql.RECUPERAR_TODOS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(PaisMapper.obtenerInstancia().mapear(rs));
            }
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla pais.", e.getMessage(), e);
        }
        return resultado;
    }

    @Override
    public void actualizar(final UUID id, final PaisEntidad entidad) {
        try (PreparedStatement ps = conexion.prepareStatement(PaisSql.ACTUALIZAR)) {
            ps.setString(1, entidad.getNombre());
            ps.setObject(2, id);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla pais.", e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(final UUID id) {
        try (PreparedStatement ps = conexion.prepareStatement(PaisSql.ELIMINAR)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (final SQLException e) {
            throw UcoParkingDatosException.crear(
                    "Error al acceder a la tabla pais.", e.getMessage(), e);
        }
    }
}
