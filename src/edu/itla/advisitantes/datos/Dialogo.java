package edu.itla.advisitantes.datos;

import javax.swing.JDialog;

public class Dialogo{
	
	private static JDialog instancia;
	
	public static synchronized JDialog getInstancia(){
				if(instancia == null){
					instancia = new JDialog();
				}
				return instancia;
	}
	
	private Dialogo(){
		instancia.setLocationRelativeTo(null);
	}

}
