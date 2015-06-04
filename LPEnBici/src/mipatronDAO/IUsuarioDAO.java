package mipatronDAO;

import misclases.Usuario;

public interface IUsuarioDAO {

	public void guardarUsuario(Usuario usuario);
	public void modificarUsuario(Usuario usuario);
	public void eliminarUsuario(Long usuario);
	
}
