package Enumerados;

public enum Provincia {
	BUENOS_AIRES (1, "Buenos Aires"),
	CATAMARCA (2, "Catamarca"),
	CHACO (3, "Chaco"),
	CHUBUT (4, "Chubut"),
	CORDOBA (5, "Cordoba"),
	CORRIENTES (6, "Corrientes"),
	ENTRE_RIOS (7, "Entre Rios"),
	FORMOSA (8, "Formosa"),
	JUJUY (9, "Jujuy"),
	LA_PAMPA (10, "La Pampa"),
	LA_RIOJA (11, "La Rioja"),
	MENDOZA (12, "Mendoza"),
	MISIONES (13, "Misiones"),
	NEUQUEN (14, "Neuquen"),
	RIO_NEGRO (15, "Rio Negro"),
	SALTA (16, "Salta"),
	SAN_JUAN (17, "San Juan"),
	SAN_LUIS (18, "San Luis"),
	SANTA_CRUZ (19, "Santa Cruz"),
	SANTA_FE (20, "Santa Fe"),
	SANTIAGO_DEL_ESTERO (21, "Santiago Del Estero"),
	TIERRA_DEL_FUEGO (22, "Tierra Del Fuego"),
	TUCUMAN (23, "Tucuman");
	
	private int codigo;
	private String nombre;
	
	private Provincia (int codigo, String nombre) {
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
