package mipatronDAO;

import java.util.List;

import misclases.Bicicleta;

public interface IBicicletaDAO {

	public void guardarBicicleta(Bicicleta bicicleta);
	public void modificarBicicleta(Bicicleta bicicleta);
	public void eliminarBicicleta(Bicicleta bicicleta);
	public Bicicleta recuperarBicicleta(Long id);
	public void eliminarBicicletaLogica(Bicicleta bici);
	public Bicicleta recuperarBicicletaPatente(String patente);
	public List<Bicicleta> recuperarBicicletasNoEliminadas();
	
	
}
