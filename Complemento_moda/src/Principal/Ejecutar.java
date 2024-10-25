package Principal;

import Entidades.Usuario;
import Menues.Login;
import Menues.Opciones;
import Utilidades.MensajesConsola;

public class Ejecutar {

	public static int tipoUsuario;
	
	public static void main(String[] args) {
		
		tipoUsuario = Login.iniciarSistema();
		MensajesConsola.saludoBienvenida(tipoUsuario);
		while(Opciones.menuPrincipal(Usuario.admin)){
			Opciones.menuSecundario(Usuario.admin);
		}

	}

}
