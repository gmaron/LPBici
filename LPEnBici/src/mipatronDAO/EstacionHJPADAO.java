package mipatronDAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import misclases.Estacion;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import misclases.Estacion;


public class EstacionHJPADAO implements IEstacionDAO {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnityPersistence");
	
	@Override
	public void guardarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		
	}
}
