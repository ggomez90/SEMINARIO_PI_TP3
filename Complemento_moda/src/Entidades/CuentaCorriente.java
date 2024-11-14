package Entidades;

import java.util.ArrayList;

import DAO.CuentaCorrienteDAO;

public class CuentaCorriente {
	
	public static ArrayList<CuentaCorriente> listadoCtaCte = new ArrayList<CuentaCorriente>();
	private static int ultimoCodigoCtaCte = CuentaCorrienteDAO.obtenerUltimaCtaCte();
	
	private int id;
	private ArrayList<Movimiento> movimientos;
	private double saldoFinal;
	
	public CuentaCorriente() {
		this.movimientos = new ArrayList<Movimiento>();
		this.id = ++ultimoCodigoCtaCte;
		this.saldoFinal = 0;
	}
	
	//Constructor de ctaCte para altas desde la BD
	public CuentaCorriente(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(ArrayList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public double getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}
	
	
}
