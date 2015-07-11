package mipatronDAO;

import java.util.List;

import misclases.Usuario;

public interface IUsuarioDAO {

	public void guardarUsuario(Usuario usuario);
	public void modificarUsuario(Usuario usuario);
	public void eliminarUsuario(Usuario usuario);
	public Usuario recuperarUsuario(String email);
	public Usuario recuperarUsuarioNoEliminado(String email);
	public List<Usuario> recuperarUsuariosNoEliminados();
	public List<Usuario> recuperarTodosUsuarios();
	public void eliminarUsuarioLogica(Usuario usr);	
}
