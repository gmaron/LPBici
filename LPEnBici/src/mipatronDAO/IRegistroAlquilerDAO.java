package mipatronDAO;

import misclases.RegistroAlquiler;

public interface IRegistroAlquilerDAO {
	
	public void guardarRegistroAlquiler(RegistroAlquiler registroAlquiler);
	public void modificarRegistroAlquiler(RegistroAlquiler registroAlquiler);
	public void eliminarRegistroAlquiler(RegistroAlquiler registroAlquiler);
	public RegistroAlquiler recuperarRegistroAlquiler(Long id);
}
