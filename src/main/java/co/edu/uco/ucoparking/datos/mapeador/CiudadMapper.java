package co.edu.uco.ucoparking.datos.mapeador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import co.edu.uco.ucoparking.entidad.CiudadEntidad;

public final class CiudadMapper {

    private static final CiudadMapper INSTANCIA = new CiudadMapper();

    private CiudadMapper() {
        super();
    }

    public static CiudadMapper obtenerInstancia() {
        return INSTANCIA;
    }

    public CiudadEntidad mapear(final ResultSet rs) throws SQLException {
        return new CiudadEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .idDepartamento(rs.getObject("id_departamento", UUID.class))
                .build();
    }
}
