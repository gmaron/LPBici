package misclases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
//	@OneToOne (cascade =  CascadeType.ALL)
//	@JoinColumn (name = "id_denuncia")
//	private Denuncia denuncia;
	
	boolean eliminado;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}) // puede haber mas de un registro asociado a un usuario
	private Usuario usuarioUso;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}) 
	private Estacion estacionEntrada; // estacion de entrada = estacion de salida hasta que la devuelva
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}) 
	private Estacion estacionSalida;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Bicicleta bicicleta; 
	
	public RegistroAlquiler( Estacion estacionSalida, Usuario usuarioUso, Bicicleta bicicleta) {
		super();
		
		this.fechaSalida = dameFecha();
		this.horaSalida = dameHora();		
		this.estacionSalida = estacionSalida;
		this.estacionEntrada = new Estacion();
		//this.estado = estado;
//		this.denuncia = new Denuncia();
//		this.denuncia.setComentario(null);
		this.bicicleta = bicicleta; 
		this.usuarioUso = usuarioUso;
		this.eliminado = false;
		
	}
	
	public RegistroAlquiler(){
		
	}
	
	
	private String dameFecha(){
		
		Date fechaActual = new Date();
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 		
		return formatoFecha.format(fechaActual);
	}
	
	private String dameHora(){
		
		Date fechaActual = new Date();
		DateFormat formatoFecha = new SimpleDateFormat("HH:mm"); 		
		return formatoFecha.format(fechaActual);
	}
	
	
	public Bicicleta getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
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

//	public Denuncia getDenuncia() {
//		//si no hay nada en denuncia , retorna null
//		return denuncia;
//	}
//
//	public void setDenuncia(Denuncia denuncia) {
//		this.denuncia = denuncia;
//	}

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
