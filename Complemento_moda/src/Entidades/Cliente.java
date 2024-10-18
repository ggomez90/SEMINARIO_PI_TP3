package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;

import Enumerados.Provincia;
import Enumerados.Sexo;

public class Cliente extends Persona{
	
	public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	
	private CuentaCorriente ctaCte;
	
	//Constructor para crear Clientes desde App
	public Cliente (int dni, String nombres, String apellido) {
		super(dni, nombres, apellido);
		this.ctaCte = new CuentaCorriente();
	}

	//Constructor para crear Clientes desde consulta SQL
	//public Cliente (int id, int dni, boolean activo, String nombres, String apellidos, String direccion, String telefono, Provincia provincia, String localidad, LocalDate fechaNacimiento, Sexo sexo)
	
	public CuentaCorriente getCtaCte() {
		return ctaCte;
	}

}
