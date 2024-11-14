package Menues;

import DAO.ClienteDAO;
import DAO.EmpleadoDAO;
import DAO.UsuarioDAO;
import Entidades.Cliente;
import Entidades.Empleado;
import Entidades.Usuario;
import Utilidades.MensajesConsola;
import Utilidades.MetodosGenerales;
import Servicios.*;

public class Opciones {
	
	public static int opcionPrincipal;
	public static int opcionSecundario;
	
	public static boolean menuPrincipal(boolean admin) {
		System.out.println("MENU PRINCIPAL");
		MensajesConsola.separador();
		if (admin) {
			opcionPrincipal = seleccionarOpcion(opcionesPrincipalAdmin(), 0, 9);
		} else {
			opcionPrincipal = seleccionarOpcion(opcionesPrincipalReducido(), 0, 5);
		}
		MensajesConsola.separador();
		if (opcionPrincipal != 0) {
			return true;
		} else {
			MensajesConsola.saludoFinal();
			return false;
		}
	}
	
	public static void menuSecundario(boolean tipoUsuario) {
		switch (opcionPrincipal) {
			case 1: if (tipoUsuario) {
						opcionSecundario = seleccionarOpcion(opcionesClienteAdmin(), 0, 5);
					} else {
						opcionSecundario = seleccionarOpcion(opcionesClienteReducido(), 0, 3);
					}
					elegidoCliente(opcionSecundario);
					break;
					
			case 2: if (tipoUsuario) {
						opcionSecundario = seleccionarOpcion(opcionesProveedorAdmin(), 0, 5);
					} else {
						opcionSecundario = seleccionarOpcion(opcionesProveedorReducido(), 0, 2);
					}
					elegidoProveedor(opcionSecundario);
					break;
					
			case 3: if (tipoUsuario) {
						opcionSecundario = seleccionarOpcion(opcionesEmpleado(), 0, 5);
						elegidoEmpleado(opcionSecundario);
					} else {
						opcionSecundario = seleccionarOpcion(opcionesProductoReducido(), 0, 2);
						elegidoProducto(opcionSecundario);
					}
					break;
					
			case 4: if (tipoUsuario) {
						opcionSecundario = seleccionarOpcion(opcionesUsuario(), 0, 5);
						elegidoUsuario(opcionSecundario);
					} else {
						opcionSecundario = seleccionarOpcion(opcionesVenta(), 0, 5);
						elegidoVenta(opcionSecundario);
					}
					break;
					
			case 5: if (tipoUsuario) {
						opcionSecundario = seleccionarOpcion(opcionesProductoAdmin(), 0, 5);
						elegidoProducto(opcionSecundario);
					} else {
						opcionSecundario = seleccionarOpcion(opcionesCobranza(), 0, 5);
						elegidoCobranza(opcionSecundario);
					}
					break;
					
			case 6: if (tipoUsuario) {
						opcionSecundario = seleccionarOpcion(opcionesVenta(), 0, 5);
						elegidoVenta(opcionSecundario);
					}
					break;
					
			case 7: if (tipoUsuario) {
						opcionSecundario = seleccionarOpcion(opcionesCompra(), 0, 5);
						elegidoCompra(opcionSecundario);
					}
					break;
					
			case 8: if (tipoUsuario) {
						opcionSecundario = seleccionarOpcion(opcionesCobranza(), 0, 5);
						elegidoCobranza(opcionSecundario);
					}
					break;
					
			case 9: if (tipoUsuario) {
						opcionSecundario = seleccionarOpcion(opcionesPago(), 0, 5);
						elegidoPago(opcionSecundario);
					}
					break;
				
			case 0: MensajesConsola.saludoFinal();
					opcionSalir();
					break;
			default: MensajesConsola.verificarDato();
			         break;
		}
		MensajesConsola.separador();
	}
	
