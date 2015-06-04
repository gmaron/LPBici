package test;

import misclases.Administrador;
import misclases.Bicicleta;
import misclases.Persona;
import misclases.Usuario;
import mipatronDAO.MyFactoryDAO;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFactoryDAO f = new MyFactoryDAO();
		
		// Alta Usuario
		Usuario usr = new Usuario("Milton","Meroni","35798492","miltonmeroni@gmail.com","13 Nro 1478","22/11/92","Masculino","1234");
		f.getUsuarioDAO().guardarUsuario(usr);		
		
		// Alta Administrador
		///Administrador usr2 = new Administrador("Gaston","Bezzi","36936105","gastonbezzi@gmail.com","13 Nro 1478","19/06/92","Masculino","1234");
		//f.getAdministradorDAO().guardarAdministrador(usr2);
		
		System.out.println("Termino el test");
		
		//Baja Administrador
		//f.getAdministradorDAO().eliminarAdministrador(usr2.getId());

		// Baja Usuario
		//f.getUsuarioDAO().eliminarUsuario(usr.getId());
		
		// Modificacion Administrador
		//Administrador usr3 = new Administrador("Leopoldo","Bezzi","36936105","gastonbezzi@gmail.com","13 Nro 1478","19/06/92","Masculino","1234");
		//f.getAdministradorDAO().modificarAdministrador(usr3);

		// TEST BICICLETA
		//Bicicleta bici = new Bicicleta("19/06/92","1/6/15", "14:00", "Estacion 1");
		//f.getBicicletaDAO().guardarBicicleta(bici);
		
	}

}
