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
import Entidades.Empleado;
import Entidades.Usuario;
import Enumerados.Provincia;
import Enumerados.Sexo;

public class UsuarioDAO {

	//Metodo para guardar un usuario en la BD
	public static void guardarUsuarioBD(Usuario usuario) {
		if (usuario != null) {
		    Connection conexion = null;
		    PreparedStatement pstmt = null;
			String consultaSQL = "INSERT INTO usuario (idUsuario, nombreUsuario, claveUsuario, idEmpleadoUsuario, isAdmin) VALUES (?, ?, ?, ?, ?)";
			try {
				conexion = Conexion.conectarBD();
				pstmt = conexion.prepareStatement(consultaSQL);
					
				pstmt.setInt(1, usuario.getId());
				pstmt.setString(2, usuario.getUsuario());
				pstmt.setString(3, usuario.getClave());
				pstmt.setInt(4, usuario.getPropietario().getId());
				pstmt.setBoolean(5, usuario.isAdmin());
				
		        pstmt.executeUpdate();

			} catch (SQLException error) {
			     System.out.println("Error al guardar el usuario en la base de datos: " + error.getMessage());
		
			} finally {
			    Conexion.cerrarPreparedStatement(pstmt);
			    Conexion.cerrarConexionBD(conexion);
		    }
		}
	}	
	
    public static int obtenerUltimoUsuario() {
        String consultaSQL = "SELECT idUsuario FROM usuario ORDER BY idUsuario DESC LIMIT 1";
        ResultSet resultado = null;
        int valor = 0;
        try {
        	Connection conexion = Conexion.conectarBD();
        	Statement stmt = conexion.createStatement();
            resultado = stmt.executeQuery(consultaSQL);
            
            if (resultado.next()) {
    			valor = resultado.getInt("idUsuario");
            }
        } catch (SQLException error) {
        	System.out.println("Error: " + error.getMessage());
        }
        return valor;
    }  

    public static void verificarUsuario(String usuario, String clave) {
        String consultaSQL = "SELECT * FROM usuario WHERE LOWER(nombreUsuario) = LOWER(?) AND LOWER(claveUsuario) = LOWER(?)";
        
        try (Connection conexion = Conexion.conectarBD();
             PreparedStatement pstmt = conexion.prepareStatement(consultaSQL)) {
             
            pstmt.setString(1, usuario);
            pstmt.setString(2, clave);
            
            ResultSet resultado = pstmt.executeQuery();
            
            // Si el resultado tiene una coincidencia, creamos un nuevo Usuario
            if (resultado.next()) {
                int idUsuario = resultado.getInt("idUsuario");
                String nombreUsuario = resultado.getString("nombreUsuario");
                String claveUsuario = resultado.getString("claveUsuario");
                int idEmpleado = resultado.getInt("idEmpleadoUsuario");
                boolean isAdmin = resultado.getBoolean("isAdmin");
                
                Empleado propietario = EmpleadoDAO.buscarEmpleado(idEmpleado);

                // Alta de usuario con el user logueado
                Usuario.usuarioLogueado = new Usuario(idUsuario, nombreUsuario, claveUsuario, propietario, isAdmin);
            }
        } catch (SQLException error) {
            System.out.println("Error al verificar usuario: " + error.getMessage());
        }
    }
    
    public static void eliminarUsuario(Usuario usuario) {
    	if (usuario.getId() != Usuario.usuarioLogueado.getId()) {
            String consultaSQL = "DELETE FROM usuario WHERE idUsuario = ?";
            
            try (Connection conexion = Conexion.conectarBD();
                PreparedStatement pstmt = conexion.prepareStatement(consultaSQL)) {
                
                pstmt.setInt(1, usuario.getId());
                pstmt.executeUpdate();
                System.out.println("Usuario eliminado exitosamente.");
                        
            } catch (SQLException error) {
                System.out.println("Error al eliminar usuario: " + error.getMessage());
            }
    	} else {
    		System.out.println("No puede eliminar el usuario con el que está actualmente logueado.");
    	}
    }

