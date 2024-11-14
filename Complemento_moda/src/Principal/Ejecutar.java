package Principal;

//pruebas
import java.util.ArrayList;
import Servicios.MetodosCliente;
import Servicios.MetodosCuentaCorriente;
import Servicios.MetodosEmpleado;
import Servicios.MetodosUsuario;
import DAO.ClienteDAO;
import DAO.EmpleadoDAO;
import DAO.UsuarioDAO;
import Entidades.Cliente;

//dejar
import Entidades.Usuario;
import Menues.Login;
import Menues.Opciones;
import Utilidades.MensajesConsola;

public class Ejecutar {

	public static int tipoUsuario;
	
	public static void main(String[] args) {
		
		tipoUsuario = Login.iniciarSistema();
		MensajesConsola.separador();
		MensajesConsola.saludoBienvenida(tipoUsuario);
		MensajesConsola.separador();
		if (tipoUsuario > 0) {
			while(Opciones.menuPrincipal(Usuario.admin)){
				Opciones.menuSecundario(Usuario.admin);
			}
		}
		
	}

}
