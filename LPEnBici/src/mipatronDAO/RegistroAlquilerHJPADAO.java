package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.RegistroAlquiler;

public class RegistroAlquilerHJPADAO implements IRegistroAlquilerDAO {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	private static EntityManager em;
	
	
	
	@Override
	public void guardarRegistroAlquiler(RegistroAlquiler registroAlquiler) {
		// TODO Auto-generated method stub
		em =  emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.persist(registroAlquiler);
	
		etx.commit();
		em.close();
	}

	@Override
	public void modificarRegistroAlquiler(RegistroAlquiler registroAlquiler) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.merge(registroAlquiler);
		
		etx.commit();
		em.close();
	}

	@Override
	public void eliminarRegistroAlquiler(RegistroAlquiler registroAlquiler) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.remove(em.merge(registroAlquiler));		
		
		etx.commit();
		em.close();
	}
	
	@Override
	public void eliminarRegistroAlquilerLogica(RegistroAlquiler reg) {		
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		reg.setEliminado(true);
		em.merge(reg);
		//em.remove(em.merge(usuario));		
		
		etx.commit();
		em.close();
	}
	
	
	@Override
	public RegistroAlquiler recuperarRegistroAlquiler(Long id) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		RegistroAlquiler reg = em.find(RegistroAlquiler.class, id); 
		em.close();
		return reg;
	}

}
