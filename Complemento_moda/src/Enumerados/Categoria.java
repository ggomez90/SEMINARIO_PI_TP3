package Enumerados;

import java.util.Scanner;

import Utilidades.MetodosGenerales;

public enum Categoria {
	INDUMENTARIA (1, "Indumentaria"),
	CALZADO (2, "Calzado"),
	ACCESORIO (3, "Accesorio");
	
	private int codigo;
	private String nombre;
	
	private Categoria (int codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public int getCodigo () {
		return this.codigo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public static Categoria seleccionarCategoria () {
		Scanner entrada = new Scanner (System.in);
		String auxOpcion;
		int opcion = 0;
		boolean exito = false;
		System.out.println("Seleccione una Categoria:");
		do {
			for (Categoria elemento: Categoria.values()) {
				System.out.println(elemento.getCodigo() + " - " + elemento.getNombre());
			}
			System.out.print("Ingrese el código de la categoría: ");
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
		for (Categoria elemento : Categoria.values()) {
            if (elemento.getCodigo() == opcion) {
                return elemento;
            }
        }
		return null;
	}
}
