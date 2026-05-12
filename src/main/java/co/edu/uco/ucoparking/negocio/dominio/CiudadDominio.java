package co.edu.uco.ucoparking.negocio.dominio;

import java.util.UUID;

import co.edu.uco.ucoparking.transversal.UtilObjeto;
import co.edu.uco.ucoparking.transversal.UtilTexto;
import co.edu.uco.ucoparking.transversal.UtilUUID;

public final class CiudadDominio {

    private UUID id;
    private String nombre;
    private DepartamentoDominio departamento;

    private CiudadDominio(final Builder builder) {
        super();
        setId(builder.id);
        setNombre(builder.nombre);
        setDepartamento(builder.departamento);
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public DepartamentoDominio getDepartamento() {
        return departamento;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    private void setNombre(final String nombre) {
        this.nombre = UtilTexto.aplicarTrim(nombre);
    }

    private void setDepartamento(final DepartamentoDominio departamento) {
        this.departamento = UtilObjeto.obtenerValorDefecto(departamento, new DepartamentoDominio.Builder().build());
    }

    public static final class Builder {

        private UUID id;
        private String nombre;
        private DepartamentoDominio departamento;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder nombre(final String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder departamento(final DepartamentoDominio departamento) {
            this.departamento = departamento;
            return this;
        }

        public CiudadDominio build() {
            return new CiudadDominio(this);
        }
    }
}
