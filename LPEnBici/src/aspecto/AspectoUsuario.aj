package aspecto;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mipatronDAO.MyFactoryDAO;
import misclases.Usuario;
import misclases.Administrador;
public aspect AspectoUsuario {
	MyFactoryDAO f = new MyFactoryDAO();
	
	/* Auditoria sobre los metodos del usuario */
	public pointcut guardarUsuarioPointcut(Usuario usuario):execution( void mipatronDAO.UsuarioHJPADAO.guardarUsuario(Usuario)) && args(usuario);
	public pointcut ModificarUsuarioPointcut(Usuario usuario):execution( void mipatronDAO.UsuarioHJPADAO.modificarUsuario(Usuario)) && args(usuario);
	public pointcut EliminarUsuarioPointcut(Usuario usuario):execution( void mipatronDAO.UsuarioHJPADAO.eliminarUsuario(Usuario)) && args(usuario);

	
	/* Auditoria sobre los metodos del Administrador */
	public pointcut guardarAdministradorPointcut(Administrador admin):execution( void mipatronDAO.AdministradorHJPADAO.guardarAdministrador(Administrador)) && args(admin);
	public pointcut ModificarAdministradorPointcut(Administrador admin):execution( void mipatronDAO.AdministradorHJPADAO.modificarAdministrador(Administrador)) && args(admin);
	public pointcut EliminarAdministradorPointcut(Administrador admin):execution( void mipatronDAO.AdministradorHJPADAO.eliminarAdministrador(Administrador)) && args(admin);
	
	
	after(Usuario usr) : guardarUsuarioPointcut(usr){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("GuardarUsuario",fecha_hora,usr.toString(),usr.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de guardarUsuario apellido ->  "+usr.getApellido());
	       
		
	}
	
	after(Usuario usr) : ModificarUsuarioPointcut(usr){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("ModificarUsuario",fecha_hora,usr.toString(),usr.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de modificarUsuario apellido ->  "+usr.getApellido());
	       
	}

	before(Usuario usr) : EliminarUsuarioPointcut(usr){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("EliminarUsuario",fecha_hora,usr.toString(),usr.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de modificarUsuario apellido ->  "+usr.getApellido());
	       
	}
	
	
	/* Implementacion para el Administrador */
	
	after(Administrador admin) : guardarAdministradorPointcut(admin){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("GuardarAdministrador",fecha_hora,admin.toString(),admin.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de guardarAdministrador apellido ->  "+admin.getApellido());
	       
		
	}
	
	after(Administrador admin) : ModificarAdministradorPointcut(admin){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("ModificarAdministrador",fecha_hora,admin.toString(),admin.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de modificarAdministrador apellido ->  "+admin.getApellido());
	       
	}

	before(Administrador admin) : EliminarAdministradorPointcut(admin){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("EliminarAdministrador",fecha_hora,admin.toString(),admin.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de EliminarAdministrador apellido ->  "+admin.getApellido());
	       
	}
	
}
