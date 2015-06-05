package mipatronDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import misclases.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import misclases.Administrador;
import misclases.Usuario;


public class UsuarioHJPADAO implements IUsuarioDAO{

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
	public void guardarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		em =  getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.persist(usuario);
	
		etx.commit();
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		//el codigo de modificacion va aca
		em.merge(usuario);
		etx.commit();
		
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.remove(usuario);		
		etx.commit();
	}


	@Override
	public Usuario recuperarUsuario(Long id) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
		return em.find(Usuario.class, id);
	}

	@Override
	public void closeEntityManager() {
		// TODO Auto-generated method stub
		em.close();
	}

}
