package co.edu.uco.ucoparking.transversal.ayudantes;

public final class ManejadorObjeto {

    private ManejadorObjeto() {
        super();
    }

    public static <O> boolean esNulo(final O objeto) {
        return objeto == null;
    }

    public static <O> O retornarValorPorDefecto(final O objeto, final O valorDefecto) {
        return esNulo(objeto) ? valorDefecto : objeto;
    }
}
