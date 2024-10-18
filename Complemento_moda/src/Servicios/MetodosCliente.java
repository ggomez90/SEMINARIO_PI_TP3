package Servicios;
import java.util.Scanner;

import Entidades.Cliente;
import Entidades.Persona;
import Enumerados.Provincia;
import Enumerados.Sexo;
import Utilidades.MetodosGenerales;

public class MetodosCliente {
	
	public Cliente altaCliente() {
		Scanner entrada = new Scanner (System.in);
		boolean exito;
		int opcion;
		int dni = MetodosGenerales.datoObligatorioEntero("Ingrese DNI: ");
		String nombres = MetodosGenerales.datoObligatorioString("Ingrese nombre/s: ");
		String apellidos = MetodosGenerales.datoObligatorioString("Ingrese apellido/s: ");
		Cliente nuevoCliente = new Cliente (dni, nombres, apellidos);
		opcion = Utilidades.MensajesConsola.opcionSINO("Desea agregar N° de teléfono?");
		if (opcion == 1) {
			System.out.print("Ingrese N° de teléfono del cliente: ");
			nuevoCliente.setTelefono(entrada.nextLine());
		}
		opcion = Utilidades.MensajesConsola.opcionSINO("Desea agregar Provincia?");
		if (opcion == 1) {
			nuevoCliente.setProvincia(Provincia.seleccionarProvincia());
		}
		opcion = Utilidades.MensajesConsola.opcionSINO("Desea agregar Localidad?");
		if (opcion == 1) {
			System.out.print("Ingrese localdad del cliente: ");
			nuevoCliente.setLocalidad(entrada.nextLine());
		}

		opcion = Utilidades.MensajesConsola.opcionSINO("Desea agregar dirección?");
		if (opcion == 1) {
			System.out.print("Ingrese direccion del cliente: ");
			nuevoCliente.setDireccion(entrada.nextLine());
		}
		
		opcion = Utilidades.MensajesConsola.opcionSINO("Desea agregar fecha de nacimiento?");
		if (opcion == 1) {
			nuevoCliente.setFechaNacimiento(Utilidades.Fecha.crearFecha("Ingrese la fecha de nacimiento del Cliente:"));
		}
		
		opcion = Utilidades.MensajesConsola.opcionSINO("Desea agregar sexo?");
		if (opcion == 1) {
			nuevoCliente.setSexo(Sexo.seleccionarSexo());
		}
		return nuevoCliente;
	}
}
