package modelo;

import java.util.Date;

public class Jefe extends Empleados {
	
	public Jefe(int emp_no, String apellido, double salario, String oficio, Date fecha_alt, double comision,
			Departamentos departamento) {
		super(emp_no, apellido, null, salario, oficio, fecha_alt, comision, departamento); 
	}


}
