package co.edu.uco.ucoparking.transversal.utilitario;

public final class UtilBooleano {

    private UtilBooleano() {
        super();
    }

    public static boolean retornarValorPorDefecto() {
        return false;
    }

    public static boolean retornarValorPorDefecto(final Boolean valor) {
        return UtilObjeto.retornarValorPorDefecto(valor, retornarValorPorDefecto());
    }
}
