package co.edu.uco.ucoparking.transversal.ayudantes;

public final class ManejadorNumerico {

    private ManejadorNumerico() {
        super();
    }

    public static int retornarValorPorDefectoInt() {
        return 0;
    }

    public static int retornarValorPorDefecto(final Integer valor) {
        return ManejadorObjeto.retornarValorPorDefecto(valor, retornarValorPorDefectoInt());
    }

    public static double retornarValorPorDefectoDouble() {
        return 0.0;
    }

    public static double retornarValorPorDefecto(final Double valor) {
        return ManejadorObjeto.retornarValorPorDefecto(valor, retornarValorPorDefectoDouble());
    }

    public static long retornarValorPorDefectoLong() {
        return 0L;
    }

    public static long retornarValorPorDefecto(final Long valor) {
        return ManejadorObjeto.retornarValorPorDefecto(valor, retornarValorPorDefectoLong());
    }
}
