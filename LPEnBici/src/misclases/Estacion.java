package misclases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Estacion {
	private String nombre;
	private String ubicacion;
	private int cantEstacionamientoLibre;
	private String estado; //OPERATIVA, CERRADA, EN CONSTRUCCION
	
	boolean eliminado;
	
	@Id @GeneratedValue
	private Long id;
	
	@OneToMany (cascade = CascadeType.ALL)
	private List<Estado> historialEstado;
	
	@OneToMany (cascade = {CascadeType.MERGE,CascadeType.REMOVE}) //, 
 	private List<Bicicleta> listaBici; //el length = cantidad de bicicletas
	
	public Estacion(String nombre, String ubicacion,
			int cantEstacionamientoLibre, String estado,String fecha) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.cantEstacionamientoLibre = cantEstacionamientoLibre;
		this.estado = estado;
		this.listaBici = new ArrayList<Bicicleta>();
		this.historialEstado = new ArrayList<Estado>();
		this.historialEstado.add(new Estado(estado, fecha));
		this.eliminado = false;
	}
	
	public Estacion(){
		
	}

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
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
	
	
}
