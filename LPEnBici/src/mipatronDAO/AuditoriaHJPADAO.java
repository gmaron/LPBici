package mipatronDAO;

//import java.util.Collections;
//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import rest.AuditoriaRest;
import misclases.Auditoria;

public class AuditoriaHJPADAO implements IAuditoriaDAO {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	private static EntityManager em;
	
	@Override
	public void guardarAuditoria(Auditoria aud) {
		em =  emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.persist(aud);
	
		etx.commit();
		em.close();		
	}

	@Override
	public AuditoriaRest recuperarAuditoriaRest(String op) {
		em = emf.createEntityManager();
		Query q = em.createQuery("Select Count (a.operacion) from Auditoria a where a.operacion='"+op+"'");
		long cant = (long)q.getSingleResult();
		int cant_int;
		if (cant < Integer.MIN_VALUE || cant > Integer.MAX_VALUE) {
			cant_int = 0;
	        throw new IllegalArgumentException
	            (cant + " cannot be cast to int without changing its value.");
	    }
		else{
			cant_int = (int)cant;
		}
		AuditoriaRest aud = new AuditoriaRest(op, cant_int);
		System.out.println("Cantidad: "+cant);
		return aud;	
	}
	
	
	
}
