package edu.itla.advisitantes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Invitacion;
import edu.itla.advisitantes.ui.VentanaPortero;

public class ModeloRegistroVisitas extends AbstractTableModel{
	
		  /**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		public static String columnas[] = { "Invitado", "Asistencia"};
		

		public static ArrayList<Invitacion> invitacionesActuales = new ArrayList<Invitacion>();
		private static ModeloRegistroVisitas instancia;
		
		public static synchronized ModeloRegistroVisitas getInstancia(){
				if(instancia == null){
					instancia = new ModeloRegistroVisitas();
				}
				return instancia;
		}
		
		  private ModeloRegistroVisitas(){
			  		Conexion.getInstancia().cargarInvitacionesActuales(VentanaPortero.eventoActual);
		  }

		@Override
		public int getColumnCount() {
			return columnas.length;
		}

		@Override
		public int getRowCount() {
			return invitacionesActuales.size();
		}
		
		@Override
		public String getColumnName(int column) {
			return columnas[column];
		}

		@Override
		public Object getValueAt(int fila, int columna) {
			Invitacion invitacionActual = invitacionesActuales.get(fila);
			String retorno = null;
			switch(columna){
			
			case 0:
				retorno = invitacionActual.getInvitado();
			break;
			
			case 1:
				retorno = invitacionActual.getAsistencia();
			break;
			}
			return retorno;
		}
}

