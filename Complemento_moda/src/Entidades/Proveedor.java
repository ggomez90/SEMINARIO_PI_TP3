package Entidades;

public class Proveedor extends Persona{

	private CuentaCorriente ctaCte;
	
	public Proveedor (int dni, String nombres, String apellido) {
		super(dni, nombres, apellido);
		this.ctaCte = new CuentaCorriente();
	}

	public CuentaCorriente getCtaCte() {
		return ctaCte;
	}
	
}
