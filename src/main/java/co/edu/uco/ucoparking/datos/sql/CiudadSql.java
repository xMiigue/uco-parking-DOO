package co.edu.uco.ucoparking.datos.sql;

public final class CiudadSql {

    public static final String CREAR =
            "INSERT INTO ciudad (id, nombre, id_departamento) VALUES (?, ?, ?)";

    public static final String ACTUALIZAR =
            "UPDATE ciudad SET nombre = ?, id_departamento = ? WHERE id = ?";

    public static final String ELIMINAR =
            "DELETE FROM ciudad WHERE id = ?";

    public static final String CONSULTAR_POR_ID =
            "SELECT c.id, c.nombre, c.id_departamento FROM ciudad c WHERE c.id = ?";

    public static final String CONSULTAR_TODOS =
            "SELECT c.id, c.nombre, c.id_departamento FROM ciudad c WHERE 1=1";

    private CiudadSql() {
        super();
    }
}
