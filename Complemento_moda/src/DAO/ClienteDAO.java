package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Entidades.Cliente;

public class ClienteDAO {

	// Método para listar todas los clientes
    public static ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String consultaSQL = "SELECT * FROM cliente";
        ResultSet resultado = null;
        
        try {
        	Connection conexion = Conexion.conectarBD();
        	Statement stmt = conexion.createStatement();
            resultado = stmt.executeQuery(consultaSQL);
        } catch (SQLException error) {
        	System.out.println("Error en conexion: " + error.getMessage());
        }
        if (resultado != null) {
        	try {
				while (resultado.next()) {
					int id = resultado.getInt("idCliente");
				    int dni = resultado.getInt("dniCliente");
					boolean activo = resultado.getBoolean("activoCliente");
				    String nombres = resultado.getString("nombresCliente");
				    String apellidos = resultado.getString("apellidosCliente");
				    String telefono = resultado.getString("telefonoCliente");
				    String direccion = resultado.getString("direccionCliente");
				    String provincia = resultado.getString("provinciaCliente");
				    String localidad = resultado.getString("localidadCliente");
				    String sexo = resultado.getString("sexoCliente");
				    Date auxFecha = resultado.getDate("fechaNacimientoCliente");
				    LocalDate fechaNacimiento = auxFecha.toLocalDate();
				    int idCtaCte = resultado.getInt("idCtaCteCliente");

				    Cliente nuevoCliente = new Cliente(dni, nombres, apellidos);
				    listaClientes.add(nuevoCliente);
				}
			} catch (SQLException error) {
				System.out.println("Error al cargar datos: " + error.getMessage());
			}
        }
        return listaClientes;
    }
	
}
