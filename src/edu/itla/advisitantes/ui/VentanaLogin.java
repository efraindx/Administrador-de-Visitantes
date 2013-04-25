package edu.itla.advisitantes.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import edu.itla.advisitantes.entidades.Usuario;
import edu.itla.advisitantes.modelos.ModeloEventosActuales;
import edu.itla.advisitantes.modelos.ModeloUsuarios;

public class VentanaLogin  extends JFrame{
	
	private static final long serialVersionUID = 1L;
			private JPasswordField campoContrase;
			private JTextField campoUsuario;
			private JButton botonAceptar;
			public static Usuario usuarioActual;
			
		public VentanaLogin(){ 
		
			super("Iniciar Sesión");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(500,300);
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				SwingUtilities.updateComponentTreeUI(this);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			setLayout(new FlowLayout());
			setLocationRelativeTo(null);
			JPanel panelCentral = new JPanel(new GridLayout(2,2));
			setIconImage(Toolkit.getDefaultToolkit().getImage("images/fotoVEntana.jpg"));
			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setFont(new Font("Elephant", Font.ITALIC, 20));
			ImageIcon icono = new ImageIcon("images/imagen.jpg");
			JLabel lblIcono = new JLabel(icono);
			setResizable(false);
			
			JPanel panelImage = new JPanel(new FlowLayout());
			panelImage.add(lblIcono);
			
			botonAceptar = new JButton("Aceptar");
			botonAceptar.setFont(new Font("Elephant",Font.ITALIC, 14));
			campoUsuario  = new JTextField(10);
			JLabel lblContrase = new JLabel("Contraseña:");
			lblContrase.setFont(new Font("Elephant", Font.ITALIC, 20));
			campoContrase = new JPasswordField(10);
			
			JMenuBar barraOpciones = new JMenuBar();
			JMenu menuArchivo = new JMenu("Archivo");
			menuArchivo.setPreferredSize(new Dimension(70,20));
			JMenuItem itemArchivo = new JMenuItem("Salir");
			JMenu menuPregunta = new JMenu("?");
			JMenuItem itemPregunta = new JMenuItem("Ayuda");
			itemArchivo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			
			itemPregunta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JDialog dialogo = new JDialog(VentanaLogin.this, true);
					dialogo.setSize(255,240);
					JTextArea texto = new JTextArea("                          Control Soft\nEste es un sistema cuya funcionalidad\n es llevar el control de las personas que\n " +
							"asisten a eventos, este sistema permite\n crear un listado de invitados, el cual es\n consultado por un portero cada vez que\n llegue una persona, puede éste buscar\n a los invitados por nombre o por apellido,\n una vez finalizado un evento, se puede\n extraer información estadística acerca de\n la asistencia a un evento.\nAsi que Inicia Sesión!");
					texto.setEditable(false);
					texto.setBackground(Color.gray);
					dialogo.setLayout(new BorderLayout());
					dialogo.getContentPane().add(texto, BorderLayout.CENTER);
					dialogo.setLocationRelativeTo(null);
					dialogo.setTitle("Información");
					dialogo.setVisible(true);
				}
			});
		
			menuArchivo.add(itemArchivo);
			menuPregunta.add(itemPregunta);
			barraOpciones.add(menuArchivo);
			barraOpciones.add(menuPregunta);
			barraOpciones.setBackground(Color.black);
			
			campoUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String nombreUsuario = campoUsuario.getText();
					@SuppressWarnings("deprecation")
					String contraseUsuario = campoContrase.getText();
					 Usuario user = ModeloUsuarios.getInstancia().encontrarUsuario(nombreUsuario, contraseUsuario);

					if(user == null){
					JOptionPane.showMessageDialog(VentanaLogin.this, "Usuario ó Contraseña incorrecta", "Advertencia", JOptionPane.WARNING_MESSAGE);
					campoContrase.setText("");
					}
					else{
					if(user.getPerfil().equals("Admin")){
						usuarioActual = user;
						VentanaLogin.this.dispose();
						try {
							new VentanaAdministrador().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else{
						usuarioActual = user;
						VentanaLogin.this.dispose();
						try {
							new VentanaPortero();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					}
				}
					
			});
			
			
			campoContrase.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String nombreUsuario = campoUsuario.getText();
						@SuppressWarnings("deprecation")
						String contraseUsuario = campoContrase.getText();
						Usuario user;
							user = ModeloUsuarios.getInstancia().encontrarUsuario(nombreUsuario, contraseUsuario);
						
						
						if(user == null){
							JOptionPane.showMessageDialog(VentanaLogin.this, "Usuario ó Contraseña incorrecta", "Advertencia", JOptionPane.WARNING_MESSAGE);
							campoContrase.setText("");
						}
						else{
							if(user.getPerfil().equals("Admin")){
								usuarioActual = user;
								VentanaLogin.this.dispose();
								try {
									new VentanaAdministrador().setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							else{
								usuarioActual = user;
								
								VentanaLogin.this.dispose();
								try {
									new ModeloEventosActuales();
								} catch (ClassNotFoundException | SQLException e1) {
									e1.printStackTrace();
								}
									new VentanaPortero();
							}
					}
				}
					
					
			});
			
			
			panelCentral.add(lblUsuario);
			panelCentral.add(campoUsuario);
			panelCentral.add(lblContrase);
			panelCentral.add(campoContrase);
			
			add(panelImage);
			add(panelCentral);
			
			botonAceptar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					String nombreUsuario = campoUsuario.getText();
						@SuppressWarnings("deprecation")
						String contraseUsuario = campoContrase.getText();
						Usuario user;
						user = ModeloUsuarios.getInstancia().encontrarUsuario(nombreUsuario, contraseUsuario);

						if(user == null){
						JOptionPane.showMessageDialog(VentanaLogin.this, "Usuario ó Contraseña incorrecta", "Advertencia", JOptionPane.WARNING_MESSAGE);
						campoContrase.setText("");
						}
						else{
						if(user.getPerfil().equals("Admin")){
							usuarioActual = user;
							VentanaLogin.this.dispose();
							try {
								new VentanaAdministrador().setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						else{
							usuarioActual = user;
							VentanaLogin.this.dispose();
							try {
								new VentanaPortero();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						}
				}
				
			});
		
			botonAceptar.addKeyListener(new KeyListener(){
				
					@SuppressWarnings("deprecation")
					public void keyPressed(KeyEvent ev) {
					
						String nombreUsuario = campoUsuario.getText();
						String contraseUsuario = campoContrase.getText();
						Usuario user;
						user = ModeloUsuarios.getInstancia().encontrarUsuario(nombreUsuario, contraseUsuario);

						if(user == null){
						JOptionPane.showMessageDialog(VentanaLogin.this, "Usuario ó Contraseña incorrecta", "Advertencia", JOptionPane.WARNING_MESSAGE);
						campoContrase.setText("");
						}
						else{
						if(user.getPerfil().equals("Admin")){
							usuarioActual = user;
							VentanaLogin.this.dispose();
							try {
								new VentanaAdministrador().setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						else{
							usuarioActual = user;
							VentanaLogin.this.dispose();
									new VentanaPortero();
							
						}
							}
					
					}

					public void keyReleased(KeyEvent arg0) {
						// 
					}

					public void keyTyped(KeyEvent arg0) {
						// 
						
					}
					
			});
			
			botonAceptar.setForeground(Color.blue);
			
			JButton botonCancelar = new JButton("Cancelar");
			botonCancelar.setFont(new Font("Elephant", Font.ITALIC, 14));
			botonCancelar.setForeground(Color.red);
			
			botonCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int seleccion = JOptionPane.showConfirmDialog(VentanaLogin.this, "Está seguro?");
					if(seleccion == JOptionPane.YES_OPTION){
					System.exit(0);
					}
				}
			});
			
			JPanel panelInferior = new JPanel(new FlowLayout());
			panelInferior.setPreferredSize(new Dimension(800,800));
			panelInferior.add(botonAceptar);
			panelInferior.add(botonCancelar);
			add(panelInferior);
			setJMenuBar(barraOpciones);
			setVisible(true);
		}
	}
