package Entidades;

import java.util.ArrayList;

public class CuentaCorriente {
	
	private int id;
	private ArrayList<Movimiento> movimientos;
	private double saldoFinal;
	
	public CuentaCorriente() {
		this.movimientos = new ArrayList<Movimiento>();
		this.saldoFinal = 0;
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
