package Servicios;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.EmpleadoDAO;
import DAO.UsuarioDAO;
import Entidades.Empleado;
import Entidades.Persona;
import Entidades.Usuario;
import Enumerados.Provincia;
import Enumerados.Sexo;
import Excepciones.RequisitosUsuarios;
import Utilidades.MensajesConsola;
import Utilidades.MetodosGenerales;

public class MetodosUsuario {
	
	public Usuario altaUsuario() {
		Empleado empleado = null;
		boolean exito = false;
		int exitoAux = 0;
		int opcion = MensajesConsola.opcionSINO("El empleado existe en la base de datos? Desea buscarlo?");
		do {
			if (opcion == 1) {
				int dni = MetodosGenerales.datoObligatorioEntero("Ingrese el DNI/legajo del empleado: ");
				empleado = EmpleadoDAO.buscarEmpleado(dni);
				if (empleado != null) {
					exito = true;
					exitoAux = 1;
				} else {
					MensajesConsola.datoNoEncontrado();
					opcion = MensajesConsola.opcionSINO("Desea intentar de nuevo?: ");
					if (opcion == 1) {
						exito = false;
					} else {
						exito = true;
					}
				}
			} else {
				exito = true;
			}
		}while (!exito);
		if (exitoAux == 1) {
			if(empleado != null) {
				String user, clave, claveRep;
				do {
					user = MetodosGenerales.datoObligatorioString("Ingrese nombre de usuario (Al menos 6 caracteres): ");
					user = this.ControlUserClave(user, user, 1);
				}while (user.equals(""));
				do {
					clave = MetodosGenerales.datoObligatorioString("Ingrese clave del usuario (Al menos 8 caracteres): ");
					claveRep = MetodosGenerales.datoObligatorioString("Ingrese nuevamente la clave: ");
					clave = this.ControlUserClave(clave, claveRep, 2);
				} while (clave.equals(""));
				return new Usuario (user, clave, empleado);
			}
		} else {
			opcion = MensajesConsola.opcionSINO("Desea crear un nuevo empleado?");
			if (opcion == 1) {
				empleado = new MetodosEmpleado().altaEmpleado();
				EmpleadoDAO.guardarEmpleadoBD(empleado);
				String user, clave, claveRep;
				do {
					user = MetodosGenerales.datoObligatorioString("Ingrese nombre de usuario (Al menos 6 caracteres): ");
					user = this.ControlUserClave(user, user, 1);
				}while (user.equals(""));
				do {
					clave = MetodosGenerales.datoObligatorioString("Ingrese clave del usuario (Al menos 8 caracteres): ");
					claveRep = MetodosGenerales.datoObligatorioString("Ingrese nuevamente la clave: ");
					clave = this.ControlUserClave(clave, claveRep, 2);
				} while (clave.equals(""));
				return new Usuario (user, clave, empleado);
			}
		}
		return null;
	}

	public String ControlUserClave (String dato1, String dato2, int codigo) {
		if (codigo == 1) {
			try {
				RequisitosUsuarios.validarUser(dato1);
				return dato1;
			} catch (RequisitosUsuarios error) {
				error.retornarError();
			}
		} else if ( codigo == 2) {
			try {
				RequisitosUsuarios.validarClave(dato1, dato2);
				return dato1;
			} catch (RequisitosUsuarios error) {
				error.retornarError();
			}
		}
		return "";
	}
	
	public void agregarUsuarioEnLista(Usuario nuevo) {
		if (nuevo != null) {
			Usuario.listaUsuarios.add(nuevo);
			MensajesConsola.cambiosGuardados();
		}
	}
	
	public String datosUsuario(Usuario usuario) {
		String retorno = null;
		if (usuario != null) {
			retorno = "Usuario: " + usuario.getUsuario() + "\n" +
					 "Clave: " + usuario.getClave() + "\n" + 
					 "Permisos ADMIN: " + MetodosGenerales.verificarBoolean(usuario.isAdmin()) + "\n" +
					 "Propietario: " + usuario.getPropietario().getApellidos() + " " + usuario.getPropietario().getNombres() + "\n";
		}
		return retorno;
	}
	
	public void imprimirDatosUsuarios(ArrayList<Usuario> listaUsuarios) {
		if (listaUsuarios != null) {
			System.out.println("Listado de Usuarios:");
			for (Usuario elemento : listaUsuarios) {
				System.out.println(this.datosUsuario(elemento));
				System.out.println("----------------------------");
			}
		}
	}
	
	private Usuario login(ArrayList<Usuario> listaUsuarios, String nombreUsuario, String clave) {
	    for (Usuario usuario : listaUsuarios) {
	        if (usuario.getUsuario().equals(nombreUsuario) && usuario.getClave().equals(clave)) {
	            System.out.println("Bienvenido: " + usuario.getPropietario().getApellidos() + " " + usuario.getPropietario().getNombres());
	            return usuario;
	        }
	    }
	    return null;
	}

