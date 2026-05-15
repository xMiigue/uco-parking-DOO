package co.edu.uco.ucoparking.negocio.dominio;

import java.util.UUID;

import co.edu.uco.ucoparking.transversal.utilitario.UtilTexto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class PaisDominio {

    private UUID id;
    private String nombre;

    private PaisDominio(final Builder builder) {
        super();
        setId(builder.id);
        setNombre(builder.nombre);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.retornarValorPorDefecto(id);
    }

    private void setNombre(final String nombre) {
        this.nombre = UtilTexto.aplicarTrim(nombre);
    }

    public static final class Builder {

        private UUID id;
        private String nombre;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder nombre(final String nombre) {
            this.nombre = nombre;
            return this;
        }

        public PaisDominio build() {
            return new PaisDominio(this);
        }
    }
}
