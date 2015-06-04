package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.RegistroAlquiler;

public class RegistroAlquilerHJPADAO implements IRegistroAlquilerDAO {

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
	public void guardarRegistroAlquiler(RegistroAlquiler registroAlquiler) {
		// TODO Auto-generated method stub
		em =  getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.persist(registroAlquiler);
	
		etx.commit();
		
	}

	@Override
	public void modificarRegistroAlquiler(RegistroAlquiler registroAlquiler) {
		// TODO Auto-generated method stub
		em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		//el codigo de modificacion va aca
		em.merge(registroAlquiler);
		etx.commit();
		
		
	}

	@Override
	public void eliminarRegistroAlquiler(RegistroAlquiler registroAlquiler) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.remove(registroAlquiler);		
		etx.commit();
		
	}
	
	@Override
	public RegistroAlquiler recuperarRegistroAlquiler(Long id) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		return em.find(RegistroAlquiler.class, id);
	}
	
	public void closeEntityManager(){
		em.close();
	}

}
