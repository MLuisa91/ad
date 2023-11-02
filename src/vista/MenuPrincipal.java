package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuPrincipal {
	
	static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

	public static void mostrarMenu() {

		int opcion = 0;
		do {
			// Mostrar menu principal
			System.out.println("---Bienvenido---");
			System.out.println("Elija una de estas opciones");
			System.out.println("1. Gestionar empleados");
			System.out.println("2. Gestionar departamentos");
			System.out.println("3. Salir");
			try {
				opcion = Integer.parseInt(teclado.readLine());
				switch (opcion) {
				case 1:
					new VentanaEmpleados().init();
					break;
				case 2:
					new VentanaDepartamentos().init();
					break;
				default:
					break;
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

		} while (opcion!=3);

	}

}
