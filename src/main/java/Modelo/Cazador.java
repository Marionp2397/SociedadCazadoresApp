package Modelo;

import java.sql.SQLException;


import DAO.DaoCazador;

/**
 * Modelo/Clase Cazador que utilziamos para los POO que representa a un cazador, incluyendo informacion relevante
 * @author Mario
 * Esta clase se utiliza para almacenar y gestionar los detalles de un cazador
 * 
 */

public class Cazador {
	
	private String Nombre;
	private String Apellidos; 
	private String DNI_Cazador;
	private String correo;
	private String Direccion;
	private String Localidad;
	
	


	public Cazador() {
	
	}
	
	/**
	 * Crea un cazador recibiendo su nombre, apellid, dni, correo, direccion y localidad
	 * @param nombre
	 * @param apellidos
	 * @param dNI_Cazador
	 * @param correo
	 * @param direccion
	 * @param localidad
	 */

	public Cazador(String nombre, String apellidos, String dNI_Cazador, String correo, String direccion,
			String localidad) {
		super();
		Nombre = nombre;
		Apellidos = apellidos;
		DNI_Cazador = dNI_Cazador;
		this.correo = correo;
		Direccion = direccion;
		Localidad = localidad;
	}




	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getApellidos() {
		return Apellidos;
	}


	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}


	public String getDNI_Cazador() {
		return DNI_Cazador;
	}


	public void setDNI_Cazador(String dNI_Cazador) {
		DNI_Cazador = dNI_Cazador;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getDireccion() {
		return Direccion;
	}


	public void setDireccion(String direccion) {
		Direccion = direccion;
	}


	public String getLocalidad() {
		return Localidad;
	}


	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	/** Inserta el objeto actual (cazador) en la base de datos utilizando el DAOCAzador
	 * @author Mario
	 * 
	 */

	public void Insertar() throws SQLException {
		
		DaoCazador dao = new DaoCazador();
		dao.Insertar(this);
	}


	@Override
	public String toString() {
		return "Cazador [Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", DNI_Cazador=" + DNI_Cazador + ", correo="
				+ correo + ", Direccion=" + Direccion + ", Localidad=" + Localidad + "]";
	}




	
	
	
	
	
}