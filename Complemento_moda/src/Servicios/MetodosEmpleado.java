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

public class MetodosEmpleado {
	
	public Empleado altaEmpleado() {
		Scanner entrada = new Scanner (System.in);
		boolean exito;
		int opcion;
		System.out.println("Datos para nuevo empleado:");
		int dni = MetodosGenerales.datoObligatorioEntero("Ingrese DNI: ");
		String nombres = MetodosGenerales.datoObligatorioString("Ingrese nombre/s: ");
		String apellidos = MetodosGenerales.datoObligatorioString("Ingrese apellido/s: ");
		String telefono = MetodosGenerales.datoObligatorioString("Ingrese N° de teléfono: ");
		Provincia provincia = Provincia.seleccionarProvincia();
		String localidad = MetodosGenerales.datoObligatorioString("Ingrese localdad: ");
		String direccion = MetodosGenerales.datoObligatorioString("Ingrese direccion: ");
		LocalDate fechaNac = Utilidades.Fecha.crearFecha("Ingrese la fecha de nacimiento:");
		Sexo sexo = Sexo.seleccionarSexo();
		LocalDate fechaIngreso = Utilidades.Fecha.crearFecha("Fecha de inicio de actividades:");
		int legajo = MetodosGenerales.datoObligatorioEntero("Ingrese Legajo: ");
		double salario = MetodosGenerales.datoObligatorioDouble("Ingrese salario: ");
		Empleado nuevoEmpleado = new Empleado (dni, nombres, apellidos, direccion, telefono, provincia, localidad, fechaNac, sexo, fechaIngreso, legajo, salario);
		return nuevoEmpleado;
	}
	
	public void agregarEmpleadoEnLista(Empleado nuevoEmpleado) {
		Empleado.listaEmpleados.add(nuevoEmpleado);
	}
	
	public Empleado buscarEmpleado(int dni, ArrayList<Empleado> listaEmpleados) {
		for (Empleado elemento: listaEmpleados) {
			if (elemento.getDni() == dni) {
				return elemento;
			}
		}
		return null;
	}

	/*public Empleado bajaEmpleado () {
		int dniBuscado = MensajesConsola.buscarPorDNI();
		Empleado buscado = this.buscarEmpleado(dniBuscado, Empleado.listaEmpleados);
		if (buscado != null) {
			int opcion = MensajesConsola.opcionSINO("El empleado pasará a estado INACTIVO, desea continuar?");
			if (opcion == 1) {
				buscado.setActivo(false);
				buscado.setFechaEgreso(LocalDate.now());
			}
			return buscado;
		} else {
			MensajesConsola.datoNoEncontrado();
		}
		return buscado;
	}*/
	
	public void bajaEmpleado (Empleado empleado, String tipo) {
		if (empleado != null) {
			MensajesConsola.estadoPersona(empleado,tipo);
			if (empleado.isActivo()) {
				int activo = MensajesConsola.opcionSINO("Desea modificar su estado?");
				if (activo == 1) {
					empleado.setActivo(false);
					empleado.setFechaEgreso(LocalDate.now());
					MensajesConsola.cambiosGuardados();
				}
			} else {
				MensajesConsola.operacionNoDisponible();
			}
		}
	}
	
	public Empleado modificarEmpleado (Empleado empleado) {
		Scanner entrada = new Scanner(System.in);
		boolean flag = false;
		do {
			MensajesConsola.camposModificablesPersona();
			MensajesConsola.camposModificablesEmpleado();
			int opcion = MetodosGenerales.datoObligatorioEntero("Ingrese una opción: ");
			boolean flagAux = MetodosGenerales.verificarRango(opcion, 1, 14);
			if (flagAux) {
				if (opcion <= 10) {
					return (Empleado) new MetodosPersona().opcionesModificablesPersona(empleado, opcion);
				} else {
					return this.opcionesModificablesEmpleado(empleado, opcion);
				}
			} else {
				MensajesConsola.verificarDato();
			}
			int salir = MensajesConsola.opcionSINO("Desea salir?: ");
			if (salir == 1) {
				flag = true;
			} else {
				flag = false;
			}
		}while(!flag);
		return empleado;	
	}

	
	private Empleado opcionesModificablesEmpleado(Empleado empleado, int opcion) {
		Scanner entrada = new Scanner (System.in);
		boolean exito = false;
		switch (opcion) {
			case 11: LocalDate fechaIngreso = Utilidades.Fecha.crearFecha("Fecha de inicio de actividades:");
					 empleado.setFechaIngreso(fechaIngreso);
					 exito = true;
					 break;
			case 12: LocalDate fechaEgreso = Utilidades.Fecha.crearFecha("Fecha de cese de actividades:");
					 empleado.setFechaEgreso(fechaEgreso);
					 exito = true;
					 break;
			case 13: int legajo = MetodosGenerales.datoObligatorioEntero("Ingrese Legajo: ");
					 empleado.setLegajo(legajo);
					 exito = true;
					 break;
			case 14: double salario = MetodosGenerales.datoObligatorioDouble("Ingrese salario: ");
					 empleado.setSalario(salario);
					 exito = true;
					 break;
		}
		if (exito) {
			MensajesConsola.cambiosGuardados();
		}
		return empleado;
	}
	
	public String datosEmpleado(Empleado empleado) {
		if (empleado != null) {
			String retorno = new MetodosPersona().datosPersona(empleado);
			String fechaAux;
			if (empleado.getFechaEgreso() != null) {
				fechaAux = Utilidades.Fecha.formatearFecha(empleado.getFechaIngreso());
			} else {
				fechaAux = " - ";
			}
			retorno = retorno + "Fecha de ingreso: " + Utilidades.Fecha.formatearFecha(empleado.getFechaIngreso()) + "\n" +
					  "Fecha de egreso: " + fechaAux + "\n" + 
					  "Legajo: " + empleado.getLegajo() + "\n" +
					  "Salario: " + empleado.getSalario();
			
			return retorno;
		} else {
			return "Dato no encontrado";
		}
	}
	
	public void imprimirDatosEmpleados(ArrayList<Empleado> listaEmpleados) {
		if (listaEmpleados != null) {
			System.out.println("Listado de empleados:");
			for (Empleado elemento : listaEmpleados) {
				System.out.println(this.datosEmpleado(elemento));
				System.out.println("----------------------------");
			}
		}
	}
}
