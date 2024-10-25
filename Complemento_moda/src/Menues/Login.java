package Menues;

import Entidades.Usuario;
import Servicios.MetodosUsuario;

public class Login {
		
	public static int iniciarSistema() {
		System.out.print("BIENVENIDO: ");
		if (new MetodosUsuario().iniciarSesion()) {
			if (Usuario.admin) {
				return 1;
			} else if (Usuario.usuarioLogueado != null) {
				if (Usuario.usuarioLogueado.isAdmin()) {
					return 2;
				} else {
					return 3;
				}
			}
		}
		return 0;	
	}
	
}
