package co.edu.uco.ucoparking.controlador.dto;

import java.util.ArrayList;
import java.util.List;

public final class Respuesta<T> {

    private final boolean exitoso;
    private final String mensaje;
    private final T datos;
    private final List<String> errores;

    private Respuesta(final boolean exitoso, final String mensaje, final T datos,
            final List<String> errores) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
        this.datos = datos;
        this.errores = errores;
    }

    public static <T> Respuesta<T> exitosa(final T datos) {
        return new Respuesta<>(true, "", datos, new ArrayList<>());
    }

    public static <T> Respuesta<T> exitosaConMensaje(final String mensaje, final T datos) {
        return new Respuesta<>(true, mensaje, datos, new ArrayList<>());
    }

    public static <T> Respuesta<T> fallida(final String mensaje) {
        final List<String> errores = new ArrayList<>();
        errores.add(mensaje);
        return new Respuesta<>(false, mensaje, null, errores);
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public T getDatos() {
        return datos;
    }

    public List<String> getErrores() {
        return errores;
    }
}
