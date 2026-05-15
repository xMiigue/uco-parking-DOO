package co.edu.uco.ucoparking.negocio.assembler.entidad;

public interface EntidadAssembler<D, E> {

    E ensamblarEntidad(D dominio);

    D ensamblarDominio(E entidad);

}
