package mipatronDAO;

public class MyFactoryDAO {

	public IUsuarioDAO getUsuarioDAO(){
		return new UsuarioHJPADAO();
	}
	
	public IAdministradorDAO getAdministradorDAO(){
		return new AdministradorHJPADAO();
	}
	
	public IBicicletaDAO getBicicletaDAO(){
		return new BicicletaHJPADAO();
	}
	
	public IEstacionDAO getEstacionDAO(){
		return new EstacionHJPADAO();
	}
	
	public IRegistroAlquilerDAO getRegAlquilerDAO(){
		return new RegistroAlquilerHJPADAO();
	}
	
	public IDenunciaDAO getDenunciaDAO(){
		return new DenunciaHJPADAO();
	}
	
	public IAuditoriaDAO getAuditoriaDAO(){
		return new AuditoriaHJPADAO();
	}
	
}
