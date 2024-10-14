package Entidades;

public class Cliente extends Persona{
	
	private CuentaCorriente ctaCte;
	
	public Cliente (int dni, String nombres, String apellido) {
		super(dni, nombres, apellido);
		this.ctaCte = new CuentaCorriente();
	}

	public CuentaCorriente getCtaCte() {
		return ctaCte;
	}

}
