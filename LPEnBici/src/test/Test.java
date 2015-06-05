package test;


import misclases.Administrador;
import misclases.Bicicleta;
import misclases.Denuncia;
import misclases.Estacion;
import misclases.Persona;
import misclases.RegistroAlquiler;
import misclases.Usuario;
import mipatronDAO.MyFactoryDAO;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFactoryDAO f = new MyFactoryDAO();
		
		/*-------------------------------------Test para altas-------------------------------------*/
		// Alta Usuario
		Usuario usr = new Usuario("Milton","Meroni","35798492","miltonmeroni@gmail.com","13 Nro 1478","22/11/92","Masculino","1234");
		f.getUsuarioDAO().guardarUsuario(usr);		
		
		// Alta Administrador
		Administrador admin = new Administrador("Gaston","Paul","36936105","gastonbezzi@gmail.com","13 Nro 354","19/06/92","Masculino","1234");
		f.getAdministradorDAO().guardarAdministrador(admin);
		
		// Alta de Bicicleta
		Bicicleta bici = new Bicicleta("MLP-001", "Apta para el uso", "10:29-04/06/2015","Estacion 1");
		f.getBicicletaDAO().guardarBicicleta(bici);
		
		// Alta de Estacion
		Estacion est = new Estacion("Estacion 1", "pza San Martin", 30, "Habilitada");
		est.getListaBici().add(bici);
		f.getEstacionDAO().modificarEstacion(est);//Esta mal
		
		/*// Alta de alquiler
		RegistroAlquiler reg = new RegistroAlquiler(null, "04/06/2015", null, "10:33", null, est, usr);
		f.getRegAlquilerDAO().guardarRegistroAlquiler(reg);		
		
		// Alta de denuncia
		Denuncia den = new Denuncia(usr, "La bici no tiene cadena");
		f.getDenunciaDAO().guardarDenuncia(den);
		
		
		System.out.println("Termino el test de alta");
		/*-------------------------------------Test para altas-------------------------------------*/
		System.out.println("Fin de test para altas");
		/*--------------------------------Test para modificaciones---------------------------------*/
		
		// modifica el apellido del usuario a "Mongocho"
		/*usr.setApellido("Mongocho");
		f.getUsuarioDAO().modificarUsuario(usr);
		
		// modifica el nombre del administrador a "pepito"
		admin.setNombre("pepito");
		f.getAdministradorDAO().modificarAdministrador(admin);
		
		// modifica la patente y agrega un registro actual
		bici.setPatente("MLP-005");
		bici.setRegActual(reg);
		f.getBicicletaDAO().modificarBicicleta(bici);
		
		// modifica la estacion
		est.setNombre("Estacion 4");
		f.getEstacionDAO().modificarEstacion(est);
		
		// modificar el alquiler convirtiendo en un alquiler historico
		reg.setEstacionEntrada(est);
		reg.setFechaEntrada("05/06/2015");
		reg.setHoraEntrada("18:00");
		f.getRegAlquilerDAO().modificarRegistroAlquiler(reg);
		
		// modificar la denuncia
		den.setComentario("La bici esta pinchada");
		f.getDenunciaDAO().modificarDenuncia(den);		
		
		/*--------------------------------Test para modificaciones---------------------------------*/
		
		/*-------------------------------------Test para bajas-------------------------------------*/
		
		// Baja Usuario
		/*f.getUsuarioDAO().eliminarUsuario(usr);
		
		//Baja Administrador
		f.getAdministradorDAO().eliminarAdministrador(admin);

		
		/*-------------------------------------Test para bajas-------------------------------------*/
	
		f.getUsuarioDAO().closeEntityManager();
		f.getAdministradorDAO().closeEntityManager();
		//f.getRegAlquilerDAO().closeEntityManager();
		f.getBicicletaDAO().closeEntityManager();
		//f.getDenunciaDAO().closeEntityManager();
		//f.getEstacionDAO().closeEntityManager();
	
	}

}
