package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.Administrador;
import misclases.Persona;


public class AdministradorHJPADAO implements IAdministradorDAO{

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
	public void guardarAdministrador(Administrador administrador) {
		// TODO Auto-generated method stub
		em =  getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.persist(administrador);
	
		etx.commit();
		
	}

	@Override
	public void modificarAdministrador(Administrador administrador) {
		// TODO Auto-generated method stub
		em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		//el codigo de modificacion va aca
		em.merge(administrador);
		etx.commit();
		
		
	}

	@Override
	public void eliminarAdministrador(Administrador administrador) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.remove(administrador);		
		etx.commit();
		
	}
	
	@Override
	public Administrador recuperarAdministrador(Long id) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		return em.find(Administrador.class, id);
	}
	
	public void closeEntityManager(){
		em.close();
	}


	
	
}
