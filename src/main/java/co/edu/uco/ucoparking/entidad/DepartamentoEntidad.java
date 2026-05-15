package co.edu.uco.ucoparking.entidad;

import java.util.UUID;

import co.edu.uco.ucoparking.transversal.utilitario.UtilTexto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class DepartamentoEntidad {

    private UUID id;
    private String nombre;
    private UUID idPais;

    private DepartamentoEntidad(final Builder builder) {
        super();
        setId(builder.id);
        setNombre(builder.nombre);
        setIdPais(builder.idPais);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public UUID getIdPais() {
        return idPais;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.retornarValorPorDefecto(id);
    }

    private void setNombre(final String nombre) {
        this.nombre = UtilTexto.aplicarTrim(nombre);
    }

    private void setIdPais(final UUID idPais) {
        this.idPais = UtilUUID.retornarValorPorDefecto(idPais);
    }

    public static final class Builder {

        private UUID id;
        private String nombre;
        private UUID idPais;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder nombre(final String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder idPais(final UUID idPais) {
            this.idPais = idPais;
            return this;
        }

        public DepartamentoEntidad build() {
            return new DepartamentoEntidad(this);
        }
    }
}
