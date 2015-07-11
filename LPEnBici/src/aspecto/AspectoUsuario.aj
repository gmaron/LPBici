package aspecto;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import mipatronDAO.MyFactoryDAO;
import misclases.Bicicleta;
import misclases.Denuncia;
import misclases.Estacion;
import misclases.RegistroAlquiler;
import misclases.Usuario;
import misclases.Administrador;
public aspect AspectoUsuario {
	MyFactoryDAO f = new MyFactoryDAO();
	
	/* Auditoria sobre los metodos del usuario */
	public pointcut guardarUsuarioPointcut(Usuario usuario):execution( void mipatronDAO.UsuarioHJPADAO.guardarUsuario(Usuario)) && args(usuario);
	public pointcut ModificarUsuarioPointcut(Usuario usuario):execution( void mipatronDAO.UsuarioHJPADAO.modificarUsuario(Usuario)) && args(usuario);
	public pointcut EliminarUsuarioPointcut(Usuario usuario):execution( void mipatronDAO.UsuarioHJPADAO.eliminarUsuarioLogica(Usuario)) && args(usuario);

	
	/* Auditoria sobre los metodos del Administrador */
	public pointcut guardarAdministradorPointcut(Administrador admin):execution( void mipatronDAO.AdministradorHJPADAO.guardarAdministrador(Administrador)) && args(admin);
	public pointcut ModificarAdministradorPointcut(Administrador admin):execution( void mipatronDAO.AdministradorHJPADAO.modificarAdministrador(Administrador)) && args(admin);
	public pointcut EliminarAdministradorPointcut(Administrador admin):execution( void mipatronDAO.AdministradorHJPADAO.eliminarAdministradorLogica(Administrador)) && args(admin);

	/* Auditoria sobre los metodos de Bicicleta */
	public pointcut guardarBicicletaPointcut(Bicicleta bici):execution( void mipatronDAO.BicicletaHJPADAO.guardarBicicleta(Bicicleta)) && args(bici);
	public pointcut ModificarBicicletaPointcut(Bicicleta bici):execution( void mipatronDAO.BicicletaHJPADAO.modificarBicicleta(Bicicleta)) && args(bici);
	public pointcut EliminarBicicletaPointcut(Bicicleta bici):execution( void mipatronDAO.BicicletaHJPADAO.eliminarBicicletaLogica(Bicicleta)) && args(bici);
	
	/* Auditoria sobre los metodos de Estacion */
	public pointcut guardarEstacionPointcut(Estacion estacion):execution( void mipatronDAO.EstacionHJPADAO.guardarEstacion(Estacion)) && args(estacion);
	public pointcut ModificarEstacionPointcut(Estacion estacion):execution( void mipatronDAO.EstacionHJPADAO.modificarEstacion(Estacion)) && args(estacion);
	public pointcut EliminarEstacionPointcut(Estacion estacion):execution( void mipatronDAO.EstacionHJPADAO.eliminarEstacionLogica(Estacion)) && args(estacion);
	
	/* Auditoria sobre los metodos de Denuncia */
	public pointcut guardarDenunciaPointcut(Denuncia denuncia):execution( void mipatronDAO.DenunciaHJPADAO.guardarDenuncia(Denuncia)) && args(denuncia);
	public pointcut ModificarDenunciaPointcut(Denuncia denuncia):execution( void mipatronDAO.DenunciaHJPADAO.modificarDenuncia(Denuncia)) && args(denuncia);
	public pointcut EliminarDenunciaPointcut(Denuncia denuncia):execution( void mipatronDAO.DenunciaHJPADAO.eliminarDenunciaLogica(Denuncia)) && args(denuncia);

	
	/* Auditoria sobre los metodos de Alquiler */
	public pointcut guardarRegistroAlquilerPointcut(RegistroAlquiler registroAlquiler):execution( void mipatronDAO.RegistroAlquilerHJPADAO.guardarRegistroAlquiler(RegistroAlquiler)) && args(registroAlquiler);
	public pointcut ModificarRegistroAlquilerPointcut(RegistroAlquiler registroAlquiler):execution( void mipatronDAO.RegistroAlquilerHJPADAO.modificarRegistroAlquiler(RegistroAlquiler)) && args(registroAlquiler);
	public pointcut EliminarRegistroAlquilerPointcut(RegistroAlquiler registroAlquiler):execution( void mipatronDAO.RegistroAlquilerHJPADAO.eliminarRegistroAlquilerLogica(RegistroAlquiler)) && args(registroAlquiler);

	
	
	/* Implementacion para el Usuario */
	
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
	
	/* Implementacion para Bicicleta */
	
	after(Bicicleta bici) : guardarBicicletaPointcut(bici){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("GuardarBicicleta",fecha_hora,bici.toString(),bici.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de guardarBicicleta patente ->  "+bici.getPatente());
	       
		
	}
	
	after(Bicicleta bici) : ModificarBicicletaPointcut(bici){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("ModificarBicicleta",fecha_hora,bici.toString(),bici.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de modificarBicicleta patente ->  "+bici.getPatente());
	       
	}

	before(Bicicleta bici) : EliminarBicicletaPointcut(bici){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("EliminarBicicleta",fecha_hora,bici.toString(),bici.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de EliminarBicicleta patente ->  "+bici.getPatente());
	       
	}

	/* Implementacion para Estacion */
	
	after(Estacion estacion) : guardarEstacionPointcut(estacion){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("GuardarEstacion",fecha_hora,estacion.toString(),estacion.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de guardarEstacion nombre ->  "+estacion.getNombre());
	       
		
	}
	
	after(Estacion estacion) : ModificarEstacionPointcut(estacion){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("ModificarEstacion",fecha_hora,estacion.toString(),estacion.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de modificarEstacion nombre ->  "+estacion.getNombre());
	       
	}

	before(Estacion estacion) : EliminarEstacionPointcut(estacion){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("EliminarEstacion",fecha_hora,estacion.toString(),estacion.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de EliminarEstacion nombre ->  "+estacion.getNombre());
	       
	}

	/* Implementacion para Denuncia */
	
	after(Denuncia denuncia) : guardarDenunciaPointcut(denuncia){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("GuardarDenuncia",fecha_hora,denuncia.toString(),denuncia.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de guardarDenuncia comentario ->  "+denuncia.getComentario());
	       
		
	}
	
	after(Denuncia denuncia) : ModificarDenunciaPointcut(denuncia){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("ModificarDenuncia",fecha_hora,denuncia.toString(),denuncia.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de modificarDenuncia comentario ->  "+denuncia.getComentario());
	       
	}

	before(Denuncia denuncia) : EliminarDenunciaPointcut(denuncia){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("EliminarDenuncia",fecha_hora,denuncia.toString(),denuncia.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de EliminarDenuncia comentario ->  "+denuncia.getComentario());
	       
	}

	/* Implementacion para Denuncia */
	
	after(RegistroAlquiler registroAlquiler) : guardarRegistroAlquilerPointcut(registroAlquiler){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("GuardarRegistroAlquiler",fecha_hora,registroAlquiler.toString(),registroAlquiler.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de guardarRegistroAlquiler ID ->  "+registroAlquiler.getId());
	       
		
	}
	
	after(RegistroAlquiler registroAlquiler) : ModificarRegistroAlquilerPointcut(registroAlquiler){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("ModificarRegistroAlquiler",fecha_hora,registroAlquiler.toString(),registroAlquiler.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de modificarRegistroAlquiler ID ->  "+registroAlquiler.getId());
	       
	}

	before(RegistroAlquiler registroAlquiler) : EliminarRegistroAlquilerPointcut(registroAlquiler){
		Date fechaActual = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
		String fecha_hora= formato.format(fechaActual);
		Auditoria aud = new Auditoria("EliminarRegistroAlquiler",fecha_hora,registroAlquiler.toString(),registroAlquiler.getId());
		f.getAuditoriaDAO().guardarAuditoria(aud);
		
		System.out.println("Despues del metodo de EliminarRegistroAlquiler ID ->  "+registroAlquiler.getId());	       
	}

}
