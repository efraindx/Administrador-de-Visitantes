package edu.itla.advisitantes.modelos;

import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Registro;
import edu.itla.advisitantes.ui.VentanaPortero;

@SuppressWarnings("serial")
public class ModeloRegistroImprevisto extends AbstractTableModel {

	private static ModeloRegistroImprevisto instancia;
	private String titulos[] = {"Nombre", "Apellido", "Usuario", "Motivo"};
	
	public static synchronized ModeloRegistroImprevisto getInstancia(){
		if(instancia == null){
			instancia = new ModeloRegistroImprevisto();
		}
		return instancia;
	}

	public int getColumnCount() {
		
		return titulos.length;
	}
	
	public String getColumnName(int columna) {
		
		return titulos[columna];
	}
	
	public void agregarRegistro(String nombre, String apellido, String usuario, String motivo) throws ClassNotFoundException, SQLException{
		Conexion.getInstancia().agregarRegistro(new Registro(nombre, apellido, usuario, motivo, VentanaPortero.eventoActual));
		fireTableDataChanged();
	}

	public int getRowCount() {
		return Conexion.getInstancia().obtenerRegistro().size();
	}

	public Object getValueAt(int fila, int columna) {
		
		String retorno = "";
		Registro registro = Conexion.getInstancia().obtenerRegistro().get(fila);
		
		switch(columna){
		
		case 0:
			retorno = registro.getNombre();
			break;
			
		case 1:
			retorno = registro.getApellido();
			break;
			
		case 2:
			retorno = registro.getUsuario();
			break;
			
		case 3:
			retorno = registro.getMotivo();
			break;
		}
		
		return retorno;
	}

}
