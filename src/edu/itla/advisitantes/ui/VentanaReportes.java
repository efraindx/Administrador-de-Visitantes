package edu.itla.advisitantes.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Invitacion;

public class VentanaReportes extends JFrame{
		/**
		 * 
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
		String[] tituloEventos = {"Eventos"};
		String[] tituloDias = {"D�as"};
		String[] filas = new String[1];
		public static ArrayList<String> eventos = new ArrayList<String>();
		public static ArrayList<String>	eventosHombres = new ArrayList<String>();
		public static ArrayList<String> eventosMujeres = new ArrayList<String>();
		public static ArrayList<String> dias = new ArrayList<String>();
	
		private JTabbedPane ventanas;
		JComboBox<String> combo;
		private String[] titulos = {"Nombre de Evento","Cantidad de Personas", "Porciento de Asistencia"};
		private DefaultTableModel tabla = new DefaultTableModel();
		private JTable tablaVisitantes, tablaEventos;
		DefaultTableModel modelo;
		JLabel lblEvento, lblDia;;
		JPanel pnlAsis, pnlEventosCategor�a;
		
		public VentanaReportes(){
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(550,520);
			setLocationRelativeTo(null);
			setResizable(false);
			setLayout(new FlowLayout());
			
			JLabel lblTituloEventos = new JLabel("Reporte de Eventos por Categor�as", new ImageIcon("images/reporte.png"), 0);
			lblTituloEventos.setFont(new Font("Bernard MT Condensed", Font.BOLD, 22));
			lblEvento = new JLabel("Seleccione la categor�a:");
			lblEvento.setFont(new Font("Elephant", Font.BOLD, 18));
			Conexion.getInstancia().cargarEventosVisitados();
			Conexion.getInstancia().cargarVisitasHumanas("Hombre");
			Conexion.getInstancia().cargarVisitasHumanas("Mujer");
			Conexion.getInstancia().cargarDiasMasVisitados();
			tabla.setColumnIdentifiers(titulos);
			
			combo = new JComboBox<String>();
			combo.addItem("Eventos m�s visitados");
			combo.addItem("Eventos m�s visitados por hombres");
			combo.addItem("Eventos m�s visitados por mujeres");
			combo.addItem("D�as en que asisten m�s personas");
			
			pnlEventosCategor�a = new JPanel(new FlowLayout());
			pnlEventosCategor�a.setPreferredSize(new Dimension(500,500));
			pnlEventosCategor�a.add(lblTituloEventos);
			pnlEventosCategor�a.add(lblEvento);
			pnlEventosCategor�a.add(combo);
			
			modelo = new DefaultTableModel();
			modelo.setColumnIdentifiers(tituloEventos);
			for(int i=0; i<eventos.size();i++){
				filas[0] = eventos.get(i);
				modelo.addRow(filas);
			}
			String filaInvitaciones[] = new String[3];
			for(int i=0; i<Conexion.getInstancia().mostrarAsistencia().size(); i++){
				
				Invitacion invitacionActual = Conexion.getInstancia().mostrarAsistencia().get(i);
				filaInvitaciones[0] = invitacionActual.getEvento();
				filaInvitaciones[1] = Integer.toString(invitacionActual.getCantidad() + Conexion.getInstancia().obtenerCantidadVisitasImprevistas(invitacionActual.getEvento()));
				double numero1 = invitacionActual.getCantidad();
				double numero2 = Conexion.getInstancia().encontrarCantidadInvitaciones(invitacionActual.getEvento());
				int resultado = (int) ((numero1/numero2)*(100));
				filaInvitaciones[2] = Integer.toString(resultado)+"%";
				tabla.addRow(filaInvitaciones);
				
			}
			
			ventanas = new JTabbedPane();
			ventanas.setPreferredSize(new Dimension(525,500));
			tablaEventos = new JTable(modelo);
			tablaVisitantes = new JTable(tabla);
			tablaEventos.setPreferredScrollableViewportSize(new Dimension(500,320));
			tablaVisitantes.setPreferredScrollableViewportSize(new Dimension(500,320));
			
			tablaVisitantes.setModel(tabla);
			JLabel lblTitulo = new JLabel("Reporte de Asistencias a Eventos", new ImageIcon("images/reporte.png"), 0);
			lblTitulo.setFont(new Font("Bernard MT Condensed", Font.BOLD, 22));
			JLabel lblTexto = new JLabel("Cantidades de Personas y Porcentajes de Asistencias ");
			lblTexto.setFont(new Font("Aharoni", Font.ITALIC+Font.BOLD, 18));
			pnlAsis = new JPanel();
			pnlAsis.add(lblTitulo);
			pnlAsis.add(lblTexto);
			pnlAsis.add(new JScrollPane(tablaVisitantes));
			
			combo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ev) {
					if(combo.getSelectedIndex() == 0){
						modelo.setRowCount(0);
						modelo.setColumnIdentifiers(tituloEventos);
						for(int i=0; i<eventos.size();i++){
							filas[0] = eventos.get(i);
							modelo.addRow(filas);
						}
					}
					if(combo.getSelectedIndex() == 1){
						modelo.setRowCount(0);
						modelo.setColumnIdentifiers(tituloEventos);
						for(int i =0; i<eventosHombres.size();i++){
							filas[0] = eventosHombres.get(i);
							modelo.addRow(filas);
						}
					}
					
					if(combo.getSelectedIndex() == 2){
						modelo.setRowCount(0);
						modelo.setColumnIdentifiers(tituloEventos);
						for(int i=0; i<eventosMujeres.size();i++){
							filas[0] = eventosMujeres.get(i);
							modelo.addRow(filas);
						}
					}
					
					if(combo.getSelectedIndex() == 3){
						modelo.setRowCount(0);
						modelo.setColumnIdentifiers(tituloDias);
						for(int i =0; i<dias.size();i++){
							filas[0] = dias.get(i);
							modelo.addRow(filas);
						}
					}
					
				}
			});
			pnlEventosCategor�a.add(new JScrollPane(tablaEventos));
			ventanas.addTab("Manejo de Asistencia", pnlAsis);
			ventanas.addTab("Eventos", pnlEventosCategor�a);
			add(ventanas);
		}
		public static void main(String[] args) {
			new VentanaReportes().setVisible(true);
		}
}
