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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import edu.itla.advisitantes.entidades.Evento;
import edu.itla.advisitantes.modelos.ModeloEventos;

@SuppressWarnings("serial")
public class VentanaModeloEventos extends JFrame {

	private JLabel lblNombre, lblFecha, lblUbicacion;
	private JTextField txtNombre, txtUbicacion;
	private JComboBox<String> cmbDia, cmbMes, cmbAño;
	private JTable tablaDeEventos;
	private JButton btnAgregar, btnEliminar;
	private JPanel panel, panelComboBox;
	private static VentanaModeloEventos instancia;
	
	public static synchronized VentanaModeloEventos getInstancia(){
			if(instancia == null){
				instancia = new VentanaModeloEventos();
			}
			return instancia;
	}

	private VentanaModeloEventos() {

		super("Mantenimiento de Eventos");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(("images/evento.png")));
		setSize(512, 528);
		setLocationRelativeTo(null);

		lblNombre = new JLabel("Nombre:");
		lblFecha = new JLabel("Fecha:");
		lblUbicacion = new JLabel("Ubicación:");

		txtNombre = new JTextField(12);
		txtUbicacion = new JTextField(12);
		btnAgregar = new JButton("Agregar");
		btnEliminar = new JButton("Eliminar");

		cmbDia = new JComboBox<String>();
		cmbMes = new JComboBox<String>();
		cmbAño = new JComboBox<String>();

		String dia = "Dia";
		cmbDia.addItem(dia);
		for (int i = 1; i <= 31; i++) {
			dia = Integer.toString(i);
			cmbDia.addItem(dia);
		}
		
		String mes = "Mes";
		cmbMes.addItem(mes);
		for (int i = 1; i <= 12; i++) {
			mes = Integer.toString(i);
			cmbMes.addItem(mes);
		}

		String año = "Año";
		cmbAño.addItem(año);
		for (int i = 2012; i <= 2030; i++) {
			año = Integer.toString(i);
			cmbAño.addItem(año);
		}

		panelComboBox = new JPanel();
		panelComboBox.setLayout(new GridLayout(1, 3, 8, 8));
		panelComboBox.add(cmbAño);
		panelComboBox.add(cmbMes);
		panelComboBox.add(cmbDia);

		txtNombre.setToolTipText("Ingrese el Nombre del Evento");
		txtUbicacion.setToolTipText("Ingrese la Ubicacion del evento");
		cmbDia.setToolTipText("Selecione el Dia");
		cmbMes.setToolTipText("Selecione el Mes");
		cmbAño.setToolTipText("Selecione el Año");
		btnAgregar.setToolTipText("Agregar Evento");
		btnEliminar.setToolTipText("Eliminar Evento");
		
		lblNombre.setFont(new Font("Lucida Calligraphy", Font.BOLD, 14));
		lblFecha.setFont(new Font("Lucida Calligraphy", Font.BOLD, 14));
		lblUbicacion.setFont(new Font("Lucida Calligraphy", Font.BOLD, 14));
		cmbDia.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
		cmbMes.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
		cmbAño.setFont(new Font("Lucida Calligraphy", Font.BOLD, 11));
		btnAgregar.setFont(new Font("Lucida Calligraphy", Font.BOLD, 14));
		btnAgregar.setForeground(Color.blue);
		btnEliminar.setFont(new Font("Lucida Calligraphy", Font.BOLD, 14));
		btnEliminar.setForeground(Color.red);

		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtNombre.getText().equals("")
						|| txtUbicacion.getText().equals("")
						|| cmbDia.getSelectedItem().toString().equals("Dia")
						|| cmbMes.getSelectedItem().toString().equals("Mes")
						|| cmbAño.getSelectedItem().toString().equals("Año")) {

					JOptionPane.showMessageDialog(VentanaModeloEventos.this,"Debe llenar los texto, y selecionar la fecha","Error al agregar evento",JOptionPane.ERROR_MESSAGE);
				}else{
					String srtFecha = cmbAño.getSelectedItem().toString()+"-"+cmbMes.getSelectedItem().toString()+"-"+cmbDia.getSelectedItem().toString();
					try {
						ModeloEventos.getInstancia().agregarEventos(new Evento(txtNombre.getText(), srtFecha, txtUbicacion.getText()));
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				txtNombre.setText("");
				txtUbicacion.setText("");
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tablaDeEventos.getSelectedRow() != -1){
					try {
						int decision = JOptionPane.showConfirmDialog(VentanaModeloEventos.this, "Está seguro?", "Confirmación", JOptionPane.WARNING_MESSAGE);
						if(decision == JOptionPane.YES_OPTION){
						ModeloEventos.getInstancia().eliminarEventos(tablaDeEventos.getSelectedRow());
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(VentanaModeloEventos.this, "Debe selecionar una fila","Error al tratar de borrar una fila",JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 8, 8));
		panel.setPreferredSize(new Dimension(420, 150));
		panel.add(lblNombre);
		panel.add(txtNombre);
		panel.add(lblFecha);
		panel.add(panelComboBox);
		panel.add(lblUbicacion);
		panel.add(txtUbicacion);
		panel.add(btnAgregar);
		panel.add(btnEliminar);
		add(panel);
		try {
			tablaDeEventos = new JTable(ModeloEventos.getInstancia());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablaDeEventos.setPreferredScrollableViewportSize(new Dimension(480, 300));
		add(new JScrollPane(tablaDeEventos));
	}
}
