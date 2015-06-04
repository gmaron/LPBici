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
	
	@Override
	public void guardarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.persist(usuario);
		
		etx.commit();
		em.close();
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarUsuario(Long usuario) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		Usuario usuarioBorrar = em.getReference(Usuario.class, usuario);//em.find(Administrador.class, administrador);
		em.remove(usuarioBorrar);		
		
		etx.commit();
		em.close();
	}

}
