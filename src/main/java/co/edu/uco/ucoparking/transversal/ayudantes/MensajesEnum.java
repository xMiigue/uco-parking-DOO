package co.edu.uco.ucoparking.transversal.ayudantes;

public enum MensajesEnum {

    ERROR_USUARIO_CONEXION_SQL_NULA(
            "Se presentó un problema con la conexión a la base de datos. Contacte al administrador."),
    ERROR_TECNICO_CONEXION_SQL_NULA(
            "La conexión SQL recibida es nula."),

    ERROR_USUARIO_CONEXION_SQL_CERRADA(
            "Se presentó un problema con la conexión a la base de datos. Contacte al administrador."),
    ERROR_TECNICO_CONEXION_SQL_CERRADA(
            "La conexión SQL recibida se encuentra cerrada."),

    ERROR_USUARIO_TRANSACCION_NO_INICIADA(
            "Se presentó un problema procesando la solicitud. Contacte al administrador."),
    ERROR_TECNICO_TRANSACCION_NO_INICIADA(
            "No existe una transacción activa sobre la conexión SQL recibida."),

    ERROR_USUARIO_TRANSACCION_YA_INICIADA(
            "Se presentó un problema procesando la solicitud. Contacte al administrador."),
    ERROR_TECNICO_TRANSACCION_YA_INICIADA(
            "Ya existe una transacción activa sobre la conexión SQL recibida."),

    ERROR_USUARIO_VALIDANDO_ESTADO_CONEXION(
            "Se presentó un error inesperado verificando la conexión. Contacte al administrador."),
    ERROR_TECNICO_VALIDANDO_ESTADO_CONEXION(
            "Error inesperado al verificar el estado de la conexión SQL.");

    private final String contenido;

    MensajesEnum(final String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }
}
