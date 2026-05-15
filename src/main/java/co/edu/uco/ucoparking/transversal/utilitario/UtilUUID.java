package co.edu.uco.ucoparking.transversal.utilitario;

import java.util.UUID;

public final class UtilUUID {

    private static final String UUID_POR_DEFECTO_COMO_CADENA = "00000000-0000-0000-0000-000000000000";

    private UtilUUID() {
        super();
    }

    public static UUID retornarValorPorDefecto() {
        return UUID.fromString(UUID_POR_DEFECTO_COMO_CADENA);
    }

    public static UUID retornarValorPorDefecto(final UUID valor) {
        return UtilObjeto.retornarValorPorDefecto(valor, retornarValorPorDefecto());
    }

    public static UUID retornarDesdeTexto(final String texto) {
        return UtilTexto.esNula(texto) ? retornarValorPorDefecto() : UUID.fromString(texto);
    }

    public static UUID generarNuevoUUID() {
        return UUID.randomUUID();
    }

    public static boolean esUUIDPorDefecto(final UUID id) {
        return retornarValorPorDefecto().equals(id);
    }
}
