package co.edu.uco.ucoparking.dto;

import java.util.UUID;

import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorTexto;
import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorUUID;

public final class PaisDTO {

    private UUID id;
    private String nombre;

    private PaisDTO(final Builder builder) {
        super();
        setId(builder.id);
        setNombre(builder.nombre);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = ManejadorUUID.retornarValorPorDefecto(id);
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(final String nombre) {
        this.nombre = ManejadorTexto.aplicarTrim(nombre);
    }

    public static final class Builder {

        private UUID id;
        private String nombre;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder nombre(final String nombre) {
            this.nombre = ManejadorTexto.aplicarTrim(nombre);
            return this;
        }

        public PaisDTO build() {
            return new PaisDTO(this);
        }
    }
}
