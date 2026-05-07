package co.edu.uco.ucoparking.datos.mapeador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import co.edu.uco.ucoparking.entidad.DepartamentoEntidad;

public final class DepartamentoMapper {

    private static final DepartamentoMapper INSTANCIA = new DepartamentoMapper();

    private DepartamentoMapper() {
        super();
    }

    public static DepartamentoMapper obtenerInstancia() {
        return INSTANCIA;
    }

    public DepartamentoEntidad mapear(final ResultSet rs) throws SQLException {
        return new DepartamentoEntidad.Builder()
                .id(rs.getObject("id", UUID.class))
                .nombre(rs.getString("nombre"))
                .idPais(rs.getObject("id_pais", UUID.class))
                .build();
    }
}
