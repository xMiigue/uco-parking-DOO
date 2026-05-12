package co.edu.uco.ucoparking.transversal.excepcion;

public final class UcoParkingDatosException extends UcoParkingException {

    private static final long serialVersionUID = 1L;

    private static final Lugar LUGAR = Lugar.DATOS;

    private UcoParkingDatosException(final String mensajeUsuario, final String mensajeTecnico,
            final Exception excepcionRaiz) {
        super(excepcionRaiz, mensajeUsuario, mensajeTecnico);
    }

    public static UcoParkingDatosException crear(final String mensajeUsuario, final String mensajeTecnico,
            final Exception excepcionRaiz) {
        return new UcoParkingDatosException(mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }

    public static Lugar obtenerLugar() {
        return LUGAR;
    }
}
