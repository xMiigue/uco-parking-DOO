package co.edu.uco.ucoparking.datos.sql;

public final class PaisSql {

    public static final String CREAR =
            "INSERT INTO pais (id, nombre) VALUES (?, ?)";

    public static final String ACTUALIZAR =
            "UPDATE pais SET nombre = ? WHERE id = ?";

    public static final String ELIMINAR =
            "DELETE FROM pais WHERE id = ?";

    public static final String RECUPERAR_POR_ID =
            "SELECT p.id, p.nombre FROM pais p WHERE p.id = ?";

    public static final String RECUPERAR_TODOS =
            "SELECT p.id, p.nombre FROM pais p WHERE 1=1";

    private PaisSql() {
        super();
    }
}
