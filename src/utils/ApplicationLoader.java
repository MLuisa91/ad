package utils;

import java.util.List;

import controlador.CRUD_Departamentos;
import controlador.CRUD_Empleados;
import modelo.Departamentos;
import modelo.Empleados;

public class ApplicationLoader {

	private CRUD_Empleados empleadosCrud;
	private CRUD_Departamentos departamentosCrud;

	public ApplicationLoader() {
		empleadosCrud = new CRUD_Empleados();
		departamentosCrud = new CRUD_Departamentos();
	}
	

	public void loadEmpleados() {

		XstreamLoader xtreamLoader = new XstreamLoader();
		List<Empleados> empleados = xtreamLoader.loadEmpleados();

		for (Empleados empleado : empleados) {
			empleadosCrud.load(empleado);
		}
	}
		
	public void loadDepartamentos() {
		XstreamLoader xstreamLoader = new XstreamLoader();
		List<Departamentos> departamentos = xstreamLoader.loadDepartamentos();
		
		for (Departamentos departamento : departamentos) {
			departamentosCrud.load(departamento);
		}
	}
	

	public CRUD_Empleados getEmpleadosCrud() {
		return empleadosCrud;
	}
	
	public CRUD_Departamentos getDepartamentosCrud() {
		return departamentosCrud;
	}
}
