package Utilidades;

import java.util.Scanner;
import Excepciones.ControlNumerico;

public class MetodosGenerales {

	public static String verificarBoolean (boolean flag) {
		if(flag) {
			return "SI";
		} else {
			return "NO";
		}
		
	}
	
	public static boolean devolverBoolean (int opcion) {
		if (opcion == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean controlStringVacio (String dato) {
		if (dato.equals("") || dato.equals(null)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean controlNumeroEntero(String numero) {
		if (!controlStringVacio(numero)) {
			Utilidades.MensajesConsola.datoVacio();
			return false;
		} else if (!Excepciones.ControlNumerico.verificarEntero(numero)) {
			Utilidades.MensajesConsola.verificarDato();
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean controlNumeroDecimal(String numero) {
		if (!controlStringVacio(numero)) {
			Utilidades.MensajesConsola.datoVacio();
			return false;
		} else if (!Excepciones.ControlNumerico.verificarDecimal(numero)) {
			Utilidades.MensajesConsola.verificarDato();
			return false;
		} else {
			return true;
		}
	}
	
	public static int datoObligatorioEntero (String mensaje) {
		boolean exito = false;
		Scanner entrada = new Scanner (System.in);
		do {
			System.out.print(mensaje);
			String datoString = entrada.nextLine();
			if (!controlStringVacio(datoString)) {
				Utilidades.MensajesConsola.datoVacio();
			} else if (MetodosGenerales.controlNumeroEntero(datoString)) {
				return Integer.parseInt(datoString);
			} else {
				exito = false;
			}
		}while (!exito);
		return 0;
	}
	
	public static double datoObligatorioDouble (String mensaje) {
		boolean exito = false;
		Scanner entrada = new Scanner (System.in);
		do {
			System.out.print(mensaje);
			String datoString = entrada.nextLine();
			if (!controlStringVacio(datoString)) {
				Utilidades.MensajesConsola.datoVacio();
			} else if (MetodosGenerales.controlNumeroDecimal(datoString)) {
				return Double.parseDouble(datoString);
			} else {
				exito = false;
			}
		}while (!exito);
		return 0;
	}
	
	public static String datoObligatorioString (String mensaje) {
		boolean exito = false;
		Scanner entrada = new Scanner (System.in);
		do {
			System.out.print(mensaje);
			String nombres = entrada.nextLine();
			if (MetodosGenerales.controlStringVacio(nombres)){
				return nombres;
			} else {
				exito = false;
				MensajesConsola.datoVacio();
			}
		}while (!exito);
		return null;
	}
	
	public static int castearEntero (String mensaje) {
		int numero = 0;
		boolean flag = true;
		Scanner entrada = new Scanner(System.in);
		do {
			System.out.print(mensaje);
			String numeroString = entrada.nextLine();
			if(Excepciones.ControlNumerico.verificarEntero(numeroString)) {
				numero = Integer.parseInt(numeroString);
				flag = true;
			} else {
				MensajesConsola.verificarDato();
				flag = false;
			}
		}while(!flag);
		return numero;
	}
	
	public static double castearDecimal (String mensaje) {
		double numero = 0;
		boolean flag = true;
		Scanner entrada = new Scanner(System.in);
		do {
			System.out.print(mensaje);
			String numeroString = entrada.nextLine();
			if(Excepciones.ControlNumerico.verificarDecimal(numeroString)) {
				numero = Double.parseDouble(numeroString);
				flag = true;
			} else {
				MensajesConsola.verificarDato();
				flag = false;
			}
		}while(!flag);		
		return numero;
	}
	
	public static boolean verificarRango (int opcion, int desde, int hasta) {
		if (opcion >= desde && opcion <= hasta) {
			return true;
		} else {
			return false;
		}
	}
	
}
