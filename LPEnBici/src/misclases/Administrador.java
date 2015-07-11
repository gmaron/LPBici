package misclases;

import javax.persistence.*;

@Entity
public class Administrador extends Persona {
	
	boolean eliminado;

	public Administrador(String nombre, String apellido, String dni,
			String domicilio, String email, String sexo, String fechaNacimiento, String password) {
		super(nombre, apellido, dni, domicilio, email, sexo, fechaNacimiento, password);
		// TODO Auto-generated constructor stub
		eliminado = false;
	}

	public Administrador() {
		
	}
	
	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	

	@Override
	public String toString() {
		return "Administrador";
	}
	

	
}
