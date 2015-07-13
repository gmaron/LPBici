package rest;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class AuditoriaRest {

	
	private String operacion;
	private int cant;
	
		
	public AuditoriaRest(String operacion, Integer cant) {
		super();
		this.operacion = operacion;
		this.cant = cant;
	}
	
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public Integer getCant() {
		return cant;
	}
	public void setCant(Integer cant) {
		this.cant = cant;
	}
	
	
	
}
