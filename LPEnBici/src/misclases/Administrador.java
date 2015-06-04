package misclases;

import javax.persistence.*;

@Entity
public class Administrador extends Persona {

	private static final long serialVersionUID = 1L;
	
	public Administrador(String nombre, String apellido, String dni,
			String domicilio, String email, String sexo, String fechaNacimiento, String password) {
		super(nombre, apellido, dni, domicilio, email, sexo, fechaNacimiento, password);
		// TODO Auto-generated constructor stub
	}

	public Administrador() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
