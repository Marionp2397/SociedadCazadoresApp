package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Modelo.Usuario;


/**Maneja las operaciones de acceso a la base de datos para objetos tipo usuario
 * Utiliza conexion base de datos proporcianda por DB.Conexion para hacer las operaciones
 * @author Mario
 * 
 */

public class DaoUsuario {
	
	/**
	 * La conexion a base de datos
	 */
	
	public static Connection con = null;
	
	/**
	 * Crea nueva instancia de DaoUsuario y establece conexion a BD
	 * @throws SQLException
	 */
	
	public DaoUsuario() throws SQLException {
		
		
		this.con = DBConexion.getConexion();
	}
	
	/**
	 * Verfifica si el usuario ya existe en la base de datos
	 * @param u el usuario que se desea verificar si existe
	 * @return true si exite, y false no existe.
	 */
	
		private boolean existe(Usuario u) {
		
		
		return true;
	}
		
		/**
		 * Inserta un nuevo usuario en la base de datos 
		 * Ejecuta una consula en SQL para insertarlo utilziando los datos proporcionados.
		 * @param u el objeto de tipo usuario  que se va a insertar en la base de datos
		 * @throws SQLException
		 */
			public void insertar(Usuario u) throws SQLException {
		
		String sql = "INSERT INTO usuario (nombre,mail,tel,permiso) VALUES (?,?,?,?) ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getNombre());
		ps.setString(2, u.getMail());
		ps.setString(3, u.getTel());
		ps.setInt(4, u.getPermiso());
		
		int filas = ps.executeUpdate();
		ps.close();
		
	}
			
			/**
			 * Actualiza un usuario existente en la base de datos
			 * Este metodo ejecuta  consulta SQL para actualizar un registro existente.
			 * @param u el objeto de tipo usuario  con los datos que se van a guardar
			 * @throws SQLException
			 */
	
	public void actualizar(Usuario u) throws SQLException {
		String sql = "UPDATE usuario SET nombre=?,mail=?,tel=?,permiso=? WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		System.out.println("AIFJAIOSJFIOAS");
		
		ps.setString(1, u.getNombre());
		ps.setString(2, u.getMail());
		ps.setString(3, u.getTel());
		ps.setInt(4, u.getPermiso());
		ps.setInt(5,u.getId());
		
		int filas = ps.executeUpdate();
		ps.close();
		
		
		
	}
	
	/**
	 * Elimina al usuario de la base de datos
	 * Ejecuta consulta SQL para eliminar el registro de la tabla usuario de la base de datos, utilizando su ID
	 * @param id del usuario que se desea eliminar
	 * @throws SQLException
	 */
	
