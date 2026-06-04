package co.edu.uco.ucoparking.controlador.excepcion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.edu.uco.ucoparking.controlador.PaisControlador;
import co.edu.uco.ucoparking.controlador.respuesta.RespuestaError;
import co.edu.uco.ucoparking.transversal.excepcion.UcoParkingException;

@RestControllerAdvice
public class ManejadorExcepciones {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(PaisControlador.class);

    @ExceptionHandler(UcoParkingException.class)
    public ResponseEntity<RespuestaError> gestionarUcoParkingException(final UcoParkingException excepcion) {
        LOGGER.error(excepcion.getMensajeTecnico(),excepcion.getExcepcionRaiz());
        return new ResponseEntity<>(
                RespuestaError.crear(excepcion.getMensajeUsuario()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError> gestionarExcepcion(final Exception excepcion) {
        var mensajeUsuario = "Se ha presentado un problema inesperado. Por favor intente de nuevo. Si el problema persiste, contacte al administrador.";
        var mensajeTecnico = "Se ha presentado una excepción no controlada. Por favor intente de nuevo. Si el problema persiste, revisar el log para mas detalles";
        LOGGER.error("Excepción no controlada: {}", mensajeTecnico, excepcion);
        
        excepcion.printStackTrace();
        return new ResponseEntity<>(RespuestaError.crear(mensajeUsuario),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
