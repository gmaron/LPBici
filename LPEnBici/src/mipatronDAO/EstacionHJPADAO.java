package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.Administrador;
import misclases.Estacion;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import misclases.Estacion;


public class EstacionHJPADAO implements IEstacionDAO {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnityPersistence");
	private static EntityManager em;
	
	/* Singleton EntityManager */
	public static EntityManager getEntityManager(){
		if(em == null || !em.isOpen()){
			em = emf.createEntityManager();
		}
		return em;
	}
	
	@Override
	public void guardarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		em =  getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.persist(estacion);
	
		etx.commit();
		
	}

	@Override
	public void modificarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		//el codigo de modificacion va aca
		em.merge(estacion);
		etx.commit();
		
	}

	@Override
	public void eliminarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.remove(estacion);		
		etx.commit();
		
	}

	@Override
	public Estacion recuperarEstacion(Long id) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		return em.find(Estacion.class, id);
	}

	@Override
	public void closeEntityManager() {
		// TODO Auto-generated method stub
		em.close();
		
	}
}
