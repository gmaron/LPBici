package test;


import misclases.Administrador;
import misclases.Bicicleta;
import misclases.Denuncia;
import misclases.Estacion;
import misclases.Persona;
import misclases.RegistroAlquiler;
import misclases.Usuario;
import mipatronDAO.MyFactoryDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
public class Test {
	private Long id;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFactoryDAO f = new MyFactoryDAO();
		
		/*-------------------------------------Test para altas-------------------------------------*/
		// Alta Usuario
		Usuario usr = new Usuario("Milton","Meroni","35798492","miltonmeroni@gmail.com","13 Nro 1478","22/11/92","Masculino","1234");
		f.getUsuarioDAO().guardarUsuario(usr);
		
		Usuario usr1 = new Usuario("Gaston","Maron","12345678","miltonmeroni@gmail.com","13 Nro 1478","22/11/92","Masculino","1234");
		f.getUsuarioDAO().guardarUsuario(usr1);		
		
		// Alta Administrador
		Administrador admin = new Administrador("Gaston","Paul","36936105","gastonbezzi@gmail.com","13 Nro 354","19/06/92","Masculino","1234");
		f.getAdministradorDAO().guardarAdministrador(admin);
		
		// Alta de Bicicleta
		Bicicleta bici = new Bicicleta("MLP-002", "Apta para el uso", "10:29-04/06/2015","Estacion 1");
		f.getBicicletaDAO().guardarBicicleta(bici);

		//Bicicleta bici1 = new Bicicleta("MLP-003", "Apta para el uso", "10:29-04/06/2015","Estacion 1");
		//f.getBicicletaDAO().guardarBicicleta(bici1);
			
		// Alta de Estacion
		Date fechaActual = new Date();
		//DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
	    DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 		
		//String Hora = formatoHora.format(fechaActual);
		String fecha= formatoFecha.format(fechaActual);
		Estacion est = new Estacion("Estacion 1", "pza San Martin", 30, "Habilitada",fecha);
		est.getListaBici().add(bici);
		f.getEstacionDAO().guardarEstacion(est);
		
		// Alta de alquiler
		RegistroAlquiler reg = new RegistroAlquiler(null, "04/06/2015", null, "10:33", null, est, usr);
		f.getRegAlquilerDAO().guardarRegistroAlquiler(reg);		
				
		// Alta de denuncia
		Denuncia den = new Denuncia(usr, "La bici no tiene cadena");
		f.getDenunciaDAO().guardarDenuncia(den);
		
		
		System.out.println("Termino el test de alta\n");
		/*-------------------------------------Fin Test para altas-------------------------------------*/

		/*-------------------------------------Test para recuperacion-------------------------------------*/
		
		Usuario usuariorec = f.getUsuarioDAO().recuperarUsuario(1L);
		System.out.println("---Recuperacion de usuario---\n");
		//System.out.println("Nombre: "+usuariorec.getNombre()+" - Apellido: "+usuariorec.getApellido());
		System.out.println("\n");
		
		System.out.println("---Recuperacion de la lista de todos los usuarios---\n");
		List<Usuario> listaUsuarios = f.getUsuarioDAO().recuperarTodosUsuarios();
		for (Usuario user : listaUsuarios){
			System.out.println("Usuario: "+user.getApellido()+"\n");
		}
		
		System.out.println("---Recuperacion de administrador---\n");
		Administrador admrec = f.getAdministradorDAO().recuperarAdministrador(3L);
		System.out.println("Nombre: "+admrec.getNombre()+" - Apellido: "+admrec.getApellido());		
		System.out.println("\n");
		
		System.out.println("---Recuperacion de bicicleta---\n");
		Bicicleta bicirec = f.getBicicletaDAO().recuperarBicicleta(1L);
		System.out.println("Patente: "+bicirec.getPatente());
		System.out.println("\n");
		
		System.out.println("---Recuperacion de estacion---\n");
		Estacion estrec = f.getEstacionDAO().recuperarEstacion(1L);
		System.out.println("Estacion: "+estrec.getNombre());
		System.out.println("\n");
		
		System.out.println("---Recuperacion de denuncia---\n");
		Denuncia denrec = f.getDenunciaDAO().recuperarDenuncia(1L);
		System.out.println("Denuncia: "+denrec.getComentario());
		System.out.println("\n");
		
		System.out.println("---Recuperacion de registro alquiler---\n");
		RegistroAlquiler regrec = f.getRegAlquilerDAO().recuperarRegistroAlquiler(1L);
		System.out.println("Usuario del registro: "+regrec.getUsuarioUso().getNombre()+" "+regrec.getUsuarioUso().getApellido());
		System.out.println("\n");
		/*-------------------------------------Fin Test para recuperacion-------------------------------------*/
		
		
		/*--------------------------------Test para modificaciones---------------------------------*/
		
		// modifica el apellido del usuario a "Mongocho"
		usr.setApellido("Mongocho");
		f.getUsuarioDAO().modificarUsuario(usr);
		
		// modifica el nombre del administrador a "pepito"
		admin.setNombre("pepito");
		f.getAdministradorDAO().modificarAdministrador(admin);
		
		// modifica la patente y agrega un registro actual
		bici.setPatente("MLP-005");
		
		//asociar registro de alquiler con la bicicleta
		bici.setRegActual(reg);
		f.getBicicletaDAO().modificarBicicleta(bici);
			
		// modifica la estacion
		est.setNombre("Estacion 4");
		f.getEstacionDAO().modificarEstacion(est);
		
		// modificar la denuncia
		den.setComentario("La bici esta pinchada");
		f.getDenunciaDAO().modificarDenuncia(den);		
				
		// modificar el alquiler convirtiendo en un alquiler historico
		reg.setEstacionEntrada(est);
		reg.setFechaEntrada("05/06/2015");
		reg.setHoraEntrada("18:00");
		reg.setDenuncia(den);
		f.getRegAlquilerDAO().modificarRegistroAlquiler(reg);			
		
		System.out.println("Termino el test de modificaciones\n");
		/*--------------------------------Fin Test para modificaciones---------------------------------*/
			
		
		/*-------------------------------------Test para bajas-------------------------------------*/
			
		f.getAdministradorDAO().eliminarAdministrador(admin);
		f.getRegAlquilerDAO().eliminarRegistroAlquiler(reg);
		f.getEstacionDAO().eliminarEstacion(est);
		
		//f.getBicicletaDAO().eliminarBicicleta(bici); No se puede borrar una bicicleta asociada a una estacion
		f.getDenunciaDAO().eliminarDenuncia(den);		
		f.getUsuarioDAO().eliminarUsuario(usr);
			
		System.out.println("Termino el test de bajas\n");
		/*-------------------------------------Fin Test para bajas-------------------------------------*/
	
		System.out.println("Termino el test");

	}

}
