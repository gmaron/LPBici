package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.Denuncia;


public class DenunciaHJPADAO implements IDenunciaDAO{

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
	public void guardarDenuncia(Denuncia denuncia) {
		// TODO Auto-generated method stub
		em =  getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.persist(denuncia);
	
		etx.commit();
		
	}

	@Override
	public void modificarDenuncia(Denuncia denuncia) {
		// TODO Auto-generated method stub
		em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		//el codigo de modificacion va aca
		em.merge(denuncia);
		etx.commit();
		
		
	}

	@Override
	public void eliminarDenuncia(Denuncia denuncia) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.remove(denuncia);		
		etx.commit();
		
	}
	
	@Override
	public Denuncia recuperarDenuncia(Long id) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		return em.find(Denuncia.class, id);
	}
	
	public void closeEntityManager(){
		em.close();
	}

}
