package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class Utiles {

	/**
	 * Método que utilizamos para convertir una fecha de formato Date a formato
	 * String
	 * 
	 * @param fecha
	 * @return
	 */
	public static String convertDateToString(Date fecha) {
		String pattern = "yyyy-MM-dd HH:mm:ss.S z";
		DateFormat df = new SimpleDateFormat(pattern);

		return df.format(fecha);
	}

	/**
	 * Método para convertir un String en formato fecha
	 * 
	 * @param fecha
	 * @return
	 */
	public static Date convertStringToDate(String fecha) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());

			return formatter.parse(fecha);
		} catch (ParseException e) {
			System.out.println("El formato de la fecha introducido no es correcto");
		}
		return null;

	}

	/**
	 * Método que devuelve true o false si es o no double
	 * 
	 * @return
	 */
	public static boolean verificarSiDouble(String valor) {
		String regex = "^(0|[1-9]\\d*)(\\.\\d+)?$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(valor).find();
	}
	
	public static boolean verificarSiInt(String valor) {
		String regex = "[0-9]$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(valor).find();
	}

}
