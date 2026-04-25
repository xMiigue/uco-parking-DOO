package co.edu.uco.ucoparking.negocio.assembler.dto.impl;

import co.edu.uco.ucoparking.dto.PaisDTO;
import co.edu.uco.ucoparking.negocio.assembler.dto.DTOAssembler;
import co.edu.uco.ucoparking.negocio.dominio.PaisDominio;
import co.edu.uco.ucoparking.transversal.UtilObjeto;

public class PaisDTOAssembler implements DTOAssembler<PaisDominio, PaisDTO>{
	
	private final static DTOAssembler<PaisDominio, PaisDTO> INSTANCE;
	
	private static DTOAssembler<PaisDominio, PaisDTO> getInstance(){
		
	public synchronized static final DTOAssembler<PaisDominio, PaisDTO> getInstance(){	
		
		if(UtilObjeto.esNulo(INSTANCE)) {
			INSTANCE = new PaisDTOAssembler();
		}
		return INSTANCE;
	}

	@Override
	public PaisDominio ensamblarDominio(PaisDTO dto) {
		var paisAEnsamblar = UtilObjeto.obtenerValorDefecto(dto, new PaisDTO.Builder().build());
		return new PaisDominio.Builder().id(paisAEnsamblar.getId()).nombre(paisAEnsamblar.getNombre()).build();
	}

	@Override
	public PaisDTO ensablarDTO(PaisDominio dominio) {
		var paisAEnsamblar = UtilObjeto.obtenerValorDefecto(dominio, new PaisDominio.Builder().build());
		return new PaisDTO.Builder().id(paisAEnsamblar.getId()).nombre(paisAEnsamblar.getNombre()).build();
	}
	
}

