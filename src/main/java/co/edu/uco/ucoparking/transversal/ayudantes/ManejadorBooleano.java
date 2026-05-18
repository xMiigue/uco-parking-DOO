package co.edu.uco.ucoparking.transversal.ayudantes;

public final class ManejadorBooleano {

    private ManejadorBooleano() {
        super();
    }

    public static boolean retornarValorPorDefecto() {
        return false;
    }

    public static boolean retornarValorPorDefecto(final Boolean valor) {
        return ManejadorObjeto.retornarValorPorDefecto(valor, retornarValorPorDefecto());
    }
}