    public static Usuario buscarUsuario(Empleado empleado) {
    	Usuario usuario = null;
    	if (poseeUsuario(empleado)) {
            String consultaSQL = "SELECT * FROM usuario WHERE idEmpleadoUsuario = ?";

            try (Connection conexion = Conexion.conectarBD();
                 PreparedStatement pstmt = conexion.prepareStatement(consultaSQL)) {

                pstmt.setInt(1, empleado.getId());

                try (ResultSet resultado = pstmt.executeQuery()) {
                    if (resultado.next()) {
                        int idUsuario = resultado.getInt("idUsuario");
                        String nombreUsuario = resultado.getString("nombreUsuario");
                        String claveUsuario = resultado.getString("claveUsuario");
                        boolean isAdmin = resultado.getBoolean("isAdmin");

                        usuario = new Usuario(idUsuario, nombreUsuario, claveUsuario, empleado, isAdmin);
                    }
                }
            } catch (SQLException error) {
                System.out.println("Error al buscar usuario: " + error.getMessage());
            }
    	}
        return usuario;
    }

    public static void actualizarUsuario(Usuario usuario) {
    	if (usuario != null) {
            String consultaSQL = "UPDATE usuario SET nombreUsuario = ?, claveUsuario = ?, idEmpleadoUsuario = ?, isAdmin = ? WHERE idUsuario = ?";

            try (Connection conexion = Conexion.conectarBD();
                 PreparedStatement pstmt = conexion.prepareStatement(consultaSQL)) {

                pstmt.setString(1, usuario.getUsuario());
                pstmt.setString(2, usuario.getClave());
                pstmt.setInt(3, usuario.getPropietario().getId());
                pstmt.setBoolean(4, usuario.isAdmin());
                pstmt.setInt(5, usuario.getId());

                int filasActualizadas = pstmt.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Usuario actualizado exitosamente.");
                } else {
                    System.out.println("No se encontró el usuario con ID: " + usuario.getId());
                }
            } catch (SQLException error) {
                System.out.println("Error al actualizar usuario: " + error.getMessage());
            }
    	}
    }
    
	// Método para listar todos los Usuarios
    public static ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        String consultaSQL = "SELECT * FROM usuario";
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
					int idUsuario = resultado.getInt("idUsuario");
	                String nombreUsuario = resultado.getString("nombreUsuario");
	                String claveUsuario = resultado.getString("claveUsuario");
	                int idEmpleado = resultado.getInt("idEmpleadoUsuario");
	                boolean isAdmin = resultado.getBoolean("isAdmin");
	                
	                Empleado propietario = EmpleadoDAO.buscarEmpleado(idEmpleado);

	                Usuario nuevoUsuario = new Usuario(idUsuario, nombreUsuario, claveUsuario, propietario, isAdmin);
				    listaUsuarios.add(nuevoUsuario);
				}
			} catch (SQLException error) {
				System.out.println("Error al cargar datos: " + error.getMessage());
			}
        }
        return listaUsuarios;
    }
    
    public static boolean poseeUsuario(Empleado empleado) {
    	boolean poseeUsuario = false;

    	if (empleado != null) {
            String consultaSQL = "SELECT COUNT(*) AS cantidad FROM usuario WHERE idEmpleadoUsuario = ?";
            try (Connection conexion = Conexion.conectarBD();
                 PreparedStatement pstmt = conexion.prepareStatement(consultaSQL)) {

                pstmt.setInt(1, empleado.getId());
                ResultSet resultado = pstmt.executeQuery();

                if (resultado.next()) {
                    int cantidad = resultado.getInt("cantidad");
                    poseeUsuario = cantidad > 0;  // Si hay al menos un registro, entonces tiene usuario
                }
            } catch (SQLException error) {
                System.out.println("El empleado no posee usuario registrado");
            }
    	}
        return poseeUsuario;
    }


}
