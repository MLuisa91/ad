package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Departamentos;

public class CRUD_Departamentos implements CRUD<Departamentos>{
	
	CRUD_XmlDepartamentos crud_XmlDepartamento;
	List<Departamentos> listaDepartamentos;
	
	public CRUD_Departamentos() {
		listaDepartamentos = new ArrayList<>();
		crud_XmlDepartamento = new CRUD_XmlDepartamentos();
	}
	
	public boolean load(Departamentos element) {
		return listaDepartamentos.add(element);
	}

	@Override
	public boolean add(Departamentos element) {
		listaDepartamentos.add(element);
		crud_XmlDepartamento.saveDepartamentos(listaDepartamentos);
		return true;
	}

	@Override
	public Departamentos search(Departamentos element) {
		//implementado en otros métodos para buscar según el atributo elegido
		return null;
	}

	@Override
	public boolean update(Departamentos element) {
		int pos = -1;
		pos = search_pos(element);
		if (pos != -1) {
			listaDepartamentos.set(pos, element);
			crud_XmlDepartamento.saveDepartamentos(listaDepartamentos);
		}
		return false;
	}

	//método para buscar un departamentos según el número que tiene asignado
	public int search_pos(Departamentos element) {
		for (int i = 0; i < listaDepartamentos.size(); i++) {
			if (element.compareToNumero(listaDepartamentos.get(i))==true) {
				return i;
			}
		}
		return -1;
	}
	
	//método para borrar un departamento de la lista, primero lo busca según el número que tiene asignado
	@Override
	public boolean delete(Departamentos element) {
		int pos = search_pos(element);
		if (pos != -1) {
			listaDepartamentos.remove(pos);
			crud_XmlDepartamento.saveDepartamentos(listaDepartamentos);
			return true;
		}
		return false;
	}
	
	/**
	 * Los siguientes métodos se han implementado para buscar 
	 * departamentos según el resto de atributos
	 * 
	 * */
	public Departamentos buscarNombre(Departamentos dnombre) {
		for (Departamentos departamentos : listaDepartamentos) {
			if(departamentos.compareToNombre(dnombre)) {
				return departamentos;
			}
		}
		return null;
	}
	
	public Departamentos buscarLocalidad(Departamentos localidad) {
		for (Departamentos departamentos : listaDepartamentos) {
			if(departamentos.compareToLocalidad(localidad)) {
				return departamentos;
			}
		}
		return null;
	}
	
	public Departamentos buscarNumero(Departamentos departamentoNum) {
		for (Departamentos departamentos : listaDepartamentos) {
			if(departamentos.compareToNumero(departamentoNum)) {
				return departamentos;
			}
		}
		return null;
	}
	
	public Iterator<Departamentos> listAll(){
		return listaDepartamentos.iterator();
	}

}
