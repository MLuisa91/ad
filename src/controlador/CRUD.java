package controlador;

import java.util.Iterator;

public interface CRUD<E> {
	
	boolean add(E element);
	E search (E element);
	boolean update (E element);
	boolean delete (E element);
	Iterator<E> listAll();

}
