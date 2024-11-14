package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Entidades.Cliente;
import Enumerados.Provincia;
import Enumerados.Sexo;

public class ClienteDAO {
	
	//Metodo para guardar clientes en la BD
	public static void guardarListaClientesBD(ArrayList<Cliente> listaClientes) {
		if (listaClientes != null) {
			for (Cliente elemento : listaClientes) {
				guardarClienteBD(elemento);
			}
		}	
	}
	
	//Metodo para guardar un cliente en la BD
	public static void guardarClienteBD(Cliente cliente) {
		if (cliente != null) {
		    Connection conexion = null;
		    PreparedStatement pstmt = null;
			String consultaSQL = "INSERT INTO cliente (idCliente, dniCliente, activoCliente, nombresCliente, apellidosCliente, telefonoCliente, direccionCliente, provinciaCliente, localidadCliente, sexoCliente, fechaNacimientoCliente, idCtaCteCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				conexion = Conexion.conectarBD();
				pstmt = conexion.prepareStatement(consultaSQL);
					
				CuentaCorrienteDAO.guardarCtaCteClienteBD(cliente);
				pstmt.setInt(1, cliente.getId());
	            pstmt.setInt(2, cliente.getDni());
	            pstmt.setBoolean(3, cliente.isActivo());
	            pstmt.setString(4, cliente.getNombres());
	            pstmt.setString(5, cliente.getApellidos());
	            pstmt.setString(6, cliente.getTelefono());
	            pstmt.setString(7, cliente.getDireccion());
		        if (cliente.getProvincia() != null) {
		        	pstmt.setString(8, cliente.getProvincia().getNombre());
			        } else {
			          	pstmt.setNull(8, java.sql.Types.VARCHAR);
		            }
		            pstmt.setString(9, cliente.getLocalidad());
		            if (cliente.getSexo() != null) {
		            	pstmt.setString(10, cliente.getSexo().getNombre());
			        } else {
			          	pstmt.setNull(10, java.sql.Types.VARCHAR);
			        }          
			        if (cliente.getFechaNacimiento() != null) {
			            pstmt.setDate(11, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
			        } else {
		                pstmt.setNull(11, java.sql.Types.DATE);
		            }
			        pstmt.setInt(12, cliente.getCtaCte().getId());
		            pstmt.executeUpdate();

			} catch (SQLException error) {
			     System.out.println("Error al guardar clientes en la base de datos: " + error.getMessage());
		
			} finally {
			    Conexion.cerrarPreparedStatement(pstmt);
			    Conexion.cerrarConexionBD(conexion);
		    }
		}
	}

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
				    String datoProvincia = resultado.getString("provinciaCliente");
				    Provincia provincia = Provincia.convertirProvincia(datoProvincia);
				    String localidad = resultado.getString("localidadCliente");
				    String datoSexo = resultado.getString("sexoCliente");
				    Sexo sexo = Sexo.convertirSexo(datoSexo);
				    LocalDate fechaNacimiento = null;
				    Date auxFecha = resultado.getDate("fechaNacimientoCliente");
				    if (auxFecha != null) {
				    	fechaNacimiento = auxFecha.toLocalDate();
				    }
				    int idCtaCte = resultado.getInt("idCtaCteCliente");

