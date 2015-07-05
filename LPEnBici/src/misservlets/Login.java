package misservlets;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("text/html"); //setea el tipo de respuesta 
//		PrintWriter out = response.getWriter(); //Habilita la salida para poder escribir
		String usuario = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		
		String usuario_admin = this.getServletContext().getInitParameter("emailAdmin");
		String pass_admin = this.getServletContext().getInitParameter("passAdmin");
		
		if (usuario_admin.equals(usuario)&&(pass_admin.equals(pass))){
			//enviar a pagina administrador
			response.sendRedirect("LoginAdministrador.html");
			//out.print("<script language='JavaScript'>alert('bienvenido administrador');</script>");
		}
		else{
			
			boolean valido = Registro.usuario_registrado.containsKey(usuario);
			if (valido&&(Registro.usuario_registrado.get(usuario).equals(pass))){
				//enviar a la pagina del usuario
				response.sendRedirect("LoginUsuario.html");
				//out.print("<script language='JavaScript'>alert('bienvenido usuario');</script>");
			}
			else{
				//out.print("<script language='JavaScript'>alert('El usuario no existe');</script>");
				response.sendRedirect("Inicio.html");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
