package edu.itla.advisitantes.entidades;

public class Usuario {

	private int id;
	private String nombre, apellido, usuario, contrase�a, perfil;
	
	public Usuario(int id, String nombre, String apellido, String usuario,String contrase�a, String perfil) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrase�a = contrase�a;
		this.perfil = perfil;
	}
	
	public Usuario(String nombre, String apellido, String usuario,String contrase�a, String perfil) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrase�a = contrase�a;
		this.perfil = perfil;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	
	
}
