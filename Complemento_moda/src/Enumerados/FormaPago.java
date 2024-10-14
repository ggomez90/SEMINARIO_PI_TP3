package Enumerados;

public enum FormaPago {
	EFECTIVO (1, "Efectivo"),
	TARJETA_DEBITO (2, "Tarjeta debito"),
	TARJETA_CREDITO (3, "Tarjeta credito"),
	TRANSFERENCIA (4, "Transferencia bancaria"),
	CUENTA_CORRIENTE (5, "Cuenta Corriente");
	
	private int codigo;
	private String nombre;
	
	private FormaPago (int codigo, String nombre){
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public int getCodigo () {
		return this.codigo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
