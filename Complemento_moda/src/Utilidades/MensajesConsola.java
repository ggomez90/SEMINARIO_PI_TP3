package Utilidades;

import java.util.Scanner;

public class MensajesConsola {

	public static void verificarDato() {
		System.out.println("El dato ingresado no es correcto, reintente.");
	}
	
	public static void datoVacio() {
		System.out.println("El dato solicitado es obligatorio, reintente");
	}
	
	public static void datoNoEncontrado() {
		System.out.println("No existen coincidencias con su búsqueda.");
	}
	
	public static int opcionSINO(String mensaje) {
		Scanner entrada = new Scanner (System.in);
		String opcionString = null;
		int opcion = 0;
		boolean flag = false;
		do {
			System.out.println(mensaje);
			System.out.println("1- SI");
			System.out.println("0- NO");
			opcionString = entrada.nextLine();
			flag = MetodosGenerales.controlNumeroEntero(opcionString);
			if (flag) {
				opcion = Integer.parseInt(opcionString);
				if (opcion < 0 || opcion > 1) {
					flag = false;
					verificarDato();
				} else {
					flag = true;
				}
			}
		}while (!flag);
		return opcion;
	}
	
	public static void camposModificablesPersona() {
		System.out.println("1- DNI");
		System.out.println("2- Nombre/s");
		System.out.println("3- Apellido/s");
		System.out.println("4- Estado del ciente");
		System.out.println("5- Teléfono");
		System.out.println("6- Provincia");
		System.out.println("7- Localidad");
		System.out.println("8- Dirección");
		System.out.println("9- Fecha de Nacimiento");
		System.out.println("10- Sexo");
	}
	
	public static void camposModificablesEmpleado() {
		System.out.println("11- Fecha de ingreso");
		System.out.println("12- Fecha de egreso");
		System.out.println("13- Legajo");
		System.out.println("14- Salario");
	}
	
	public static int buscarPorDNI() {
		Scanner entrada = new Scanner (System.in);
		boolean flag = false;
		int dni = 0;
		do {
			System.out.print("Ingrese el DNI a buscar: ");
			String dniString = entrada.nextLine();
			flag = MetodosGenerales.controlNumeroEntero(dniString);
			if (flag) {
				dni = Integer.parseInt(dniString);
				flag = true;
			} else {
				verificarDato();
			}
		}while(!flag);
		return dni;
	}
}
