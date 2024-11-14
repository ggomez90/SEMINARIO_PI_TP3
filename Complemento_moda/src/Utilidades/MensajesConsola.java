package Utilidades;

import java.util.Scanner;

import Entidades.Persona;
import Entidades.Usuario;

public class MensajesConsola {
	
	public static void separador() {
		System.out.println("=================================================");
	}

	public static void verificarDato() {
		System.out.println("El dato ingresado no es correcto, reintente.");
	}
	
	public static void datoVacio() {
		System.out.println("El dato solicitado es obligatorio, reintente");
	}
	
	public static void datoNoEncontrado() {
		System.out.println("No existen coincidencias con su búsqueda.");
	}
	
	public static void cambiosGuardados() {
		System.out.println("Los cambios fueron guardados con éxito.");
	}
	
	public static void moduloEnProceso(String mensaje) {
		System.out.println("El módulo de " + mensaje + " se encuentra en producción.");
	}
	
	public static int ingreseOpcion() {
		int opcion = MetodosGenerales.datoObligatorioEntero("Ingrese una opción: ");
		return opcion;
	}
	
	public static void saludoBienvenida(int tipoUsuario) {
		if (tipoUsuario == 1 || tipoUsuario == 2) {
			System.out.println("USUARIO " + Usuario.usuarioLogueado.getUsuario().toUpperCase());
		} else {
			System.out.println("Comuniquese con el administrador para reestablecer su usuario/clave");
		}
	}
	
	public static void saludoFinal() {
		System.out.println("El programa ha finalizado, hasta luego.");
	}
	
	public static void enProcesoDesarrollo() {
		System.out.println("Este módulo se encuentra en proceso de desarrollo.");
	}
	
	public static String retornarParametroParaConsola(String mensaje) {
		if (mensaje != null) {
			return mensaje;
		} else {
			return "Sin datos.";
		}
	}
	
	public static void imprimirEnConsola(String mensaje) {
		if (mensaje != null) {
			System.out.println(mensaje);
		}
	}
	
	public static void estadoPersona(Persona persona, String mensaje) {
		if (persona != null) {
			if (persona.isActivo()) {
				System.out.println(mensaje + " se encuentra ACTIVO.");
			} else {
				System.out.println(mensaje + " NO se encuentra ACTIVO.");
			}
		}
	}
	
	public static void operacionNoDisponible() {
		System.out.println("La operacion no se puede realizar.");
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
		System.out.println("4- Estado");
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
	
	public static void camposModificablesUsuario() {
		System.out.println("1- Nombre de usuario");
		System.out.println("2- Clave");
		System.out.println("3- Permisos");
		System.out.println("4- Cambio de propietario");
	}
	
	public static int buscarPorDatoEntero(String mensaje) {
		Scanner entrada = new Scanner (System.in);
		boolean flag = false;
		int dni = 0;
		do {
			System.out.print(mensaje);
			String dniString = entrada.nextLine();
			flag = MetodosGenerales.controlNumeroEntero(dniString);
			if (flag) {
				dni = Integer.parseInt(dniString);
				flag = true;
			} /*else {
				verificarDato();
			}*/
		}while(!flag);
		return dni;
	}
}
