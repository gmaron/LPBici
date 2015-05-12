package paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import misclases.*;

/**
 * Servlet implementation class Registro
 */
@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected static HashMap<String,String> usuario_registrado = new HashMap<String, String>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html"); //setea el tipo de respuesta 
		PrintWriter out = response.getWriter(); //Habilita la salida para poder escribir
		
		String usuario_nombre = request.getParameter("nombre");
		String usuario_apellido = request.getParameter("apellido");
		String usuario_dni = request.getParameter("dni");
		String usuario_email = request.getParameter("email");
		String usuario_domicilio = request.getParameter("domicilio");
		String usuario_sexo = request.getParameter("sexo");
		String usuario_fechanac = request.getParameter("fechanac");
		String usuario_pass = "1234567"; //hacer un random
		
		if (!usuario_registrado.containsKey(usuario_email)){
			Usuario nuevo_usuario = new Usuario(usuario_nombre, 
					usuario_apellido, usuario_email, usuario_dni, usuario_domicilio,
					usuario_sexo, usuario_fechanac);
			// Ligo objeto (lista de usuarios) al alcance de aplicacion
			usuario_registrado.put(usuario_email, usuario_pass);
			
			// Envio email al usuario	
			String[] emailList = {usuario_email};
			Emailer smtpMailSender = new Emailer();
					
			try {
				smtpMailSender.postMail( emailList, usuario_pass,nuevo_usuario);
			} catch (AuthenticationFailedException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			} 
					    		
								
			
			
			ServletContext sc=this.getServletConfig().getServletContext();
			sc.setAttribute(nuevo_usuario.getEmail(),nuevo_usuario);
			sc.setAttribute("usuariosRegistrados",usuario_registrado);
			//out.print("<script language='JavaScript'>alert('Registro exitoso');</script>");
			response.sendRedirect("Inicio.html");
			//informar registro exitoso y envio de contraseaña
		}
		else{
			//informar que el usuario ya esta registrado
			//out.print("<script language='JavaScript'>alert('Usuario ya registrado');</script>");
			response.sendRedirect("Inicio.html");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
