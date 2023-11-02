package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import controlador.CRUD_Empleados;
import modelo.Departamentos;
import modelo.Empleados;
import utils.Utiles;

public class BusquedaEmpleados {

	static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
	static CRUD_Empleados cEmpleados;

	public static void menuBusqueda() throws IOException {
		int campo = 0;
		do {
			System.out.println("Introduce el campo de empleado por el que quieres realizar la búsqueda: ");
			System.out.println("1. Número de empleado");
			System.out.println("2. Apellido del empleado");
			System.out.println("3. Jefe");
			System.out.println("4. Salario");
			System.out.println("5. Oficio");
			System.out.println("6. Fecha de alta");
			System.out.println("7. Comision");
			System.out.println("8. Número del departamento");
			campo = Integer.parseInt(teclado.readLine());
			switch (campo) {
			case 1:
				System.out.println("Introduce el número de empleado: ");
				Integer numEmp = teclado.read();
				Empleados empleado = new Empleados(numEmp, null, null, null, null, null, null, null);
				empleado = cEmpleados.search(empleado);
				if (empleado == null) {
					System.out.println("El empleado no existe");
				} else {
					empleado.toString();
				}
				reiniciarMenu();
				break;
			case 2:
				System.out.println("Introduce el apellido: ");
				String apellido = teclado.readLine();
				empleado = new Empleados(null, apellido, null, null, null, null, null, null);
				empleado = cEmpleados.buscarApellido(empleado);
				if (empleado == null) {
					System.out.println("El empleado no existe");
				} else {
					empleado.toString();
				}
				reiniciarMenu();
				break;
			case 3:
				System.out.println("Introduce el número del director o jefe del empleado:");
				Integer director = teclado.read();
				empleado = new Empleados(null, null, director, null, null, null, null, null);
				empleado = cEmpleados.buscarJefe(empleado);
				if (empleado == null) {
					System.out.println("El empleado no existe");
				} else {
					empleado.toString();
				}
				reiniciarMenu();
				break;
			case 4:
				System.out.println("Salario del empleado: ");
				Double salario = Double.parseDouble(teclado.readLine());
				empleado = new Empleados(null, null, null, salario, null, null, null, null);
				empleado = cEmpleados.buscarSalario(empleado);
				if (empleado == null) {
					System.out.println("El empleado no existe");
				} else {
					empleado.toString();
				}
				reiniciarMenu();
				break;
			case 5:
				System.out.println("Oficio del empleado");
				String oficio = teclado.readLine();
				empleado = new Empleados(null, null, null, null, oficio, null, null, null);
				empleado = cEmpleados.buscarOficio(empleado);
				if (empleado == null) {
					System.out.println("El empleado no existe");
				} else {
					empleado.toString();
				}
				reiniciarMenu();
				break;
			case 6:
				System.out.println("Fecha en la que el empleado fue dado de alta: ");
				String fecha = teclado.readLine();
				Date fecha2 = Utiles.convertStringToDate(fecha);
				empleado = new Empleados(null, null, null, null, null, fecha2, null, null);
				empleado = cEmpleados.buscarFechaAlta(empleado);
				if (empleado == null) {
					System.out.println("El empleado no existe");
				} else {
					empleado.toString();
				}
				reiniciarMenu();
				break;
			case 7:
				System.out.println("Comision del empleado: ");
				Double comision = Double.parseDouble(teclado.readLine());
				empleado = new Empleados(null, null, null, null, null, null, comision, null);
				empleado = cEmpleados.buscarComision(empleado);
				if (empleado == null) {
					System.out.println("El empleado no existe");
				} else {
					empleado.toString();
				}
				reiniciarMenu();
				break;
			case 8:
				System.out.println("Número del departamento del empleado: ");
				Integer numeroDept = teclado.read();
				Departamentos departamento = new Departamentos(numeroDept, null, null);
				departamento = cEmpleados.buscarDepartamento(departamento);
				if (departamento == null) {
					System.out.println("El departamento no existe");
				} else {
					departamento.toString();
				}
				reiniciarMenu();
				break;
			default:
				System.out.println("Ese campo no está registrado para los empleados. ");
				break;
			}

		} while (campo < 1 || campo > 8);
	}

	private static void reiniciarMenu() throws IOException {
		String op;
		do {
			System.out.println("¿Desea realizar otra acción? (s/n)");
			op = teclado.readLine();
			if (op.equalsIgnoreCase("s")) {
				MenuPrincipal.mostrarMenu();
			} else if (op.equalsIgnoreCase("n")) {
				System.out.println("Hasta pronto.");
				break;
			} else {
				System.out.println("La opción no es correcta");
			}
		} while (!op.equalsIgnoreCase("s") || !op.equalsIgnoreCase("n"));

	}

}
