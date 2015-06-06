package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.Bicicleta;


public class BicicletaHJPADAO implements IBicicletaDAO {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	private static EntityManager em;
	
	
	@Override
	public void guardarBicicleta(Bicicleta bicicleta) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.persist(bicicleta);
		
		etx.commit();
		em.close();
	}
	
	@Override
	public void modificarBicicleta(Bicicleta bicicleta) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.merge(bicicleta);
		
		etx.commit();
		em.close();
	}
	
	@Override
	public void eliminarBicicleta(Bicicleta bicicleta) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		
		etx.begin();
	
		em.remove(em.merge(bicicleta));		
		
		etx.commit();
		em.close();
	}

	@Override
	public Bicicleta recuperarBicicleta(Long id) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		Bicicleta bici = em.find(Bicicleta.class, id); 
		em.close();
		return bici;
	}
	
	
}
