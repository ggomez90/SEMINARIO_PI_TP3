package Servicios;
import Entidades.Cliente;
import Entidades.CuentaCorriente;

public class MetodosCuentaCorriente {

	public void mostrarCuentaCorriente(Cliente cliente) {
		System.out.println("Listado de Movimientos de la Cuenta Corriente de: " + cliente.getApellidos() + " " + cliente.getNombres());
	    System.out.println("--------------------------------------------------------------------------------------------------");
	    cliente.getCtaCte().setSaldoFinal(MetodosMovimiento.listarMovimientos(cliente.getCtaCte().getMovimientos()));
	}	
}
