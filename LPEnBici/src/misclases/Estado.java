package misclases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Estado {
	private String estado;
	private String fecha;
	
	@Id @GeneratedValue
	private Long id;

	
	public Estado(String estado, String fecha) {
		super();
		this.estado = estado;
		this.fecha = fecha;
	}
	
	public Estado(){
		
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
