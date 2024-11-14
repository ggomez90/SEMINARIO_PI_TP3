package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;

import DAO.ClienteDAO;
import DAO.EmpleadoDAO;
import Enumerados.Provincia;
import Enumerados.Sexo;

public class Empleado extends Persona{
	
	public static ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
	private static int ultimoCodigoEmpleado = EmpleadoDAO.obtenerUltimoEmpleado();
	
	private LocalDate fechaIngreso;
	private LocalDate fechaEgreso;
	private int legajo;
	private double salario;
	
	//Constructor para empleado desde app
	public Empleado (int dni, String nombres, String apellidos, String direccion, String telefono, Provincia provincia, String localidad, LocalDate fechaNacimiento, Sexo sexo, LocalDate fechaIngreso, int legajo, double salario) {
		super(dni, nombres, apellidos, direccion, telefono, provincia, localidad, fechaNacimiento, sexo);
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = null;
		this.legajo = legajo;
		this.salario = salario;
		super.setId(++ultimoCodigoEmpleado);
	}

	//Constructor para empleado desde BD
	public Empleado (int id, int dni, boolean activo, String nombres, String apellidos, String direccion, String telefono, Provincia provincia, String localidad, LocalDate fechaNacimiento, Sexo sexo, LocalDate fechaIngreso, LocalDate fechaEgreso, int legajo, double salario) {
		super(id, dni, activo, nombres, apellidos, direccion, telefono, provincia, localidad, fechaNacimiento, sexo);
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.legajo = legajo;
		this.salario = salario;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDate getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(LocalDate fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
}
