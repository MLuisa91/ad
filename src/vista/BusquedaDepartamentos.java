package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controlador.CRUD_Departamentos;
import modelo.Departamentos;

public class BusquedaDepartamentos {

	static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
	static CRUD_Departamentos cDepartamentos;

	public static void menuBusqueda() throws IOException {
		int campo = 0;

		do {
			System.out.println("Introduce el campo de departamento por el que quieres realizar la búsqueda: ");
			System.out.println("1. Nombre");
			System.out.println("2. Localidad");
			System.out.println("3. Numero de departamento");
			campo = Integer.parseInt(teclado.readLine());
			switch (campo) {
			case 1:
				System.out.println("Introduce el nombre del departamento: ");
				String nombre = teclado.readLine();
				Departamentos departamento = new Departamentos(null, nombre, null);
				departamento = cDepartamentos.buscarNombre(departamento);
				if (departamento == null) {
					System.out.println("El departamento no existe");
				} else {
					departamento.toString();
				}
				reiniciarMenu();
				break;
			case 2:
				System.out.println("Introduce la localidad donde se ubica el departamento: ");
				String localidad = teclado.readLine();
				departamento = new Departamentos(null, null, localidad);
				departamento = cDepartamentos.buscarLocalidad(departamento);
				if (departamento == null) {
					System.out.println("El departamento no existe");
				} else {
					departamento.toString();
				}
				reiniciarMenu();
				break;
			case 3:
				System.out.println("Introduce el número del departamento: ");
				Integer numero = teclado.read();
				departamento = new Departamentos(numero, null, null);
				departamento = cDepartamentos.buscarNumero(departamento);
				if (departamento == null) {
					System.out.println("El departamento no existe");
				} else {
					departamento.toString();
				}
				reiniciarMenu();
				break;
			default:
				System.out.println("Opción incorrecta");
				break;
			}
			// Volver a menu
		} while (campo < 1 || campo > 4);
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
