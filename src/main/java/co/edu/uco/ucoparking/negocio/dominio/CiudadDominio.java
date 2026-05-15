package co.edu.uco.ucoparking.negocio.dominio;

import java.util.UUID;

import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorObjeto;
import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorTexto;
import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorUUID;

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
        this.id = ManejadorUUID.retornarValorPorDefecto(id);
    }

    private void setNombre(final String nombre) {
        this.nombre = ManejadorTexto.aplicarTrim(nombre);
    }

    private void setDepartamento(final DepartamentoDominio departamento) {
        this.departamento = ManejadorObjeto.retornarValorPorDefecto(departamento, new DepartamentoDominio.Builder().build());
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