	public boolean iniciarSesion() {
		System.out.println("Ingrese sus credenciales.");
		Scanner entrada = new Scanner (System.in);
		boolean exito = false;
		int intento = 1;
		do {
			System.out.print("USUARIO: ");
			String user = entrada.nextLine();
			System.out.print("CLAVE: ");
			String clave = entrada.nextLine();
			UsuarioDAO.verificarUsuario(user, clave);
			//Usuario.usuarioLogueado = this.login(Usuario.listaUsuarios, user, clave);
			if (Usuario.usuarioLogueado != null) {
				if (Usuario.usuarioLogueado.isAdmin()) {
					Usuario.admin = true;
				}
				return true;
			} 
//			else if (this.iniciarSesionTester(user, clave)) {
//				Usuario.admin = true;
//				return true;
			else {
				System.out.print("Usuario o contraseña incorrectos: ");
				if (intento == 5) {
					System.out.println("Intentos agotados.");
					return false;
				} else {
					intento++;
					System.out.println("Intento " + intento + ".");
				}
			}
		}while (!exito);
		return exito;
	}
	
//	public boolean iniciarSesionTester(String user, String clave) {
//		if (user.equals("admin") && clave.equals("admin")) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	public Usuario buscarUsuario (ArrayList<Usuario> listaUsuarios) {
		Scanner entrada = new Scanner (System.in);
		System.out.print("Ingrese usuario a buscar: ");
		String user = entrada.nextLine();
		for (Usuario elemento: listaUsuarios) {
			if (elemento.getUsuario().equals(user)) {
				return elemento;
			}
		}
		MensajesConsola.datoNoEncontrado();
		return null;
	}
	
	public void bajaUsuario() {
		boolean exito = false;
		do {
			Usuario eliminado = this.buscarUsuario(Usuario.listaUsuarios);
			if (eliminado != null) {
				if (eliminado == Usuario.usuarioLogueado) {
					System.out.println("No puede eliminar el usuario con el que está logueado.");
				} else {
					int opcion = MensajesConsola.opcionSINO("Confirma eliminar el usuario " + eliminado.getUsuario() + "?: ");
					if (opcion == 1) {
						Usuario.listaUsuarios.remove(Usuario.listaUsuarios.indexOf(eliminado));
						MensajesConsola.cambiosGuardados();
						exito = true;
					}
				}
			} else {
				int opcion = MensajesConsola.opcionSINO("Desea buscar nuevamente?: ");
				if (opcion == 0) {
					exito = true;
				}
			}
		}while(!exito);
	}
	
	public Usuario modificarUsuario(Usuario usuario) {
		if (usuario != null) {
			Scanner entrada = new Scanner(System.in);
			boolean flag = false;
			do {
				MensajesConsola.camposModificablesUsuario();
				int opcion = MetodosGenerales.datoObligatorioEntero("Ingrese una opcion: ");
				boolean flagAux = MetodosGenerales.verificarRango(opcion, 1, 4);
				if (flagAux) {
					return this.opcionesModificablesUsuario(usuario, opcion);
				} else {
					MensajesConsola.verificarDato();
				}
				int salir = MensajesConsola.opcionSINO("Desea salir?: ");
				if (salir == 1) {
					flag = true;
				} else {
					flag = false;
				}
			}while (!flag);
		}
		return null;
	}
	
	public Usuario opcionesModificablesUsuario(Usuario usuario, int opcion) {
		Scanner entrada = new Scanner (System.in);
		String aux, claveRep;
		switch (opcion) {
			case 1: do {
						aux = MetodosGenerales.datoObligatorioString("Ingrese nuevo nombre de usuario (Al menos 6 caracteres): ");
						aux = this.ControlUserClave(aux, aux, 1);
					}while (aux.equals(null));
					usuario.setUsuario(aux);
					break;
			case 2: do {
						aux = MetodosGenerales.datoObligatorioString("Ingrese nueva clave del usuario (Al menos 8 caracteres): ");
						claveRep = MetodosGenerales.datoObligatorioString("Ingrese nuevamente la clave: ");
						aux = this.ControlUserClave(aux, claveRep, 2);
					} while (aux.equals(null));
					usuario.setClave(aux);
					break;
			case 3: System.out.println("El usuario posee permisos admin: " + MetodosGenerales.verificarBoolean(usuario.isAdmin()));
					int permiso = MensajesConsola.opcionSINO("\"Desea cambiar los permisos del usuario?\"");
					if (permiso == 1) {
						if (usuario.isAdmin()) {
							usuario.setAdmin(false);
						} else {
							usuario.setAdmin(true);
						}
					}
					break;
			case 4: int buscar = MensajesConsola.opcionSINO("El empleado existe en la base de datos? Desea buscarlo?");
						boolean exito = false;
						do {
							if (buscar == 1) {
								int dni = MetodosGenerales.datoObligatorioEntero("Ingrese el DNI/legajo del empleado: ");
								Empleado propietario = EmpleadoDAO.buscarEmpleado(dni);
								if (propietario != null) {
									usuario.setPropietario(propietario);
									exito = true;
								} else {
									MensajesConsola.datoNoEncontrado();
									buscar = MensajesConsola.opcionSINO("Desea buscar de nuevo?: ");
									if (buscar == 1) {
										exito = false;
									} else {
										exito = true;
									}
								}
							} else {
								System.out.println("No puede ejecutar esta acción si el empleado es inexistente");
								exito = true;
							}
						}while (!exito);
						break;
		}
		return usuario;
	}
}


