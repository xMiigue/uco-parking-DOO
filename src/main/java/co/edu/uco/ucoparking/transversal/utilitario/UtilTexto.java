package co.edu.uco.ucoparking.transversal.utilitario;

public final class UtilTexto {

    public static final String VACIO = "";

    private UtilTexto() {
        super();
    }

    public static boolean esNula(final String texto) {
        return UtilObjeto.esNulo(texto);
    }

    public static String retornarValorPorDefecto(final String texto, final String valorDefecto) {
        return UtilObjeto.retornarValorPorDefecto(texto, valorDefecto);
    }

    public static String retornarValorPorDefecto(final String texto) {
        return retornarValorPorDefecto(texto, VACIO);
    }

    public static String aplicarTrim(final String texto) {
        return retornarValorPorDefecto(texto).trim();
    }
}
