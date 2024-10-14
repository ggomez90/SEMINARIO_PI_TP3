package Entidades;

import java.util.ArrayList;

public class CuentaCorriente {
	
	private ArrayList<Movimiento> movimientos;
	private double saldoFinal;
	
	public CuentaCorriente() {
		this.saldoFinal = 0;
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