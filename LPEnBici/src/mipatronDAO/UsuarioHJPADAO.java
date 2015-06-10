package mipatronDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import misclases.Persona;
import misclases.Usuario;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class UsuarioHJPADAO implements IUsuarioDAO{

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	private static EntityManager em;
	
	
	
	@Override
	public void guardarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		em =  emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.persist(usuario);
	
		etx.commit();
		em.close();
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.merge(usuario);
		
		etx.commit();
		em.close();
		
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
	
		em.remove(em.merge(usuario));		
		
		etx.commit();
		em.close();
	}


	@Override
	public Usuario recuperarUsuario(Long id) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		Usuario usr = em.find(Usuario.class, id); 
		em.close();
		return usr;
	}
	
	public List<Usuario> recuperarTodosUsuarios(){
		em = emf.createEntityManager();
		List<Usuario> resultList = em.createQuery("Select u from Usuario u").getResultList();
		
		return resultList;
	}

	

}
