package Enumerados;

public enum Categoria {
	INDUMENTARIA (1, "Indumentaria"),
	CALZADO (2, "Calzado"),
	ACCESORIO (3, "Accesorio");
	
	private int codigo;
	private String nombre;
	
	private Categoria (int codigo, String nombre) {
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
