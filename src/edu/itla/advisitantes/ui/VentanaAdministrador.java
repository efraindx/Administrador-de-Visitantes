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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.itla.advisitantes.datos.Dialogo;
import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.modelos.ModeloEventosActuales;
import edu.itla.advisitantes.modelos.ModeloInvitaciones;

public class VentanaAdministrador extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnManUsuarios, btnManEventos, btnManInvitados, btnManInvitaciones, btnReportes, btnSalir, btnVenPortero;
	private JLabel lblLogo, lblMantenimiento;
	private JPanel panelLogo;
	private JToolBar barra;
	
	public VentanaAdministrador(){
		
		super("Bienvenido Administrador");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(740,500);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/mantenimiento.png"));
		setResizable(false);
		setLocationRelativeTo(null);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
		
		btnManUsuarios = new JButton(" Man. de Usuarios", new ImageIcon("images/usuario.png"));
		btnManUsuarios.setFont(new Font("Lucida Calligraphy", 0,14));
		btnManEventos = new JButton(" Man. de Eventos",new ImageIcon("images/evento.png"));
		btnManEventos.setFont(new Font("Lucida Calligraphy", 0,14));
		btnManInvitados = new JButton("Man. de Invitados",new ImageIcon("images/invitado.png"));
		btnManInvitados.setFont(new Font("Lucida Calligraphy", 0,14));
		btnManInvitaciones = new JButton("Man. de Invitaciones",new ImageIcon("images/invitaciones.png"));
		btnManInvitaciones.setFont(new Font("Lucida Calligraphy", 0,14));
		btnReportes = new JButton("   Reportes",new ImageIcon("images/reporte.png"));
		btnReportes.setFont(new Font("Lucida Calligraphy", 0,14));
		btnVenPortero = new JButton("Ventana Portero", new ImageIcon("images/portero.png"));
		btnVenPortero.setFont(new Font("Lucida Calligraphy", 0,14));
		
		btnSalir = new JButton("Cerrar Sesión", new ImageIcon("images/iconoSalir.png"));
		btnSalir.setPreferredSize(new Dimension(160,40));
		btnSalir.setFont(new Font("Monotype Coursiva", Font.ITALIC, 12));
		btnSalir.setForeground(Color.magenta);
		
		btnManUsuarios.setToolTipText("Mantenimiento de Usuarios");
		btnManEventos.setToolTipText("Mantenimiento de Eventos");
		btnManInvitados.setToolTipText("Mantenimiento de Invitados");
		btnManInvitaciones.setToolTipText("Mantenimiento de Invitaciones");
		btnReportes.setToolTipText("Mantenimiento de Reportes");
		btnVenPortero.setToolTipText("Ventana Portero");
		
		btnVenPortero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAdministrador.this.dispose();
				try {
					new ModeloEventosActuales();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				try {
					new VentanaPortero();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnManUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						try {
							VentanaModeloUsuarios.getInstancia().setVisible(true);
						} catch (SQLException e) {
							e.printStackTrace();
						}
			}
		});
		
		btnManEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						VentanaModeloEventos.getInstancia().setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		
		btnManInvitados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						VentanaModeloInvitados.getInstancia().setVisible(true);
			}
		});
		
		btnManInvitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						ModeloInvitaciones.invitaciones = new ArrayList<>();
						Conexion.getInstancia().cargarComboboxEventos();
						Conexion.getInstancia().cargarComboboxInvitados();
						Conexion.getInstancia().cargarInvitaciones();
						new VentanaModeloInvitaciones().setVisible(true);
			}
		});
		
		btnReportes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JDialog dialogo = Dialogo.getInstancia();
				dialogo.setContentPane(new VentanaReportes().getContentPane());
				dialogo.setSize(new VentanaReportes().getSize());
				dialogo.setIconImage(Toolkit.getDefaultToolkit().getImage(("images/reporte.png")));
				dialogo.setLocationRelativeTo(null);
				dialogo.setTitle("Reportes");
				dialogo.setVisible(true);
			}
		});

		
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 VentanaAdministrador.this.dispose();
				 new VentanaLogin();
			}
		});
		
		JPanel pnlInferior = new JPanel(new FlowLayout());
		pnlInferior.add(btnSalir);

		lblLogo = new JLabel(new ImageIcon("images/imgegnUlloa.jpg"));
		
		lblMantenimiento =  new JLabel(new ImageIcon("images/mantenimiento.png"));
		panelLogo = new JPanel();
		panelLogo.add(lblLogo);
		add(panelLogo, BorderLayout.NORTH);
		add(lblMantenimiento, BorderLayout.CENTER);
		add(pnlInferior, BorderLayout.SOUTH);
		barra = new JToolBar();
		barra.setFloatable(true);
		barra.setPreferredSize(new Dimension(220,100));
		barra.setLayout(new GridLayout(6,1,2,2));
		barra.add(btnManUsuarios);
		barra.add(btnManEventos);
		barra.add(btnManInvitados);
		barra.add(btnManInvitaciones);
		barra.add(btnReportes);
		barra.add(btnVenPortero);
		add(barra, BorderLayout.EAST);
		setVisible(true);
	}
}
