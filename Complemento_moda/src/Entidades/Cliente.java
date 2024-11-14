package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;

import DAO.ClienteDAO;
import Enumerados.Provincia;
import Enumerados.Sexo;

public class Cliente extends Persona{
	
	public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private static int ultimoCodigoCliente = ClienteDAO.obtenerUltimoCliente();
	
	private CuentaCorriente ctaCte;
	
	//Constructor para crear Clientes desde App
	public Cliente (int dni, String nombres, String apellido) {
		super(dni, nombres, apellido);
		super.setId(++ultimoCodigoCliente);
		this.ctaCte = new CuentaCorriente();
	}

	//Constructor para crear Clientes desde consulta SQL
	public Cliente (int id, int dni, boolean activo, String nombres, String apellidos, String direccion, String telefono, Provincia provincia, String localidad, LocalDate fechaNacimiento, Sexo sexo, int idCtaCte) {
		super(id, dni, activo, nombres, apellidos, direccion, telefono, provincia,localidad, fechaNacimiento, sexo);
		this.ctaCte = new CuentaCorriente(idCtaCte);
	}
	
	public static int getUltimoCodigoCliente() {
		return ultimoCodigoCliente;
	}

	public static void setUltimoCodigoCliente(int ultimoCodigoCliente) {
		Cliente.ultimoCodigoCliente = ultimoCodigoCliente;
	}

	public CuentaCorriente getCtaCte() {
		return ctaCte;
	}

	public void setCtaCte(CuentaCorriente ctaCte) {
		this.ctaCte = ctaCte;
	}

}
