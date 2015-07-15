package mipatronDAO;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



import javax.persistence.Query;

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
	public void eliminarDenunciaLogica(Denuncia den) {
		
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		den.setEliminado(true);
		em.merge(den);
		//em.remove(em.merge(usuario));		
		
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
	
	@Override
	public List<Denuncia> recuperarDenuncias(){				
		em = emf.createEntityManager();
		Query q = em.createQuery("from Denuncia d where d.eliminado = '"+false+"'") ;
		@SuppressWarnings("unchecked")
		List<Denuncia> resultList = Collections.checkedList(q.getResultList(), Denuncia.class);
		System.out.println("Busque denuncias");
		for (Denuncia den : resultList){
			System.out.println("denuncias: "+den.getComentario()+"\n");
		}
		return resultList;				
	}	
	
}
