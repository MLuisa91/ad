package controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDataControler {

	//Validaciones a través de patrones
	
	public static boolean validarUsuario(String usuario) {
		String patron = "[a-zA-Z0-9-_]{4,10}$"; 
		return validar(usuario, patron);
	}

	public static boolean validarPassword(String password) {
		String patron = "[A-Z]{2,}[0-9]{5,}[a-z]{1,}$";
		return validar(password, patron);
	}
	
	//En este método añadimos el uso de la ñ y de las vocales acentuadas para poder utilizarlas
	public static boolean validarCadena(String cadena) {
		String patron = "^([A-ZÑÁÉÍÓÚ]{1}[a-zñáéíóú]+[ ]?){1,2}$";
		return validar(cadena, patron) && cadena.length()>=3;
	}
	
	public static boolean validar(String cadena, String patron) {
		Pattern p = Pattern.compile(patron);
		Matcher m = p.matcher(cadena);

		if (m.matches()) {
			return true;
		}

		return false;
	}
	
	public static boolean validarFecha(String fecha) {
		String patron = "^\\d{4}([\\-/.])(0?[1-9]|1[1-2])\\1(3[01]|[12][0-9]|0?[1-9])$";
		return validar(fecha, patron);
	}
	
	public static boolean validarDni(String dni) {
		String patron = "[0-9]{8}[A-Z]{1}";
		return validar(dni, patron);
	}
	
	public static boolean validarAula(String aula) {
		String patron = "^{1}[123]{1}[A-Z]";
		return validar(aula, patron);
	}
	
	public static boolean validarTelefono(String telefono) {
		String patron = "^?[6|7|9][0-9]{8}$";
		return validar(telefono, patron);
	}
	
	

}
