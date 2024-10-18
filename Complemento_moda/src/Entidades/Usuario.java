package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;

import Archivos.Usuario;
import Enumerados.Provincia;
import Enumerados.Sexo;
import Utilidades.MensajesConsola;
import Utilidades.MetodosGenerales;

public class Usuario {
	
	public static ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	private String usuario;
	private String clave;
	private Empleado propietario;
	private boolean isAdmin;

	public Usuario (String usuario, String clave, Empleado propietario) {
		this.usuario = usuario;
		this.clave = clave;
		this.propietario = propietario;
		this.isAdmin = MetodosGenerales.devolverBoolean(MensajesConsola.opcionSINO("El usuario será administrador?"));
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Persona getPropietario() {
		return propietario;
	}

	public void setPropietario(Empleado propietario) {
		this.propietario = propietario;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
