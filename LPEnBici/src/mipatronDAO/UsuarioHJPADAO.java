package mipatronDAO;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import misclases.Usuario;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class UsuarioHJPADAO implements IUsuarioDAO{

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	private static EntityManager em;
			
	@Override
	public void guardarUsuario(Usuario usuario) {
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
		
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
	
		em.remove(em.merge(usuario));		
		
		etx.commit();
		em.close();
	}

	@Override
	public void eliminarUsuarioLogica(Usuario usr) {
		
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		usr.setEliminado(true);
		em.merge(usr);
		//em.remove(em.merge(usuario));		
		
		etx.commit();
		em.close();
	}

	@Override
	public Usuario recuperarUsuario(String email) {
		em = emf.createEntityManager();
		//Usuario usr = em.find(Usuario.class, id);
		
		Usuario usr = null;
		Query q ;
		try {
			q = em.createQuery("FROM Usuario u WHERE u.email ='"+email+"'");
			usr = (Usuario) q.getSingleResult();
			System.out.println("pass: "+usr.getPassword());
		} catch (Exception e) {
			usr = null;
			//e.printStackTrace();
		}
		em.close();
		return usr;
	}
	
	public List<Usuario> recuperarUsuariosNoEliminados(){
		em = emf.createEntityManager();
		Query q = em.createQuery("from Usuario u where u.eliminado = '"+false+"'") ;
		@SuppressWarnings("unchecked")
		List<Usuario> resultList = Collections.checkedList(q.getResultList(), Usuario.class);
//		for (Usuario user : resultList){
//			System.out.println("Usuario: "+user.getApellido()+"\n");
//		}
		return resultList;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		em = emf.createEntityManager();
		Query q = em.createQuery("from Usuario") ;
		@SuppressWarnings("unchecked")
		List<Usuario> resultList = Collections.checkedList(q.getResultList(), Usuario.class);
//		for (Usuario user : resultList){
//			System.out.println("Usuario: "+user.getApellido()+"\n");
//		}
		return resultList;
	}

	@Override
	public Usuario recuperarUsuarioNoEliminado(String email) {
		em = emf.createEntityManager();
		//Usuario usr = em.find(Usuario.class, id);
		
		Usuario usr = null;
		Query q ;
		try {
			q = em.createQuery("FROM Usuario u WHERE (u.email ='"+email+"' and u.eliminado='"+false+"')");
			usr = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			usr = null;
			//e.printStackTrace();
		}
		em.close();
		return usr;
	}

	

}
