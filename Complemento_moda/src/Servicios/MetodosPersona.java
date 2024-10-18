package Servicios;

import Entidades.Persona;
import Enumerados.Provincia;
import Enumerados.Sexo;

import java.util.ArrayList;
import java.util.Scanner;
import Excepciones.ControlNumerico;
import DAO.PersonaDAO;
import Utilidades.MetodosGenerales;
import Utilidades.Fecha;
import Utilidades.MensajesConsola;

public class MetodosPersona {
	
	public Persona buscarPersona(int dni, ArrayList<Persona> listaPersonas) {
		for (Persona elemento: listaPersonas) {
			if (elemento.getDni() == dni) {
				return elemento;
			}
		}
		return null;
	}
	
	public String datosPersona(Persona persona) {
		if (persona != null) {
			return "DNI: " + persona.getDni() + "\n" + 
					"Activo: " + MetodosGenerales.verificarBoolean(persona.isActivo()) + "\n" +  
					"Apellido/s" + persona.getApellidos() + "\n" + 
					"Nombre/s: " + persona.getNombres() + "\n" +
					"Fecha de Nacimiento: " + Utilidades.Fecha.formatearFecha(persona.getFechaNacimiento()) + "\n" +
					"Sexo: " + persona.getSexo().getNombre() + "\n" +
					"Direccion: " + persona.getDireccion() + "\n" +
					"Telefono: " + persona.getTelefono() + "\n" +
					"Provincia: " + persona.getProvincia().getNombre() + "\n" +
					"Localidad: " + persona.getLocalidad() + "\n";
		}
		return null;
	}
	
	public ArrayList<String> listarPersonas (ArrayList<Persona> listadoPersonas){
		if (listadoPersonas != null) {
			ArrayList<String> lista = new ArrayList<String>();
			for (Persona elemento: listadoPersonas) {
				lista.add(datosPersona(elemento));
			}
			return lista;
		}
		return null;
	}
	
	public Persona modificarPersona(Persona persona, String opciones) {
		Scanner entrada = new Scanner(System.in);
		boolean flag = false;
		do {
			MensajesConsola.camposModificablesPersona();
			int opcion = MetodosGenerales.datoObligatorioEntero(opciones);
			boolean flagAux = MetodosGenerales.verificarRango(opcion, 1, 10);
			if (flagAux) {
				return opcionesModificablesPersona(persona, opcion);
			} else {
				MensajesConsola.verificarDato();
			}
			int salir = MensajesConsola.opcionSINO("Desea salir?: ");
			if (salir == 1) {
				flag = true;
			} else {
				flag = false;
			}
		}while (!flag);
		return null;
	}
	
	public Persona opcionesModificablesPersona(Persona persona, int opcion) {
		Scanner entrada = new Scanner (System.in);
		switch (opcion) {
			case 1: int dni = MetodosGenerales.datoObligatorioEntero("Ingrese nuevo DNI: ");
					persona.setDni(dni);
					break;
			case 2: String nombres = MetodosGenerales.datoObligatorioString("Ingrese nuevo nombre/s: ");
					persona.setNombres(nombres);		
					break;
			case 3: String apellidos = MetodosGenerales.datoObligatorioString("Ingrese nuevo apellido/s: ");
					persona.setApellidos(apellidos);		
					break;
			case 4: int activo = MensajesConsola.opcionSINO("El cliente se encuentra ACTIVO?");
					if (activo == 1) {
						persona.setActivo(true);
					} else {
						persona.setActivo(false);
					}
			case 5: System.out.print("Ingrese nuevo N° de teléfono del cliente: ");
					persona.setTelefono(entrada.nextLine());
					break;
			case 6: persona.setProvincia(Provincia.seleccionarProvincia());
					break;
			case 7: System.out.print("Ingrese nueva localdad del cliente: ");
					persona.setLocalidad(entrada.nextLine());
					break;
			case 8: System.out.print("Ingrese direccion del cliente: ");
					persona.setDireccion(entrada.nextLine());
					break;
			case 9: persona.setFechaNacimiento(Utilidades.Fecha.crearFecha("Ingrese la nueva fecha de nacimiento del Cliente:"));
					break;
			case 10:persona.setSexo(Sexo.seleccionarSexo());
					break;
		}
		return persona;
	}
	
	public Persona bajaPersona (Persona persona, String tipo) {
		int opcion = MensajesConsola.opcionSINO("El " + tipo + " pasará a estado INACTIVO, desea continuar?");
		if (opcion == 1) {
			persona.setActivo(false);
			return persona;
		}
		return persona;
	}

}
