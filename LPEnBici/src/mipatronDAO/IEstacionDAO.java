package mipatronDAO;

import java.util.List;

import misclases.Bicicleta;
import misclases.Estacion;

public interface IEstacionDAO {

	public void guardarEstacion(Estacion estacion);
	public void modificarEstacion(Estacion estacion);
	public void eliminarEstacion(Estacion estacion);
	public Estacion recuperarEstacion(Long id);
	public void eliminarEstacionLogica(Estacion estacion);
	public List<Estacion> recuperarEstacionesNoEliminadas();
	public List<Estacion> recuperarEstacionesOperativas();
	public Estacion recuperarEstacionNombre(String nombre);
	public List<Bicicleta> listarBicicletasEstacion(Long idEstacion);
	
}
