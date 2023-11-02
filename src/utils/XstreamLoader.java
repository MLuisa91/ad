package utils;

import java.io.File;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import controlador.Constantes;
import modelo.Departamentos;
import modelo.Empleados;

public class XstreamLoader {

	public List<Empleados> loadEmpleados() {
		XStream xs = new XStream();

		xs.alias("empleados", EmpleadosMapper.class);
		xs.alias("empleado", Empleados.class);
		xs.addImplicitCollection(EmpleadosMapper.class, "empleado");
		return ((EmpleadosMapper) xs.fromXML(new File(Constantes.FILE_EMPLEADOS))).getEmpleado();
	}

	public List<Departamentos> loadDepartamentos() {
		XStream xs = new XStream();

		xs.alias("departamentos", DepartamentosMapper.class);
		xs.alias("departamento", Departamentos.class);
		xs.addImplicitCollection(DepartamentosMapper.class, "departamento");
		return ((DepartamentosMapper) xs.fromXML(new File(Constantes.FILE_DEPARTAMENTOS))).getDepartamento();

	}

}
