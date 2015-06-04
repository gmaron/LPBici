package mipatronDAO;

import misclases.Estacion;

public interface IEstacionDAO {

	public void guardarEstacion(Estacion estacion);
	public void modificarEstacion(Estacion estacion);
	public void eliminarEstacion(Estacion estacion);
	public Estacion recuperarEstacion(Long id);
	public void closeEntityManager();
	
}
