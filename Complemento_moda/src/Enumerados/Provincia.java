package Enumerados;

import java.util.Scanner;

import Utilidades.MetodosGenerales;

public enum Provincia {
	BUENOS_AIRES (1, "BUENOS AIRES"),
	CATAMARCA (2, "CATAMARCA"),
	CHACO (3, "CHACO"),
	CHUBUT (4, "CHUBUT"),
	CORDOBA (5, "CORDOBA"),
	CORRIENTES (6, "CORRIENTES"),
	ENTRE_RIOS (7, "ENTRE RIOS"),
	FORMOSA (8, "FORMOSA"),
	JUJUY (9, "JUJUY"),
	LA_PAMPA (10, "LA PAMPA"),
	LA_RIOJA (11, "LA RIOJA"),
	MENDOZA (12, "MENDOZA"),
	MISIONES (13, "MISIONES"),
	NEUQUEN (14, "NEUQUEN"),
	RIO_NEGRO (15, "RIO NEGRO"),
	SALTA (16, "SALTA"),
	SAN_JUAN (17, "SAN JUAN"),
	SAN_LUIS (18, "SAN LUIS"),
	SANTA_CRUZ (19, "SANTA CRUZ"),
	SANTA_FE (20, "SANTA FE"),
	SANTIAGO_DEL_ESTERO (21, "SANTIAGO DEL ESTERO"),
	TIERRA_DEL_FUEGO (22, "TIERRA DEL FUEGO"),
	TUCUMAN (23, "TUCUMAN");
	
	private int codigo;
	private String nombre;
	
	private Provincia (int codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public int getCodigo () {
		return this.codigo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public static String mostrarProvincia(Provincia elemento) {
		if (elemento != null) {
			return elemento.getNombre();
		} else {
			return "No existe provincia seleccionada.";
		}
	}
	
	public static Provincia seleccionarProvincia () {
		Scanner entrada = new Scanner (System.in);
		String auxOpcion;
		int opcion = 0;
		boolean exito = false;
		System.out.println("Seleccione una provincia:");
		do {
			for (Provincia elemento: Provincia.values()) {
				System.out.println(elemento.getCodigo() + " - " + elemento.getNombre());
			}
			System.out.print("Ingrese el código de la provincia: ");
			auxOpcion = entrada.nextLine();
			exito = MetodosGenerales.controlNumeroEntero(auxOpcion);
			if (exito) {
				opcion = Integer.parseInt(auxOpcion);
				if (opcion < 1 || opcion > 23) {
					System.out.println("Código inválido, intente de nuevo.");
					exito = false;
				} else {
					exito = true;
				}
			} else {
				Utilidades.MensajesConsola.verificarDato();
			}
		} while(!exito);
		for (Provincia elemento : Provincia.values()) {
            if (elemento.getCodigo() == opcion) {
                return elemento;
            }
        }
		return null;
	}
	
	public static Provincia convertirProvincia(String dato) {
		if (dato != null) {
			for (Provincia elemento : Provincia.values()) {
	            if (elemento.getNombre().equals(dato)) {
	                return elemento;
	            }
	        }
		}
		return null;
	}
}
