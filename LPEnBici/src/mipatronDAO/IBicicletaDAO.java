package mipatronDAO;

import misclases.Bicicleta;

public interface IBicicletaDAO {

	public void guardarBicicleta(Bicicleta bicicleta);
	public void modificarBicicleta(Bicicleta bicicleta);
	public void eliminarBicicleta(Bicicleta bicicleta);
	public Bicicleta recuperarBicicleta(Long id);
	
	
}
