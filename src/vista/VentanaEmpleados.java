package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Iterator;

import controlador.CRUD_Departamentos;
import controlador.CRUD_Empleados;
import modelo.Departamentos;
import modelo.Empleados;
import modelo.Jefe;
import modelo.Operario;
import utils.ApplicationLoader;
import utils.Utiles;

public class VentanaEmpleados {

	static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
	private CRUD_Empleados empleadosCrud;
	private CRUD_Departamentos departamentosCrud;
	private ApplicationLoader loader = new ApplicationLoader();

	public void init() {
		// empezamos cargando la información del fichero de empleados
		this.loadFiles();
		this.mostrarMenu();
	}

	private void mostrarMenu() {
		int opcion = 0;
		do {
			// Mostrar menu
			System.out.println("---Bienvenido---");
			System.out.println("Elija una de estas opciones");
			System.out.println("1. Crear empleado");
			System.out.println("2. Modificar emlpleado");
			System.out.println("3. Eliminar empleado");
			System.out.println("4. Empleado es jefe o no");
			System.out.println("5. Buscar empleado");
			try {
				opcion = Integer.parseInt(teclado.readLine());
				switch (opcion) {
				case 1:
					anadirEmpleado();
					break;
				case 2:
					listarEmpleados();
					modificarEmpleado();
					break;
				case 3:
					listarEmpleados();
					eliminarEmpleado();
					break;
				case 4:
					listarEmpleados();
					crearJefe();
					break;
				case 5:
					BusquedaEmpleados.menuBusqueda();
				default:
					break;
				}
			} catch (NumberFormatException | IOException e) {
				System.out.println("Opción elegida no es correcta.");
				this.mostrarMenu();
			}

		} while (opcion < 1 || opcion > 5);
	}

	private void pregunta() throws IOException {
		String opcion = "";
		do {
			System.out.println("Desea realizar otra operación S/N");
			opcion = teclado.readLine().toUpperCase();
			if (opcion.equals("S")) {
				this.mostrarMenu();
			} else {
				MenuPrincipal.mostrarMenu();
			}
		} while (!opcion.equals("S") && !opcion.equals("N"));
	}

	// para cargar en los cruds las listas de empleados y departamentos
	private void loadFiles() {
		loader.loadEmpleados();
		loader.loadDepartamentos();
		empleadosCrud = loader.getEmpleadosCrud();
		departamentosCrud = loader.getDepartamentosCrud();
	}

	private void crearJefe() throws NumberFormatException, IOException {
		System.out.println("Introduce el numero de empleado que es jefe");
		int i = Integer.parseInt(teclado.readLine());

		// Empleados empleado = new Empleados();
		System.out.println("Introduce el numero de jefe");
		int numeroJefe = Integer.parseInt(teclado.readLine());
		// Jefe jefe = new Jefe(empleado, 2);
	}

	// Para añadir un empleado al crud
	private void anadirEmpleado() throws IOException {
		String idtext = "";
		Integer id = 0;
		Integer existeEmpleado = 0;
		// repetimos hasta que el id del empleado tenga un formato válido y el empleado
		// no exista
		do {
			do {
				System.out.println("Introduce el numero de empleado");
				idtext = teclado.readLine();
			} while (!Utiles.verificarSiInt(idtext));

			id = Integer.parseInt(idtext);
			existeEmpleado = empleadosCrud.search_pos(new Empleados(id, null, null, null, null, null, null, null));
			if (existeEmpleado > -1) {
				System.out.println(String.format("El id de empleado introducido ya existe %s", idtext));
			}

		} while (existeEmpleado > -1);

		// creamos el empleado y lo guardamos en el crud
		Empleados empleado = generarEmpleado(id);
		if (empleadosCrud.add(empleado)) {
			System.out.println("Empleado dado de alta correctamente.");
		} else {
			System.out.println("Se ha producido un error en el alta de usuario.");
		}

		pregunta();
	}

