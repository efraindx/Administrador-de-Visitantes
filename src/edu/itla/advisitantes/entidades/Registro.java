package edu.itla.advisitantes.entidades;

public class Registro {
	
	private String nombre, apellido, usuario, motivo, evento;

	public String getNombre() {
		return nombre;
	}

	public Registro(String nombre, String apellido, String usuario, String motivo, String evento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.motivo = motivo;
		this.evento = evento;
	}
	
	public Registro(String nombre, String apellido, String usuario, String motivo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.motivo = motivo;
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}
	

}
