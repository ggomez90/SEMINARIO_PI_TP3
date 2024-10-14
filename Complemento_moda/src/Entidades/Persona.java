package Entidades;

import java.time.LocalDate;
import Enumerados.Provincia;
import Enumerados.Sexo;

public abstract class Persona {
	
	private int dni;
	private boolean activo;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String telefono;
	private Provincia provincia;
	private String localidad;
	private LocalDate fechaNacimiento;
	private Sexo sexo;
	
	public Persona (int dni, String nombres, String apellidos) {
		this.dni = dni;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.activo = true;
	}
	
	public Persona (int dni, String nombres, String apellidos, String direccion, String telefono, Provincia provincia, String localidad, LocalDate fechaNacimiento, Sexo sexo) {
		this.dni = dni;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.activo = true;
		this.direccion = direccion;
		this.telefono = telefono;
		this.provincia = provincia;
		this.localidad = localidad;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

}
