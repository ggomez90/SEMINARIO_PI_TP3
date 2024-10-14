package Excepciones;

public class ControlNumerico {
	
	public static boolean verificarEntero (String numero) {
		try{
			Integer.parseInt(numero);
			return true;
		} catch (NumberFormatException error) {
			System.out.println("ERROR: " + error.getMessage());
			return false;
		}
	}
	
	public static boolean verificarDecimal (String numero) {
		try{
			Double.parseDouble(numero);
			return true;
		} catch (NumberFormatException error) {
			System.out.println("ERROR: " + error.getMessage());
			return false;
		}
	}
	
}
