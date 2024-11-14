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

public class EmpleadoDAO {
	
	//Metodo para guardar una lista de Empleados en la BD
	public static void guardarListaEmpleadosBD(ArrayList<Empleado> listaEmpleados) {
		if (listaEmpleados != null) {
			for (Empleado elemento : listaEmpleados) {
				guardarEmpleadoBD(elemento);
			}
		}	
	}
	
	//Metodo para guardar un Empleado en la BD
	public static void guardarEmpleadoBD(Empleado empleado) {
		if (empleado != null) {
		    Connection conexion = null;
		    PreparedStatement pstmt = null;
			String consultaSQL = "INSERT INTO empleado (idEmpleado, fechaIngresoEmpleado, fechaEgresoEmpleado, legajoEmpleado, salarioEmpleado, "
					+ "nombresEmpleado, apellidosEmpleado, dniEmpleado, telefonoEmpleado, direccionEmpleado, provinciaEmpleado, localidadEmpleado, "
					+ "fechaNacEmpleado, sexoEmpleado, activoEmpleado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				conexion = Conexion.conectarBD();
				pstmt = conexion.prepareStatement(consultaSQL);
				
				pstmt.setInt(1, empleado.getId());
				if (empleado.getFechaIngreso() != null) {
		            pstmt.setDate(2, java.sql.Date.valueOf(empleado.getFechaIngreso()));
		        } else {
	                pstmt.setNull(2, java.sql.Types.DATE);
	            }
				if (empleado.getFechaEgreso() != null) {
		            pstmt.setDate(3, java.sql.Date.valueOf(empleado.getFechaEgreso()));
		        } else {
	                pstmt.setNull(3, java.sql.Types.DATE);
	            }
				pstmt.setInt(4, empleado.getLegajo());
				pstmt.setDouble(5, empleado.getSalario());
				pstmt.setString(6, empleado.getNombres());
				pstmt.setString(7, empleado.getApellidos());
				pstmt.setInt(8, empleado.getDni());
				pstmt.setString(9, empleado.getTelefono());
				pstmt.setString(10, empleado.getDireccion());
				if (empleado.getProvincia() != null) {
		        	pstmt.setString(11, empleado.getProvincia().getNombre());
			    } else {
			        pstmt.setNull(11, java.sql.Types.VARCHAR);
		        }
				pstmt.setString(12, empleado.getLocalidad());
		        if (empleado.getFechaNacimiento() != null) {
		            pstmt.setDate(13, java.sql.Date.valueOf(empleado.getFechaNacimiento()));
		        } else {
	                pstmt.setNull(13, java.sql.Types.DATE);
	            }
	            if (empleado.getSexo() != null) {
	            	pstmt.setString(14, empleado.getSexo().getNombre());
		        } else {
		          	pstmt.setNull(14, java.sql.Types.VARCHAR);
		        }
	            pstmt.setBoolean(15, empleado.isActivo());
		
		        pstmt.executeUpdate();

			} catch (SQLException error) {
			     System.out.println("Error al guardar Empleado en la base de datos: " + error.getMessage());
		
			} finally {
			    Conexion.cerrarPreparedStatement(pstmt);
			    Conexion.cerrarConexionBD(conexion);
		    }
		}
	}

	// Método para listar todos los Empleados
    public static ArrayList<Empleado> listarEmpleados() {
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        String consultaSQL = "SELECT * FROM empleado";
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
					int id = resultado.getInt("idEmpleado");
				    Date auxFechaIngreso = resultado.getDate("fechaIngresoEmpleado");
				    LocalDate fechaIngreso = null;
				    if (auxFechaIngreso != null) {
				    	fechaIngreso = auxFechaIngreso.toLocalDate();
				    }
				    Date auxFechaEgreso = resultado.getDate("fechaEgresoEmpleado");
				    LocalDate fechaEgreso = null;
				    if (auxFechaEgreso != null) {
				    	fechaEgreso = auxFechaEgreso.toLocalDate();
				    }
				    int legajo = resultado.getInt("legajoEmpleado");
				    double salario = resultado.getDouble("salarioEmpleado");
				    String nombres = resultado.getString("nombresEmpleado");
				    String apellidos = resultado.getString("apellidosEmpleado");
				    int dni = resultado.getInt("dniEmpleado");
					boolean activo = resultado.getBoolean("activoEmpleado");		    
				    String telefono = resultado.getString("telefonoEmpleado");
				    String direccion = resultado.getString("direccionEmpleado");    
				    String datoProvincia = resultado.getString("provinciaEmpleado");
				    Provincia provincia = Provincia.convertirProvincia(datoProvincia);
				    String localidad = resultado.getString("localidadEmpleado");
				    String datoSexo = resultado.getString("sexoEmpleado");
				    Sexo sexo = Sexo.convertirSexo(datoSexo);
				    LocalDate fechaNacimiento = null;
				    Date auxFecha = resultado.getDate("fechaNacEmpleado");
				    if (auxFecha != null) {
				    	fechaNacimiento = auxFecha.toLocalDate();
				    }
				    
				    Empleado nuevoEmpleado = new Empleado(id, dni, activo, nombres, apellidos, direccion, telefono, provincia, localidad, fechaNacimiento, sexo, fechaIngreso, fechaEgreso, legajo, salario);
				    listaEmpleados.add(nuevoEmpleado);
				}
			} catch (SQLException error) {
				System.out.println("Error al cargar datos: " + error.getMessage());
			}
        }
        return listaEmpleados;
    }
    
 // Método para buscar un Empleado por su DNI o LEGAJO
    public static Empleado buscarEmpleado(int dato) {
        Empleado empleado = null;
        String consultaSQL = "SELECT * FROM empleado WHERE dniEmpleado = ? OR legajoEmpleado = ? OR idEmpleado = ?";
        ResultSet resultado = null;

        try {
            Connection conexion = Conexion.conectarBD();
            PreparedStatement pstmt = conexion.prepareStatement(consultaSQL);
            pstmt.setInt(1, dato);  // Asigna el dato al primer parámetro de la consulta
            pstmt.setInt(2, dato);  // Asigna el dato al segundo parámetro de la consulta
            pstmt.setInt(3, dato);
            resultado = pstmt.executeQuery();
            
            if (resultado.next()) {  // Si existe un resultado
				int id = resultado.getInt("idEmpleado");
			    Date auxFechaIngreso = resultado.getDate("fechaIngresoEmpleado");
			    LocalDate fechaIngreso = null;
			    if (auxFechaIngreso != null) {
			    	fechaIngreso = auxFechaIngreso.toLocalDate();
			    }
			    Date auxFechaEgreso = resultado.getDate("fechaEgresoEmpleado");
			    LocalDate fechaEgreso = null;
			    if (auxFechaEgreso != null) {
			    	fechaEgreso = auxFechaEgreso.toLocalDate();
			    }
			    int legajo = resultado.getInt("legajoEmpleado");
			    double salario = resultado.getDouble("salarioEmpleado");
			    String nombres = resultado.getString("nombresEmpleado");
			    String apellidos = resultado.getString("apellidosEmpleado");
			    int dni = resultado.getInt("dniEmpleado");
				boolean activo = resultado.getBoolean("activoEmpleado");		    
			    String telefono = resultado.getString("telefonoEmpleado");
			    String direccion = resultado.getString("direccionEmpleado");    
			    String datoProvincia = resultado.getString("provinciaEmpleado");
			    Provincia provincia = Provincia.convertirProvincia(datoProvincia);
			    String localidad = resultado.getString("localidadEmpleado");
			    String datoSexo = resultado.getString("sexoEmpleado");
			    Sexo sexo = Sexo.convertirSexo(datoSexo);
			    LocalDate fechaNacimiento = null;
			    Date auxFecha = resultado.getDate("fechaNacEmpleado");
			    if (auxFecha != null) {
			    	fechaNacimiento = auxFecha.toLocalDate();
			    }
			    
			    return empleado = new Empleado(id, dni, activo, nombres, apellidos, direccion, telefono, provincia, localidad, fechaNacimiento, sexo, fechaIngreso, fechaEgreso, legajo, salario);
            }
        } catch (SQLException error) {
            System.out.println("No existen coincidencias con el dato ingresado.");
        } finally {
            try {
                if (resultado != null) resultado.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar ResultSet: " + e.getMessage());
            }
        }
        System.out.println("No existe un Empleado registrado con estos datos.");
        return empleado;
    }

    public static int obtenerUltimoEmpleado() {
        String consultaSQL = "SELECT idEmpleado FROM empleado ORDER BY idEmpleado DESC LIMIT 1";
        ResultSet resultado = null;
        int valor = 0;
        try {
        	Connection conexion = Conexion.conectarBD();
        	Statement stmt = conexion.createStatement();
            resultado = stmt.executeQuery(consultaSQL);
            
            if (resultado.next()) {
    			valor = resultado.getInt("idEmpleado");
            }
        } catch (SQLException error) {
        	System.out.println("Error: " + error.getMessage());
        }
        return valor;
    }
    
    public static void actualizarEmpleado(Empleado empleado) {
        String consultaSQL = "UPDATE empleado SET "
        		+ "fechaIngresoEmpleado = ?, "
        		+ "fechaEgresoEmpleado = ?, "
        		+ "salarioEmpleado = ?, "
        		+ "legajoEmpleado = ?, "
                + "dniEmpleado = ?, "
                + "nombresEmpleado = ?, "
                + "apellidosEmpleado = ?, "
                + "direccionEmpleado = ?, "
                + "telefonoEmpleado = ?, "
                + "provinciaEmpleado = ?, "
                + "localidadEmpleado = ?, "
                + "fechaNacEmpleado = ?, "
                + "sexoEmpleado = ?, "
                + "activoEmpleado = ? "
                + "WHERE idEmpleado = ?";

        try (Connection conexion = Conexion.conectarBD();
             PreparedStatement pstmt = conexion.prepareStatement(consultaSQL)) {

            if (empleado.getFechaIngreso()!= null) {
                pstmt.setDate(1, java.sql.Date.valueOf(empleado.getFechaIngreso()));
            } else {
                pstmt.setNull(1, java.sql.Types.DATE);
            }
            if (empleado.getFechaEgreso() != null) {
                pstmt.setDate(2, java.sql.Date.valueOf(empleado.getFechaEgreso()));
            } else {
                pstmt.setNull(2, java.sql.Types.DATE);
            }
            pstmt.setDouble(3, empleado.getSalario());
            pstmt.setInt(4, empleado.getLegajo());
            pstmt.setInt(5, empleado.getDni());
            pstmt.setString(6, empleado.getNombres());
            pstmt.setString(7, empleado.getApellidos());
            pstmt.setString(8, empleado.getDireccion());
            pstmt.setString(9, empleado.getTelefono());
			if (empleado.getProvincia() != null) {
	        	pstmt.setString(10, empleado.getProvincia().getNombre());
		    } else {
		        pstmt.setNull(10, java.sql.Types.VARCHAR);
	        }
			pstmt.setString(11, empleado.getLocalidad());
            if (empleado.getFechaNacimiento() != null) {
                pstmt.setDate(12, java.sql.Date.valueOf(empleado.getFechaNacimiento()));
            } else {
                pstmt.setNull(12, java.sql.Types.DATE);
            }
            if (empleado.getSexo() != null) {
            	pstmt.setString(13, empleado.getSexo().getNombre());
	        } else {
	          	pstmt.setNull(13, java.sql.Types.VARCHAR);
	        }
            pstmt.setBoolean(14, empleado.isActivo());
            
            pstmt.setInt(15, empleado.getId());  // Identificador del empleado para el WHERE

            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("ACTUALIZADO CON EXITO");
            } else {
                System.out.println("No se encontró un empleado con el ID especificado.");
            }
        } catch (SQLException error) {
            System.out.println("Error al actualizar empleado: " + error.getMessage());
        }
    }
}
