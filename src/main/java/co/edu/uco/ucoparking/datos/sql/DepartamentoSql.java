package co.edu.uco.ucoparking.datos.sql;

public final class DepartamentoSql {

    public static final String CREAR =
            "INSERT INTO departamento (id, nombre, id_pais) VALUES (?, ?, ?)";

    public static final String ACTUALIZAR =
            "UPDATE departamento SET nombre = ?, id_pais = ? WHERE id = ?";

    public static final String ELIMINAR =
            "DELETE FROM departamento WHERE id = ?";

    public static final String RECUPERAR_POR_ID =
            "SELECT d.id, d.nombre, d.id_pais, "
            + "p.id AS pais_id, p.nombre AS pais_nombre "
            + "FROM departamento d "
            + "INNER JOIN pais p ON d.id_pais = p.id "
            + "WHERE d.id = ?";

    public static final String RECUPERAR_TODOS =
            "SELECT d.id, d.nombre, d.id_pais, "
            + "p.id AS pais_id, p.nombre AS pais_nombre "
            + "FROM departamento d "
            + "INNER JOIN pais p ON d.id_pais = p.id "
            + "WHERE 1=1";

    private DepartamentoSql() {
        super();
    }
}
