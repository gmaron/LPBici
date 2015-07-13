package misclases;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="Auditoria")
public class Auditoria {

	
	private String operacion;
	private String fecha_hora;
	private String entidad;
	private long id_entidad;
	
	@Id @GeneratedValue
	private Long id;
	
	
	
	
	public Auditoria(String operacion, String fecha_hora, String entidad,
			long id_entidad) {
		super();
		this.operacion = operacion;
		this.fecha_hora = fecha_hora;
		this.entidad = entidad;
		this.id_entidad = id_entidad;
	}


	public Auditoria(){
		
	}
	
	
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getFecha_hora() {
		return fecha_hora;
	}
	public void setFecha_hora(String fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public long getId_entidad() {
		return id_entidad;
	}
	public void setId_entidad(long id_entidad) {
		this.id_entidad = id_entidad;
	}
	
	
	
}
