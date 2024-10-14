package Enumerados;

public enum Sexo {
	MASCULINO(1, "Masculino"),
	FEMENINO(2, "Femenino"),
	OTRO(3, "Otro");
	
	private int codigo;
	private String nombre;
	
	private Sexo (int codigo, String nombre){
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
