package Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


import Modelo.Licencia;

/**
 * Servlet implementation class GestionLicencia
 */
public class GestionLicencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionLicencia() {
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
		
		String Licencia_ID = request.getParameter("Licencia_ID");
		String Fecha_Emision = request.getParameter("Fecha_Emision");
		String Num_Licencia = request.getParameter("Num_Licencia");
		String Tipo_Licencia = request.getParameter("Tipo_Licencia");
		String DNI_Cazador = request.getParameter("DNI_Cazador");


		Licencia l1 =  new Licencia(Licencia_ID,Fecha_Emision, Num_Licencia, Tipo_Licencia, DNI_Cazador);
		System.out.println(l1.toString());
		
		try {
			l1.insertar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}


