package Servicios;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Entidades.Cliente;
import Entidades.Empleado;
import Entidades.Persona;
import Enumerados.Provincia;
import Enumerados.Sexo;
import Utilidades.MensajesConsola;
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
	
	public void agregarClienteEnLista(Cliente nuevoCliente) {
		Cliente.listaClientes.add(nuevoCliente);
	}
	
	public Cliente buscarCliente(int dni, ArrayList<Cliente> listaClientes) {
		for (Cliente elemento: listaClientes) {
			if (elemento.getDni() == dni) {
				return elemento;
			}
		}
		return null;
	}
	
	/*public void bajaCliente () {
		int dniBuscado = MensajesConsola.buscarPorDNI();
		Cliente buscado = this.buscarCliente(dniBuscado, Cliente.listaClientes);
		if (buscado != null) {
			int opcion = MensajesConsola.opcionSINO("El Cliente pasará a estado INACTIVO, desea continuar?");
			if (opcion == 1) {
				buscado.setActivo(false);
			}
		} else {
			MensajesConsola.datoNoEncontrado();
		}
	}*/
	
	public void bajaCliente (Cliente cliente, String tipo) {
		MensajesConsola.estadoPersona(cliente,tipo);
		if (cliente.isActivo()) {
			int activo = MensajesConsola.opcionSINO("Desea modificar su estado?");
			if (activo == 1) {
				cliente.setActivo(false);
				MensajesConsola.cambiosGuardados();
			}
		} else {
			MensajesConsola.operacionNoDisponible();
		}
	}
	
	public Cliente modificarCliente (Cliente cliente) {
		return (Cliente) new MetodosPersona().modificarPersona(cliente, "Ingrese una opción: ");
	}
	
	public String datosCliente(Cliente cliente) {
		return new MetodosPersona().datosPersona(cliente);
	}
	
	public void imprimirDatosClientes(ArrayList<Cliente> listaClientes) {
		if (listaClientes != null) {
			System.out.println("Listado de Clientes:");
			for (Cliente elemento : listaClientes) {
				System.out.println(this.datosCliente(elemento));
				System.out.println("----------------------------");
			}
		}
	}
}
