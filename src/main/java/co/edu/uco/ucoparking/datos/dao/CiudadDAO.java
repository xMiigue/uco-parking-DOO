package co.edu.uco.ucoparking.datos.dao;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.AccederDAO;
import co.edu.uco.ucoparking.datos.ActualizarDAO;
import co.edu.uco.ucoparking.datos.EliminarDAO;
import co.edu.uco.ucoparking.datos.RecuperarDAO;
import co.edu.uco.ucoparking.entidad.CiudadEntidad;

public interface CiudadDAO extends AccederDAO<CiudadEntidad>, RecuperarDAO<CiudadEntidad, UUID>,
        ActualizarDAO<CiudadEntidad, UUID>, EliminarDAO<UUID> {
}
