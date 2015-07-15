package mipatronDAO;

import java.util.List;

import misclases.RegistroAlquiler;
import misclases.Usuario;

public interface IRegistroAlquilerDAO {
	
	public void guardarRegistroAlquiler(RegistroAlquiler registroAlquiler);
	public void modificarRegistroAlquiler(RegistroAlquiler registroAlquiler);
	public void eliminarRegistroAlquiler(RegistroAlquiler registroAlquiler);
	public RegistroAlquiler recuperarRegistroAlquiler(Long id);
	public void eliminarRegistroAlquilerLogica(RegistroAlquiler reg);
	public List<RegistroAlquiler> recuperarAlquilerUsuario(Usuario usr);
}
