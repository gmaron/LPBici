package lpBici.controller;

import java.io.Serializable;

import misclases.*;

import java.util.*;

//import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;




public class FiltroUsuario implements Serializable{

	/**
	 * 
	 */
	

	private static final long serialVersionUID = 1L;
	
	private List<Usuario> usuarios;
    private List<Usuario> usuariosFiltrados;
    private Usuario usuarioSeleccionado;
    
    @ManagedProperty("#{PersonaBean}")
    private PersonaBean pb;
    
  
    
	public List<Usuario> getUsuarios() {
		return usuarios;
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
