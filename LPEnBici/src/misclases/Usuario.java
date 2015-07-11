package misclases;

import javax.persistence.Entity;

@Entity
public class Usuario extends Persona{

	boolean habilitado;
	boolean eliminado;
	public Usuario(String nombre, String apellido, String email,
			String dni, String domicilio, String sexo, String fechaNacimiento,String password) {
		super(nombre, apellido, email, dni, domicilio, sexo, fechaNacimiento, password);
		// TODO Auto-generated constructor stub
		habilitado = true;
		eliminado = false;
	}

	
	
	public Usuario() {

	}


	
	
	public boolean isEliminado() {
		return eliminado;
	}



	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}



	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}



	@Override
	public String toString() {
		return "Usuario";
	} 
	
	
	
}