	public static int seleccionarOpcion(String opciones, int desde, int hasta) {
		int opcion = -1;
		boolean exito = false;
		System.out.println(opciones);
		do {
			opcion = MensajesConsola.ingreseOpcion();
			exito = MetodosGenerales.verificarRango(opcion, desde, hasta);
			if (!exito) {
				MensajesConsola.verificarDato();
			}
		}while(!exito);
		return opcion;
	}

	private static String opcionesPrincipalAdmin() {
		return "1- CLIENTES\n" + 
			   "2- PROVEEDOR\n" +
		       "3- EMPLEADO\n" + 
		       "4- USUARIO\n" +
		       "5- PRODUCTO\n" + 
		   	   "6- VENTA\n" +
		   	   "7- COMPRA\n" +
		   	   "8- COBRANZA\n" +
		   	   "9- PAGO\n" +
		   	   "0- SALIR\n";
	}
	
	public static String opcionesPrincipalReducido() {
		return "1- CLIENTES\n" + 
			   "2- PROVEEDOR\n" +
			   "3- PRODUCTO\n" + 
			   "4- VENTA\n" +
			   "5- COBRANZA\n" + 
			   "0- SALIR\n";
	}
	
	private static void opcionSalir() {
		System.exit(0);
	}
	
	private static String opcionesClienteAdmin() {	
		return "1- Alta Cliente\n" + 
		       "2- Buscar Cliente\n" +
			   "3- Listar Clientes\n" + 
			   "4- Baja Cliente\n" +
			   "5- Modificar Cliente\n" + 
			   "0- Volver al menu principal\n";
	}
	
	private static String opcionesClienteReducido() {
		return "1- Alta Cliente\n" + 
		       "2- Buscar Cliente\n" +
			   "3- Listar Clientes\n" + 
			   "0- Volver al menu principal\n";
	}
	
	private static String opcionesProveedorAdmin() {
		return "1- Buscar Proveedor\n" +
			   "2- Listar Proveedores\n" + 
			   "3- Alta Proveedor\n" + 
			   "4- Baja Proveedor\n" +
			   "5- Modificar Proveedor\n" + 
			   "0- Volver al menu principal\n";
	}
	
	private static String opcionesProveedorReducido() {
		return "1- Buscar Proveedor\n" +
			   "2- Listar Proveedores\n" + 
			   "0- Volver al menu principal\n";
	}
	
	private static String opcionesEmpleado() {
		return "1- Alta Empleado\n" + 
			   "2- Baja Empleado\n" +
			   "3- Modificar Empleado\n" + 
		       "4- Buscar Empleado\n" +
			   "5- Listar Empleados\n" + 
			   "0- Volver al menu principal\n";
	}
	
	private static String opcionesUsuario() {
		return "1- Alta Usuario\n" + 
			   "2- Baja Usuario\n" +
			   "3- Modificar Usuario\n" + 
		       "4- Buscar Usuario\n" +
			   "5- Listar Usuarios\n" + 
			   "0- Volver al menu principal\n";
	}
	
	private static String opcionesProductoAdmin() {
		return "1- Buscar Producto\n" +
			   "2- Listar Productos\n" + 
			   "3- Alta Producto\n" + 
			   "4- Baja Producto\n" +
			   "5- Modificar Producto\n" + 
			   "0- Volver al menu principal\n";
	}
	
	private static String opcionesProductoReducido() {
		return "1- Buscar Producto\n" +
			   "2- Listar Producto\n" + 
			   "0- Volver al menu principal\n";
	}
	
	private static String opcionesVenta() {
		return "1- Alta Venta\n" + 
			   "2- Baja Venta\n" +
			   "3- Modificar Venta\n" + 
		       "4- Buscar Venta\n" +
			   "5- Listar Ventas\n" + 
			   "0- Volver al menu principal\n";
	}
	
	private static String opcionesCompra() {
		return "1- Alta Compra\n" + 
			   "2- Baja Compra\n" +
			   "3- Modificar Compra\n" + 
		       "4- Buscar Compra\n" +
			   "5- Listar Compras\n" + 
			   "0- Volver al menu principal\n";
	}
	
