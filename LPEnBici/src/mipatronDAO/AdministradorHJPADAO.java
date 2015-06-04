package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.Administrador;
import misclases.Persona;


public class AdministradorHJPADAO implements IAdministradorDAO{

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnityPersistence");

	@Override
	public void guardarAdministrador(Administrador administrador) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.persist(administrador);
	
		etx.commit();
		em.close();
	}

	@Override
	public void modificarAdministrador(Administrador administrador) {
		// TODO Auto-generated method stub
		
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		//ACA ?????????????????
		
		etx.commit();
		em.close();
		
		
	}

	@Override
	public void eliminarAdministrador(Long administrador) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		Administrador adminBorrar = em.getReference(Administrador.class, administrador);//em.find(Administrador.class, administrador);
		em.remove(adminBorrar);		
		
		etx.commit();
		em.close();
		
	}
	
	
}
