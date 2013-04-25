package edu.itla.advisitantes.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Invitado;

@SuppressWarnings("serial")
public class VentanaVisitantes  extends JFrame{

	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tablaVisitantes;
	private String titulos[] = {"Nombre","Apellido","Teléfono","Dirección","Sexo"};
	private static VentanaVisitantes instancia;
	
	public static synchronized VentanaVisitantes getInstancia(){
				if(instancia == null){
					instancia = new VentanaVisitantes();
				}
				return instancia;
	}
	
	private VentanaVisitantes(){
		
		super("Visitantes");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		setIconImage(Toolkit.getDefaultToolkit().getImage(("images/invitado.png")));
		setResizable(false);
		
		setSize(550,400);
		
		tablaVisitantes = new JTable();
		tablaVisitantes.setPreferredScrollableViewportSize(new Dimension(500,250));
		modelo.setColumnIdentifiers(titulos);
		tablaVisitantes.setModel(modelo);
		
		for(int i=0; i<Conexion.getInstancia().mostrarVisitantes(VentanaPortero.eventoActual).size(); i++){
			
			Invitado invitado = Conexion.getInstancia().mostrarVisitantes(VentanaPortero.eventoActual).get(i);
			String filas[] = new String[5];
			filas[0] = invitado.getNombre();
			filas[1] = invitado.getApellido();
			filas[2] = invitado.getTelefono();
			filas[3] = invitado.getDireccion();
			filas[4] = invitado.getSexo();
			modelo.addRow(filas);
		}
		
		JPanel pnlSuperior = new JPanel(new FlowLayout());
		JLabel lblTitulo = new JLabel("Listado de Invitados de: "+VentanaPortero.eventoActual+" ", new ImageIcon("images/invitado.png"), getDefaultCloseOperation());
		lblTitulo.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 25));
		lblTitulo.setForeground(Color.blue);
		pnlSuperior.setPreferredSize(new Dimension(600,70));
		pnlSuperior.add(lblTitulo);
		add(pnlSuperior);
		add(new JScrollPane(tablaVisitantes));
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new VentanaVisitantes().setVisible(true);
	}
}
