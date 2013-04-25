package edu.itla.advisitantes.entidades;

public class Evento {
	
	private String nombre,ubicacion, fecha;
	private int ID, cantidadPersonas;
	
	public Evento(String nombre, String fecha, String ubicacion) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
	}
	
	public Evento(String nombre,String ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}
	
	public Evento(int id, String nombre, String fecha, String ubicacion){
		ID = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String valor) {
		this.fecha = valor;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getCantidadPersonas() {
		return cantidadPersonas;
	}

	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}
	

}