	public void borrar(int id) throws SQLException {
		
		String sql = "DELETE FROM usuario WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,id);
		int filas = ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Obtiene un usuario de la base de datos por su ID
	 * Ejecuta consulta SQL, selecciona el registro de usuario, utilizando su ID proporcionada.
	 * Luego crea y devuelve un objeto usuario con los datos obtenidos de la base de datos
	 * @param id del usuario obtenido de la base de datos
	 * @return un objeto de usuario con los datos obtenidos de la base de datos.
	 * @throws SQLException
	 */
	
	public Usuario obtenerPorID(int id) throws SQLException {
		
		String sql = "SELECT * FROM usuario WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		Usuario u = new Usuario(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));

		return u;
	}
	
	/**
	 * Verifica credenciales de inicio de sesion de un usuario en la base de datos
	 * Ejectua consulta SQL para buscar en tabla usuario que coincida correo y contraseña
	 * Si se encuentra, se crea y devuelve un objeto usuario con datos obtenidos de la base de datos
	 * @param u objeto tipo usuario contiene direccion de correo del usuario
	 * @param pass la contraseña proporcionada por el usuario para inicar sesion
	 * @return obetjo usuario con los datos del usuario obtenido de la base de datos si las credenciales son validas, de lo contrario devuevle null
	 * @throws SQLException
	 */
	
	public Usuario  logeando(Usuario u,String pass) throws SQLException {
		
		String sql = "SELECT * FROM usuario WHERE mail=? AND pass=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getMail());
		ps.setString(2, pass);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		Usuario aux = new Usuario(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));

		return aux;
		
		
		
	}
	/**
	 * Obtiene un lista de todo los usuarios almacenados en la base de datos
	 * Ejecuta consulta SQL selecciona todos los registros de la tabla usuario en la base de datos y devuelve como una lista de objetos usuarios
	 * @return una lista de objetos usuario almacenadas en la base de datos o null ni no encuentra nada
	 * @throws SQLException
	 */
	
public ArrayList<Usuario> listar() throws SQLException{
		
		String sql = "SELECT * FROM usuario";
		PreparedStatement ps = con.prepareStatement(sql);	
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Usuario> ls = null;
		
		while(rs.next()) {	
			if(ls == null) {
				ls = new ArrayList<Usuario>();
			}
			
			ls.add(new Usuario(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
		}	
		return ls;

	}
	
	
	/**
	 * Obtiene una lista de usuarios de un determinado tipo almacenados en la base de datos
	 * Ejecuta consulta SQL selecciona todos registros de la tabla usuario que tengan el tipo permiso especificado y los devuelve como una lista de objetos usuario
	 * @param tipo de permiso de los usuarios que se desea obtener
	 * @return lista objetos usuario que tienen el permiso especificado o null si no encuntra
	 * @throws SQLException
	 */
	
	public ArrayList<Usuario> listar(int tipo) throws SQLException{
		
		String sql = "SELECT * FROM usuario WHERE permiso=?";
		PreparedStatement ps = con.prepareStatement(sql);	
		ps.setInt(1, tipo);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Usuario> ls = null;
		
		while(rs.next()) {	
			if(ls == null) {
				ls = new ArrayList<Usuario>();
			}
			
			ls.add(new Usuario(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
		}	
		return ls;

	}
	
	/**
	 * Obtiene un usuario por su telefono desde la base de datos
	 * Ejectua consulta SQL para seleccionar registro de la tabla usuario en la base de datos, que tenga el numero telefono especificado y lo devuelve.
	 * @param tel del usuario que se desea obtener
	 * @return objeto usuario que tiene el numero telefono especificado o null si no encuentra nada
	 * @throws SQLException
	 */
	
public Usuario listar(String tel) throws SQLException{
		
		String sql = "SELECT * FROM usuario WHERE tel=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, tel);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Usuario> ls = null;
		
		rs.next();	
		Usuario u = new Usuario(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
			
		return u;
	
	}
	
/**
 * Obtiene una representacion JSON de todos los usuarios almacenados en la base de datos
 * Usa metodo listar para obtener de todos los usuarios almacenados en la base de datos y luego 
 * utiliza  la blibioteca gson para convertir en cadena Json
 * @return una cadena que representa todos usuarios almacenados en la base de datos en formato JSon
 * @throws SQLException
 */
	

	public String listarJson() throws SQLException {
		
		String json= "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this.listar());
		
		return json;
		
	}
	
	/**
	 * Obtiene una representacion JSOn de los usuarios de un determinado tipo de almacenamiento en la base de datos
	 * Usa metodo listar con parametro tipo para obtener una lista de usuarios de un determinado tipo de almacenado en la base de datos
	 * y luegoo utiliza la bilbioteca Gson para convertir esta lista en una cadena JSon
	 * @param tipo de permiso de los usuarios que se desea obtener
	 * @return una cadena que representa los usuarios del tipo especificado almacenados en la base de datos formato JSon
	 * @throws SQLException
	 */
	
public String listarJson(int tipo) throws SQLException {
		
		String json= "";
		
		Gson gson = new Gson();
		
		json = gson.toJson(this.listar(tipo));
		
		return json;
		
	}


}
