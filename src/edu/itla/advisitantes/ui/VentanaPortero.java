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
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.itla.advisitantes.datos.Dialogo;
import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.modelos.ModeloEventosActuales;


public class VentanaPortero extends JFrame{
	
				private static final long serialVersionUID = 1L;
				public static String eventoActual;
				public static JLabel lblEvento;
				public static JDialog dialogo;
				private JTable tablaEventos;
				private JButton botonCambiar;
				
		public VentanaPortero(){
			super("Portero ControlSoft");
			setSize(740,500);
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			setResizable(false);
		
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e2) {
				e2.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(this);
			
			setIconImage(Toolkit.getDefaultToolkit().getImage("images/iconoP.gif"));
			JLabel lblIcon = new JLabel(new ImageIcon("images/portero.jpg"));
			JLabel lblIcon3 = new JLabel(new ImageIcon("images/iconoP.gif"));
			
			JPanel panelSuperior = new JPanel();
			panelSuperior.add(lblIcon);
			
			JButton btnBuscarVisitantes = new JButton("Buscar Visitantes", new ImageIcon("images/iconoBuscar.png"));
			btnBuscarVisitantes.setToolTipText("Buscar Visitantes");
			btnBuscarVisitantes.setFont(new Font("Lucida Calligraphy", 0,14));
			JButton btnVisitantes = new JButton("    Visitantes", new ImageIcon("images/invitado.png"));
			btnVisitantes.setToolTipText("Mostrar Visitantes");
			btnVisitantes.setFont(new Font("Lucida Calligraphy", 0,14));
			JButton btnVisitas = new JButton("  Registrar Visitas", new ImageIcon("images/reporte.png"));
			btnVisitas.setToolTipText("Registrar Visitas");
			btnVisitas.setFont(new Font("Lucida Calligraphy", 0,14));
			JButton btnEventos = new JButton(" Eventos Próximos", new ImageIcon("images/evento.png"));
			btnEventos.setToolTipText("Eventos Próximos");
			btnEventos.setFont(new Font("Lucida Calligraphy", 0,14));
			JButton btnVisiPendientes = new JButton(" Visitantes Pendientes", new ImageIcon("images/gentes.png"));
			btnVisiPendientes.setToolTipText("Visitantes Pendientes");setFont(new Font("Lucida Calligraphy", 0,14));
			btnVisiPendientes.setFont(new Font("Lucida Calligraphy", 0,14));
			
			btnBuscarVisitantes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog dialogo = Dialogo.getInstancia();
					dialogo.setContentPane(new VentanaBuscarInvitados().getContentPane());
					dialogo.setSize(new VentanaBuscarInvitados().getSize());
					dialogo.setIconImage(Toolkit.getDefaultToolkit().getImage(("images/iconoBuscar.png")));
					dialogo.setLocationRelativeTo(null);
					dialogo.setTitle("Búsqueda de Visitantes");
					dialogo.setVisible(true);
				}
			});
			
			btnVisitantes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaVisitantes.getInstancia().setVisible(true);
				}
			});
			
			btnVisitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						try {
							Conexion.getInstancia().cargarInvitacionesActuales(eventoActual);
							VentanaRegistrosInvitaciones.getInstancia().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			});
			
			btnEventos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JDialog dialogo = Dialogo.getInstancia();
					dialogo.setContentPane(new VentanaEventosProximos().getContentPane());
					dialogo.setSize(new VentanaEventosProximos().getSize());
					dialogo.setLocationRelativeTo(null);
					dialogo.setIconImage(Toolkit.getDefaultToolkit().getImage("images/evento.png"));
					dialogo.setVisible(true);
				}
			});
			
			btnVisiPendientes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JDialog dialogo = Dialogo.getInstancia();
					dialogo.setContentPane(new VentanaInvitadosPendientes().getContentPane());
					dialogo.setSize(new VentanaInvitadosPendientes().getSize());
					dialogo.setLocationRelativeTo(null);
					dialogo.setVisible(true);
				}
			});
		
			
			JToolBar barra = new JToolBar();
			barra.setLayout(new GridLayout(6,1,2,2));
			barra.setPreferredSize(new Dimension(230,150));
			barra.add(btnBuscarVisitantes);
			barra.add(btnVisitantes);
			barra.add(btnVisitas);
			barra.add(btnEventos);
			barra.add(btnVisiPendientes);
			
			if(VentanaLogin.usuarioActual.getPerfil().equals("Admin")){
				botonCambiar = new JButton("Volver", new ImageIcon("images/back.png"));
				botonCambiar.setToolTipText("Regresar");
				botonCambiar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						VentanaPortero.this.dispose();
						try {
							new VentanaAdministrador();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			else{
				botonCambiar = new JButton("Cerrar Sesión", new ImageIcon("images/iconoSalir.png"));
				botonCambiar.setToolTipText("Salir");
				botonCambiar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						VentanaPortero.this.dispose();
						new VentanaLogin();
					}
				});
			}
			
			botonCambiar.setFont(new Font("Monotype Coursiva", Font.ITALIC, 12));
			botonCambiar.setForeground(Color.magenta);
			barra.add(botonCambiar);
			add(BorderLayout.NORTH, panelSuperior);
			add(barra, BorderLayout.EAST);
			add(lblIcon3, BorderLayout.CENTER);
			lblEvento = new JLabel("                           Evento Actual: ");
			lblEvento.setFont(new Font("Monotype Corsiva", Font.BOLD+Font.ITALIC, 22));
		
			add(lblEvento, BorderLayout.SOUTH);
			setLocationRelativeTo(null);
			setVisible(true);
			
			JPanel pnlSuperior = new JPanel(new GridLayout(2,1));
			JLabel lblMostrar = new JLabel(new ImageIcon("images/imagenModelo.jpg"));
			JLabel lblO = new JLabel("               Eliga el evento y luego pulse \"Seleccionar\"");
			lblO.setFont(new Font("Serif", Font.ITALIC+Font.BOLD, 20));
			pnlSuperior.add(lblMostrar);
			pnlSuperior.add(lblO);
			JButton botonSeleccionar = new JButton("Seleccionar");
			botonSeleccionar.setPreferredSize(new Dimension(130,30));
			
			botonSeleccionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						if(tablaEventos.getSelectedRow() != -1){
							eventoActual = ModeloEventosActuales.eventosActuales.get(tablaEventos.getSelectedRow()).getNombre();
							lblEvento.setText("                           Evento Actual: "+eventoActual);
							VentanaPortero.dialogo.dispose();
						}
						else{
							JOptionPane.showMessageDialog(VentanaPortero.this, "Debe seleccionar un evento", "Advertencia", JOptionPane.WARNING_MESSAGE);
						}
				}
			});
			
			if(!ModeloEventosActuales.eventosActuales.isEmpty()){
				
				dialogo = new JDialog(VentanaPortero.this,true);
				dialogo.setSize(new Dimension(550,520));
				dialogo.setLayout(new FlowLayout());
				dialogo.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				try {
					tablaEventos = new JTable(new ModeloEventosActuales());
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				dialogo.setLocationRelativeTo(null);
				dialogo.getContentPane().add(pnlSuperior);
				dialogo.getContentPane().add(botonSeleccionar);
				dialogo.getContentPane().add(new JScrollPane(tablaEventos));
				dialogo.setVisible(true);
			}
		}
}
