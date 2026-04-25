package co.edu.uco.ucoparking.negocio.assembler.dto;

public interface DTOAssembler<D, T>{
	
	D ensamblarDominio(T dto);
	T ensablarDTO(D dominio);

}
