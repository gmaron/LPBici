package lpBici.controller;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private List<Estacion> estacionesFiltradas;

	private List<Bicicleta> bicicletasFiltradas;
	private List<Bicicleta> bicicletasEstacion = new ArrayList<Bicicleta>();
	private Bicicleta bicicletaSeleccionada;
	
	public EstacionBean(){
		
	}

	
	public String altaEstacion(){
		Estacion est = new Estacion();
		est = f.getEstacionDAO().recuperarEstacionNombre(this.estacion.getNombre());
		if (est == null){
			f.getEstacionDAO().guardarEstacion(estacion);
			this.estacion = new Estacion();
			return "ExitoAltaEstacion";
		}else{
			this.estacion = new Estacion();
			return "FracasoAltaEstacion";
		}
	}
	
	public String ModEstacion(){
		int tam = estacionSeleccionada.getHistorialEstado().size();
		Estado estaAnt = estacionSeleccionada.getHistorialEstado().get(tam - 1);
		if (!estaAnt.getEstado().equals(estacionSeleccionada.getEstado())){			
			estacionSeleccionada.getHistorialEstado().add(new Estado(estacionSeleccionada.getEstado(), dameFecha()));			
		}
		f.getEstacionDAO().modificarEstacion(estacionSeleccionada);
		estacionSeleccionada = null;
		return "ExitoModEstacion";
	}
			
	public String EliminarEstacion(){		
		//Si elimino la estacion, elimino todo lo que a ella corresponda.
		
		f.getEstacionDAO().eliminarEstacionLogica(estacionSeleccionada);
		return "ExitoEliminarEstacion";
	}
	
	public String mostrarEstacion(){
		
		return "ForwardEstacionGenericaAdmin";
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
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 		
		return formatoFecha.format(fechaActual);
	}
	
}
