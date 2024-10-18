package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/ComplementoModa";
	private static final String usuario = "root";
	private static final String clave = "9029";
	
	static {
		try {
			Class.forName(CONTROLADOR);
		}catch(ClassNotFoundException error) {
			error.printStackTrace();
			System.out.println("Error en controlador: " + error.getLocalizedMessage());
		}
	}

	public static Connection conectarBD() throws SQLException {
		Connection conexion = null;
		conexion = DriverManager.getConnection(URL, usuario, clave);
		return conexion;
	}
	
}
