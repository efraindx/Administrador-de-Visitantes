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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.itla.advisitantes.db.Conexion;
import edu.itla.advisitantes.entidades.Invitacion;
import edu.itla.advisitantes.modelos.ModeloRegistroImprevisto;
import edu.itla.advisitantes.modelos.ModeloRegistroVisitas;

public class VentanaRegistrosInvitaciones extends JFrame{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JPanel panel;
		private JTable tablaRegistroImprevisto;
		private JLabel lblNombre, lblApellido, lblMotivo;
		private JTextField txtNombre, txtApellido;
		private JTextArea txtMotivo;
		private JButton btnAgregar;
		public static JTable tablaRegistros;
		private static VentanaRegistrosInvitaciones instancia;
		
		public static synchronized VentanaRegistrosInvitaciones getInstancia(){
				if(instancia == null){
					instancia = new VentanaRegistrosInvitaciones();
				}
				return instancia;
		}
		
			private VentanaRegistrosInvitaciones(){
				super("Registro Visitas");
				setResizable(false);
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				setSize(520,520);
				setIconImage(Toolkit.getDefaultToolkit().getImage("images/reporte.png"));
				setLocationRelativeTo(null);
				
				btnAgregar = new JButton("Agregar");
				btnAgregar.setForeground(Color.blue);
				
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(txtNombre.getText().equals("") || txtApellido.getText().equals("") || txtMotivo.getText().equals("")){
							
							JOptionPane.showMessageDialog(VentanaRegistrosInvitaciones.this, "Debe llenar todos los campos", "Error al agregar un registro", JOptionPane.ERROR_MESSAGE);
						}else{
							
							try {
								ModeloRegistroImprevisto.getInstancia().agregarRegistro(txtNombre.getText(), txtApellido.getText(), VentanaLogin.usuarioActual.getUsuario(), txtMotivo.getText());
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						txtNombre.setText("");
						txtApellido.setText("");
						txtMotivo.setText("");
					}
				});
				
				JLabel lblCentral = new JLabel("Registro Invitaciones Normales", new ImageIcon("images/reporte.png"), 0);
				lblCentral.setPreferredSize(new Dimension(500,40));
				lblCentral.setFont(new Font("Bernard MT Condensed", Font.BOLD, 22));
				lblCentral.setForeground(Color.black);
				JPanel pnlInicial = new JPanel();
				
				final JRadioButton radioPresente = new JRadioButton("Presente");
				final JRadioButton radioAusente = new JRadioButton("Ausente");
				radioPresente.setFont(new Font("Bodoni MT Black", Font.ITALIC, 16));
				radioAusente.setFont(new Font("Bodoni MT Black", Font.ITALIC, 16));
				pnlInicial.add(lblCentral);
				pnlInicial.add(radioPresente);
				pnlInicial.add(radioAusente);
				
				radioPresente.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(tablaRegistros.getSelectedRow() != -1){
							Invitacion invitacionActual = ModeloRegistroVisitas.invitacionesActuales.get(tablaRegistros.getSelectedRow());
							if(!invitacionActual.getAsistencia().equals("Presente")){
								invitacionActual.setAsistencia("Presente");
								Conexion.getInstancia().modificarInvitacionesActuales(invitacionActual.getId(), "Presente");
								tablaRegistros.updateUI();
							}
							else{
								JOptionPane.showMessageDialog(VentanaRegistrosInvitaciones.this, "El invitado ya está presente", "Indicación", JOptionPane.WARNING_MESSAGE);
							}
						}
						else{
							JOptionPane.showMessageDialog(VentanaRegistrosInvitaciones.this, "Debe seleccionar un invitado", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						radioPresente.setSelected(false);
					}
				});
				
				radioAusente.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(tablaRegistros.getSelectedRow() != -1){
							Invitacion invitacionActual = ModeloRegistroVisitas.invitacionesActuales.get(tablaRegistros.getSelectedRow());
							if(!invitacionActual.getAsistencia().equals("Ausente")){
								invitacionActual.setAsistencia("Ausente");
								Conexion.getInstancia().modificarInvitacionesActuales(invitacionActual.getId(), "Ausente");
								tablaRegistros.updateUI();
							}
							else{
								JOptionPane.showMessageDialog(VentanaRegistrosInvitaciones.this, "El invitado ya está ausente", "Indicación", JOptionPane.WARNING_MESSAGE);
							}
						}
						else{
							JOptionPane.showMessageDialog(VentanaRegistrosInvitaciones.this, "Debe seleccionar un invitado", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						radioAusente.setSelected(false);
					}
				
				});
				
				tablaRegistros = new JTable(ModeloRegistroVisitas.getInstancia());
				tablaRegistros.setPreferredScrollableViewportSize(new Dimension(480,280));
				pnlInicial.add(new JScrollPane(tablaRegistros));
				JTabbedPane tabla = new JTabbedPane();
				tabla.addTab("Visitas", pnlInicial);
				
				lblNombre = new JLabel("Nombre:");
				lblApellido = new JLabel("Apellido:");
				lblMotivo = new JLabel("Motivo:");
				
				txtNombre = new JTextField(10);
				txtApellido = new JTextField(12);
				txtMotivo = new JTextArea(5,15);
				txtMotivo.setToolTipText("Ingrese el motivo");
			
				panel = new JPanel(new GridLayout(2,2,5,5));
				panel.add(lblNombre);
				panel.add(txtNombre);
				panel.add(lblApellido);
				panel.add(txtApellido);
				
				JPanel asd = new JPanel();
				asd.add(lblMotivo);
				asd.add(new JScrollPane(txtMotivo));
				
				tablaRegistroImprevisto = new JTable(ModeloRegistroImprevisto.getInstancia());
				tablaRegistroImprevisto.setPreferredScrollableViewportSize(new Dimension(460,150));
				JPanel pnlVisiImpre = new JPanel(new FlowLayout());
				JLabel lblInicial = new JLabel("Registro Invitaciones Imprevistas", new ImageIcon("images/reporte.png"), 0);
				lblInicial.setFont(new Font("Bernard MT Condensed", Font.BOLD, 22));
				pnlVisiImpre.add(lblInicial);
				pnlVisiImpre.add(panel);
				pnlVisiImpre.add(asd);
				pnlVisiImpre.setPreferredSize(new Dimension(480,50));
				
				pnlVisiImpre.add(new JScrollPane(tablaRegistroImprevisto));
				pnlVisiImpre.add(btnAgregar);
				tabla.addTab("Visitas Imprevistas", pnlVisiImpre);
				
				add(tabla);
			}
}
