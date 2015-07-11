package mipatronDAO;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.Estacion;



public class EstacionHJPADAO implements IEstacionDAO {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	private static EntityManager em;
		
	@Override
	public void guardarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		em =  emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.persist(estacion);
		
		etx.commit();
		em.close();
	}

	@Override
	public void modificarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.merge(estacion);
		
		etx.commit();
		em.close();
	}

	@Override
	public void eliminarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.remove(em.merge(estacion));		
		
		etx.commit();
		em.close();
	}
	
	@Override
	public void eliminarEstacionLogica(Estacion estacion) {
		
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		estacion.setEliminado(true);
		em.merge(estacion);
		//em.remove(em.merge(usuario));		
		
		etx.commit();
		em.close();
	}

	@Override
	public Estacion recuperarEstacion(Long id) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		Estacion est = em.find(Estacion.class, id);
		em.close();
		return est;
	}

}
