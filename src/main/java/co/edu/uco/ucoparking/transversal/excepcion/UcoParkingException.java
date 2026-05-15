package co.edu.uco.ucoparking.transversal.excepcion;

import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorObjeto;
import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorTexto;

public class UcoParkingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Throwable excepcionRaiz;
    private final String mensajeUsuario;
    private final String mensajeTecnico;

    protected UcoParkingException(final Throwable excepcionRaiz, final String mensajeUsuario,
            final String mensajeTecnico) {
        super(mensajeTecnico);
        this.excepcionRaiz = ManejadorObjeto.retornarValorPorDefecto(excepcionRaiz, new Exception());
        this.mensajeUsuario = ManejadorTexto.aplicarTrim(mensajeUsuario);
        this.mensajeTecnico = ManejadorTexto.aplicarTrim(mensajeTecnico);
    }

    public static UcoParkingException crear(final String mensajeUsuario) {
        return new UcoParkingException(new Exception(), mensajeUsuario, mensajeUsuario);
    }

    public static UcoParkingException crear(final String mensajeUsuario, final String mensajeTecnico) {
        return new UcoParkingException(new Exception(), mensajeUsuario, mensajeTecnico);
    }

    public static UcoParkingException crear(final Throwable excepcionRaiz, final String mensajeUsuario,
            final String mensajeTecnico) {
        return new UcoParkingException(excepcionRaiz, mensajeUsuario, mensajeTecnico);
    }

    public Throwable getExcepcionRaiz() {
        return excepcionRaiz;
    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    public String getMensajeTecnico() {
        return mensajeTecnico;
    }
}
