package edu.itla.advisitantes.modelos;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import edu.itla.advisitantes.datos.Calendario;
import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Evento;

public class ModeloEventosActuales extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static ArrayList<Evento> eventosActuales  = new ArrayList<Evento>();
	String[] encabezados = {"Nombre","Ubicación"};

	public ModeloEventosActuales() throws ClassNotFoundException, SQLException{
			eventosActuales =  new ArrayList<Evento>();
			Conexion.getInstancia().cargarEventosActuales(Calendario.obtenerFechaActual());
	}

	public String getColumnName(int column) {
		return encabezados[column];
	}

	public int getColumnCount() {
		return encabezados.length;
	}

	public int getRowCount() {
		return eventosActuales.size();
	}

	public Object getValueAt(int fila, int columna) {
			String retorno = null;
			Evento evento = eventosActuales.get(fila);
			
			switch(columna){
			case 0:
				retorno = evento.getNombre();
			break;
			
			case 1:
				retorno = evento.getUbicacion();
			break;
			
			}
			return retorno;
	}

}
