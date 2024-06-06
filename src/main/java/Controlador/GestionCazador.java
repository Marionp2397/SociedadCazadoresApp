package Controlador;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Modelo.Cazador;

/**
 * Servlet implementation class GestionCazador
 */
public class GestionCazador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionCazador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String Nombre = request.getParameter("Nombre");
		String Apellidos = request.getParameter("Apellidos");
		String DNI_Cazador = request.getParameter("DNI_Cazador");
		String correo = request.getParameter("correo");
		String Direccion = request.getParameter("Direccion");
		String Localidad = request.getParameter("Localidad");
		
		

		
		
		Cazador n1 =  new Cazador(Nombre, Apellidos, DNI_Cazador, correo, Direccion, Localidad);
		System.out.println(n1.toString());
	

		try {
			n1.Insertar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}	
	
	
	
	


