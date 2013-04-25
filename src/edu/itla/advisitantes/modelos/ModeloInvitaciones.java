package edu.itla.advisitantes.modelos;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Invitacion;

public class ModeloInvitaciones extends AbstractTableModel{
	
			/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			private static ModeloInvitaciones instancia;
			String[] encabezados = {"Invitado", "Evento"};
			public static ArrayList<Invitacion> invitaciones = new ArrayList<Invitacion>();
			
	
	public static synchronized ModeloInvitaciones getInstancia() throws ClassNotFoundException, SQLException{
			if(instancia == null){
				instancia = new ModeloInvitaciones();
			}
			return instancia;
	}
		
	public String getColumnName(int columna) {
		return encabezados[columna];
	}
	
	public void agregar(String invitado, String evento) throws ClassNotFoundException, SQLException{
			Conexion.getInstancia().agregarInvitaciones(invitado, evento, "Ausente");
			invitaciones.add(new Invitacion(invitado, evento, "Ausente"));
			fireTableDataChanged();
	}

	public void eliminar(int fila) throws ClassNotFoundException, SQLException{
			Conexion.getInstancia().eliminarInvitaciones(invitaciones.get(fila));
			invitaciones.remove(fila);
			fireTableRowsDeleted(fila, fila);
	}

	public int getColumnCount() {
		return encabezados.length;
	}
	
	public int getRowCount() {
		return invitaciones.size();
	}

	public Object getValueAt(int fila, int columna) {
			String retorno = null;
			Invitacion invitacion = invitaciones.get(fila);
			switch(columna){
			case 0:
				retorno = invitacion.getInvitado();
			break;
			
			case 1:
				retorno = invitacion.getEvento();
			break;
			}
			return retorno;
	}

}
