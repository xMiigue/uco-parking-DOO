package co.edu.uco.ucoparking.transversal.excepciones;

import co.edu.uco.ucoparking.transversal.utilitario.UtilObjeto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilTexto;

public final class ExcepcionBaseUcoParking extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Throwable excepcionRaiz;
    private final String mensajeUsuario;
    private final String mensajeTecnico;

    private ExcepcionBaseUcoParking(final Throwable excepcionRaiz, final String mensajeUsuario,
            final String mensajeTecnico) {
        super(mensajeTecnico);
        this.excepcionRaiz = UtilObjeto.retornarValorPorDefecto(excepcionRaiz, new Exception());
        this.mensajeUsuario = UtilTexto.aplicarTrim(mensajeUsuario);
        this.mensajeTecnico = UtilTexto.aplicarTrim(mensajeTecnico);
    }

    public static ExcepcionBaseUcoParking crear(final String mensajeUsuario) {
        return new ExcepcionBaseUcoParking(new Exception(), mensajeUsuario, mensajeUsuario);
    }

    public static ExcepcionBaseUcoParking crear(final String mensajeUsuario, final String mensajeTecnico) {
        return new ExcepcionBaseUcoParking(new Exception(), mensajeUsuario, mensajeTecnico);
    }

    public static ExcepcionBaseUcoParking crear(final Throwable excepcionRaiz, final String mensajeUsuario,
            final String mensajeTecnico) {
        return new ExcepcionBaseUcoParking(excepcionRaiz, mensajeUsuario, mensajeTecnico);
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
