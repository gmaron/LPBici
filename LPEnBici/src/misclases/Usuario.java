package misclases;

import javax.persistence.Entity;

@Entity
public class Usuario extends Persona{

	boolean habilitado;
	
	public Usuario(String nombre, String apellido, String email,
			String dni, String domicilio, String sexo, String fechaNacimiento,String password) {
		super(nombre, apellido, email, dni, domicilio, sexo, fechaNacimiento, password);
		// TODO Auto-generated constructor stub
		//habilitado = true;
	}

	
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}



	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	} 
	
	
	
}
