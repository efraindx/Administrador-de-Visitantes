package edu.itla.advisitantes.db;

import edu.itla.advisitantes.modelos.ModeloInvitados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import edu.itla.advisitantes.datos.Calendario;
import edu.itla.advisitantes.entidades.Evento;
import edu.itla.advisitantes.entidades.Invitacion;
import edu.itla.advisitantes.entidades.Invitado;
import edu.itla.advisitantes.entidades.Registro;
import edu.itla.advisitantes.entidades.Usuario;
import edu.itla.advisitantes.modelos.ModeloEventos;
import edu.itla.advisitantes.modelos.ModeloEventosActuales;
import edu.itla.advisitantes.modelos.ModeloInvitaciones;
import edu.itla.advisitantes.modelos.ModeloInvitadosPendientes;
import edu.itla.advisitantes.modelos.ModeloRegistroVisitas;
import edu.itla.advisitantes.modelos.ModeloUsuarios;
import edu.itla.advisitantes.ui.VentanaEventosProximos;
import edu.itla.advisitantes.ui.VentanaModeloInvitaciones;
import edu.itla.advisitantes.ui.VentanaPortero;
import edu.itla.advisitantes.ui.VentanaReportes;

public class Conexion {

			
			private PreparedStatement enunciado;
			private Connection conexion;
			private static Conexion instancia;
			private Statement estado;
			private ResultSet resultado;
			private ArrayList<Registro> registros;
			private ArrayList<Invitado> invitados;
			private ArrayList<Evento> eventos;
			private ArrayList<Invitacion> invitaciones;
			
			public static synchronized Conexion getInstancia(){
					if(instancia == null){
						instancia = new Conexion();
					}
					return instancia;
			}
			
