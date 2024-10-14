package Entidades;

import Enumerados.FormaPago;

public class Venta {
	
	private static int ultimoCodigo;
	
	private int codigo;
	private Cliente cliente;
	private int cantidad;
	private Producto[] producto;
	private double total;
	private FormaPago pago;
	
	//Constructor para alta de Venta desde App
	public Venta(Cliente cliente, int cantidad, Producto[] producto, double total, FormaPago pago) {
		this.codigo = ++ultimoCodigo;
		this.cliente = cliente;
		this.cantidad = cantidad;
		this.producto = producto;
		this.total = total;
		//this.pago = seleccionarPago();
	}
	
	//Constrcutor para alta de Venta desde BD
	public Venta(int codigo, Cliente cliente, int cantidad, Producto[] producto, double total, FormaPago pago){
		this.codigo = codigo;
		this.cliente = cliente;
		this.cantidad = cantidad;
		this.producto = producto;
		this.total = total;
		this.pago = pago;
	}

	public static int getUltimoCodigo() {
		return ultimoCodigo;
	}

	public static void setUltimoCodigo(int ultimoCodigo) {
		Venta.ultimoCodigo = ultimoCodigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto[] getProducto() {
		return producto;
	}

	public void setProducto(Producto[] producto) {
		this.producto = producto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public FormaPago getPago() {
		return pago;
	}

	public void setPago(FormaPago pago) {
		this.pago = pago;
	}
	
	
	
}
