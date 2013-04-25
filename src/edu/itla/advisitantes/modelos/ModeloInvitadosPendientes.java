package edu.itla.advisitantes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Invitado;
import edu.itla.advisitantes.ui.VentanaPortero;

public class ModeloInvitadosPendientes extends AbstractTableModel{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public static ArrayList<Invitado> invitadosPendientes = new ArrayList<Invitado>();
		
		public ModeloInvitadosPendientes(){
					Conexion.getInstancia().cargarVisitantesPendientes(VentanaPortero.eventoActual);
		}

	public String getColumnName(int column) {
		return ModeloInvitados.titulos[column];
	}

	public int getColumnCount() {
		return ModeloInvitados.titulos.length;
	}

	public int getRowCount() {
		return invitadosPendientes.size();
	}

public Object getValueAt(int fila, int columna) {
		
		String retorno = "";
		Invitado invitado = invitadosPendientes.get(fila);
		
		switch(columna){
		
		case 0:
			retorno = invitado.getNombre();
			break;
			
		case 1:
			retorno = invitado.getApellido();
			break;
			
		case 2:
			retorno = invitado.getTelefono();
			break;
			
		case 3:
			retorno = invitado.getDireccion();
			break;
			
		case 4:
			retorno = invitado.getSexo();
			break;
		}
		return retorno;
	}

}
