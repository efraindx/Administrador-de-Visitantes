package edu.itla.advisitantes.modelos;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Evento;

public class ModeloEventos extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
		public static ArrayList<Evento> eventos = new ArrayList<Evento>();;
		String[] encabezados = {"Nombre", "Fecha", "Ubicación"};
	
		private static ModeloEventos instancia;
		
		public static synchronized ModeloEventos getInstancia() throws ClassNotFoundException, SQLException{
			if(instancia == null){
				instancia = new ModeloEventos();
			}
			return instancia;
		}
		
		private ModeloEventos() throws ClassNotFoundException, SQLException{
				Conexion.getInstancia().cargarEventos();
		}
		
		public void agregarEventos(Evento ev) throws ClassNotFoundException, SQLException{
				Conexion.getInstancia().agregarEventos(ev);
				fireTableDataChanged();
		}
		
		public void eliminarEventos(int fila) throws ClassNotFoundException, SQLException{
			Conexion.getInstancia().eliminarEventosInvitaciones(eventos.get(fila).getNombre());
			Conexion.getInstancia().eliminarEventos(eventos.get(fila).getID());
			eventos.remove(fila);
			fireTableDataChanged();
		}

		public String getColumnName(int column) {
			return encabezados[column];
		}

		public int getColumnCount() {
			return encabezados.length;
		}

		public int getRowCount() {
			return eventos.size();
		}

		public Object getValueAt(int fila, int columna) {
				String retorno = null;
				Evento evento = eventos.get(fila);
				
				switch(columna){
				case 0:
					retorno = evento.getNombre();
				break;
				
				case 1:
					retorno = String.valueOf(evento.getFecha());
				break;
				
				case 2:
					retorno = evento.getUbicacion();
				break;
				
				}
				
				return retorno;
		}
		
		public void setValueAt(Object valor, int fila, int columna) {
				Evento evento = eventos.get(fila);
				switch(columna){
				case 0:
					Conexion.getInstancia().modificarEventosInvitaciones(evento, (String)valor);
					evento.setNombre((String) valor);
				break;
				
				case 1:
					evento.setFecha((String)valor);
				break;
				
				case 2:
					evento.setUbicacion((String) valor);
				break;
				}
				Conexion.getInstancia().modificarEventos(evento);
				eventos = Conexion.getInstancia().obtenerEventos();
				fireTableDataChanged();
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}
		
		
}
