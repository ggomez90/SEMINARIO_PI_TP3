package Excepciones;

public class RequisitosUsuarios extends Exception{

	//identificador de version de clase, es unico y sirve para deserializar un objeto serializado. 
	public static final long serial = 1L;
	
	//Constructor recibe un argumento String, que será un mensaje personalizado para mostrar en el metodo getMessage()
	public RequisitosUsuarios(String mensaje) {
		super(mensaje);
	}
	
	public void retornarError() {
		System.out.println("ERROR, revise los requisitos.");
	}
	
	public static void validarUser(String user) throws RequisitosUsuarios {
		if (user.length() < 6) {
			throw new RequisitosUsuarios("El usuario debe contener al menos 6 caracteres.");
		}
	}
	
	public static void validarClave(String clave, String claveRep) throws RequisitosUsuarios {
		if (clave.length() < 8) {
			throw new RequisitosUsuarios("El clave debe contener al menos 8 caracteres.");
		}
		if (clave.equals(claveRep) == false) {
			throw new RequisitosUsuarios("Las claves ingresadas no coinciden");
		}
	}
}
