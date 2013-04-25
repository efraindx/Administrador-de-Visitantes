package edu.itla.advisitantes.modelos;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Usuario;

@SuppressWarnings("serial")
public class ModeloUsuarios extends AbstractTableModel{

	private String titulos[] = {"Nombre","Apellido","Usuario","Contraseña","Perfil"};
	private static ModeloUsuarios instancia;
	public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	public static synchronized ModeloUsuarios getInstancia(){
		
		if(instancia == null){
			try {
				instancia = new ModeloUsuarios();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instancia;
	}
	
	private ModeloUsuarios() throws ClassNotFoundException, SQLException{
			Conexion.getInstancia().cargarUsuarios();
	}
	
	public void agregarUsuarios(Usuario user) throws ClassNotFoundException, SQLException{
		usuarios.add(user);
		Conexion.getInstancia().agregarUsuarios(user);
		fireTableDataChanged();
	}
	
	public void eliminarUsuario(int fila) throws ClassNotFoundException, SQLException{
		Conexion.getInstancia().eliminarUsuarios(usuarios.get(fila));
		usuarios.remove(fila);
		fireTableRowsDeleted(fila, fila);
	}
	
	public int getColumnCount() {
		return titulos.length;
	}
	
	public String getColumnName(int columna) {
		return titulos[columna];
	}
	
	public int getRowCount() {
		return usuarios.size();
	}
	
	public Object getValueAt(int fila, int columna) {
		String retorno = "";
		Usuario user = usuarios.get(fila);
		
		switch(columna){
		
		case 0:
			retorno = user.getNombre();
			break;
			
		case 1:
			retorno = user.getApellido();
			break;
			
		case 2:
			retorno = user.getUsuario();
			break;
			
		case 3:
			retorno = user.getContraseña();
			break;
			
		case 4:
			retorno = user.getPerfil();
			break;
		}
		return retorno;
	}
	
	
	public void setValueAt(Object valor, int fila, int columna) {
		Usuario user = usuarios.get(fila);
		int ID = user.getId();
		
		switch(columna){
		
		case 0:
			user.setNombre((String)valor);
			Conexion.getInstancia().modificarUsuarios(0, ID, (String) valor);
			break;
			
		case 1:
			user.setApellido((String)valor);
			Conexion.getInstancia().modificarUsuarios(1, ID, (String) valor);
			break;
			
		case 2:
			user.setUsuario((String)valor);
			Conexion.getInstancia().modificarUsuarios(2, ID, (String) valor);
			break;
			
		case 3:
			user.setContraseña((String)valor);
			Conexion.getInstancia().modificarUsuarios(3, ID, (String) valor);
			break;
			
		case 4:
			String perfil = (String)valor;
			if(perfil.equals("Admin")||perfil.equals("Portero")){
				user.setPerfil(perfil);
				Conexion.getInstancia().modificarUsuarios(4, ID, perfil);
			}else{
				JOptionPane.showMessageDialog(null, "Sólo existen dos tipos de perfiles: Admin y Portero","Error al modificar", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
	}

	public boolean isCellEditable(int fila, int columna) {
		return true;
	}
	
	public Usuario encontrarUsuario(String nombre, String contrase){
		Usuario user = null;
		for(int i =0; i<usuarios.size(); i++){
			if(ModeloUsuarios.usuarios.get(i).getUsuario().equals(nombre) && ModeloUsuarios.usuarios.get(i).getContraseña().equals(contrase)){
					user = usuarios.get(i);
			}
		}
		return user;
	}
}
