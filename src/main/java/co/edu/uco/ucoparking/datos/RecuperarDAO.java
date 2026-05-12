package co.edu.uco.ucoparking.datos;

import java.util.List;
import java.util.UUID;

public interface RecuperarDAO<E> {

    E recuperarPorId(UUID id);

    List<E> recuperarTodos(E filtro);
}
