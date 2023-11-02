package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import modelo.Empleados;
import modelo.Jefe;
import modelo.Operario;

public class EmpleadosMapper {

	@XStreamImplicit
	List<Empleados> empleado;

	public List<Empleados> getEmpleado() {
		Iterator<Empleados> iterator = empleado.iterator();
		List<Empleados> empleadosAux = new ArrayList<>();
		while (iterator.hasNext()) {
			Empleados empl = iterator.next();
			if (empl.getJefe() == null)
				empleadosAux.add(new Jefe(empl.getNumero(), empl.getApellido(), empl.getSalario(), empl.getOficio(),
						empl.getFechaAlta(), empl.getComision(), empl.getDepartamento()));
			else
				empleadosAux.add(new Operario(empl.getNumero(), empl.getApellido(), empl.getJefe(), empl.getSalario(),
						empl.getOficio(), empl.getFechaAlta(), empl.getComision(), empl.getDepartamento()));
		}
		return empleadosAux;
	}

}
