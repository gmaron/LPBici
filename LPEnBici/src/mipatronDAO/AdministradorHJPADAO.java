package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.Administrador;
import misclases.Persona;


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
	public Administrador recuperarAdministrador(Long id) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		Administrador admin = em.find(Administrador.class, id);
		em.close();
		return admin;
	}
	


	
	
}
