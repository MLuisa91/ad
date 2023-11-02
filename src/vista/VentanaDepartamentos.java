package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import controlador.CRUD_Departamentos;
import modelo.Departamentos;
import utils.ApplicationLoader;
import utils.Utiles;

public class VentanaDepartamentos {

	static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
	private CRUD_Departamentos departamentosCrud;
	private ApplicationLoader loader = new ApplicationLoader();
	
	public void init() {
		//cargamos la información al fichero
		this.loadFileDepartamentos();
		this.menuDepartamentos();
	}

	public void menuDepartamentos() {

		int opcion = 0;

		do {
			// Mostrar menu
			System.out.println("---Bienvenido---");
			System.out.println("Elija una de estas opciones");
			System.out.println("1. Añadir departamento");
			System.out.println("2. Modificar departamento");
			System.out.println("3. Eliminar departamento");
			System.out.println("4. Listar departamento");
			System.out.println("5. Buscar departamento");
			try {
				opcion = Integer.parseInt(teclado.readLine());
				switch (opcion) {
				case 1:
					nuevoDepartamento(teclado);
					break;
				case 2:
					modificarDepartamento();
					break;
				case 3:
					eliminarDepartamento();
					break;
				case 4:
					listarDepartamentos();
					break;
				case 5:
					BusquedaDepartamentos.menuBusqueda();
				default:
					break;
				}
			} catch (NumberFormatException | IOException e) {
				System.out.println("La opción elegida no es correcta.");
				this.menuDepartamentos();
			}

		} while (opcion < 1 || opcion > 5);
	}

	private void loadFileDepartamentos() {
		loader.loadDepartamentos();
		departamentosCrud = loader.getDepartamentosCrud();
	}

	private void modificarDepartamento() throws IOException {
		int posicion = 0;
		try {
			do {
				String idText = "";
				do {
					listarDepartamentos();
					System.out.println("Introduce el numero de departamento que quieres modificar.");
					idText = teclado.readLine();
				} while (!Utiles.verificarSiInt(idText));

				int numero = Integer.parseInt(idText);
				posicion = departamentosCrud.search_pos(new Departamentos(numero, null, null));

				if (posicion == -1) {
					System.out.println("El empleado no existe");
				} else {

					Departamentos departamento = generarDepartamento(numero);

					if (departamentosCrud.update(departamento)) {
						System.out.println("Departamento modificado correctamente.");
					} else {
						System.out.println("Ha ocurrido un error en la actualización del departamento.");
					}
				}
			} while (posicion == -1);
		} catch (NumberFormatException e) {
			System.out.println("El departamento introducido no es correcto.");
			this.modificarDepartamento();;
		}

		pregunta();

	}

	private void eliminarDepartamento() throws IOException {
		Boolean eliminado = false;
		try {
			// repetimos hasta que podamos eliminar correctamente el departamento
			do {
				String numeroText = "";
				do {
					listarDepartamentos();
					System.out.println("Introduce el numero de departamento que quieres eliminar.");
					numeroText = teclado.readLine();
				} while (!Utiles.verificarSiInt(numeroText));
				int numero = Integer.parseInt(numeroText);

				eliminado = departamentosCrud.delete(new Departamentos(numero, null, null));
				if (!eliminado) {
					System.out.println("El departamento no existe");
				} else {
					System.out.println("Departamento eliminado correctamente.");
				}
			} while (!eliminado);
		} catch (NumberFormatException e) {
			System.out.println("El departamento introducido no es correcto.");
			this.eliminarDepartamento();
		}

		listarDepartamentos();
		pregunta();

	}

	private void nuevoDepartamento(BufferedReader teclado) throws IOException {

		String idtext = "";
		Integer numero = 0;
		Integer existeDepartamento = 0;
		
		do {
			do {
				System.out.println("Introduce el numero del departamento");
				idtext = teclado.readLine();
			}while(!Utiles.verificarSiInt(idtext));
			numero = Integer.parseInt(idtext);
			existeDepartamento = departamentosCrud.search_pos(new Departamentos(numero, null, null));
			if (existeDepartamento > -1) {
				System.out.println("El id de departamento introducido ya existe.");
			}
		}while(existeDepartamento > -1);
		//creamos el departamento y lo guardamos en el crud
		Departamentos departamento = generarDepartamento(numero);
		if(departamentosCrud.add(departamento)) {
			System.out.println("El departamento ha sido dado de alta correctamente.");
		}else {
			System.out.println("Se ha producido un error.");
		}
		
		pregunta();
	}

	private Departamentos generarDepartamento(Integer numero) throws IOException {
		System.out.println("Introduce el nombre del departamento: ");
		String nombre = teclado.readLine();
		System.out.println("Introduce la localidad donde se encuentra: ");
		String localidad = teclado.readLine();
		
		return new Departamentos(numero, nombre, localidad);
	}

	private void listarDepartamentos() throws IOException {
		for (Iterator iterator = departamentosCrud.listAll(); iterator.hasNext();) {
			Departamentos departamento = (Departamentos) iterator.next();
			System.out.println(departamento.toString());
		}
		pregunta();
	}
	
	private void pregunta() throws IOException {
		String opcion = "";
		do {
			System.out.println("Desea realizar otra operación S/N");
			opcion = teclado.readLine().toUpperCase();
			if (opcion.equals("S")) {
				this.menuDepartamentos();;
			} else {
				MenuPrincipal.mostrarMenu();
			}
		} while (!opcion.equals("S") && !opcion.equals("N"));
	}

}
