package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Departamentos;
import modelo.Empleados;
import modelo.Jefe;

public class CRUD_Empleados implements CRUD<Empleados> {

	CRUD_Empleado_Xml crud_Xml;
	List<Empleados> listaEmpleados;
	List<Departamentos> listaDepartamentos;

	public CRUD_Empleados() {
		listaEmpleados = new ArrayList<>();
		crud_Xml = new CRUD_Empleado_Xml();
		listaDepartamentos = new ArrayList<>();
	}

	public boolean load(Empleados element) {
		return listaEmpleados.add(element);
	}

	@Override
	public boolean add(Empleados element) {
		listaEmpleados.add(element);
		crud_Xml.saveEmpleados(listaEmpleados); // guardamos los empleados en el fichero
		return true;
	}

	@Override
	public Empleados search(Empleados element) {
		// implementado para buscar segun el atributo que se escoja: apellido, numero de
		// empleado, etc.
		return null;
	}

	@Override
	public boolean update(Empleados element) {
		int pos = -1;
		pos = search_pos(element);
		if (pos != -1) {
			listaEmpleados.set(pos, element);
			crud_Xml.saveEmpleados(listaEmpleados); // guardamos los empleados en el fichero
			return true;
		}
		return false;
	}

	// método para buscar según el id o el número de empleado
	public int search_pos(Empleados element) {
		for (int i = 0; i < listaEmpleados.size(); i++) {
			if (element.compareToNo(listaEmpleados.get(i)) == true) {
				return i;
			}
		}
		return -1;
	}

	// método para borrar un elemento según su número asignado
	@Override
	public boolean delete(Empleados element) {
		int pos = search_pos(element);
		if (pos != -1) {
			listaEmpleados.remove(pos);
			crud_Xml.saveEmpleados(listaEmpleados); // guardamos los empleados en el fichero
			return true;
		}
		return false;
	}

	/*
	 * Método que nos devuelve la lista entera de empleados
	 */
	@Override
	public Iterator<Empleados> listAll() {
		return listaEmpleados.iterator();
	}
	
	public Iterator<Empleados> listJefes() {
		Iterator<Empleados> empleadosIterator = listaEmpleados.iterator();
		List<Empleados> jefes = new ArrayList<>();
		while(empleadosIterator.hasNext()) {
			Empleados empleado = empleadosIterator.next();
			
			if(empleado instanceof Jefe)
				jefes.add(empleado);
		}
		
		return jefes.iterator();
	}

	/*
	 * Los siguientes métodos se han implementado para buscar un empleado según el
	 * resto de atributos que tienen asignado excepto su director o jefe
	 * 
	 */
	public Empleados buscarApellido(Empleados apellido) {
		for (Empleados empleados : listaEmpleados) {
			if (empleados.compareToApellido(apellido)) {
				return empleados;
			}
		}
		return null;
	}

	public Empleados buscarSalario(Empleados salario) {
		for (Empleados empleados : listaEmpleados) {
			if (empleados.compareToSalario(salario)) {
				return empleados;
			}
		}
		return null;
	}

	public Empleados buscarOficio(Empleados oficio) {
		for (Empleados empleados : listaEmpleados) {
			if (empleados.compareToOficio(oficio)) {
				return empleados;
			}
		}
		return null;
	}

	public Empleados buscarComision(Empleados comision) {
		for (Empleados empleados : listaEmpleados) {
			if (empleados.compareToComision(comision)) {
				return empleados;
			}
		}
		return null;
	}

	public Departamentos buscarDepartamento(Departamentos dept_no) {
		for (Departamentos departamento : listaDepartamentos) {
			if (departamento.compareToNumero(dept_no)) {
				return departamento;
			}
		}
		return null;
	}

	public Empleados buscarJefe(Empleados jefe) {
		for (Empleados empleado : listaEmpleados) {
			if(empleado.compareToJefe(jefe)) {
				return empleado;
			}
		}
		return null;
	}

	public Empleados buscarFechaAlta(Empleados fecha) {
		for (Empleados empleado : listaEmpleados) {
			if(empleado.compareToFechaAlta(fecha)) {
				return empleado;
			}
		}
		return null;
	}

}