	private static String opcionesCobranza() {
		return "1- Alta Cobranza\n" + 
			   "2- Baja Cobranza\n" +
			   "3- Modificar Cobranza\n" + 
		       "4- Buscar Cobranza\n" +
			   "5- Listar Cobranza\n" + 
			   "0- Volver al menu principal\n";
	}
	
	private static String opcionesPago() {
		return "1- Alta Pago\n" + 
			   "2- Baja Pago\n" +
			   "3- Modificar Pago\n" + 
		       "4- Buscar Pago\n" +
			   "5- Listar Pago\n" + 
			   "0- Volver al menu principal\n";
	}
	
	
	//ver tema retorno en opcion de modificacion
	public static void elegidoCliente(int opcion) {
		MetodosCliente elemento = new MetodosCliente();
		switch (opcion) {
		case 1: ClienteDAO.guardarClienteBD(elemento.altaCliente());
				break;
		case 2: Cliente buscado = ClienteDAO.buscarClientePorDNI(MensajesConsola.buscarPorDatoEntero("Ingrese DNI del cliente: "));
				MensajesConsola.imprimirEnConsola(elemento.datosCliente(buscado));
				if (buscado != null) {
					int aux = MensajesConsola.opcionSINO("Desea ver la cuenta corriente?");
					if (aux == 1) {
						MetodosCuentaCorriente.mostrarCuentaCorriente(buscado);
					}
				}
				break;
		case 3: elemento.imprimirDatosClientes(ClienteDAO.listarClientes());
				break;
		case 4: ClienteDAO.actualizarCliente(elemento.bajaCliente(ClienteDAO.buscarClientePorDNI(MensajesConsola.buscarPorDatoEntero("Ingrese DNI del cliente: ")), "Cliente "));
				break;
		case 5: ClienteDAO.actualizarCliente(elemento.modificarCliente(ClienteDAO.buscarClientePorDNI(MensajesConsola.buscarPorDatoEntero("Ingrese DNI del cliente: "))));
				break;
		case 0: default: MensajesConsola.verificarDato();
				break;
		}
	}
	
	public static void elegidoEmpleado(int opcion) {
		MetodosEmpleado elemento = new MetodosEmpleado();
		switch (opcion) {
		case 1: EmpleadoDAO.guardarEmpleadoBD(elemento.altaEmpleado());
				break;
		case 2: EmpleadoDAO.actualizarEmpleado(EmpleadoDAO.buscarEmpleado(MensajesConsola.buscarPorDatoEntero("Ingrese DNI/legajo del empleado: ")));
		        break;
		case 3: EmpleadoDAO.actualizarEmpleado(elemento.modificarEmpleado(EmpleadoDAO.buscarEmpleado(MensajesConsola.buscarPorDatoEntero("Ingrese DNI/legajo del empleado: "))));
		        break;
		case 4: MensajesConsola.imprimirEnConsola(elemento.datosEmpleado(EmpleadoDAO.buscarEmpleado(MensajesConsola.buscarPorDatoEntero("Ingrese DNI/legajo del empleado: "))));
		        break;
		case 5: elemento.imprimirDatosEmpleados(EmpleadoDAO.listarEmpleados());
		        break;
		default: MensajesConsola.verificarDato();
				 break;
		}
	}
	
	public static void elegidoUsuario(int opcion) {
		MetodosUsuario elemento = new MetodosUsuario();
		switch (opcion) {
		case 1: UsuarioDAO.guardarUsuarioBD(elemento.altaUsuario());
				break;
		case 2: UsuarioDAO.eliminarUsuario(UsuarioDAO.buscarUsuario(EmpleadoDAO.buscarEmpleado(MensajesConsola.buscarPorDatoEntero("Ingrese DNI/legajo del empleado: "))));
		        break;
		case 3: UsuarioDAO.actualizarUsuario(elemento.modificarUsuario(UsuarioDAO.buscarUsuario(EmpleadoDAO.buscarEmpleado(MensajesConsola.buscarPorDatoEntero("Ingrese DNI/legajo del empleado: ")))));
		        break;
		case 4: MensajesConsola.imprimirEnConsola(elemento.datosUsuario(UsuarioDAO.buscarUsuario(EmpleadoDAO.buscarEmpleado(MensajesConsola.buscarPorDatoEntero("Ingrese DNI/legajo del empleado: ")))));
		        break;
		case 5: elemento.imprimirDatosUsuarios(UsuarioDAO.listarUsuarios());
		        break;
		default: MensajesConsola.verificarDato();
				 break;
		}
	}
	
