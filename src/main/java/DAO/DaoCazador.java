package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Cazador;
import com.google.gson.Gson;

/**Maneja las operaciones de acceso a la base de datos para objetos tipo cazador
 * Utiliza conexion base de datos proporcianda por DB.Conexion para hacer las operaciones
 * @author Mario
 * 
 */

public class DaoCazador {
	
	/**
	 * La conexion a base de datos
	 */

	public Connection con = null;
	
	/**
	 * La instancia unica de la clase DaoCAzador (Singleton)
	 */
	
	private static DaoCazador instance = null;
	
	/**
	 * Crea nueva instancia de DaoCAzador y establece conexion a BD
	 * @throws SQLException
	 */
	
	public DaoCazador() throws SQLException {
		
		this.con = DBConexion.getConexion();
	}
	
	/**
	 * Devuelve  la instancia  unica de la clase DAOCazador, creandola si es necesario
	 * Implementa Singleton, garantiza solo haya una instancia de DAoCAzador en toda la apliacion
	 * @return instancia unica de la clase DaoCazador
	 * @throws SQLException si hay error
	 */
	
	public static DaoCazador getInstance() throws SQLException {
		
		if (instance== null) {
			instance = new DaoCazador();
		}
		return instance;
		
	}
	
	
	/**
	 * Inserta un nuevo cazador en la base de datos
	 * Inserta nuevo registro en la tabla cazador de la base de datos utilizando los datos proporcionados
	 * @param n el objeto tipo cazador que se inserta en la base de datos
	 * @throws SQLException si hay errores
	 */
			public void Insertar(Cazador n) throws SQLException {
		
		String sql = "INSERT INTO cazador (Nombre, Apellidos, DNI_Cazador, correo, Direccion, Localidad) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, n.getNombre());
		ps.setString(2, n.getApellidos());
		ps.setString(3, n.getDNI_Cazador());
		ps.setString(4, n.getCorreo());
		ps.setString(5, n.getDireccion());
		ps.setString(6, n.getLocalidad());
		
		
		
		
		int filas = ps.executeUpdate();
		ps.close();
		
		
			}
			
			/**
			 * Obtiene una lista de todos los cazadores almacenados en la base de datos
			 * Ejecuta consulta SQL para seleccionar todos los registros de la tabla cazador y los devuelve en una lista
			 * 
			 * @return lista de objeto cazador almacenados en base de datos o null si no encutra nada
			 * @throws SQLException si hay errores
			 */
			
	public ArrayList<Cazador> listar() throws SQLException{
		
		PreparedStatement ps = con.prepareStatement("Select * From cazador");
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Cazador> result = null;
		
		while (rs.next()) {
			
			if(result == null) {
				result = new ArrayList<>();
			}
			
			result.add(new Cazador(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
		}
		
		
		
		return result;
		
	}
	
	/**
	 * Obtiene representacion  JSON de todos cazadores almacenados en base de datos
	 * Utiliza listar para obtener una lista  de cazadores, utiliza la biblioteca gson para convertir esta lista en Json
	 * @return una cadena que representa todos los cazadores almacenados en la base de datos en formato JSOn
	 * @throws SQLException si hay errores
	 */
	
	public String dameJson() throws SQLException {
		
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listar());
		
		return json;
	}
			
			
		}

