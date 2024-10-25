package Enumerados;

import java.util.Scanner;

import Utilidades.MetodosGenerales;

public enum Sexo {
	MASCULINO(1, "Masculino"),
	FEMENINO(2, "Femenino"),
	OTRO(3, "Otro");
	
	private int codigo;
	private String nombre;
	
	private Sexo (int codigo, String nombre){
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public int getCodigo () {
		return this.codigo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public static String mostrarSexo(Sexo elemento) {
		if (elemento != null) {
			return elemento.getNombre();
		} else {
			return "No existe sexo seleccionada.";
		}
	}
	
	public static Sexo seleccionarSexo () {
		Scanner entrada = new Scanner (System.in);
		String auxOpcion;
		int opcion = 0;
		boolean exito = false;
		System.out.println("Seleccione una sexo:");
		do {
			for (Sexo elemento: Sexo.values()) {
				System.out.println(elemento.getCodigo() + " - " + elemento.getNombre());
			}
			System.out.print("Ingrese el código del sexo: ");
			auxOpcion = entrada.nextLine();
			exito = MetodosGenerales.controlNumeroEntero(auxOpcion);
			if (exito) {
				opcion = Integer.parseInt(auxOpcion);
				if (opcion < 1 || opcion > 3) {
					System.out.println("Código inválido, intente de nuevo.");
					exito = false;
				} else {
					exito = true;
				}
			} else {
				Utilidades.MensajesConsola.verificarDato();
			}
		} while(!exito);
		for (Sexo elemento : Sexo.values()) {
            if (elemento.getCodigo() == opcion) {
                return elemento;
            }
        }
		return null;
	}
}
