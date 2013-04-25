package edu.itla.advisitantes.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Invitado;

@SuppressWarnings("serial")
public class VentanaBuscarInvitado extends JFrame{

	private JTextField txtBuscador;
	private JButton btnBuscar;
	private static VentanaBuscarInvitado instancia;
	private JTable tablaBuscador;
	private DefaultTableModel tabla = new DefaultTableModel();
	private String titulos[] = {"Nombre","Apellido","Teléfono","Dirección","Sexo"};
	
	public static synchronized VentanaBuscarInvitado getInstancia(){
		if(instancia == null){
			instancia = new VentanaBuscarInvitado();
		}
		return instancia;
	}
	
	private VentanaBuscarInvitado(){
		
		super("Búsqueda de Visitantes");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(("images/iconoBuscar.png")));
		setSize(550,400);
		
		tablaBuscador = new JTable();
		tablaBuscador.setPreferredScrollableViewportSize(new Dimension(500,300));
		tabla.setColumnIdentifiers(titulos);
		tablaBuscador.setModel(tabla);
		
		txtBuscador = new JTextField(12);
		btnBuscar = new JButton("Buscar Visitante");
		
		txtBuscador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tabla.setRowCount(0);
				if(txtBuscador.getText().equals("")){
					JOptionPane.showMessageDialog(VentanaBuscarInvitado.this, "Debe insertar un nombre o apellido", "Error al tratar de buscar", JOptionPane.ERROR_MESSAGE);
				
				}else{
					boolean comprobar = false;
					for(int i=0; i<Conexion.getInstancia().mostrarVisitantes(VentanaPortero.eventoActual).size(); i++){
						
						Invitado user = Conexion.getInstancia().mostrarVisitantes(VentanaPortero.eventoActual).get(i);
						String fila[] = new String[5];
						if(txtBuscador.getText().equals(user.getNombre()) || txtBuscador.getText().equals(user.getApellido())){
							comprobar = true;
							fila[0] = user.getNombre();
							fila[1] = user.getApellido();
							fila[2] = user.getTelefono();
							fila[3] = user.getDireccion();
							fila[4] = user.getSexo();
							tabla.addRow(fila);
						}
					}
					if(comprobar == false){
						JOptionPane.showMessageDialog(VentanaBuscarInvitado.this, "No se encontró el nombre o apellido", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				txtBuscador.setText("");
				
				
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabla.setRowCount(0);
				if(txtBuscador.getText().equals("")){
					JOptionPane.showMessageDialog(VentanaBuscarInvitado.this, "Debe insertar un nombre o apellido", "Error al tratar de buscar", JOptionPane.ERROR_MESSAGE);
				
				}else{
					boolean comprobar = false;
					for(int i=0; i<Conexion.getInstancia().mostrarVisitantes(VentanaPortero.eventoActual).size(); i++){
						
						Invitado user = Conexion.getInstancia().mostrarVisitantes(VentanaPortero.eventoActual).get(i);
						String fila[] = new String[5];
						if(txtBuscador.getText().equals(user.getNombre()) || txtBuscador.getText().equals(user.getApellido())){
							comprobar = true;
							fila[0] = user.getNombre();
							fila[1] = user.getApellido();
							fila[2] = user.getTelefono();
							fila[3] = user.getDireccion();
							fila[4] = user.getSexo();
							tabla.addRow(fila);
						}
					}
					if(comprobar == false){
						JOptionPane.showMessageDialog(VentanaBuscarInvitado.this, "No se encontró el nombre o apellido", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				txtBuscador.setText("");
			}
		});
		
		JPanel pnlSuperior = new JPanel(new FlowLayout());
		JLabel lblPrimero = new JLabel("Búsqueda de Visitantes ", new ImageIcon("images/iconoBuscar.png"), getDefaultCloseOperation());
		lblPrimero.setForeground(Color.red);
		pnlSuperior.setPreferredSize(new Dimension(600,70));
		pnlSuperior.add(lblPrimero);
		JLabel lblTitulo = new JLabel("Inserte un nombre o apellido:");
		lblPrimero.setFont(new Font("Cooper Black", Font.ITALIC, 25));
		pnlSuperior.add(lblTitulo);
		add(pnlSuperior);
		add(lblTitulo);
		add(txtBuscador);
		add(btnBuscar);
		add(new JScrollPane(tablaBuscador));
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new VentanaBuscarInvitado();
	}
}
