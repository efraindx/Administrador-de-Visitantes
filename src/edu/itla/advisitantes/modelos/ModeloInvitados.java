package edu.itla.advisitantes.modelos;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Invitado;

@SuppressWarnings("serial")
public class ModeloInvitados extends AbstractTableModel{

	private static ModeloInvitados instancia;
	public static String titulos[] = {"Nombre","Apellido","Teléfono","Dirección","Sexo"};
	public static ArrayList<Invitado> invitados = new ArrayList<Invitado>();
	
	public static synchronized ModeloInvitados getInstancia(){
		
		if(instancia == null){
			instancia = new ModeloInvitados();
		}
		return instancia;
	}
	
	private ModeloInvitados(){
		Conexion.getInstancia().cargarInvitados();
	}
	
	public void agregarInvitado(Invitado invitado) throws ClassNotFoundException, SQLException{
		invitados.add(invitado);
		Conexion.getInstancia().agregarInvitados(invitado);
		fireTableDataChanged();
	}
	
	public void eliminarInvitado(int fila) throws ClassNotFoundException, SQLException{
		Conexion.getInstancia().eliminarInvitadosInvitaciones(invitados.get(fila));
		Conexion.getInstancia().eliminarInvitados(invitados.get(fila));
		invitados.remove(fila);
		fireTableRowsDeleted(fila, fila);
	}

	public int getColumnCount() {
		return titulos.length;
	}

	public String getColumnName(int columna) {
		return titulos[columna];
	}
	public int getRowCount() {
		return invitados.size();
	}

	public Object getValueAt(int fila, int columna) {
		
		String retorno = "";
		Invitado invitado = invitados.get(fila);
		
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
	
	
	public void setValueAt(Object valor, int fila, int columna) {
		
		Invitado invitado = invitados.get(fila);
		
		switch(columna){
		
		case 0:
			Conexion.getInstancia().modificarInvitadosInvitaciones(0,invitado, (String) valor);
			invitado.setNombre((String)valor);
			Conexion.getInstancia().modificarInvitados(0, invitado.getId(), (String) valor);
			break;
			
		case 1:
			Conexion.getInstancia().modificarInvitadosInvitaciones(1,invitado, (String) valor);
			invitado.setApellido((String)valor);
			Conexion.getInstancia().modificarInvitados(1, invitado.getId(), (String) valor);
			break;
			
		case 2:
			invitado.setTelefono((String)valor);
			Conexion.getInstancia().modificarInvitados(2, invitado.getId(), (String)valor);
			break;
			
		case 3:
			invitado.setDireccion((String)valor);
			Conexion.getInstancia().modificarInvitados(3, invitado.getId(), (String)valor);
			break;
			
		case 4:
			String sexo = (String)valor;
			
			if(sexo.equals("Hombre") || sexo.equals("Mujer")){
				invitado.setSexo(sexo);
				Conexion.getInstancia().modificarInvitados(4, invitado.getId(), (String) valor);
			}else{
				JOptionPane.showMessageDialog(null, "Sólo hay dos géneros: Hombre ó Mujer", "Error al modificar el invitado",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
}
