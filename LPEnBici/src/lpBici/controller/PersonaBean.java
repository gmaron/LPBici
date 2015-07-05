package lpBici.controller;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.AuthenticationFailedException;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import mipatronDAO.MyFactoryDAO;
import misclases.Administrador;
import misclases.Usuario;
import misservlets.Emailer;



public class PersonaBean {
	
	MyFactoryDAO f = new MyFactoryDAO();

//	private Usuario usr = new Usuario();
//	private Administrador admin = new Administrador();
	private Usuario usr = null;
	private Administrador admin = null;
	private String fechaActual;

	private String email;
	private String pass;
	
	
	private List<Usuario> usuarios = null;
	private List<Usuario> usuariosNoEliminados = null;
    private List<Usuario> usuariosFiltrados;
    private Usuario usuarioSeleccionado;
	
	
    private String Months[]= {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep"
    		, "Oct", "Nov", "Dec" };
    
	
	public PersonaBean(){
		
	}
	
	

	public String altaUsuario(){
		//System.out.println("email: "+this.email+" | "+"pass: "+this.pass);
		Usuario usuario = new Usuario(); 
		String password_generada = UUID.randomUUID().toString().substring(0, 8);
		usuario = (Usuario) f.getUsuarioDAO().recuperarUsuario(usr.getEmail());
		if (usuario == null){
			usr.setPassword(password_generada);
			System.out.println("Fecha de nacimiento: "+usr.getFechaNacimiento());
			String fecha_aux = convertirFecha(usr.getFechaNacimiento());
			usr.setFechaNacimiento(fecha_aux);
			f.getUsuarioDAO().guardarUsuario(usr);
			this.enviarContrasena();
			this.usr = new Usuario();
			RequestContext.getCurrentInstance().execute("PF('esperaAlta').hide();");
			RequestContext.getCurrentInstance().execute("PF('exitoAlta').show();");
			return null;
//			return "ExitoRegistro";
		}
		else{
			RequestContext.getCurrentInstance().execute("PF('esperaAlta').hide();");
			RequestContext.getCurrentInstance().execute("PF('errorAlta').show();");
			this.usr = new Usuario();
			return null;
//			return "FracasoRegistro";
		}				
	}
		
