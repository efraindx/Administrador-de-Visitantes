package edu.itla.advisitantes.datos;

import java.util.Calendar;

public class Calendario {
	
		private static Calendar  calendario;
		
		public static String obtenerFechaActual(){
			String retorno = null;
			calendario = Calendar.getInstance();
			int year = calendario.get(Calendar.YEAR);
			int mes = calendario.get(Calendar.MONTH)+1;
			int dia = calendario.get(Calendar.DAY_OF_MONTH);
			retorno = year+"-"+mes+"-"+dia;
			return retorno;
		}
}
