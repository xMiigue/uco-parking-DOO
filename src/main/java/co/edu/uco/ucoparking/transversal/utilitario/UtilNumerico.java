package co.edu.uco.ucoparking.transversal.utilitario;

public final class UtilNumerico {

    private UtilNumerico() {
        super();
    }

    public static int retornarValorPorDefectoInt() {
        return 0;
    }

    public static int retornarValorPorDefecto(final Integer valor) {
        return UtilObjeto.retornarValorPorDefecto(valor, retornarValorPorDefectoInt());
    }

    public static double retornarValorPorDefectoDouble() {
        return 0.0;
    }

    public static double retornarValorPorDefecto(final Double valor) {
        return UtilObjeto.retornarValorPorDefecto(valor, retornarValorPorDefectoDouble());
    }

    public static long retornarValorPorDefectoLong() {
        return 0L;
    }

    public static long retornarValorPorDefecto(final Long valor) {
        return UtilObjeto.retornarValorPorDefecto(valor, retornarValorPorDefectoLong());
    }
}
