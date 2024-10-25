package Utilidades;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Utilidades.MetodosGenerales;

public class Fecha {

	public static String formatearFecha(LocalDate fecha) {
		if (fecha != null) {
			DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return fecha.format(fechaFormateada);
		}
		return null;
	}
	
	public static LocalDate crearFecha (String mensaje) {
		boolean exito = false;
		LocalDate fecha = null;
		System.out.println(mensaje);
		do {
			int dia = MetodosGenerales.castearEntero("Ingrese el dia: ");
			int mes = MetodosGenerales.castearEntero("Ingrese el mes: ");
			int anio = MetodosGenerales.castearEntero("Ingrese el año: ");
			try {
				fecha = LocalDate.of(anio, mes, dia);
				exito = true;
			} catch (DateTimeParseException error) {
				System.out.println("Fecha no válida. Intente nuevamente.");
			} catch (DateTimeException error) {
				System.out.println("Fecha no válida. Intente nuevamente.");
			} catch(IllegalArgumentException error) {
				System.out.println("Fecha no válida. Intente nuevamente.");
			}
			if (anio > LocalDate.now().getYear()) {
				System.out.println("Año no válido. Intente nuevamente.");
				exito = false;
			}
		}while(!exito);
		return fecha;
	}
	
}
