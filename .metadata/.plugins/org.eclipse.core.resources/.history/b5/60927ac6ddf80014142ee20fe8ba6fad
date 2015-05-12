package misclases;

import java.util.ArrayList;





public class Bicicleta {
	private String patente;
	private String estado; //APTA, REPARACION, DESUSO, DENUNCIADA
	private ArrayList<Estado> historialEstado;
	private String fechaIngreso;
	private String ubicacionActual;
	private ArrayList<RegistroAlquiler> historial;
	private RegistroAlquiler regActual;
	private ArrayList<Denuncia> historialDenuncia;

	
	
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
	public ArrayList<RegistroAlquiler> getHistorial() {
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
	public ArrayList<Denuncia> getHistorialDenuncia() {
		return historialDenuncia;
	}
	public void setHistorialDenuncia(ArrayList<Denuncia> historialDenuncia) {
		this.historialDenuncia = historialDenuncia;
	}

	public ArrayList<Estado> getHistorialEstado() {
		return historialEstado;
	}

	public void setHistorialEstado(ArrayList<Estado> historialEstado) {
		this.historialEstado = historialEstado;
	}
	
	
	
	
	
	
}


