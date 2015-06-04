package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.Bicicleta;


public class BicicletaHJPADAO implements IBicicletaDAO {

private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnityPersistence");
	
	@Override
	public void guardarBicicleta(Bicicleta bicicleta) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.persist(bicicleta);
	
		etx.commit();
		em.close();
	}
	
	@Override
	public void modificarBicicleta(Bicicleta bicicleta) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void eliminarBicicleta(Bicicleta bicicleta) {
		// TODO Auto-generated method stub
		
	}
	
}
