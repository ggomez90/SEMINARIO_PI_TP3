package Servicios;
import Entidades.Cliente;
import Entidades.CuentaCorriente;

public class MetodosCuentaCorriente {
	
	public static void mostrarCuentaCorriente(Cliente cliente) {
		System.out.println("Cuenta Corriente: " + cliente.getApellidos() + " " + cliente.getNombres());
	    System.out.println("--------------------------------------------------------------------------------------------------");
	    cliente.getCtaCte().setSaldoFinal(MetodosMovimiento.listarMovimientos(cliente.getCtaCte().getMovimientos()));
	}	
}