	private void enviarContrasena(){
		// Envio email al usuario	
		String[] emailList = {usr.getEmail()};
		Emailer smtpMailSender = new Emailer();
		
		try {
			smtpMailSender.postMail( emailList, usr.getPassword(),usr);
		} catch (AuthenticationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (javax.mail.MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
							    	
		
		
	}
	
	public String modificarPerfil(){
		if (admin==null){
			String fe= convertirFecha(this.usr.getFechaNacimiento());
			this.usr.setFechaNacimiento(fe);
			f.getUsuarioDAO().modificarUsuario(this.usr);
			RequestContext.getCurrentInstance().execute("PF('modUsuarioEspera').hide();");
			RequestContext.getCurrentInstance().execute("PF('modUsuarioExito').show();");
		}
		else{
			if (this.usr == null){
				String fe= convertirFecha(this.admin.getFechaNacimiento());
				this.admin.setFechaNacimiento(fe);
				f.getAdministradorDAO().modificarAdministrador(admin);
				RequestContext.getCurrentInstance().execute("PF('modUsuarioEspera').hide();");
				RequestContext.getCurrentInstance().execute("PF('modUsuarioExito').show();");
			}
		}
		return null;
	}
	

	
	public String AdminEliminarUsuario(){
		usuarioSeleccionado.setEliminado(true);
		f.getUsuarioDAO().eliminarUsuario(usuarioSeleccionado);
		usuariosNoEliminados = f.getUsuarioDAO().recuperarUsuariosNoEliminados();
		RequestContext.getCurrentInstance().execute("PF('delUsuarioEspera').hide();");
		RequestContext.getCurrentInstance().execute("PF('delUsuarioExito').show();");
		return null;
//		return "Exito_usuarioEliminado";
	}
	
	public String convertirFecha (String fe){
		String mes = fe.charAt(4)+""+fe.charAt(5)+""+fe.charAt(6);
		String mesNum="";
		int i;
		for (i = 0; i < 12; i++){
			if (Months[i].equals(mes)){
				if (i < 10){
					mesNum = "0"+Integer.toString(i+1);
				}else{
					mesNum = Integer.toString(i+1);
				}
			}
		}
		String dia = fe.charAt(8)+""+fe.charAt(9);
		String anio = fe.charAt(26)+""+fe.charAt(27);
		System.out.println(dia+mes+anio);
		String fecha_aux = dia+"/"+mesNum+"/"+anio;
		return fecha_aux;
	}
	
	public String AdminmodUsuario(){

		String fe= convertirFecha(usuarioSeleccionado.getFechaNacimiento());
		usuarioSeleccionado.setFechaNacimiento(fe);
		f.getUsuarioDAO().modificarUsuario(usuarioSeleccionado);
		RequestContext.getCurrentInstance().execute("PF('modUsuarioEspera').hide();");
		RequestContext.getCurrentInstance().execute("PF('modUsuarioExito').show();");
		usuarioSeleccionado = null;
		return null;
//		return "Exito_AdminmodUsuario";
	}
	
	public String login(){
		System.out.println("email: "+this.email+" | "+"pass: "+this.pass);
		Usuario usuario = new Usuario(); 
		usuario = (Usuario) f.getUsuarioDAO().recuperarUsuarioNoEliminado(this.email);
		if (usuario == null){
			Administrador admin = new Administrador();
			admin = (Administrador) f.getAdministradorDAO().recuperarAdministrador(this.email);
			if (admin == null){	
				System.out.println("email: "+this.email+" | "+"pass: "+this.pass);
				RequestContext.getCurrentInstance().execute("PF('uci').show();");
				//return "FracasoLogin";
				return null;
			}
			else{
				if (admin.getPassword().equals(this.pass)){
					this.admin = admin;
					return "ExitoLoginAdministrador";
				}
				else{
					RequestContext.getCurrentInstance().execute("PF('uci').show();");
					//return "FracasoLogin";
					return null;
				}
			}
		}
		else{
			if (usuario.getPassword().equals(this.pass)){
				this.usr = usuario;
				return "ExitoLoginUsuario";
			}
			else{
				RequestContext.getCurrentInstance().execute("PF('uci').show();");
//				return "FracasoLogin";
				return null;
			}
		}
	}
	
	public String logout() {
	this.usr = new Usuario();
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	//		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(this);
	return "/index.xhtml?faces-redirect=true";
		
	}

	public Usuario getUsr() {
		return usr;
	}


	public void setUsr(Usuario usr) {
		this.usr = usr;
	}
	
	public String getFechaActual() {
		DateFormat formFecha = new SimpleDateFormat("dd/MM/yy");
		fechaActual = formFecha.format(new Date()); 
		System.out.println(fechaActual);
	    return fechaActual;
	  }

	public void setFormatoFecha(String formatoFecha) {
		this.fechaActual = formatoFecha;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}


	public List<Usuario> getUsuarios() {
		if (usuarios == null){
			usuarios =  f.getUsuarioDAO().recuperarTodosUsuarios();
		}
		return usuarios;
	}

	
	
	public List<Usuario> getUsuariosNoEliminados() {
		if (usuariosNoEliminados == null){
			usuariosNoEliminados =  f.getUsuarioDAO().recuperarUsuariosNoEliminados();
		}
		return usuariosNoEliminados;
	}



	public void setUsuariosNoEliminados(List<Usuario> usuariosNoEliminados) {
		this.usuariosNoEliminados = usuariosNoEliminados;
	}



	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	
	 public void onRowSelect(SelectEvent event) {
	        FacesMessage msg = new FacesMessage("Usuario Seleccionado", ((Usuario) event.getObject()).getNombre());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	    public void onRowUnselect(UnselectEvent event) {
	        FacesMessage msg = new FacesMessage("Usuario no Seleccionado", ((Usuario) event.getObject()).getNombre());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
 
	
}
