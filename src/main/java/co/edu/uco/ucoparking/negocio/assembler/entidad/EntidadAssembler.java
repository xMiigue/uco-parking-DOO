package co.edu.uco.ucoparking.negocio.assembler.entidad;

public interface EntidadAssembler<D, E> {

    // Convierte un objeto de dominio en una entidad de persistencia
    E ensamblarEntidad(D dominio);

    // Convierte una entidad de persistencia en un objeto de dominio
    D ensamblarDominio(E entidad);

}