	/**
	 * Método para eliminar un empleado
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private void eliminarEmpleado() throws NumberFormatException, IOException {
		Boolean eliminado = false;
		try {
			// repetimos hasta que podamos eliminar correctamente el empleado
			do {
				String numeroText = "";
				do {
					System.out.println("Introduce el numero de empleado que quieres eliminar.");
					numeroText = teclado.readLine();
				} while (!Utiles.verificarSiInt(numeroText));
				int numero = Integer.parseInt(numeroText);

				eliminado = empleadosCrud.delete(new Empleados(numero, null, null, null, null, null, null, null));
				if (!eliminado) {
					System.out.println("El empleado no existe");
				} else {
					System.out.println("Empleado eliminado correctamente.");
				}
			} while (!eliminado);
		} catch (NumberFormatException e) {
			System.out.println("El empleado introducido no es correcto.");
			this.eliminarEmpleado();
		}

		listarEmpleados();
		pregunta();

	}

	/**
	 * Método para eliminar un empleado
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private void modificarEmpleado() throws NumberFormatException, IOException {
		int posicion = 0;
		try {
			do {
				String idText = "";
				do {
					System.out.println("Introduce el numero de empleado que quieres modificar.");
					idText = teclado.readLine();
				} while (!Utiles.verificarSiInt(idText));

				int numero = Integer.parseInt(idText);
				posicion = empleadosCrud.search_pos(new Empleados(numero, null, null, null, null, null, null, null));

				if (posicion == -1) {
					System.out.println("El empleado no existe");
				} else {

					Empleados empleado = generarEmpleado(numero);

					if (empleadosCrud.update(empleado)) {
						System.out.println("Empleado modificado correctamente.");
					} else {
						System.out.println("Ha ocurrido un error en la actualización del empleado.");
					}
				}
			} while (posicion == -1);
		} catch (NumberFormatException e) {
			System.out.println("El empleado introducido no es correcto.");
			this.modificarEmpleado();
		}

		pregunta();

	}

	/**
	 * Método para generar un objeto de empleado
	 * 
	 * @param numero
	 * @return
	 * @throws IOException
	 */
	private Empleados generarEmpleado(Integer numero) throws IOException {
		System.out.println("Introduce el apellido:");
		String apellido = teclado.readLine();

		String directorText = "";
		Boolean existeDirector = true;
		Integer dir = 0;
		do {
			do {
				System.out.println("¿Desea establecer este empleado como jefe S/N?");
				String respuesta = teclado.readLine().toUpperCase();
				if (respuesta.equals("S")) {
					directorText = null;
				} else {
					listarJefes();
					System.out.println("Introduce su número de jefe:");
					directorText = teclado.readLine();
				}
			} while (directorText!=null && !Utiles.verificarSiInt(directorText));

			if (directorText != null && empleadosCrud.search_pos(
					new Empleados(Integer.parseInt(directorText), null, null, null, null, null, null, null)) < 0) {								
				System.out.println("El empleado introducido no existe.");
				existeDirector = false;
			}
			
		} while (!existeDirector);
		
		if(directorText!=null)
			dir= Integer.parseInt(directorText);

		String salarioText = "";
		do {
			System.out.println("Introduce su salario:");
			salarioText = teclado.readLine();
		} while (!Utiles.verificarSiDouble(salarioText));
		Double salario = Double.parseDouble(salarioText);

		System.out.println("Introduce oficio:");
		String oficio = teclado.readLine();

		Date fecha_alt = null;
		do {
			System.out.println("Introduce fecha de alta dd-mm-yyyy:");
			fecha_alt = Utiles.convertStringToDate(teclado.readLine());
		} while (fecha_alt == null);

		String comisionText = "";
		do {
			System.out.println("Introduce la comisión:");
			comisionText = teclado.readLine();
		} while (!Utiles.verificarSiDouble(comisionText));
		Double comision = Double.parseDouble(comisionText);

		String deptText = "";
		Integer dept_no = 0;
		Departamentos departamento = null;
		do {
			do {
				System.out.println("Introduce el numero de departamento al que pertenece:");
				this.listarDepartamentos();
				deptText = teclado.readLine();
			} while (!Utiles.verificarSiInt(deptText));

			dept_no = Integer.parseInt(deptText);
			departamento = departamentosCrud.buscarNumero(new Departamentos(dept_no, null, null));
			if (departamento == null)
				System.out.println("El departamento introducido no existe.");

		} while (departamento == null);

		// creamos el objeto empleado
		if (directorText == null)
			return new Jefe(numero, apellido, salario, oficio, fecha_alt, comision, departamento);
		else
			return new Operario(numero, apellido, dir, salario, oficio, fecha_alt, comision, departamento);
	}

	private void listarEmpleados() {
		for (Iterator iterator = empleadosCrud.listAll(); iterator.hasNext();) {
			Empleados empleado = (Empleados) iterator.next();
			System.out.println(empleado.toString());
		}
	}
	
	private void listarJefes() {
		for (Iterator iterator = empleadosCrud.listJefes(); iterator.hasNext();) {
			Empleados empleado = (Empleados) iterator.next();
			System.out.println(empleado.toString());
		}
	}
	
	private void listarDepartamentos() {
		for (Iterator iterator = departamentosCrud.listAll(); iterator.hasNext();) {
			Departamentos departamento = (Departamentos) iterator.next();
			System.out.println(departamento.toString());
		}
	}
}
