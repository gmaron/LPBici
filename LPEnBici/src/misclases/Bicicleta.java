package misclases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;





@Entity
public class Bicicleta {
	private String patente;
//	private String estado; //APTA, REPARACION, DESUSO, DENUNCIADA	
	private String fechaIngreso;
	private String ubicacionActual;
	private RegistroAlquiler regActual;	
	
	@Id @GeneratedValue
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Estado> historialEstado;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<RegistroAlquiler> historial;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Denuncia> historialDenuncia;

	

	
	public Bicicleta(String patente, String estado, String fechaIngreso,
			String ubicacionActual) {
		super();
		this.patente = patente;
		this.fechaIngreso = fechaIngreso;
		this.ubicacionActual = ubicacionActual;
		this.historial = new ArrayList<RegistroAlquiler>();
		this.historialDenuncia = new ArrayList<Denuncia>();
		this.historialEstado = new ArrayList<Estado>();
		this.historialEstado.add(new Estado(estado, this.fechaIngreso));
	}
	
	public Bicicleta (){
		
	}
	
	public void retirarBicicleta(RegistroAlquiler reg){
		this.regActual = reg;
	}
	
	public void estacionarBicicleta(Denuncia den){
		//completar la fecha/hora/estacion devolucion
		historial.add(this.regActual);
		if (den != null)
			historialDenuncia.add(den);
	}
	
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	/*
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}*/
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getUbicacionActual() {
		return ubicacionActual;
	}
	public void setUbicacionActual(String ubicacionActual) {
		this.ubicacionActual = ubicacionActual;
	}
	public List<RegistroAlquiler> getHistorial() {
		return historial;
	}
	public void setHistorial(List<RegistroAlquiler> historial) {
		this.historial = historial;
	}
	public RegistroAlquiler getRegActual() {
		return regActual;
	}
	public void setRegActual(RegistroAlquiler regActual) {
		this.regActual = regActual;
	}
	public List<Denuncia> getHistorialDenuncia() {
		return historialDenuncia;
	}
	public void setHistorialDenuncia(List<Denuncia> historialDenuncia) {
		this.historialDenuncia = historialDenuncia;
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
	
	
	
	
}


