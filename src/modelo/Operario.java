package modelo;

import java.util.Date;

public class Operario extends Empleados {
	
	public Operario(int emp_no, String apellido, Integer jefe, double salario, String oficio, Date fecha_alt, double comision,
			Departamentos departamento) {
		super(emp_no, apellido, jefe, salario, oficio, fecha_alt, comision, departamento); 
	}

}
