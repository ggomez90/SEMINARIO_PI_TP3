package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entidades.Cliente;

public class CuentaCorrienteDAO {
	
	//Metodo para guardar una Cta cte en la BD
	public static void guardarCtaCteClienteBD(Cliente cliente) {
		if (cliente != null) {
		   Connection conexion = null;
		   PreparedStatement pstmt = null;
	       String consultaSQL = "INSERT INTO cuentacorriente (idCuentaCorriente, idClienteProveedor) VALUES (?, ?)";
		   try {
				conexion = Conexion.conectarBD();
				pstmt = conexion.prepareStatement(consultaSQL);
			    pstmt.setInt(1, cliente.getCtaCte().getId());
	            pstmt.setInt(2, cliente.getId());

		        pstmt.executeUpdate();

			    } catch (SQLException error) {
			        System.out.println("Error al guardar la cuenta corriente en la base de datos: " + error.getMessage());
		
			    } finally {
			    	Conexion.cerrarPreparedStatement(pstmt);
			        Conexion.cerrarConexionBD(conexion);
			    }
			} else {
				System.out.println("No existen datos para guardar");
			}
		}
	
    public static int obtenerUltimaCtaCte() {
        String consultaSQL = "SELECT idCuentaCorriente FROM cuentacorriente ORDER BY idCuentaCorriente DESC LIMIT 1";
        ResultSet resultado = null;
        int valor = 0;
        try {
        	Connection conexion = Conexion.conectarBD();
        	Statement stmt = conexion.createStatement();
            resultado = stmt.executeQuery(consultaSQL);
            
            if (resultado.next()) {
    			valor = resultado.getInt("idCuentaCorriente");
            }
        } catch (SQLException error) {
        	System.out.println("Error: " + error.getMessage());
        }
        return valor;
    }

}
