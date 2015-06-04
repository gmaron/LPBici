package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.Administrador;
import misclases.Bicicleta;


public class BicicletaHJPADAO implements IBicicletaDAO {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnityPersistence");
	private static EntityManager em;
	
	
	public static EntityManager getEntityManager(){
		if(em == null || !em.isOpen()){
			em = emf.createEntityManager();
		}
		return em;
	}

	@Override
	public void guardarBicicleta(Bicicleta bicicleta) {
		// TODO Auto-generated method stub
		em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.persist(bicicleta);
		etx.commit();
	}
	
	@Override
	public void modificarBicicleta(Bicicleta bicicleta) {
		// TODO Auto-generated method stub
		em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.merge(bicicleta);
	
		etx.commit();
		
	}
	
	@Override
	public void eliminarBicicleta(Bicicleta bicicleta) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
	
		em.remove(bicicleta);		
		
		etx.commit();
	
	}

	@Override
	public Bicicleta recuperarBicicleta(Long id) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		return em.find(Bicicleta.class, id);
	}
	
	public void closeEntityManager(){
		em.close();
	}
	
}