			public Conexion(){
						try {
							Class.forName("com.mysql.jdbc.Driver");
							conexion = DriverManager.getConnection("jdbc:mysql://localhost/proyecto", "root", "itla");
							estado = conexion.createStatement();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
			}
			
			public void agregarUsuarios(Usuario user){
					int perfil;
					try {
						enunciado = conexion.prepareStatement("insert into usuarios (nombre,apellido,usuario,password,perfil) values (?,?,?,?,?)");
						enunciado.setString(1, user.getNombre());
						enunciado.setString(2, user.getApellido());
						enunciado.setString(3, user.getNombre());
						enunciado.setString(4, user.getContraseña());
						
						if(user.getPerfil().equals("Admin")){
							perfil = 1;
						}
						else{
							perfil = 2;
						}
						enunciado.setInt(5, perfil);
						enunciado.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			
			}
			
			public void modificarUsuarios(int posicion, int ID, String valor){
					int perfil;
				
					switch(posicion){
				
					case 0:
						try {
						enunciado = conexion.prepareStatement("UPDATE usuarios set nombre=? where id=?" );
						enunciado.setString(1, valor);
						enunciado.setInt(2, ID);
						enunciado.execute();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					break;
					
					case 1:
						try {
						enunciado = conexion.prepareStatement("UPDATE usuarios set apellido=? where id=?" );
						enunciado.setString(1, valor);
						enunciado.setInt(2, ID);
						enunciado.execute();
						} catch (SQLException e) {
							e.printStackTrace();
						};
					
					case 2:
						try {
						enunciado = conexion.prepareStatement("UPDATE usuarios set usuario=? where id=?" );
						enunciado.setString(1, valor);
						enunciado.setInt(2, ID);
						enunciado.execute();
						} catch (SQLException e) {
							e.printStackTrace();
						};
					break;
					
					case 3:
						try {
						enunciado = conexion.prepareStatement("UPDATE usuarios set password=? where id=?" );
						enunciado.setString(1, valor);
						enunciado.setInt(2, ID);
						enunciado.execute();
						} catch (SQLException e) {
							e.printStackTrace();
						};
					break;
					
					case 4:
						try {
						if(valor.equals("Admin")){
							perfil = 1;
						}
						else{
							perfil = 2;
						}
						enunciado = conexion.prepareStatement("UPDATE usuarios set perfil=? where id=?" );
						enunciado.setInt(1, perfil);
						enunciado.setInt(2, ID);
						enunciado.execute();
						} catch (SQLException e) {
							e.printStackTrace();
						};
					break;
					
				
					}
			}
			
			public void eliminarUsuarios(Usuario user){
					try {
						enunciado = conexion.prepareStatement("DELETE from usuarios where id=?");
						enunciado.setInt(1, user.getId());
						enunciado.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
			}
			
			public ResultSet consultar(String sql){
				ResultSet resultado = null;
					try {
						enunciado = conexion.prepareStatement(sql);
						resultado = enunciado.executeQuery();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				return resultado;
			}
			
			public void cargarUsuarios(){
				String perfil;
				ResultSet resultado = consultar("SELECT * from usuarios");
					try {
						while(resultado.next()){
							if(resultado.getInt("perfil") == 1){
								perfil = "Admin";
							}
							else{
								perfil = "Portero";
							}
							ModeloUsuarios.usuarios.add(new Usuario(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("usuario"), resultado.getString("password"), perfil));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
			public void cargarComboboxEventos(){
				ResultSet resultado;
				try {
					resultado = consultar("SELECT (nombre) from eventos");
					VentanaModeloInvitaciones.listaEventos = new JComboBox<Object>();
					while(resultado.next()){
						VentanaModeloInvitaciones.listaEventos.addItem(resultado.getString("nombre"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void cargarComboboxInvitados(){
				ResultSet resultado;
				try {
					resultado = consultar("SELECT concat(nombre,' ', apellido) as nombre from invitados");
					VentanaModeloInvitaciones.listaInvitados = new JComboBox<Object>();
					while(resultado.next()){ 
						VentanaModeloInvitaciones.listaInvitados.addItem(resultado.getString("nombre"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void cargarEventos(){
				ResultSet resultado;
				try {
					resultado = consultar("SELECT * from eventos");
					while(resultado.next()){
						ModeloEventos.eventos.add(new Evento(resultado.getInt("id"),resultado.getString("nombre"), resultado.getString("fecha"), resultado.getString("ubicacion")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void cargarEventosActuales(String fechaEvento){
				ResultSet resultado;
				try {
					resultado = consultar("SELECT * from eventos where fecha = '"+fechaEvento+"'");
					while(resultado.next()){
						ModeloEventosActuales.eventosActuales.add(new Evento(resultado.getString("nombre"),resultado.getString("ubicacion")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void cargarInvitaciones(){
				ResultSet resultado;
				try {
					resultado = consultar("SELECT * from invitaciones");
					while(resultado.next()){
						ModeloInvitaciones.invitaciones.add(new Invitacion(resultado.getInt("id"), resultado.getString("invitado"), resultado.getString("evento"), resultado.getString("asistencia")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void cargarInvitados(){
				ResultSet resultado;
				try {
					resultado = consultar("SELECT * from invitados");
					while(resultado.next()){
						ModeloInvitados.invitados.add(new Invitado(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("telefono"), resultado.getString("direccion"), resultado.getString("sexo")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void agregarEventos(Evento evento){
					try {
					enunciado = conexion.prepareStatement("INSERT INTO eventos (nombre, fecha, ubicacion) values (?,?,?)");
					enunciado.setString(1, evento.getNombre());
					enunciado.setString(2, evento.getFecha());
					enunciado.setString(3, evento.getUbicacion());
					enunciado.execute();
					ModeloEventos.eventos.add(evento);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Fecha invalida, formato: Year-Moth-Day");
					}
			}
			
			public void modificarEventos(Evento evento){
				
				try {
					enunciado = conexion.prepareStatement("update eventos set nombre=?, fecha=?, ubicacion=? where id=?");
					enunciado.setString(1, evento.getNombre());
					enunciado.setString(2, evento.getFecha());
					enunciado.setString(3, evento.getUbicacion());
					enunciado.setInt(4, evento.getID());
					enunciado.executeUpdate();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Debe Ingresar la fecha correcta", "Error al trata de modificar", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			public void modificarEventosInvitaciones(Evento evento, String valor){
					try {
						enunciado = conexion.prepareStatement("UPDATE invitaciones set evento = ? where evento = ?");
						enunciado.setString(1, valor);
						enunciado.setString(2, evento.getNombre());
						enunciado.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
			public ArrayList<Evento> obtenerEventos(){
				
				try {
					resultado = estado.executeQuery("select * from eventos order by id desc");
					eventos = new ArrayList<Evento>();
					while(resultado.next()){
						Evento evento = new Evento(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("fecha"), resultado.getString("ubicacion"));
						eventos.add(evento);
					}
					
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				return eventos;
			}
			
			public void eliminarEventos(int ID){
					try {
						enunciado = conexion.prepareStatement("DELETE from eventos where id=?");
						enunciado.setInt(1, ID);
						enunciado.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
			public void eliminarEventosInvitaciones(String evento){
				try {
					enunciado = conexion.prepareStatement("DELETE from invitaciones where evento=?");
					enunciado.setString(1, evento);
					enunciado.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
			
			public void agregarInvitaciones(String invitado, String evento, String asistencia){
				try {
					enunciado = conexion.prepareStatement("INSERT INTO invitaciones (invitado, evento, asistencia) values (?,?,?)");
					enunciado.setString(1, invitado);
					enunciado.setString(2, evento);
					enunciado.setString(3, asistencia);
					enunciado.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			public void agregarInvitados(Invitado invitado){
					try {
						enunciado = conexion.prepareStatement("insert into invitados (nombre,apellido,telefono,direccion,sexo) values (?,?,?,?,?)");
						enunciado.setString(1, invitado.getNombre());
						enunciado.setString(2, invitado.getApellido());
						enunciado.setString(3, invitado.getTelefono());
						enunciado.setString(4, invitado.getDireccion());
						enunciado.setString(5, invitado.getSexo());
						enunciado.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
			public void modificarInvitados(int posicion, int ID, String valor){
				
					switch(posicion){
					
					case 0:
						try {
						enunciado = conexion.prepareStatement("UPDATE invitados set nombre=? where id=?");
						enunciado.setString(1, valor);
						enunciado.setInt(2, ID);
						enunciado.execute();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					break;
					
					case 1:
						try {
						enunciado = conexion.prepareStatement("UPDATE invitados set apellido=? where id=?");
						enunciado.setString(1, valor);
						enunciado.setInt(2, ID);
						enunciado.execute();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					break;
					
					case 2:
						try {
						enunciado = conexion.prepareStatement("UPDATE invitados set telefono=? where id=?");
						enunciado.setString(1, valor);
						enunciado.setInt(2, ID);
						enunciado.execute();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					break;
					
					case 3:
						try {
						enunciado = conexion.prepareStatement("UPDATE invitados set direccion=? where id=?");
						enunciado.setString(1, valor);
						enunciado.setInt(2, ID);
						enunciado.execute();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					break;
					
					case 4:
						try {
						enunciado = conexion.prepareStatement("UPDATE invitados set nombre=? where id=?");
						enunciado.setString(1, valor);
						enunciado.setInt(2, ID);
						enunciado.execute();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					break;
					
					}
			}
			
			public void modificarInvitadosInvitaciones(int posicion, Invitado invitado, String valor){
				try {		
					if(posicion == 0){
							String invi = invitado.getNombre()+" "+invitado.getApellido();
							String[] invis = invi.split(" ");
							String invi2 = valor+" "+invis[1];
						
								enunciado = conexion.prepareStatement("UPDATE invitaciones set invitado = ? where invitado =?");
								enunciado.setString(1, invi2);
								enunciado.setString(2, invi);
								enunciado.execute();
								}
					else{
							String invi = invitado.getNombre()+" "+invitado.getApellido();
							String[] invis = invi.split(" ");
							String invi2 = invis[0]+" "+valor;
								enunciado = conexion.prepareStatement("UPDATE invitaciones set invitado = ? where invitado =?");
								enunciado.setString(1, invi2);
								enunciado.setString(2, invi);
								enunciado.execute();
						}
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void eliminarInvitados(Invitado invitado){
					try {
						enunciado = conexion.prepareStatement("DELETE FROM invitados where id=?");
						enunciado.setInt(1, invitado.getId());
						enunciado.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
			}
			
			public void eliminarInvitadosInvitaciones(Invitado invitado){
						String invi = invitado.getNombre()+" "+invitado.getApellido();
				try {
					enunciado = conexion.prepareStatement("DELETE FROM invitaciones where invitado=?");
					enunciado.setString(1, invi);
					enunciado.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
		}
			
			public void eliminarInvitaciones(Invitacion invi){
					try {
						enunciado = conexion.prepareStatement("DELETE FROM invitaciones where id = ?");
						enunciado.setInt(1, invi.getId());
						enunciado.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
			}
			
			public void modificarInvitaciones(int posicion, int ID, String valor){
				
				switch(posicion){
				
				case 0:
					try {
					enunciado = conexion.prepareStatement("UPDATE invitaciones set invitado = ? where ID = ?");
					enunciado.setString(1, valor);
					enunciado.setInt(2, ID);
					enunciado.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
						
				break;
				
				case 1:
					try {
					enunciado = conexion.prepareStatement("UPDATE invitaciones set evento = ? where ID = ?");
					enunciado.setString(1, valor);
					enunciado.setInt(2, ID);
					enunciado.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
				}
					
			}
				public void modificarInvitacionesActuales(int id, String valor){
					try {
						enunciado = conexion.prepareStatement("UPDATE invitaciones set asistencia = ? where id = ?");
						enunciado.setString(1, valor);
						enunciado.setInt(2, id);
						enunciado.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			
			public void cargarInvitacionesActuales(String nombreEvento){
				
					ResultSet resultado = consultar("SELECT * from invitaciones where evento = '"+nombreEvento+"'");
						try {
							ModeloRegistroVisitas.invitacionesActuales = new ArrayList<>();
							while(resultado.next()){
									ModeloRegistroVisitas.invitacionesActuales.add(new Invitacion(resultado.getInt("id"), resultado.getString("invitado"), resultado.getString("asistencia")));
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
			}
			
			public void agregarRegistro(Registro registro){
					try {
						enunciado = conexion.prepareStatement("insert into registros values(?,?,?,?,?)");
						enunciado.setString(1, registro.getNombre());
						enunciado.setString(2, registro.getApellido());
						enunciado.setString(3, registro.getUsuario());
						enunciado.setString(4, registro.getMotivo());
						enunciado.setString(5, VentanaPortero.eventoActual);
						enunciado.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
			public ArrayList<Registro> obtenerRegistro() {
					try {
						resultado = estado.executeQuery("SELECT * from registros where evento = '"+VentanaPortero.eventoActual+"' order by nombre ");
						registros = new ArrayList<Registro>();
						while(resultado.next()){
							registros.add(new Registro(resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("usuario"),resultado.getString("motivo")));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				return registros;
			}
			
			public int obtenerCantidadVisitasImprevistas(String evento){
				int retorno =0;
					resultado = consultar("SELECT count(nombre) as cantidad from registros where evento = '"+evento+"'");
					try {
						while(resultado.next()){
							retorno = resultado.getInt("cantidad");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return retorno;
					
			}
			
			public void cargarVisitantesPendientes(String nombreEventoActual){
						try {
							resultado = estado.executeQuery("SELECT nombre,apellido,telefono,direccion,sexo from invitados join invitaciones on(concat(nombre,' ', apellido) = invitado) where (asistencia = 'ausente' or asistencia is null) and evento='"+nombreEventoActual+"'");
							ModeloInvitadosPendientes.invitadosPendientes = new ArrayList<>();
							while(resultado.next()){
								ModeloInvitadosPendientes.invitadosPendientes.add(new Invitado(resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("telefono"), resultado.getString("direccion"), resultado.getString("sexo")));
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
			}
			
			public ArrayList<Invitado> mostrarVisitantes(String eventoActual){
				
				try {
					resultado = estado.executeQuery("select nombre, apellido, telefono, direccion, sexo "+
								"from invitados  join invitaciones "+
								"on (concat(nombre,' ', apellido) = invitado) "+
								"where evento='"+eventoActual+"' ");
					invitados = new ArrayList<Invitado>();
					while(resultado.next()){
						
						Invitado invitado = new Invitado(0, resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("telefono"),
								resultado.getString("direccion"), resultado.getString("sexo"));
						
						invitados.add(invitado);
					}	
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return invitados;
			}
			
			public void cargarEventosProximos(){
				ResultSet resultado = null;
				resultado = consultar("SELECT * from eventos where fecha > '"+Calendario.obtenerFechaActual()+"'");
				try {
					VentanaEventosProximos.eventosProximos = new ArrayList<>();
					while(resultado.next()){
						VentanaEventosProximos.eventosProximos.add(new Evento(resultado.getString("nombre"), resultado.getString("fecha"), resultado.getString("ubicacion")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			public void cargarEventosVisitados(){
					resultado = consultar("SELECT evento from invitaciones where asistencia = 'presente' group by evento order by asistencia desc");
					try {
						VentanaReportes.eventos = new ArrayList<>();
						while(resultado.next()){
							VentanaReportes.eventos.add(resultado.getString("evento"));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			
			public void cargarVisitasHumanas(String sexo){
				if(sexo.equals("Hombre")){
					resultado = consultar("SELECT evento, count(sexo) as cantidad_hombre from invitados join invitaciones on (concat(nombre, ' ', apellido) = invitado) where sexo = 'hombre' and asistencia = 'presente' group by evento order by cantidad_hombre desc");
					try {
						VentanaReportes.eventosHombres = new ArrayList<>();
						while(resultado.next()){
							VentanaReportes.eventosHombres.add(resultado.getString("evento"));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else{
					resultado = consultar("SELECT evento, count(sexo) as cantidad_mujere from invitados join invitaciones on (concat(nombre, ' ', apellido) = invitado) where sexo = 'mujer' and asistencia = 'presente' group by evento order by cantidad_mujere desc");
					try {
						VentanaReportes.eventosMujeres = new ArrayList<>();
						while(resultado.next()){
							VentanaReportes.eventosMujeres.add(resultado.getString("evento"));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			}
			
			public ArrayList<Invitacion> mostrarAsistencia(){

				try {
					resultado = estado.executeQuery("SELECT evento,count(asistencia)as cantidad, asistencia FROM invitaciones where asistencia='presente'group by evento");
					
					invitaciones = new ArrayList<Invitacion>();
					while(resultado.next()){
						Invitacion invitacion = new Invitacion(resultado.getString("evento"), resultado.getInt("cantidad"), resultado.getString("asistencia"));
						invitaciones.add(invitacion);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return invitaciones;
			}
			
			public int encontrarCantidadInvitaciones(String evento){
				ResultSet resultado = null;
				int retorno = 0;
				resultado = consultar("SELECT (evento), count(invitado) as cantidad from invitaciones where evento = '"+evento+"'");
				try {
					while(resultado.next()){
						try {
							retorno = resultado.getInt("cantidad");
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
					}
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				return retorno;
			}
			
			public void cargarDiasMasVisitados(){
				try {
					resultado = estado.executeQuery("SELECT DATE_FORMAT(fecha, '%W')as dia from eventos group by dia order by count(DATE_FORMAT(fecha, '%W'))desc");
					VentanaReportes.dias = new ArrayList<>();
					while(resultado.next()){
						VentanaReportes.dias.add(resultado.getString("dia"));
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			
			public ArrayList<Invitado> mostrarVisitantes(String evento, String texto){
				
				try {
					resultado = estado.executeQuery("select nombre, apellido, telefono, direccion, sexo "+
								"from invitados join invitaciones "+
								"on (concat(nombre,' ', apellido) = invitado) "+
								"where evento='"+evento+"' and (nombre like '%"+texto+"%' or apellido like '%"+texto+"%') ");
					invitados = new ArrayList<Invitado>();
					while(resultado.next()){
						
						Invitado invitado = new Invitado(0, resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("telefono"),
								resultado.getString("direccion"), resultado.getString("sexo"));
						
						invitados.add(invitado);
					}	
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return invitados;
			}
}
