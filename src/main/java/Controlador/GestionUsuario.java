package Controlador;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import DAO.DaoUsuario;
import Modelo.Usuario;

/**
 * Servlet implementation class GestionUsuario
 */
public class GestionUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
			PrintWriter out = response.getWriter();
		
			
		int opcion = Integer.parseInt(request.getParameter("op"));
		
		
		if(opcion == 2) {
			
			//proceso logica edicion
		
			int id = Integer.parseInt(request.getParameter("id"));
			Usuario u = new Usuario();
			try {
				u.obtenerPorId(id);
				out.print(u.dameJson());
				System.out.println(u.dameJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}else if(opcion== 1) {
			DaoUsuario usuarios;
			try {
				usuarios = new DaoUsuario();
				out.print(usuarios.listarJson());

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(opcion== 3){
			
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				DaoUsuario usuarios = new DaoUsuario();
				usuarios.borrar(id);
				System.out.println("Estoy borrando " + id);
				System.out.println("Estoy opcion " + opcion);
				out.print(usuarios.listarJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(opcion==4) {
			
			int tipo = Integer.parseInt(request.getParameter("tipoUsuario"));
			
			try {
				DaoUsuario daoUsuario = new DaoUsuario();

				out.print(daoUsuario.listarJson(tipo));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
		
		
		
		
		
				
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 
		String nombre = request.getParameter("nombre");
		String mail = request.getParameter("mail");
		String tel = request.getParameter("tel");
		int permiso = Integer.parseInt(request.getParameter("permiso"));
		String id = request.getParameter("id");
		
		Usuario u;
		
		try {
			
			u = new Usuario(nombre, mail, tel, permiso);
			if(id == "") {
				

				DaoUsuario dao = new DaoUsuario();
				dao.insertar(u);
				
				
				//u.insertar();
			}else {
				
				int idInt = Integer.parseInt(id);
				u.setId(idInt);		
				u.actualizar();	
				
				//u.actualizar(Integer.parseInt(id));	

			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	
	
		
		response.sendRedirect("ListarUsuarios.html");
	
		
		

	}
}
