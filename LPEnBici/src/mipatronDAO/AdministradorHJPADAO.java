package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import misclases.Administrador;



public class AdministradorHJPADAO implements IAdministradorDAO{

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	private static EntityManager em;
	
	@Override
	public void guardarAdministrador(Administrador administrador) {
		// TODO Auto-generated method stub
		em =  emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.persist(administrador);
	
		etx.commit();
		em.close();
	}

	@Override
	public void modificarAdministrador(Administrador administrador) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();

		em.merge(administrador);
		
		etx.commit();
		em.close();
	}

	@Override
	public void eliminarAdministrador(Administrador administrador) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.remove(em.merge(administrador));		
		
		etx.commit();
		em.close();
	}
	
	@Override
	public Administrador recuperarAdministrador(String email) {
		em = emf.createEntityManager();
		Administrador admin = null;
		Query q ;
		try {
			q = em.createQuery("FROM Administrador a WHERE a.email ='"+email+"'");
			admin = (Administrador) q.getSingleResult();
			System.out.println("Encontre a Administrador: "+admin.getEmail());
		} catch (Exception e) {
			admin = null;
			System.out.println("NO Encontre a Administrador");

			//e.printStackTrace();
		}
		em.close();
		return admin;
	}
	


	
	
}
