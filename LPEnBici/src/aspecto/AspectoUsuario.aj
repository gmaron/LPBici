package aspecto;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mipatronDAO.MyFactoryDAO;
import misclases.Usuario;
public aspect AspectoUsuario {
	MyFactoryDAO f = new MyFactoryDAO();
	
	
	public pointcut guardarUsuarioPointcut(Usuario usuario):execution( void mipatronDAO.UsuarioHJPADAO.guardarUsuario(Usuario)) && args(usuario);
	
	after(Usuario usr) : guardarUsuarioPointcut(usr){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("GuardarUsuario",fecha_hora,usr.toString(),usr.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de guardarUsuario apellido ->  "+usr.getApellido());
	       
	}

}
