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
	
}
