package edu.itla.advisitantes.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.itla.advisitantes.modelos.ModeloInvitaciones;

@SuppressWarnings("serial")
public class VentanaModeloInvitaciones extends JFrame{
	
			private JTable tablaInvitaciones;
			private static VentanaModeloInvitaciones instancia;
			public static JComboBox<Object> listaEventos = new JComboBox<Object>();
			public static JComboBox<Object> listaInvitados = new JComboBox<Object>();
			
			public static synchronized VentanaModeloInvitaciones getInstancia() throws ClassNotFoundException, SQLException{
					if(instancia == null){
						instancia = new VentanaModeloInvitaciones();
					}
					return instancia;
			}
		
			public VentanaModeloInvitaciones(){
			
			super("Mantenimiento de Invitaciones");
			
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(520,500);
			setIconImage(Toolkit.getDefaultToolkit().getImage(("images/invitaciones.png")));
			setResizable(false);
			setLayout(new FlowLayout());
			
			JPanel pnlDatos = new JPanel(new GridLayout(3,2,15,15));
			JLabel lblInvitado = new JLabel("Invitado:");
			lblInvitado.setPreferredSize(new Dimension(40,40));
			lblInvitado.setFont(new Font("Serif", Font.ITALIC+Font.BOLD, 20));
			listaInvitados.setMaximumRowCount(3);
			JLabel lblEvento = new JLabel("Evento:");
			lblEvento.setFont(new Font("Serif", Font.ITALIC+Font.BOLD, 20));
			listaEventos.setMaximumRowCount(3);
			JButton botonAgregar = new JButton("Agregar");
			botonAgregar.setForeground(Color.blue);
			botonAgregar.setFont(new Font("Serif", Font.ITALIC+Font.BOLD, 20));
			JButton botonEliminar = new JButton("Eliminar");
			botonEliminar.setForeground(Color.red);
			botonEliminar.setFont(new Font("Serif", Font.ITALIC+Font.BOLD, 20));
			pnlDatos.add(lblInvitado);
			pnlDatos.add(new JScrollPane(listaInvitados));
			pnlDatos.add(lblEvento);
			pnlDatos.add(new JScrollPane(listaEventos));
			pnlDatos.add(botonAgregar);
			try {
				tablaInvitaciones = new JTable(ModeloInvitaciones.getInstancia());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tablaInvitaciones.setPreferredScrollableViewportSize(new Dimension(480,280));
			
			botonAgregar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					String invitado = (String) listaInvitados.getSelectedItem();
					String evento = (String) listaEventos.getSelectedItem();
					try {
						ModeloInvitaciones.getInstancia().agregar(invitado, evento);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			
			botonEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(tablaInvitaciones.getSelectedRow() == -1){
						JOptionPane.showMessageDialog(VentanaModeloInvitaciones.this, "Debe seleccionar una fila", "Advertencia", JOptionPane.WARNING_MESSAGE);
					}
					else{
					int fila = tablaInvitaciones.getSelectedRow();
					try {
						int decision = JOptionPane.showConfirmDialog(VentanaModeloInvitaciones.this, "Está seguro?","Confirmación", JOptionPane.YES_NO_OPTION);
						if(decision == JOptionPane.YES_OPTION){
							ModeloInvitaciones.getInstancia().eliminar(fila);
						}
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				}
			});
			
			pnlDatos.add(botonEliminar);
			add(pnlDatos);
			add(new JScrollPane(tablaInvitaciones));
			setLocationRelativeTo(null);
		}
}
