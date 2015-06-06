package mipatronDAO;

import misclases.Denuncia;

public interface IDenunciaDAO {

	public void guardarDenuncia(Denuncia denuncia);
	public void modificarDenuncia(Denuncia denuncia);
	public void eliminarDenuncia(Denuncia denuncia);
	public Denuncia recuperarDenuncia(Long id);
}
