package Modelo;

import java.sql.SQLException;



import com.google.gson.Gson;

import DAO.DaoUsuario;

/**
 * Modelo/Clase Usuario que utilizamos para POO que representa un usuario, incluyendo informacion relevante
 * @author Mario
 * Esta clase se utiliza para almacenar y gestionar los detalles de un usuario.
 * 
 */
public class Usuario {
	
	
	private int id;
	private String nombre;
	private String mail;
	private String tel;
	private int permiso;
	
	
	public Usuario() {
		
	}
	
	/**
	 * 
	 * Crea un usuario recibiendo su nombre, mail, tel y permiso.
	 * @param nombre
	 * @param mail
	 * @param tel
	 * @param permiso
	 */
	
	public Usuario(String nombre, String mail, String tel, int permiso) {
		
		this.nombre = nombre;
		this.mail = mail;
		this.tel = tel;
		this.permiso = permiso;
	}
	
/**
 * Crea un usuario recibiendo su id, nombre, mail, tel y permiso.
 * @param id
 * @param nombre
 * @param mail
 * @param tel
 * @param permiso
 */
	
	public Usuario(int id, String nombre, String mail, String tel, int permiso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.mail = mail;
		this.tel = tel;
		this.permiso = permiso;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getPermiso() {
		return permiso;
	}

	public void setPermiso(int permiso) {
		this.permiso = permiso;
		
		
	}
	
		/**
		 * Obtiene un usuario de la base de datos por su ID y actualiza los campos del objeto actual con los datos obtenidos
		 * Crea instancia de Daousuario, llama al metodo obtenerporID de DAoUsuario, pasando ID del usuario como parametro, esos datos se usan para actualizar campos.
		 * @param id del usuario que se desea obtener
		 * @throws SQLException
		 */

		public void obtenerPorId(int id) throws SQLException {
		
		DaoUsuario dao = new DaoUsuario();
		Usuario aux = dao.obtenerPorID(id);
		
		this.setId(aux.getId());
		this.setNombre(aux.getNombre());
		this.setTel(aux.getTel());
		this.setMail(aux.getMail());
		this.setPermiso(aux.getPermiso());
	}
	
		/**
		 * Autentifica al usurio utilziand contraseña proporcionada y actualiza los datos si es exitosa
		 * Crea instancia en DaoUsuario y llama al metodo logeando de DAoUsuario pasando el usuario y contraseña por parametro. 
		 * Si es exitosa (obtiene objeto usuario no nulo) se actualiza los campos del objeto con los datos autentificados
		 * @param pass
		 * @return true si es exitosa, false si no lo es
		 * @throws SQLException
		 */
		
	public boolean logeo (String pass) throws SQLException {
		
		boolean ok = false;
		
		DaoUsuario dao = new DaoUsuario();
		Usuario aux = dao.logeando(this, pass);
		
		if(aux != null) {
			ok=true;
			this.setId(aux.getId());
			this.setNombre(aux.getNombre());
			this.setTel(aux.getTel());
			this.setMail(aux.getMail());
			this.setPermiso(aux.getPermiso());
			
		}
		
		return ok;
	}
		
		/**
		 * Establece el id del objeto actual
		 * @param id el nuevo ID que se asigna al objeto
		 */
		 
		public void id (int id) {
			this.id = id;
			
		}
		/**
		 * Convierte el objeto actual en una representacion JSON
		 * Usa biblioteca Gson para convertir objeto en cadena JSON
		 * @return una cadena que representa el objeto actual en JSON
		 */
	
		
		public String dameJson() {
			String json="";
			
			Gson gson = new Gson ();
			
			json = gson.toJson(this);
			return json;
		}
		
		/**
		 * Actualiza la informacion del objeto actual en la base de datos
		 * Crea instancia de DaoUsuario y llama al metodo actualizar en DaoUsuario, pasando objeto como parametro para actualizar
		 * 
		 * @throws SQLException
		 */
		public void actualizar() throws SQLException {
			
			DaoUsuario dao = new DaoUsuario();
			dao.actualizar(this);
		}
		
		/**
		 * Inserta el objeto actual en la base de datos
		 * Crea instancia de DAoUsuario y llama metodo insertar de DaoUsuario, pasando objeto actual como parametro para insertar en base de datos
		 * @throws SQLException
		 */
		
		public void insertar() throws SQLException {
			
			DaoUsuario dao = new DaoUsuario();
			dao.insertar(this);
		}
		
		/**
		 * Borra el usuario de la base de datos con el ID especifico
		 * Crea instancia de DAoUsuario y llama metodo borrar de DaoUsuario, pasando el ID del usuario como parametro para eliminar de la base de datos.
		 * @throws SQLException
		 */
		
		public void borrar(int id) throws SQLException {
			DaoUsuario dao = new DaoUsuario();
			dao.borrar(id);
			
			
		}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", mail=" + mail + ", tel=" + tel + ", permiso=" + permiso
				+ "]";
	}
	
	
	
	
	
}
