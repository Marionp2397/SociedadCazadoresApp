package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Modelo.Cazador;
import Modelo.Licencia;

/**Maneja las operaciones de acceso a la base de datos para objetos tipo licencia
 * Utiliza conexion base de datos proporcianda por DB.Conexion para hacer las operaciones
 * @author Mario
 * 
 */

public class DaoLicencia {
	
	/**
	 * La conexion a base de datos
	 */
		public Connection con = null;
		
		/**
		 * La instancia unica de la clase DaoLicencia (Singleton)
		 */
		
		private static DaoLicencia instance = null;
		
		/**
		 * Crea nueva instancia de Daolicencia y establece conexion a BD
		 * @throws SQLException
		 */

		
		public DaoLicencia() throws SQLException {
			
			this.con = DBConexion.getConexion();
		}
		
		
		/**
		 * Devuelve  la instancia  unica de la clase DAOlicencia, creandola si es necesario
		 * Implementa Singleton, garantiza solo haya una instancia de DAolicencia en toda la apliacion
		 * @return instancia unica de la clase Daolicencia
		 * @throws SQLException si hay error
		 */
		
		public static DaoLicencia getInstance() throws SQLException {
				if (instance == null) {
					instance = new DaoLicencia();
					
				}
				return instance;
		}
		
		/**
		 * Inserta una nueva licencia en la base de datos
		 * Inserta nuevo registro en la tabla licencia de la base de datos utilizando los datos proporcionados
		 * @param n el objeto tipo licencia que se inserta en la base de datos
		 * @throws SQLException si hay errores
		 */
		
		
				public void Insertar(Licencia n) throws SQLException {
			
			String sql = "INSERT INTO licencia (Fecha_Emision, Num_Licencia, Tipo_Licencia, DNI_Cazador) VALUES (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, n.getFecha_Emision());
			ps.setString(2, n.getNum_Licencia());
			ps.setString(3, n.getTipo_Licencia());
			ps.setString(4, n.getDNI_Cazador());

			
			
			int filas = ps.executeUpdate();
			ps.close();
			
			
	}
				
				/**
				 * Obtiene una lista de todas las licencias almacenados en la base de datos
				 * Ejecuta consulta SQL para seleccionar todos los registros de la tabla licencia y los devuelve en una lista
				 * 
				 * @return lista de objeto listar almacenados en base de datos o null si no encuetra nada
				 * @throws SQLException si hay errores
				 */
				
				
				public ArrayList<Licencia> listar() throws SQLException{
					
					PreparedStatement ps = con.prepareStatement("Select * From licencia");
					ResultSet rs = ps.executeQuery();
					
					ArrayList<Licencia> result = null;
					
					while (rs.next()) {
						
						if(result == null) {
							result = new ArrayList<>();
						}
						
						result.add(new Licencia(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
					}
					
					
					
					return result;
					
				}
				
				/**
				 * Obtiene representacion  JSON de todas las licencias almacenados en base de datos
				 * Utiliza listar para obtener una lista  de licencias, utiliza la biblioteca gson para convertir esta lista en Json
				 * @return una cadena que representa todas las licencias almacenados en la base de datos en formato JSOn
				 * @throws SQLException si hay errores
				 */
				
				public String dameJson() throws SQLException {
					
					String json = "";
					Gson gson = new Gson();
					
					json = gson.toJson(this.listar());
					
					return json;
				}
						
						
					}

	

