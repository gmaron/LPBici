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
import misclases.Bicicleta;
import misclases.Denuncia;
import misclases.Estacion;
import misclases.RegistroAlquiler;
import misclases.Usuario;
import misservlets.Emailer;



public class PersonaBean {
	


	MyFactoryDAO f = new MyFactoryDAO();

//	private Usuario usr = new Usuario();
//	private Administrador admin = new Administrador();
	private Usuario usr = new Usuario();
	private Administrador admin = new Administrador();
	private String fechaActual;
	
	private String email;
	private String pass;
	
	
	private List<Usuario> usuarios = null;
	private List<Usuario> usuariosNoEliminados = null;
    private List<Usuario> usuariosFiltrados;
    private Usuario usuarioSeleccionado;
	
	
    private String Months[]= {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep"
    		, "Oct", "Nov", "Dec" };
    
    
    private List<RegistroAlquiler> listaAlquileres;
    private List<RegistroAlquiler> alquileresFiltrados;
    private RegistroAlquiler alquilerSeleccionado;
    private Denuncia denuncia = new Denuncia();
	
    private List<RegistroAlquiler> listaAlquileresHistoricos;
    
    private List<RegistroAlquiler> listaAlquileresActivosAdmin;
    private List<RegistroAlquiler> listaAlquileresHistoricosAdmin;

    
    private boolean errorLogin = false;
    private boolean modAdmin = false;
    private boolean modUsr = false;
    
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
//			RequestContext.getCurrentInstance().execute("PF('esperaAlta').hide();");			
//			RequestContext.getCurrentInstance().execute("PF('exitoAlta').show();");			
			//return null;
			return "ExitoRegistro";
		}
		else{			
//			RequestContext.getCurrentInstance().execute("PF('esperaAlta').hide();");
//			RequestContext.getCurrentInstance().execute("PF('errorAlta').show();");
			this.usr = new Usuario();
			this.errorLogin = true;
			return null;
			//return "FracasoRegistro";
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
//			RequestContext.getCurrentInstance().execute("PF('modUsuarioEspera').hide();");
//			RequestContext.getCurrentInstance().execute("PF('modUsuarioExito').show();");
			return "ExitoModPerfilUsuario";
		}
		else{
			if (this.usr == null){
				String fe= convertirFecha(this.admin.getFechaNacimiento());
				this.admin.setFechaNacimiento(fe);
				f.getAdministradorDAO().modificarAdministrador(admin);
//				RequestContext.getCurrentInstance().execute("PF('modUsuarioEspera').hide();");
//				RequestContext.getCurrentInstance().execute("PF('modUsuarioExito').show();");
				return "ExitoModPerfilAdministrador";
			}
			return  null;
		}
	}
	

	
	public String AdminEliminarUsuario(){
		usuarioSeleccionado.setEliminado(true);
		f.getUsuarioDAO().eliminarUsuarioLogica(usuarioSeleccionado);
		usuariosNoEliminados = f.getUsuarioDAO().recuperarUsuariosNoEliminados();
//		RequestContext.getCurrentInstance().execute("PF('delUsuarioEspera').hide();");
//		RequestContext.getCurrentInstance().execute("PF('delUsuarioExito').show();");
//		return null;
		return "Exito_usuarioEliminado";
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
//		RequestContext.getCurrentInstance().execute("PF('modUsuarioEspera').hide();");
//		RequestContext.getCurrentInstance().execute("PF('modUsuarioExito').show();");
		usuarioSeleccionado = null;
//		return null;
		return "Exito_AdminmodUsuario";
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
					this.usr = null;
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
				this.admin = null;
				return "ExitoLoginUsuario";
			}
			else{
				RequestContext.getCurrentInstance().execute("PF('uci').show();");
//				return "FracasoLogin";
				return null;
			}
		}
	}
	
	public String logout(){
		this.usr = new Usuario();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(this);
		return "/index.xhtml?faces-redirect=true";	
	}

	
	public String retirarBicicleta(Estacion estacionSeleccionada){				
		Bicicleta b = estacionSeleccionada.dameBiciDisponible();	
		if (b != null){
			RegistroAlquiler reg = new RegistroAlquiler(estacionSeleccionada, this.usr, b);
			b.setAlquilada(true);
			estacionSeleccionada.retirarBicicleta();
//			estacionSeleccionada.setCantBiciDisponible(estacionSeleccionada.getCantBiciDisponible()-1);
//			estacionSeleccionada.setCantEstacionamientoLibre(estacionSeleccionada.getCantEstacionamientoLibre()+1);
			f.getBicicletaDAO().modificarBicicleta(b);		
			f.getEstacionDAO().modificarEstacion(estacionSeleccionada);
			f.getRegAlquilerDAO().guardarRegistroAlquiler(reg);
			return "ExitoRetiroBicicleta";
		}else
			return "FracasoRetiroBicicleta";		
	}
	
	public String devolverBicicleta (){
		String fecha = dameFecha();
		String hora = dameHora();
		alquilerSeleccionado.setFechaEntrada(fecha);
		alquilerSeleccionado.setHoraEntrada(hora);
		
		System.out.println("alquilerSeleccionado.estacionEntrada.nombre: "+alquilerSeleccionado.getNombreEstacionEntrada());
		
		Estacion est = f.getEstacionDAO().recuperarEstacionNombre(alquilerSeleccionado.getNombreEstacionEntrada());
		alquilerSeleccionado.setEstacionEntrada(est);
//		est.setCantBiciDisponible(est.getCantBiciDisponible()+1);
//		est.setCantEstacionamientoLibre(est.getCantEstacionamientoLibre()-1);
		Bicicleta b = alquilerSeleccionado.getBicicleta();
		if  (est.estacionarBicicleta(b,alquilerSeleccionado.getEstacionSalida())){
			alquilerSeleccionado.getEstacionSalida().getListaBici().remove(b);							
		}	
		
		b.setAlquilada(false);
		b.setUbicacionActual(est.getNombre());
		
		denuncia.setBicicleta(b);
		denuncia.setUsuarioDenuncia(usr);
		
		f.getDenunciaDAO().guardarDenuncia(denuncia);
		//f.getEstacionDAO().modificarEstacion(alquilerSeleccionado.getEstacionSalida());
		//f.getEstacionDAO().modificarEstacion(est);
		//f.getBicicletaDAO().modificarBicicleta(b); -- BORRE ESTO, CREO QUE ESTA DE MAS
		f.getRegAlquilerDAO().modificarRegistroAlquiler(alquilerSeleccionado);

		return null;
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

	

	public RegistroAlquiler getAlquilerSeleccionado() {
		return alquilerSeleccionado;
	}



	public void setAlquilerSeleccionado(RegistroAlquiler alquilerSeleccionado) {
		this.alquilerSeleccionado = alquilerSeleccionado;
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
	
	
	
	 public List<RegistroAlquiler> getListaAlquileres() {
		listaAlquileres = f.getRegAlquilerDAO().recuperarAlquilerUsuario(usr);
		return listaAlquileres;
	}



	public void setListaAlquileres(List<RegistroAlquiler> listaAlquileres) {
		this.listaAlquileres = listaAlquileres;
	}

	
	public List<RegistroAlquiler> getListaAlquileresHistoricos() {
		listaAlquileresHistoricos = f.getRegAlquilerDAO().recuperarAlquilerHistoricoUsuario(usr);
		return listaAlquileresHistoricos;
	}

	public void setListaAlquileresHistoricos(List<RegistroAlquiler> listaAlquileresHistoricos) {
		this.listaAlquileresHistoricos = listaAlquileresHistoricos;
	}

	public List<RegistroAlquiler> getListaAlquileresActivosAdmin() {
		listaAlquileresActivosAdmin = f.getRegAlquilerDAO().recuperarAlquileres("Activo");
		return listaAlquileresActivosAdmin;
	}

	public void setListaAlquileresActivosAdmin(List<RegistroAlquiler> listaAlquileresActivosAdmin) {
		this.listaAlquileresActivosAdmin = listaAlquileresActivosAdmin;
	}

	public List<RegistroAlquiler> getListaAlquileresHistoricosAdmin() {
		listaAlquileresHistoricosAdmin = f.getRegAlquilerDAO().recuperarAlquileres("Historico");
		return listaAlquileresHistoricosAdmin;
	}

	public void setListaAlquileresHistoricosAdmin(List<RegistroAlquiler> listaAlquileresHistoricosAdmin) {
		this.listaAlquileresHistoricosAdmin = listaAlquileresHistoricosAdmin;
	}



	public List<RegistroAlquiler> getAlquileresFiltrados() {
		return alquileresFiltrados;
	}



	public void setAlquileresFiltrados(List<RegistroAlquiler> alquileresFiltrados) {
		this.alquileresFiltrados = alquileresFiltrados;
	}

	

	public Denuncia getDenuncia() {
		return denuncia;
	}



	public void setDenuncia(Denuncia denuncia) {
		this.denuncia = denuncia;
	}



	public void onRowSelect(SelectEvent event) {
	        FacesMessage msg = new FacesMessage("Usuario Seleccionado", ((Usuario) event.getObject()).getNombre());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	    public void onRowUnselect(UnselectEvent event) {
	        FacesMessage msg = new FacesMessage("Usuario no Seleccionado", ((Usuario) event.getObject()).getNombre());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
		private String dameFecha(){
			
			Date fechaActual = new Date();
			DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 		
			return formatoFecha.format(fechaActual);
		}
		
		private String dameHora(){
			
			Date fechaActual = new Date();
			DateFormat formatoFecha = new SimpleDateFormat("HH:mm"); 		
			return formatoFecha.format(fechaActual);
		}


		public boolean isErrorLogin() {
			return errorLogin;
		}

		public void setErrorLogin(boolean errorLogin) {
			this.errorLogin = errorLogin;
		}
		
		
}
