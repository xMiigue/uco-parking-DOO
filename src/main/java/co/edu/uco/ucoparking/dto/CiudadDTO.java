package co.edu.uco.ucoparking.dto;

import java.util.UUID;

import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorObjeto;
import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorTexto;
import co.edu.uco.ucoparking.transversal.ayudantes.ManejadorUUID;

public final class CiudadDTO {

    private UUID id;
    private String nombre;
    private DepartamentoDTO departamento;

    private CiudadDTO(final Builder builder) {
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

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    private void setId(final UUID id) {
        this.id = ManejadorUUID.retornarValorPorDefecto(id);
    }

    private void setNombre(final String nombre) {
        this.nombre = ManejadorTexto.aplicarTrim(nombre);
    }

    private void setDepartamento(final DepartamentoDTO departamento) {
        this.departamento = ManejadorObjeto.retornarValorPorDefecto(departamento, new DepartamentoDTO.Builder().build());
    }

    public static final class Builder {

        private UUID id;
        private String nombre;
        private DepartamentoDTO departamento;

        public Builder id(final UUID id) {
            this.id = id;
            return this;
        }

        public Builder nombre(final String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder departamento(final DepartamentoDTO departamento) {
            this.departamento = departamento;
            return this;
        }

        public CiudadDTO build() {
            return new CiudadDTO(this);
        }
    }
}
