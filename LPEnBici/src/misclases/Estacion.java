package misclases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Estacion {
	private String nombre;
	private String lat;
	private String lon;
	private int cantEstacionamientoLibre;
	private String estado; //OPERATIVA, CERRADA, EN CONSTRUCCION
	private int cantBiciDisponible;
	
	boolean eliminado;
	
	@Id @GeneratedValue
	private Long id;
	
	@OneToMany (cascade = CascadeType.ALL)
	private List<Estado> historialEstado;
		
	@OneToMany (cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.EAGER) 
 	private List<Bicicleta> listaBici; //el length = cantidad de bicicletas
		
	public Estacion(String nombre, String lat, String lon,
			int cantEstacionamientoLibre, String estado) {
		super();
		this.nombre = nombre;
		this.lat = lat;
		this.lon = lon;
		this.cantEstacionamientoLibre = cantEstacionamientoLibre;
		this.estado = estado;		
		this.listaBici = new ArrayList<Bicicleta>();
		this.historialEstado = new ArrayList<Estado>();
		this.historialEstado.add(new Estado(estado, dameFecha()));
		this.cantBiciDisponible = listaBici.size();
		this.eliminado = false;
	}
	
	
	private String dameFecha(){
		
		Date fechaActual = new Date();
		DateFormat formatoFecha = new SimpleDateFormat("HH:mm-dd/MM/yyyy"); 		
		return formatoFecha.format(fechaActual);
	}
	
	public Estacion(){
		
	}			
	
	
	public Bicicleta dameBiciDisponible(){		 
		if (this.cantBiciDisponible > 0){
			for (Bicicleta b: this.listaBici){
				if(!b.isAlquilada()){
					return b; 
				}					
			}			
		}
		return null;					
	}
	
	public void agregarBicicleta(Bicicleta bici){
		this.listaBici.add(bici);
		this.cantEstacionamientoLibre--;
		this.cantBiciDisponible++;		
	}
	
	public boolean estacionarBicicleta(Bicicleta bici,Estacion estSalida){
		if (!this.nombre.equals(estSalida.getNombre())){
			this.listaBici.add(bici);
			this.cantEstacionamientoLibre--;
			this.cantBiciDisponible++;
			return true;
		}else{
			this.cantEstacionamientoLibre--;
			this.cantBiciDisponible++;
			return false;
		}
	}
	
	public void retirarBicicleta(){
		this.cantEstacionamientoLibre++;
		this.cantBiciDisponible--;
	}
	
	
	public int getCantBiciDisponible() {		
		return cantBiciDisponible;
	}


	public void setCantBiciDisponible(int cantBiciDisponible) {
		this.cantBiciDisponible = cantBiciDisponible;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

		
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public int getCantEstacionamientoLibre() {
		return cantEstacionamientoLibre;
	}
	public void setCantEstacionamientoLibre(int cantEstacionamientoLibre) {
		this.cantEstacionamientoLibre = cantEstacionamientoLibre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
		//this.historialEstado.add(new Estado(estado, dameFecha()));
	}
	public List<Bicicleta> getListaBici() {
		return listaBici;
	}
	public void setListaBici(List<Bicicleta> listaBici) {
		this.listaBici = listaBici;
	}


	public List<Estado> getHistorialEstado() {
		return historialEstado;
	}


	public void setHistorialEstado(List<Estado> historialEstado) {
		this.historialEstado = historialEstado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Override
	public String toString() {
		return "Estacion";
	} 
	
	
	

	public String informacion() {
		String informacion = "<b>Estacion: </b>"+this.getNombre()+"<br>"
				+ "<b>Cant Bicicletas aptas:</b> "+this.getCantBiciDisponible()+"<br>"
				+ "<b>Cant de estacionamientos libres:</b> "+this.getCantEstacionamientoLibre()+"<br>"
				+ "<b>Estado: </b>"+this.getEstado();
		
		return informacion;
	}
	


	
	
}
