package Modelo;

import java.sql.SQLException;


import DAO.DaoLicencia;

/**
 * Modelo/Clase Licencia que utilizamos para POO que representa una licencia de caza, incluyendo informacion relevante.
 * @author Mario
 * Esta clase se utiliza para almacenar y gestionar los detalles de una licencia de caza
 */

public class Licencia {
	
	private String Licencia_ID;
	private String Fecha_Emision;
	private String Num_Licencia;
	private String Tipo_Licencia;
	private String DNI_Cazador;

	
	public Licencia() {
		
	}

	



/**
 * 
 * Crea una licencia recibiendo su ID,fecha,numero de licencia, tipo de licencia y el dni del cazador
 * @param licencia_ID
 * @param fecha_Emision
 * @param num_Licencia
 * @param tipo_Licencia
 * @param dNI_Cazador
 */

	public Licencia(String licencia_ID, String fecha_Emision, String num_Licencia, String tipo_Licencia,
			String dNI_Cazador) {
		super();
		Licencia_ID = licencia_ID;
		Fecha_Emision = fecha_Emision;
		Num_Licencia = num_Licencia;
		Tipo_Licencia = tipo_Licencia;
		DNI_Cazador = dNI_Cazador;
	}







	public String getLicencia_ID() {
		return Licencia_ID;
	}







	public void setLicencia_ID(String licencia_ID) {
		Licencia_ID = licencia_ID;
	}







	public String getFecha_Emision() {
		return Fecha_Emision;
	}







	public void setFecha_Emision(String fecha_Emision) {
		Fecha_Emision = fecha_Emision;
	}







	public String getNum_Licencia() {
		return Num_Licencia;
	}







	public void setNum_Licencia(String num_Licencia) {
		Num_Licencia = num_Licencia;
	}







	public String getTipo_Licencia() {
		return Tipo_Licencia;
	}







	public void setTipo_Licencia(String tipo_Licencia) {
		Tipo_Licencia = tipo_Licencia;
	}







	public String getDNI_Cazador() {
		return DNI_Cazador;
	}







	public void setDNI_Cazador(String dNI_Cazador) {
		DNI_Cazador = dNI_Cazador;
	}





/**
 * Inserta el objeto actual (licencia) en a base de datos con ayuda de DaoLicencia
 * @throws SQLException
 */

	public void insertar() throws SQLException {
		
		DaoLicencia dao = new DaoLicencia();
		dao.Insertar(this);
	}







	@Override
	public String toString() {
		return "Licencia [Licencia_ID=" + Licencia_ID + ", Fecha_Emision=" + Fecha_Emision + ", Num_Licencia="
				+ Num_Licencia + ", Tipo_Licencia=" + Tipo_Licencia + ", DNI_Cazador=" + DNI_Cazador + "]";
	}







	

	
	
}
