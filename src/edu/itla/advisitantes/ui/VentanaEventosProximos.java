package edu.itla.advisitantes.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Evento;

public class VentanaEventosProximos extends JFrame{
	
			/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			public static ArrayList<Evento> eventosProximos = new ArrayList<Evento>();
			String[] filas = new String[3];
			String[] titulos = {"Nombre", "Fecha", "Ubicación"};
			
		public VentanaEventosProximos(){
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(550,350);
			setResizable(false);
			setLocationRelativeTo(null);
			setLayout(new FlowLayout());
			
			Conexion.getInstancia().cargarEventosProximos();
			
			DefaultTableModel modelo = new DefaultTableModel();
			modelo.setColumnIdentifiers(titulos);
			for(int i =0; i<eventosProximos.size();i++){
				Evento eventoActual = eventosProximos.get(i);
				filas[0] = eventoActual.getNombre();
				filas[1] = eventoActual.getFecha();
				filas[2] = eventoActual.getUbicacion();
				modelo.addRow(filas);
			}
			
			JTable tablaEventosProximos = new JTable(modelo);
			tablaEventosProximos.setPreferredScrollableViewportSize(new Dimension(500,215));
			JLabel lblMostrar = new JLabel("Eventos Próximos", new ImageIcon("images/evento.png"),0);
			lblMostrar.setFont(new Font("Cooper Black", Font.BOLD, 28));
			lblMostrar.setForeground(Color.orange);
			add(lblMostrar);
			add(new JScrollPane(tablaEventosProximos));
		}
		public static void main(String[] args) {
			new VentanaEventosProximos().setVisible(true);
		}
}
