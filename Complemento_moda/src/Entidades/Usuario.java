package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;

import DAO.ClienteDAO;
import DAO.UsuarioDAO;
import Enumerados.Provincia;
import Enumerados.Sexo;
import Utilidades.MensajesConsola;
import Utilidades.MetodosGenerales;

public class Usuario {
	
	public static ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	public static Usuario usuarioLogueado;
	public static boolean admin = false;
	private static int ultimoCodigoUsuario = UsuarioDAO.obtenerUltimoUsuario();
	
	private int id;
	private String usuario;
	private String clave;
	private Empleado propietario;
	private boolean isAdmin;

	//Constructor para alta de objetos desde app
	public Usuario (String usuario, String clave, Empleado propietario) {
		this.id = ++ultimoCodigoUsuario;
		this.usuario = usuario;
		this.clave = clave;
		this.propietario = propietario;
		this.isAdmin = MetodosGenerales.devolverBoolean(MensajesConsola.opcionSINO("El usuario será administrador?"));
	}
	
	//Constructor para alta de objetos desde consulta SQL
	public Usuario(int id, String usuario, String clave, Empleado propietario, boolean permisos) {
		this.id = id;
		this.usuario = usuario;
		this.clave = clave;
		this.propietario = propietario;
		this.isAdmin = permisos;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
