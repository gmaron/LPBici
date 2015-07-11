package misclases;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Ver Single table
public class RegistroAlquiler {
	
	@Id @GeneratedValue
	private Long id;
	
	private String fechaEntrada; //fecha de entrada = 0
	private String fechaSalida; 
	private String horaEntrada; //hora de entrada = 0
	private String horaSalida; 
	private String estado;
	
	boolean eliminado;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}) // puede haber mas de un registro asociado a un usuario
	private Usuario usuarioUso;

	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}) 
	private Estacion estacionEntrada; // estacion de entrada = estacion de salida hasta que la devuelva
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}) 
	private Estacion estacionSalida;
		
	//preguntar porque hay redundancia de datos
	@ManyToOne(cascade = CascadeType.MERGE) 
	private Denuncia denuncia;
		
	
	public RegistroAlquiler(String fechaEntrada, String fechaSalida,
			String horaEntrada, String horaSalida, Estacion estacionEntrada,
			Estacion estacionSalida,/* String estado,*/ Usuario usuarioUso) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.estacionEntrada = estacionEntrada;
		this.estacionSalida = estacionSalida;
		//this.estado = estado;
		this.usuarioUso = usuarioUso;
		this.eliminado = false;
		
	}
	
	public RegistroAlquiler(){
		
	}
	
	public String getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public String getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}
	public Estacion getEstacionEntrada() {
		return estacionEntrada;
	}
	public void setEstacionEntrada(Estacion estacionEntrada) {
		this.estacionEntrada = estacionEntrada;
	}
	public Estacion getEstacionSalida() {
		return estacionSalida;
	}
	public void setEstacionSalida(Estacion estacionSalida) {
		this.estacionSalida = estacionSalida;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Usuario getUsuarioUso() {
		return usuarioUso;
	}
	public void setUsuarioUso(Usuario usuarioUso) {
		this.usuarioUso = usuarioUso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Denuncia getDenuncia() {
		//si no hay nada en denuncia , retorna null
		return denuncia;
	}

	public void setDenuncia(Denuncia denuncia) {
		this.denuncia = denuncia;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	 
	@Override
	public String toString() {
		return "RegistroAlquiler";
	} 
	
	
	
	
}
