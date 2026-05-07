package co.edu.uco.ucoparking.datos.sql;

public final class CiudadSql {

    public static final String CREAR =
            "INSERT INTO ciudad (id, nombre, id_departamento) VALUES (?, ?, ?)";

    public static final String ACTUALIZAR =
            "UPDATE ciudad SET nombre = ?, id_departamento = ? WHERE id = ?";

    public static final String ELIMINAR =
            "DELETE FROM ciudad WHERE id = ?";

    public static final String RECUPERAR_POR_ID =
            "SELECT c.id, c.nombre, c.id_departamento, "
            + "d.id AS dep_id, d.nombre AS dep_nombre, d.id_pais, "
            + "p.id AS pais_id, p.nombre AS pais_nombre "
            + "FROM ciudad c "
            + "INNER JOIN departamento d ON c.id_departamento = d.id "
            + "INNER JOIN pais p ON d.id_pais = p.id "
            + "WHERE c.id = ?";

    public static final String RECUPERAR_TODOS =
            "SELECT c.id, c.nombre, c.id_departamento, "
            + "d.id AS dep_id, d.nombre AS dep_nombre, d.id_pais, "
            + "p.id AS pais_id, p.nombre AS pais_nombre "
            + "FROM ciudad c "
            + "INNER JOIN departamento d ON c.id_departamento = d.id "
            + "INNER JOIN pais p ON d.id_pais = p.id "
            + "WHERE 1=1";

    private CiudadSql() {
        super();
    }
}
