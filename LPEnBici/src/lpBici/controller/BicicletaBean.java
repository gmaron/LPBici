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
import misclases.Denuncia;
import misclases.Estacion;
import misclases.Estado;

public class BicicletaBean {
	MyFactoryDAO f = new MyFactoryDAO();
		
	private Bicicleta bicicleta = new Bicicleta();	
	private List<Bicicleta> bicicletasNoEliminadas = null;
	private List<Bicicleta> bicicletasFiltradas;	
	private Bicicleta bicicletaSeleccionada;
	
	private List<Denuncia> denunciasBicicletas;
	private List<Denuncia> denunciasFiltradas;
	
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
			
			/*Esta bicicleta es la que voy a linkear con la estacion, si no la creo, no llamo al constructor, 
			 * entonces nunca creo las listas historial y demas... 
			 * */
			Bicicleta bicle = new Bicicleta(bicicleta.getPatente(), bicicleta.getEstado(), dameFecha(), bicicleta.getUbicacionActual());
			
			Estacion est = f.getEstacionDAO().recuperarEstacionNombre(bicicleta.getUbicacionActual());
			est.agregarBicicleta(bicle);
			
//			est.setCantBiciDisponible(est.getCantBiciDisponible()+1);
//			est.getListaBici().add(bicle);			
			
			System.out.println("Guardo la bici con patente: "+bicicleta.getPatente()+" en la estacion: "+est.getNombre());
			f.getEstacionDAO().modificarEstacion(est);
			//f.getBicicletaDAO().guardarBicicleta(bicicleta);
			this.bicicleta = new Bicicleta();
			return "BicicletaExitoAlta";			
		}else{
			this.bicicleta = new Bicicleta();
			return "BicicletaFracasoAlta";			
		}					
	}
	
	public String ModBicicleta(){		
		/* 
		 * Hay que ver si se modifico la Estacion, para linkearla a la nueva
		 * */		
		int tam = bicicletaSeleccionada.getHistorialEstado().size();
		if (tam > 0){
			Estado estaAnt = bicicletaSeleccionada.getHistorialEstado().get(tam - 1);
			if (!estaAnt.getEstado().equals(bicicletaSeleccionada.getEstado())){			
				bicicletaSeleccionada.getHistorialEstado().add(new Estado(bicicletaSeleccionada.getEstado(), dameFecha()));			
			}	
			f.getBicicletaDAO().modificarBicicleta(bicicletaSeleccionada);
			bicicletaSeleccionada = null;		
			return "BicicletaExitoModificada";
		}
		else{
			return "BicicletaFracasoModificada";
		}
	}
	
	public String EliminarBicicleta(){	
		/* Al eliminar una bicicleta, se deberian eliminar en cascada las cosas relacionadas a ella */		
		f.getBicicletaDAO().eliminarBicicletaLogica(bicicletaSeleccionada);
		bicicletasNoEliminadas = f.getBicicletaDAO().recuperarBicicletasNoEliminadas();
		return "BicicletaExitoEliminada";				
	}
	

	public List<Denuncia> getDenunciasBicicletas() {		
		if (denunciasBicicletas == null){
			denunciasBicicletas = f.getDenunciaDAO().recuperarDenuncias();
			return denunciasBicicletas;
		}
		else{
			return f.getDenunciaDAO().recuperarDenuncias();
		}		
	}

	
	
	
	public void setDenunciasBicicletas(List<Denuncia> denunciasBicicletas) {
		this.denunciasBicicletas = denunciasBicicletas;
	}

	
	
	public List<Denuncia> getDenunciasFiltradas() {
		return denunciasFiltradas;
	}

	public void setDenunciasFiltradas(List<Denuncia> denunciasFiltradas) {
		this.denunciasFiltradas = denunciasFiltradas;
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

	private String dameFecha(){
		
		Date fechaActual = new Date();
		DateFormat formatoFecha = new SimpleDateFormat("HH:mm-dd/MM/yyyy"); 		
		return formatoFecha.format(fechaActual);
	}
	
	
}
