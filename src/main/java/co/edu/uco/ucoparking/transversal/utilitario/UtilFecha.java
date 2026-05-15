package co.edu.uco.ucoparking.transversal.utilitario;

import java.sql.Date;
import java.time.LocalDate;

public final class UtilFecha {

    private UtilFecha() {
        super();
    }

    public static LocalDate retornarFechaPorDefecto() {
        return LocalDate.of(1, 1, 1);
    }

    public static LocalDate retornarValorPorDefecto(final LocalDate valor) {
        return UtilObjeto.retornarValorPorDefecto(valor, retornarFechaPorDefecto());
    }

    public static LocalDate retornarFechaActual() {
        return LocalDate.now();
    }

    public static boolean esFechaPorDefecto(final LocalDate fecha) {
        return retornarFechaPorDefecto().equals(fecha);
    }

    public static LocalDate sqlDateALocalDate(final Date valor) {
        return UtilObjeto.retornarValorPorDefecto(valor, Date.valueOf(retornarFechaPorDefecto())).toLocalDate();
    }

    public static boolean esFechaPosteriorOIgualAHoy(final LocalDate valor) {
        return valor.isAfter(LocalDate.now()) || valor.isEqual(LocalDate.now());
    }

    public static boolean esFechaAnteriorA(final LocalDate valor, final LocalDate referencia) {
        return valor.isBefore(referencia);
    }
}
