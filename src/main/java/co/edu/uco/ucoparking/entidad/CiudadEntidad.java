package co.edu.uco.ucoparking.entidad;

import java.util.UUID;

import co.edu.uco.ucoparking.transversal.utilitario.UtilTexto;
import co.edu.uco.ucoparking.transversal.utilitario.UtilUUID;

public final class CiudadEntidad {

    private UUID id;
    private String nombre;
    private UUID idDepartamento;

    private CiudadEntidad(final Builder builder) {
        super();
        setId(builder.id);
        setNombre(builder.nombre);
        setIdDepartamento(builder.idDepartamento);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public UUID getIdDepartamento() {
        return idDepartamento;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.retornarValorPorDefecto(id);
    }

    private void setNombre(final String nombre) {
        this.nombre = UtilTexto.aplicarTrim(nombre);
    }

    private void setIdDepartamento(final UUID idDepartamento) {
        this.idDepartamento = UtilUUID.retornarValorPorDefecto(idDepartamento);
    }

    public static final class Builder {

        private UUID id;
        private String nombre;
        private UUID idDepartamento;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder nombre(final String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder idDepartamento(final UUID idDepartamento) {
            this.idDepartamento = idDepartamento;
            return this;
        }

        public CiudadEntidad build() {
            return new CiudadEntidad(this);
        }
    }
}
