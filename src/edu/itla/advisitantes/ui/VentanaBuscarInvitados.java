package edu.itla.advisitantes.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Invitado;

@SuppressWarnings("serial")
public class VentanaBuscarInvitados extends JFrame{

	private JTextField txtBuscador;
	private JTable tablaBuscador;
	private DefaultTableModel tabla = new DefaultTableModel();
	private String titulos[] = {"Nombre","Apellido","Teléfono","Dirección","Sexo"};
	
	public VentanaBuscarInvitados(){
		
		
		setLayout(new FlowLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(550,400);
		
		tablaBuscador = new JTable();
		tablaBuscador.setPreferredScrollableViewportSize(new Dimension(500,200));
		tabla.setColumnIdentifiers(titulos);
		tablaBuscador.setModel(tabla);
		
		txtBuscador = new JTextField(12);
		
		txtBuscador.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
					tabla.setRowCount(0);
					for(int i=0; i<Conexion.getInstancia().mostrarVisitantes(VentanaPortero.eventoActual, txtBuscador.getText()).size(); i++){
						Invitado user = Conexion.getInstancia().mostrarVisitantes(VentanaPortero.eventoActual, txtBuscador.getText()).get(i);
						String fila[] = new String[5];
						fila[0] = user.getNombre();
						fila[1] = user.getApellido();
						fila[2] = user.getTelefono();
						fila[3] = user.getDireccion();
						fila[4] = user.getSexo();
						tabla.addRow(fila);
					}
				}
				@Override
				public void keyReleased(KeyEvent arg0) {
					if(txtBuscador.getText().equals("")){
						tabla.setRowCount(0);
					}
				}
				@Override
				public void keyPressed(KeyEvent arg0) {
				}
			});
		
		JPanel pnlSuperior = new JPanel(new FlowLayout());
		JLabel lblPrimero = new JLabel("Búsqueda de Visitantes ", new ImageIcon("images/iconoBuscar.png"), getDefaultCloseOperation());
		lblPrimero.setForeground(Color.red);
		pnlSuperior.setPreferredSize(new Dimension(600,70));
		pnlSuperior.add(lblPrimero);
		JLabel lblTitulo = new JLabel("Inserte un nombre o apellido: ");
		lblTitulo.setFont(new Font("Times New Roman", Font.ITALIC+Font.BOLD, 18));
		lblPrimero.setFont(new Font("Cooper Black", Font.ITALIC, 25));
		pnlSuperior.add(lblTitulo);
		add(pnlSuperior);
		add(lblTitulo);
		add(txtBuscador);
		add(new JScrollPane(tablaBuscador));
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new VentanaBuscarInvitados().setVisible(true);
	}
}
