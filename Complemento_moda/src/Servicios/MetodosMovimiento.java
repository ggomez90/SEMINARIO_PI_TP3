package Servicios;
import java.util.ArrayList;

import Entidades.Movimiento;

public class MetodosMovimiento {
	
	public static double listarMovimientos(ArrayList<Movimiento> listadoMovimientos) {
	    System.out.printf("%-10s %-58s %-10s %-10s %-10s%n", "Código", "Detalle", "Debe", "Haber", "Saldo");
	    System.out.println("--------------------------------------------------------------------------------------------------");
	    double saldo = 0;
	    if (listadoMovimientos != null) {
		    for (Movimiento elemento : listadoMovimientos) {
		        saldo += elemento.getMontoHaber() - elemento.getMontoDebe();
		        System.out.printf("%-10d %-100s %-10.2f %-10.2f %-10.2f%n",
		        		elemento.getCodigo(),
		        		elemento.getDetalle(),
		        		elemento.getMontoDebe(),
		        		elemento.getMontoHaber(),
		                saldo);
		    }
	    } else {
	    	System.out.println("La cuenta corriente no posee movimientos.");
	    }
	    System.out.println("--------------------------------------------------------------------------------------------------");
	    System.out.printf("%-10s %5.2f%n", "Saldo Final:", saldo);
	    return saldo;
	}


}
