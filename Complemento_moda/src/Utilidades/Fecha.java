package Utilidades;

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
		System.out.println(mensaje);
		do {
			int dia = MetodosGenerales.castearEntero("Ingrese el dia: ");
			int mes = MetodosGenerales.castearEntero("Ingrese el mes: ");
			int anio = MetodosGenerales.castearEntero("Ingrese el año: ");
			try {
				LocalDate fecha = LocalDate.of(anio, mes, dia);
				return fecha;
			} catch (DateTimeParseException | IllegalArgumentException error) {
				System.out.println("Fecha no válida. Intente nuevamente.");
			}
		}while(!exito);
		return null;
	}
	
}
