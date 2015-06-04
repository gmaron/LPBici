package misclases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;




@Entity
public class Bicicleta {
	private String patente;
	private String estado; //APTA, REPARACION, DESUSO, DENUNCIADA	
	private String fechaIngreso;
	private String ubicacionActual;
	private RegistroAlquiler regActual;	
	
	@OneToMany(mappedBy="Estado")
	private List<Estado> historialEstado;
	
	private List<RegistroAlquiler> historial;
	
	private List<Denuncia> historialDenuncia;

	
	@Id @GeneratedValue
	private Long id;
	
	public Bicicleta(String patente, String estado, String fechaIngreso,
			String ubicacionActual) {
		super();
		this.patente = patente;
		this.estado = estado;
		this.fechaIngreso = fechaIngreso;
		this.ubicacionActual = ubicacionActual;
		this.historial = new ArrayList<RegistroAlquiler>();
		this.historialDenuncia = new ArrayList<Denuncia>();
		this.historialEstado = new ArrayList<Estado>();
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
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
	public void setHistorial(ArrayList<RegistroAlquiler> historial) {
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
	public void setHistorialDenuncia(ArrayList<Denuncia> historialDenuncia) {
		this.historialDenuncia = historialDenuncia;
	}

	public List<Estado> getHistorialEstado() {
		return historialEstado;
	}

	public void setHistorialEstado(ArrayList<Estado> historialEstado) {
		this.historialEstado = historialEstado;
	}
	
}


