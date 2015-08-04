package lpBici.controller;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;

import mipatronDAO.MyFactoryDAO;
import misclases.Bicicleta;
import misclases.Estacion;
import misclases.Estado;


public class EstacionBean {
	MyFactoryDAO f = new MyFactoryDAO();

	private Estacion estacion = new Estacion();
	private Estacion estacionSeleccionada;
	private List<Estacion> estacionesNoEliminadas = null;
	private Map<String , String> estacionesNoEliminadasMap = null;
	private Map<String , String> estacionesConEstacionamientoLibre = null;
	private Map<String , String> estacionesModificar = null;
	private List<Estacion> estacionesFiltradas;
	private List<Bicicleta> bicicletasFiltradas;
	private List<Bicicleta> bicicletasEstacion = new ArrayList<Bicicleta>();
	private Bicicleta bicicletaSeleccionada;
	private List<Bicicleta> bicicletasEstacionDisponibles = new ArrayList<Bicicleta>();
	private List<Bicicleta> bicicletasOperativas = new ArrayList<Bicicleta>();
	private List<Estacion> estacionesOperativas = new ArrayList<Estacion>();
	
	public EstacionBean(){
		
	}
	
	public String altaEstacion(){
		Estacion est = new Estacion();
		est = f.getEstacionDAO().recuperarEstacionNombre(this.estacion.getNombre());
		if (est == null){
			Estacion estacion_nueva = new Estacion(this.estacion.getNombre(), this.estacion.getLat(), this.estacion.getLon(), this.estacion.getCantEstacionamientoLibre(), this.estacion.getEstado());
			f.getEstacionDAO().guardarEstacion(estacion_nueva);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La Plata en Bici - Administracion Estaciones", "Estacion creada con exito.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			this.estacion = new Estacion();
			return "ExitoAltaEstacion";
		}else{
			this.estacion = new Estacion();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La Plata en Bici - Administracion Estaciones", "Estacion ya existente.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			return "FracasoAltaEstacion";
		}
	}
	
	public String ModEstacion(){
		int tam = estacionSeleccionada.getHistorialEstado().size();
		Estado estaAnt;
		if (tam > 0){
			 estaAnt = estacionSeleccionada.getHistorialEstado().get(tam - 1);
			 if (!estaAnt.getEstado().equals(estacionSeleccionada.getEstado())){			
					estacionSeleccionada.getHistorialEstado().add(new Estado(estacionSeleccionada.getEstado(), dameFecha()));			
			 }
		}
		else{
			estaAnt = new Estado();
		}
		
		f.getEstacionDAO().modificarEstacion(estacionSeleccionada);
		estacionSeleccionada = null;
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La Plata en Bici - Administracion Estaciones", "Estacion modificada con exito.");
		RequestContext.getCurrentInstance().showMessageInDialog(message);
		return "ExitoModEstacion";
	}
			
	public String EliminarEstacion(){		
		f.getEstacionDAO().eliminarEstacionLogica(estacionSeleccionada);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La Plata en Bici - Administracion Estaciones", "Estacion eliminada con exito.");
		RequestContext.getCurrentInstance().showMessageInDialog(message);
		return "ExitoEliminarEstacion";
	}
	
	public String mostrarEstacion(){		
		return "ForwardEstacionGenericaAdmin";
	}
	
	public String mostrarEstacionUsuario(){
		return "ForwardEstacionGenericaUsuario";		
	}
	
	public List<Bicicleta> getBicicletasEstacionDisponibles() {
		return bicicletasEstacionDisponibles;
	}

