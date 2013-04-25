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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import edu.itla.advisitantes.entidades.Usuario;
import edu.itla.advisitantes.modelos.ModeloUsuarios;

@SuppressWarnings("serial")
public class VentanaModeloUsuarios extends JFrame {

	private JLabel lblNombre, lblApellido, lblUsuario, lblContraseña, lblTipoDePerfil;
	private JTextField txtNombre, txtApellido, txtUsuario;
	private JPasswordField txtContraseña;
	private JButton btnAgregar, btnEliminar;
	private JRadioButton rbtAdministrador, rbtPortero;
	private JTable tablaDeUsuario;
	private JPanel panel, panel2;
	private ButtonGroup grupoDeRadio;
	private String perfil;
	private static VentanaModeloUsuarios instancia;
	
	public static synchronized VentanaModeloUsuarios getInstancia() throws SQLException{
				if(instancia == null){
						instancia = new VentanaModeloUsuarios();
				}
				return instancia;
	}
	
		private VentanaModeloUsuarios(){
		
		super("Mantenimiento de Usuarios");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(520,550);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/usuario.png"));
		setLocationRelativeTo(null);
		setResizable(false);
		
		tablaDeUsuario = new JTable(ModeloUsuarios.getInstancia());
		tablaDeUsuario.setPreferredScrollableViewportSize(new Dimension(480,250));
		
		lblNombre = new JLabel("Nombre:");
		lblApellido = new JLabel("Apellido:");
		lblUsuario = new JLabel("Usuario:");
		lblContraseña = new JLabel("Contraseña:");
		lblTipoDePerfil = new JLabel("Tipo de Perfil:");
		
		txtNombre = new JTextField(8);
		txtApellido = new JTextField(8);
		txtUsuario = new JTextField(8);
		txtContraseña = new JPasswordField(8);
		
		rbtAdministrador = new JRadioButton("Admin");
		rbtPortero = new JRadioButton("Portero");
		grupoDeRadio = new ButtonGroup();
		grupoDeRadio.add(rbtPortero);
		grupoDeRadio.add(rbtAdministrador);
		
		btnAgregar = new JButton("Agregar");
		btnEliminar = new JButton("Eliminar");
		
		lblNombre.setFont(new Font("Bodoni MT Black",0,16));
		lblApellido.setFont(new Font("Bodoni MT Black",0,16));
		lblUsuario.setFont(new Font("Bodoni MT Black",0,16));
		lblContraseña.setFont(new Font("Bodoni MT Black",0,16));
		lblTipoDePerfil.setFont(new Font("Bodoni MT Black",0,16));
		btnAgregar.setFont(new Font("Bodoni MT Black",0,16));
		btnAgregar.setForeground(Color.blue);
		btnEliminar.setFont(new Font("Bodoni MT Black",0,16));
		btnEliminar.setForeground(Color.red);
		rbtAdministrador.setFont(new Font("Bodoni MT Black",0,16));
		rbtPortero.setFont(new Font("Bodoni MT Black",0,16));
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String pass = new String(txtContraseña.getPassword());
				
				if(txtNombre.getText().equals("") || txtApellido.getText().equals("") || txtUsuario.getText().equals("") || pass.equals(null) || (rbtAdministrador.isSelected()== false && rbtPortero.isSelected()== false)){
					
					JOptionPane.showMessageDialog(VentanaModeloUsuarios.this, "Debe llenar todos los campos", "Error al agregar usuario",JOptionPane.ERROR_MESSAGE);
					
				}else{
					try {
						ModeloUsuarios.getInstancia().agregarUsuarios(new Usuario(txtNombre.getText(), txtApellido.getText(), txtUsuario.getText(), pass, perfil));
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}
				txtNombre.setText("");
				txtApellido.setText("");
				txtUsuario.setText("");
				txtContraseña.setText("");
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (tablaDeUsuario.getSelectedRow() != -1){
					try {
						int decision = JOptionPane.showConfirmDialog(VentanaModeloUsuarios.this, "Está seguro?", "Confirmación", JOptionPane.YES_NO_OPTION);
						if(decision == JOptionPane.YES_OPTION){
							ModeloUsuarios.getInstancia().eliminarUsuario(tablaDeUsuario.getSelectedRow());
						}
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(VentanaModeloUsuarios.this, "Debe selecionar una fila","ERROR al eliminar un usuario", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		rbtAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perfil = "Admin";
			}
		});
		rbtPortero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perfil = "Portero";
			}
		});
		
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,2,5,8));
		panel2.add(rbtAdministrador);
		panel2.add(rbtPortero);
		
		panel = new JPanel(new GridLayout(6,2,8,8));
		panel.setPreferredSize(new Dimension(385,230));
		panel.add(lblNombre);
		panel.add(txtNombre);
		panel.add(lblApellido);
		panel.add(txtApellido);
		panel.add(lblUsuario);
		panel.add(txtUsuario);
		panel.add(lblContraseña);
		panel.add(txtContraseña);
		panel.add(lblTipoDePerfil);
		panel.add(panel2);
		panel.add(btnAgregar);
		panel.add(btnEliminar);
		add(panel);
		add(new JScrollPane(tablaDeUsuario));
	}
}
