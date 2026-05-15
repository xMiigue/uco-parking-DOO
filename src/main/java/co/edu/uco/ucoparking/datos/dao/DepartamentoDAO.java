package co.edu.uco.ucoparking.datos.dao;

import java.util.UUID;

import co.edu.uco.ucoparking.datos.AccederDAO;
import co.edu.uco.ucoparking.datos.ActualizarDAO;
import co.edu.uco.ucoparking.datos.EliminarDAO;
import co.edu.uco.ucoparking.datos.RecuperarDAO;
import co.edu.uco.ucoparking.entidad.DepartamentoEntidad;

public interface DepartamentoDAO extends AccederDAO<DepartamentoEntidad>, RecuperarDAO<DepartamentoEntidad, UUID>,
        ActualizarDAO<DepartamentoEntidad, UUID>, EliminarDAO<UUID> {
}