	public void setBicicletasEstacionDisponibles(
			List<Bicicleta> bicicletasEstacionDisponibles) {
		this.bicicletasEstacionDisponibles = bicicletasEstacionDisponibles;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public Map<String , String> getEstacionesNoEliminadasMap() {
		System.out.println("Entre a buscar estaciones");
		estacionesNoEliminadasMap = new HashMap<String, String>();
		estacionesNoEliminadas =  f.getEstacionDAO().recuperarEstacionesNoEliminadas();		
		for (Estacion est : estacionesNoEliminadas){
			estacionesNoEliminadasMap.put(est.getNombre(),est.getNombre());		
		}
		return estacionesNoEliminadasMap;
	}

	public void setEstacionesNoEliminadasMap(Map<String, String> estacionesNoEliminadasMap) {
		this.estacionesNoEliminadasMap = estacionesNoEliminadasMap;
	}
	
	public List<Estacion> getEstacionesNoEliminadas() {
		if (estacionesNoEliminadas == null){
			estacionesNoEliminadas = f.getEstacionDAO().recuperarEstacionesNoEliminadas();
			return estacionesNoEliminadas;
		}
		else{
			return f.getEstacionDAO().recuperarEstacionesNoEliminadas();
		}
		
	}

	public void setEstacionesNoEliminadas(List<Estacion> estacionesNoEliminadas) {
		this.estacionesNoEliminadas = estacionesNoEliminadas;
	}
	
	public List<Estacion> getEstacionesFiltradas() {
		return estacionesFiltradas;
	}
	
	public void setEstacionesFiltradas(List<Estacion> estacionesFiltradas) {
		this.estacionesFiltradas = estacionesFiltradas;
	}
	
	public Estacion getEstacionSeleccionada() {
		return estacionSeleccionada;
	}
	
	public void setEstacionSeleccionada(Estacion estacionSeleccionada) {
		this.estacionSeleccionada = estacionSeleccionada;
	}
	
	public List<Bicicleta> getBicicletasFiltradas() {
		return bicicletasFiltradas;
	}

	public void setBicicletasFiltradas(List<Bicicleta> bicicletasFiltradas) {
		this.bicicletasFiltradas = bicicletasFiltradas;
	}

	public List<Bicicleta> getBicicletasEstacion() {
		if (this.estacionSeleccionada.getListaBici().size()>0){
			this.bicicletasEstacion = new ArrayList<Bicicleta>();
			for (Bicicleta b : this.estacionSeleccionada.getListaBici()){
				if (!b.isEliminado()){
					this.bicicletasEstacion.add(b);
				}
			}
			return this.bicicletasEstacion;
		}else{
			return new ArrayList<Bicicleta>();
		}
	}

	public void setBicicletasEstacion(List<Bicicleta> bicicletasEstacion) {
		this.bicicletasEstacion = bicicletasEstacion;
	}

	public Bicicleta getBicicletaSeleccionada() {
		return bicicletaSeleccionada;
	}

	public void setBicicletaSeleccionada(Bicicleta bicicletaSeleccionada) {
		this.bicicletaSeleccionada = bicicletaSeleccionada;
	}

	private String dameFecha(){
		
		Date fechaActual = new Date();
		DateFormat formatoFecha = new SimpleDateFormat("HH:mm-dd/MM/yyyy"); 		
		return formatoFecha.format(fechaActual);
	}

	public List<Bicicleta> getBicicletasOperativas() {
		return bicicletasOperativas;
	}

	public void setBicicletasOperativas(List<Bicicleta> bicicletasOperativas) {
		this.bicicletasOperativas = bicicletasOperativas;
	}

	public List<Estacion> getEstacionesOperativas() {
		if (this.estacionesOperativas == null){
			this.estacionesOperativas = f.getEstacionDAO().recuperarEstacionesOperativas();
			return this.estacionesOperativas;
		}
		else{
			return  f.getEstacionDAO().recuperarEstacionesOperativas();
		}
	}

	public void setEstacionesOperativas(List<Estacion> estacionesOperativas) {
		this.estacionesOperativas = estacionesOperativas;
	}

	public Map<String, String> getEstacionesConEstacionamientoLibre() {
		estacionesConEstacionamientoLibre = new HashMap<String, String>();
		estacionesNoEliminadas =  f.getEstacionDAO().recuperarEstacionesNoEliminadas();		
		for (Estacion est : estacionesNoEliminadas){
			if (est.getCantEstacionamientoLibre()>0)
				estacionesConEstacionamientoLibre.put(est.getNombre(),est.getNombre());		
		}
		
		return estacionesConEstacionamientoLibre;
	}

	public void setEstacionesConEstacionamientoLibre(
			Map<String, String> estacionesConEstacionamientoLibre) {
		this.estacionesConEstacionamientoLibre = estacionesConEstacionamientoLibre;
	}

	public Map<String, String> getEstacionesModificar() {
		return estacionesModificar;
	}
	
	public Map<String, String> estacionesModificar (Bicicleta bici){
		estacionesModificar = new HashMap<String, String>();
		estacionesNoEliminadas =  f.getEstacionDAO().recuperarEstacionesNoEliminadas();		
		for (Estacion est : estacionesNoEliminadas){
			if ((est.getCantEstacionamientoLibre()>0)||(est.getNombre().equals(bici.getUbicacionActual())))
				estacionesModificar.put(est.getNombre(),est.getNombre());
		}
		
		return estacionesModificar;
	}
			
	public void setEstacionesModificar(Map<String, String> estacionesModificar) {
		this.estacionesModificar = estacionesModificar;
	}
		
}
