package mipatronDAO;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	public void eliminarBicicletaLogica(Bicicleta bici) {
		
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		bici.setEliminado(true);
		em.merge(bici);
		//em.remove(em.merge(usuario));		
		
		etx.commit();
		em.close();
	}
	
	
	@Override
	public Bicicleta recuperarBicicleta(Long id) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		
		Bicicleta bici = em.find(Bicicleta.class, id);
		System.out.println("Recupere bicicleta con id: "+bici.getId());	
		
		em.close();
		return bici;
	}
	
	@Override
	public Bicicleta recuperarBicicletaPatente(String patente) {
		em = emf.createEntityManager();
		Bicicleta bici = null;
		Query q ;
		try {
			q = em.createQuery("FROM Bicicleta b WHERE b.patente ='"+patente+"'");
			bici = (Bicicleta) q.getSingleResult();
			System.out.println("Patente: "+bici.getPatente());
		} catch (Exception e) {
			bici = null;
			//e.printStackTrace();
		}
		em.close();
		return bici;
	}
	
	@Override
	public List<Bicicleta> recuperarBicicletasNoEliminadas(){
		em = emf.createEntityManager();
		Query q = em.createQuery("from Bicicleta b where b.eliminado = '"+false+"'") ;
		@SuppressWarnings("unchecked")
		List<Bicicleta> resultList = Collections.checkedList(q.getResultList(), Bicicleta.class);
		System.out.println("Busque bicicletas");
		for (Bicicleta bici : resultList){
			System.out.println("bicicletas: "+bici.getPatente()+"\n");
		}
		return resultList;
	}
	
	
	
}
