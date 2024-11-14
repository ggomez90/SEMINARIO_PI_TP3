package Menues;

import Entidades.Usuario;
import Servicios.MetodosUsuario;
import Utilidades.MensajesConsola;

public class Login {
		
	public static int iniciarSistema() {
		System.out.print("BIENVENIDO: ");
		if (new MetodosUsuario().iniciarSesion()) {
			if (Usuario.admin) {
				return 1;
			} else {
				return 2;
			}
		}
		return 0;	
	}
	
}
