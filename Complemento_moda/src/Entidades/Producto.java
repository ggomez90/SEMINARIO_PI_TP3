package Entidades;

import Enumerados.Categoria;

public class Producto {

	private static int ultimoCodigo;
	
	private int codigo;
	private String detalle;
	private String talle;
	private double precio;
	private String marca;
	private String material;
	private Categoria categoria;
	private int stock;
	
	//Constructor para alta de producto desde App
	public Producto(String detalle, String talle, double precio, String marca, String material, int stock) {
		this.codigo = ++ultimoCodigo;
		this.detalle = detalle;
		this.talle = talle;
		this.precio = precio;
		this.marca = marca;
		this.material = material;
		//this.categoria = seleccionarCategoria();
		this.stock = stock;
	}
	
	//Constructor para alta de prodcuto desde consulta BD
	public Producto(int codigo, String detalle, String talle, double precio, String marca, String material, Categoria categoria, int stock) {
		this.codigo = codigo;
		this.detalle = detalle;
		this.talle = talle;
		this.precio = precio;
		this.marca = marca;
		this.material = material;
		this.categoria = categoria;
		this.stock = stock;	
	}

	public static int getUltimoCodigo() {
		return ultimoCodigo;
	}

	public static void setUltimoCodigo(int ultimoCodigo) {
		Producto.ultimoCodigo = ultimoCodigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getTalle() {
		return talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
}
