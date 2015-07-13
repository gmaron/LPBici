package lpBici.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import mipatronDAO.MyFactoryDAO;
import misclases.Bicicleta;

public class BicicletaBean {
	MyFactoryDAO f = new MyFactoryDAO();
		
	private Bicicleta bicicleta = new Bicicleta();	
	private List<Bicicleta> bicicletasNoEliminadas = null;
	private List<Bicicleta> bicicletasFiltradas;
	private Bicicleta bicicletaSeleccionada;
	
	EstacionBean est = new EstacionBean();
	
	public BicicletaBean(){
		
	}
	
	public String alta (){
		Bicicleta bici = new Bicicleta();		
		bici = (Bicicleta) f.getBicicletaDAO().recuperarBicicletaPatente(bicicleta.getPatente());		
		if (bici == null){
			Date fechaActual = new Date();
			DateFormat formato = new SimpleDateFormat("HH:mm-dd/MM/yyyy");
			String fecha_hora= formato.format(fechaActual);
			bicicleta.setFechaIngreso(fecha_hora);
			
			
			/* Aca tendriamos que ver como linkear la bici con la estacion */
			
			System.out.println("Guardo la bici con patente: "+bicicleta.getPatente());						
			f.getBicicletaDAO().guardarBicicleta(bicicleta);
			this.bicicleta = new Bicicleta();
			return "BicicletaExitoAlta";			
		}else{
			this.bicicleta = new Bicicleta();
			return "BicicletaFracasoAlta";			
		}					
	}
	
	public String ModBicicleta(){
		
		/* 
		 * Hay que ver si se modifico la Estacion, para linkearla a la nueva y si se modifico el estado
		 * agregarlo al historial.
		 * */
	
		f.getBicicletaDAO().modificarBicicleta(bicicletaSeleccionada);
		bicicletaSeleccionada = null;		
		return "BicicletaExitoModificada";
	}
	
	public String EliminarBicicleta(){
		
		/* Al eliminar una bicicleta, se deberian eliminar en cascada las cosas relacionadas a ella */
		
		f.getBicicletaDAO().eliminarBicicletaLogica(bicicletaSeleccionada);
		bicicletasNoEliminadas = f.getBicicletaDAO().recuperarBicicletasNoEliminadas();
		return "BicicletaExitoEliminada";				
	}
	
	
	public Bicicleta getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}

	public List<Bicicleta> getBicicletasNoEliminadas() {
		if (bicicletasNoEliminadas == null){
			bicicletasNoEliminadas =  f.getBicicletaDAO().recuperarBicicletasNoEliminadas();
		}
		return f.getBicicletaDAO().recuperarBicicletasNoEliminadas();		
	}

	public void setBicicletasNoEliminadas(List<Bicicleta> bicicletasNoEliminadas) {
		this.bicicletasNoEliminadas = bicicletasNoEliminadas;
	}

	public Bicicleta getBicicletaSeleccionada() {
		return bicicletaSeleccionada;
	}

	public void setBicicletaSeleccionada(Bicicleta bicicletaSeleccionada) {
		this.bicicletaSeleccionada = bicicletaSeleccionada;
	}
	
	public void onRowSelect(SelectEvent event) {
	    FacesMessage msg = new FacesMessage("Bicicleta Seleccionada", ((Bicicleta) event.getObject()).getPatente());
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	 
	public void onRowUnselect(UnselectEvent event) {
	    FacesMessage msg = new FacesMessage("Bicicleta no Seleccionada", ((Bicicleta) event.getObject()).getPatente());
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Bicicleta> getBicicletasFiltradas() {
		return bicicletasFiltradas;
	}

	public void setBicicletasFiltradas(List<Bicicleta> bicicletasFiltradas) {
		this.bicicletasFiltradas = bicicletasFiltradas;
	}
	
}
