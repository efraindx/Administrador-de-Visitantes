package edu.itla.advisitantes.ui;

import edu.itla.advisitantes.modelos.ModeloInvitados;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import edu.itla.advisitantes.entidades.Invitado;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class VentanaModeloInvitados extends JFrame{

	private JLabel lblNombre, lblApellido, lblTelefono, lblDireccion, lblSexo;
	private JTextField txtNombre, txtApellido, txtTelefono, txtDireccion;
	private JButton btnAgregar, btnEliminar;
	private JRadioButton rbtMasculino, rbtFemenino;
	private ButtonGroup grupoDeRadios;
	public static JTable tablaDeInvitados;
	private JPanel panel, panel2;
	private String sexo;
	private static VentanaModeloInvitados instancia;
	
	public static synchronized VentanaModeloInvitados getInstancia(){
				if(instancia == null){
						instancia = new VentanaModeloInvitados();
				}
				return instancia;
	}
	
		private VentanaModeloInvitados(){
		
		super("Mantenimiento de Invitado");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(520,565);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(("images/invitado.png")));
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		lblNombre = new JLabel("Nombre:");
		lblApellido = new JLabel("Apellido:");
		lblTelefono = new JLabel("Teléfono:");
		lblDireccion = new JLabel("Dirección:");
		lblSexo = new JLabel("Sexo:");
		
		txtNombre = new JTextField(8);
		txtApellido = new JTextField(8);
		txtTelefono = new JTextField(8);
		txtDireccion = new JTextField(8);
		
		btnAgregar = new JButton("Agregar");
		btnEliminar = new JButton("Eliminar");
		
		grupoDeRadios = new ButtonGroup();
		rbtMasculino = new JRadioButton("Hombre");
		rbtFemenino = new JRadioButton("Mujer");
		grupoDeRadios.add(rbtMasculino);
		grupoDeRadios.add(rbtFemenino);
		
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,2,8,8));
		panel2.add(rbtMasculino);
		panel2.add(rbtFemenino);
		
		lblNombre.setFont(new Font("Algerian",0,16));
		lblApellido.setFont(new Font("Algerian",0,16));
		lblTelefono.setFont(new Font("Algerian",0,16));
		lblDireccion.setFont(new Font("Algerian",0,16));
		lblSexo.setFont(new Font("Algerian",0,16));
		btnAgregar.setFont(new Font("Algerian",0,16));
		btnAgregar.setForeground(Color.blue);
		btnEliminar.setFont(new Font("Algerian",0,16));
		btnEliminar.setForeground(Color.red);
		rbtFemenino.setFont(new Font("Algerian",0,16));
		rbtMasculino.setFont(new Font("Algerian",0,16));
		
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtNombre.getText().equals("") || txtApellido.getText().equals("") || txtTelefono.getText().equals("") || txtDireccion.getText().equals("") || (rbtFemenino.isSelected()==false && rbtMasculino.isSelected()==false)){
					
					JOptionPane.showMessageDialog(VentanaModeloInvitados.this, "No puede dejar campos vacíos","Error al agregar invitado", JOptionPane.ERROR_MESSAGE);
				}else{
					try {
						ModeloInvitados.getInstancia().agregarInvitado(new Invitado(txtNombre.getText(), txtApellido.getText(), txtTelefono.getText(), txtDireccion.getText(), sexo));
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}
				txtNombre.setText("");
				txtApellido.setText("");
				txtTelefono.setText("");
				txtDireccion.setText("");
				
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tablaDeInvitados.getSelectedRow() != -1){
					try {
						int decision = JOptionPane.showConfirmDialog(VentanaModeloInvitados.this, "Está seguro?", "Confirmación", JOptionPane.YES_NO_OPTION);
						if(decision == JOptionPane.YES_OPTION){
							ModeloInvitados.getInstancia().eliminarInvitado(tablaDeInvitados.getSelectedRow());
						}
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(VentanaModeloInvitados.this, "Debe selecionar una fila","Error al tratar de borrar una fila",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		tablaDeInvitados = new JTable(ModeloInvitados.getInstancia());
		tablaDeInvitados.setPreferredScrollableViewportSize(new Dimension(480,250));
		
		
		rbtFemenino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sexo = "Mujer";
			}
		});
		rbtMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sexo = "Hombre";
			}
		});
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(6,2,8,8));
		panel.setPreferredSize(new Dimension(385,230));
		panel.add(lblNombre);
		panel.add(txtNombre);
		panel.add(lblApellido);
		panel.add(txtApellido);
		panel.add(lblTelefono);
		panel.add(txtTelefono);
		panel.add(lblDireccion);
		panel.add(txtDireccion);
		panel.add(lblSexo);
		panel.add(panel2);
		panel.add(btnAgregar);
		panel.add(btnEliminar);
		add(panel);
		add(new JScrollPane(tablaDeInvitados));
	}
}
