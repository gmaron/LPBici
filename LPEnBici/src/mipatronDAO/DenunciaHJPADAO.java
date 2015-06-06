package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.Denuncia;


public class DenunciaHJPADAO implements IDenunciaDAO{

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	private static EntityManager em;
	

	@Override
	public void guardarDenuncia(Denuncia denuncia) {
		// TODO Auto-generated method stub
		em =  emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();		
		etx.begin();
		
		em.persist(denuncia);
		
		etx.commit();		
		em.close();
	}

	@Override
	public void modificarDenuncia(Denuncia denuncia) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
	
		em.merge(denuncia);
	
		etx.commit();
		em.close();
	}

	@Override
	public void eliminarDenuncia(Denuncia denuncia) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.remove(em.merge(denuncia));
		
		etx.commit();
		em.close();
	}
	
	@Override
	public Denuncia recuperarDenuncia(Long id) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		Denuncia den = em.find(Denuncia.class, id); 
		em.close();
		return den;
	}
	

}
