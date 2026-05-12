package co.edu.uco.ucoparking.datos.mapeador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import co.edu.uco.ucoparking.entidad.PaisEntidad;

public final class PaisMapper {

    private static final PaisMapper INSTANCIA = new PaisMapper();

    private PaisMapper() {
        super();
    }

    public static PaisMapper obtenerInstancia() {
        return INSTANCIA;
    }

    public PaisEntidad mapear(final ResultSet rs) throws SQLException {
        return new PaisEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .build();
    }
}
