package Entidades;

import java.time.LocalDate;

import Enumerados.Provincia;
import Enumerados.Sexo;

public class Usuario {
	
	private String usuario;
	private String clave;
	private Persona propietario;
	
	public Usuario (String usuario, String clave, Persona propietario) {
		this.usuario = usuario;
		this.clave = clave;
		this.propietario = propietario;
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

	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}
	
}
