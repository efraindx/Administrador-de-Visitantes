package edu.itla.advisitantes.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.itla.advisitantes.modelos.ModeloInvitadosPendientes;

public class VentanaInvitadosPendientes extends JFrame{
	
			/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
			public VentanaInvitadosPendientes(){
				super("Invitados Pendientes");
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				setSize(530,430);
				setResizable(false);
				setIconImage(Toolkit.getDefaultToolkit().getImage("images/eventos.png"));
				setLocationRelativeTo(null);
				setLayout(new FlowLayout());
				
				JLabel lblCentral = new JLabel("Listado de invitados que faltan por llegar ", new ImageIcon("images/gentes.png"),0);
				JPanel pnlCentral = new JPanel(new GridLayout(5,1));
				lblCentral.setFont(new Font("Matura MT Script Capitals", Font.ITALIC+Font.BOLD, 20));
				pnlCentral.add(lblCentral);
				add(lblCentral);
				
				JTable tablaPendientes = new JTable(new ModeloInvitadosPendientes());
				tablaPendientes.setPreferredScrollableViewportSize(new Dimension(480,300));
				add(new JScrollPane(tablaPendientes));
			}
			public static void main(String[] args) {
				new VentanaInvitadosPendientes().setVisible(true);
			}
}
