package co.edu.uco.ucoparking.datos;

import java.util.List;

public interface RecuperarDAO<E, ID> {

    E recuperarPorId(ID id);

    List<E> recuperarTodos(E filtro);
}