				    Cliente nuevoCliente = new Cliente(id, dni, activo, nombres, apellidos, direccion, telefono, provincia, localidad, fechaNacimiento, sexo, idCtaCte);
				    listaClientes.add(nuevoCliente);
				}
			} catch (SQLException error) {
				System.out.println("Error al cargar datos: " + error.getMessage());
			}
        }
        return listaClientes;
    }
    
 // Método para buscar un cliente por su DNI
    public static Cliente buscarClientePorDNI(int dniCliente) {
        Cliente cliente = null;
        String consultaSQL = "SELECT * FROM cliente WHERE dniCliente = ?";
        ResultSet resultado = null;

        try {
            Connection conexion = Conexion.conectarBD();
            PreparedStatement pstmt = conexion.prepareStatement(consultaSQL);
            pstmt.setInt(1, dniCliente);  // Asigna el DNI al parámetro de la consulta
            resultado = pstmt.executeQuery();
            
            if (resultado.next()) {  // Si existe un resultado
                int id = resultado.getInt("idCliente");
                boolean activo = resultado.getBoolean("activoCliente");
                String nombres = resultado.getString("nombresCliente");
                String apellidos = resultado.getString("apellidosCliente");
                String telefono = resultado.getString("telefonoCliente");
                String direccion = resultado.getString("direccionCliente");
                String datoProvincia = resultado.getString("provinciaCliente");
                Provincia provincia = Provincia.convertirProvincia(datoProvincia);
                String localidad = resultado.getString("localidadCliente");
                String datoSexo = resultado.getString("sexoCliente");
                Sexo sexo = Sexo.convertirSexo(datoSexo);
                LocalDate fechaNacimiento = null;
                Date auxFecha = resultado.getDate("fechaNacimientoCliente");
                if (auxFecha != null) {
                    fechaNacimiento = auxFecha.toLocalDate();
                }
                int idCtaCte = resultado.getInt("idCtaCteCliente");

                cliente = new Cliente(id, dniCliente, activo, nombres, apellidos, direccion, telefono, provincia, localidad, fechaNacimiento, sexo, idCtaCte);
            }
        } catch (SQLException error) {
            System.out.println("Error en conexión o en la consulta: " + error.getMessage());
        } finally {
            try {
                if (resultado != null) resultado.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar ResultSet: " + e.getMessage());
            }
        }
        return cliente;
    }

    public static int obtenerUltimoCliente() {
        String consultaSQL = "SELECT idCliente FROM cliente ORDER BY idCliente DESC LIMIT 1";
        ResultSet resultado = null;
        int valor = 0;
        try {
        	Connection conexion = Conexion.conectarBD();
        	Statement stmt = conexion.createStatement();
            resultado = stmt.executeQuery(consultaSQL);
            
            if (resultado.next()) {
    			valor = resultado.getInt("idCliente");
            }
        } catch (SQLException error) {
        	System.out.println("Error: " + error.getMessage());
        }
        return valor;
    }
    
 // Método para actualizar un cliente en la base de datos
    public static void actualizarCliente(Cliente cliente) {
    	if (cliente != null) {
	        String consultaSQL = "UPDATE cliente SET "
	                + "dniCliente = ?, "
	                + "activoCliente = ?, "
	                + "nombresCliente = ?, "
	                + "apellidosCliente = ?, "
	                + "telefonoCliente = ?, "
	                + "direccionCliente = ?, "
	                + "provinciaCliente = ?, "
	                + "localidadCliente = ?, "
	                + "sexoCliente = ?, "
	                + "fechaNacimientoCliente = ? "
	                + "WHERE idCliente = ?";
	
	        Connection conexion = null;
	        PreparedStatement pstmt = null;
	
	        try {
	            conexion = Conexion.conectarBD();
	            pstmt = conexion.prepareStatement(consultaSQL);
	            pstmt.setInt(1, cliente.getDni());
	            pstmt.setBoolean(2, cliente.isActivo());
	            pstmt.setString(3, cliente.getNombres());
	            pstmt.setString(4, cliente.getApellidos());
	            pstmt.setString(5, cliente.getTelefono());
	            pstmt.setString(6, cliente.getDireccion());
	            
	            if (cliente.getProvincia() != null) {
	                pstmt.setString(7, cliente.getProvincia().getNombre());
	            } else {
	                pstmt.setNull(7, java.sql.Types.VARCHAR);
	            }
	            
	            pstmt.setString(8, cliente.getLocalidad());
	            
	            if (cliente.getSexo() != null) {
	                pstmt.setString(9, cliente.getSexo().getNombre());
	            } else {
	                pstmt.setNull(9, java.sql.Types.VARCHAR);
	            }
	            
	            if (cliente.getFechaNacimiento() != null) {
	                pstmt.setDate(10, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
	            } else {
	                pstmt.setNull(10, java.sql.Types.DATE);
	            }
	            
	            pstmt.setInt(11, cliente.getId());  // Identificador del cliente para la cláusula WHERE
	
	            int filasActualizadas = pstmt.executeUpdate();
	            
	            if (filasActualizadas > 0) {
	                System.out.println("Cliente actualizado correctamente.");
	            } else {
	                System.out.println("No se encontró el cliente con el ID especificado.");
	            }
	
	        } catch (SQLException error) {
	            System.out.println("Error al actualizar cliente: " + error.getMessage());
	        } finally {
	            try {
	                if (pstmt != null) pstmt.close();
	                if (conexion != null) conexion.close();
	            } catch (SQLException e) {
	                System.out.println("Error al cerrar recursos: " + e.getMessage());
	            }
	        }
    	}   
    }


}