	public static void elegidoProveedor(int opcion) {
		switch (opcion) {
		case 1: MensajesConsola.moduloEnProceso("alta de proveedor");
				break;
		case 2: MensajesConsola.moduloEnProceso("baja de proveedor");
		        break;
		case 3: MensajesConsola.moduloEnProceso("modificación de proveedor");
		        break;
		case 4: MensajesConsola.moduloEnProceso("búsqueda de proveedor");
		        break;
		case 5: MensajesConsola.moduloEnProceso("lista de proveedores");
		        break;
		default: MensajesConsola.verificarDato();
				 break;
		}
	}
	
	public static void elegidoProducto(int opcion) {
		switch (opcion) {
		case 1: MensajesConsola.moduloEnProceso("alta de producto");
				break;
		case 2: MensajesConsola.moduloEnProceso("baja de producto");
		        break;
		case 3: MensajesConsola.moduloEnProceso("modificación de producto");
		        break;
		case 4: MensajesConsola.moduloEnProceso("búsqueda de producto");
		        break;
		case 5: MensajesConsola.moduloEnProceso("lista de productos");
		        break;
		default: MensajesConsola.verificarDato();
				 break;
		}
	}
	
	public static void elegidoCompra(int opcion) {
		switch (opcion) {
		case 1: MensajesConsola.moduloEnProceso("alta de compra");
				break;
		case 2: MensajesConsola.moduloEnProceso("baja de compra");
		        break;
		case 3: MensajesConsola.moduloEnProceso("modificación de compra");
		        break;
		case 4: MensajesConsola.moduloEnProceso("búsqueda de compra");
		        break;
		case 5: MensajesConsola.moduloEnProceso("lista de compras");
		        break;
		default: MensajesConsola.verificarDato();
				 break;
		}
	}
	
	public static void elegidoVenta(int opcion) {
		switch (opcion) {
		case 1: MensajesConsola.moduloEnProceso("alta de venta");
				break;
		case 2: MensajesConsola.moduloEnProceso("baja de venta");
		        break;
		case 3: MensajesConsola.moduloEnProceso("modificación de venta");
		        break;
		case 4: MensajesConsola.moduloEnProceso("búsqueda de venta");
		        break;
		case 5: MensajesConsola.moduloEnProceso("lista de ventas");
		        break;
		default: MensajesConsola.verificarDato();
				 break;
		}
	}
	
	public static void elegidoCobranza(int opcion) {
		switch (opcion) {
		case 1: MensajesConsola.moduloEnProceso("alta de cobranza");
				break;
		case 2: MensajesConsola.moduloEnProceso("baja de cobranza");
		        break;
		case 3: MensajesConsola.moduloEnProceso("modificación de cobranza");
		        break;
		case 4: MensajesConsola.moduloEnProceso("búsqueda de cobranza");
		        break;
		case 5: MensajesConsola.moduloEnProceso("lista de cobranzas");
		        break;
		default: MensajesConsola.verificarDato();
				 break;
		}
	}
	
	public static void elegidoPago(int opcion) {
		switch (opcion) {
		case 1: MensajesConsola.moduloEnProceso("alta de pago");
				break;
		case 2: MensajesConsola.moduloEnProceso("baja de pago");
		        break;
		case 3: MensajesConsola.moduloEnProceso("modificación de pago");
		        break;
		case 4: MensajesConsola.moduloEnProceso("búsqueda de pago");
		        break;
		case 5: MensajesConsola.moduloEnProceso("lista de pagos");
		        break;
		default: MensajesConsola.verificarDato();
				 break;
		}
	}
}
