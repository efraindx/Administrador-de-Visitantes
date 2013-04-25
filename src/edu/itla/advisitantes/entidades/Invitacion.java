package edu.itla.advisitantes.entidades;

public class Invitacion {
	
		private String invitado;
		private String evento;
		private String asistencia;
		private int ID, cantidad;
		
		public Invitacion(String invitado, String evento, String asistencia) {
			this.invitado = invitado;
			this.evento = evento;
			this.asistencia = asistencia;
		}
		
		public Invitacion(int iD, String invitado, String evento, String asistencia) {
			ID = iD;
			this.invitado = invitado;
			this.evento = evento;
			this.asistencia = asistencia;
		}
		
		public Invitacion(int id, String invitado, String asistencia){
			this.ID = id;
			this.invitado = invitado;
			this.asistencia = asistencia;
		}
		
		public Invitacion(String evento, int cantidad, String asistencia) {
			this.evento = evento;
			this.setCantidad(cantidad);
			this.asistencia = asistencia;
		}
		
		public int getId(){
			return ID;
		}

		public String getInvitado() {
			return invitado;
		}
		public void setInvitado(String invitado) {
			this.invitado = invitado;
		}
		public String getEvento() {
			return evento;
		}
		public void setEvento(String evento) {
			this.evento = evento;
		}

		public String getAsistencia() {
			return asistencia;
		}

		public void setAsistencia(String asistencia) {
			this.asistencia = asistencia;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
	
		

}
