package co.edu.uco.ucoparking.transversal.ayudantes;

public final class ManejadorTexto {

    public static final String VACIO = "";

    private ManejadorTexto() {
        super();
    }

    public static boolean esNula(final String texto) {
        return ManejadorObjeto.esNulo(texto);
    }

    public static String retornarValorPorDefecto(final String texto, final String valorDefecto) {
        return ManejadorObjeto.retornarValorPorDefecto(texto, valorDefecto);
    }

    public static String retornarValorPorDefecto(final String texto) {
        return retornarValorPorDefecto(texto, VACIO);
    }

    public static String aplicarTrim(final String texto) {
        return retornarValorPorDefecto(texto).trim();
    }
}
