package Enumerados;

import java.util.Scanner;

import Utilidades.MetodosGenerales;

public enum FormaPago {
	EFECTIVO (1, "Efectivo"),
	TARJETA_DEBITO (2, "Tarjeta debito"),
	TARJETA_CREDITO (3, "Tarjeta credito"),
	TRANSFERENCIA (4, "Transferencia bancaria"),
	CUENTA_CORRIENTE (5, "Cuenta Corriente");
	
	private int codigo;
	private String nombre;
	
	private FormaPago (int codigo, String nombre){
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public int getCodigo () {
		return this.codigo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public static FormaPago seleccionarFormaPago () {
		Scanner entrada = new Scanner (System.in);
		String auxOpcion;
		int opcion = 0;
		boolean exito = false;
		System.out.println("Seleccione una Forma de pago:");
		do {
			for (FormaPago elemento: FormaPago.values()) {
				System.out.println(elemento.getCodigo() + " - " + elemento.getNombre());
			}
			System.out.print("Ingrese el código: ");
			auxOpcion = entrada.nextLine();
			exito = MetodosGenerales.controlNumeroEntero(auxOpcion);
			if (exito) {
				opcion = Integer.parseInt(auxOpcion);
				if (opcion < 1 || opcion > 5) {
					System.out.println("Código inválido, intente de nuevo.");
					exito = false;
				} else {
					exito = true;
				}
			} else {
				Utilidades.MensajesConsola.verificarDato();
			}
		} while(!exito);
		for (FormaPago elemento : FormaPago.values()) {
            if (elemento.getCodigo() == opcion) {
                return elemento;
            }
        }
		return null;
	}
}
