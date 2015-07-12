package lpBici.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mipatronDAO.MyFactoryDAO;
import misclases.Estacion;


public class EstacionBean {
	MyFactoryDAO f = new MyFactoryDAO();

	private Estacion estacion = new Estacion();	
	private List<Estacion> estacionesNoEliminadas = null;
	private Map<String , String> estacionesNoEliminadasMap = null;

	
	public EstacionBean(){
		
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
		return estacionesNoEliminadas;
	}

	public void setEstacionesNoEliminadas(List<Estacion> estacionesNoEliminadas) {
		this.estacionesNoEliminadas = estacionesNoEliminadas;
	}


	
	
	
}
