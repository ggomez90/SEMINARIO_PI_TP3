package Servicios;

import java.util.Scanner;

import Entidades.Empleado;
import Entidades.Usuario;
import Excepciones.RequisitosUsuarios;
import Utilidades.MensajesConsola;
import Utilidades.MetodosGenerales;

public class MetodosUsuario {
	
	public Usuario altaUsuario() {
		Empleado empleado;
		int opcion = MensajesConsola.opcionSINO("El empleado existe en la base de datos? Desea buscarlo?");
		if (opcion == 1) {
			int dni = MetodosGenerales.datoObligatorioEntero("Ingrese el DNI del empleado: ");
			empleado = new MetodosPersona().buscarPersona(dni, Empleado.listaEmpleados);
		}
		
		String user, clave, claveRep;
		do {
			user = MetodosGenerales.datoObligatorioString("Ingrese nombre de usuario (Al menos 6 caracteres): ");
			user = this.ControlUserClave(user, user, 1);
		}while (user.equals(null));
		do {
			clave = MetodosGenerales.datoObligatorioString("Ingrese clave del usuario (Al menos 8 caracteres): ");
			claveRep = MetodosGenerales.datoObligatorioString("Ingrese nuevamente la clave: ");
			clave = this.ControlUserClave(clave, claveRep, 2);
		} while (clave.equals(null));
		
	}

	public String ControlUserClave (String dato1, String dato2, int codigo) {
		if (codigo == 1) {
			try {
				RequisitosUsuarios.validarUser(dato1);
				return dato1;
			} catch (RequisitosUsuarios error) {
				error.retornarError();
				error.getMessage();
			}
		} else if ( codigo == 2) {
			try {
				RequisitosUsuarios.validarClave(dato1, dato2);
				return dato1;
			} catch (RequisitosUsuarios error) {
				error.retornarError();
				error.getMessage();
			}
		}
		return null;
	}
	
	
	
	

}


