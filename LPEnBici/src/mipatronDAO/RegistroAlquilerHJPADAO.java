package mipatronDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import misclases.RegistroAlquiler;
import misclases.Usuario;

public class RegistroAlquilerHJPADAO implements IRegistroAlquilerDAO {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	private static EntityManager em;
	
	
	
	@Override
	public void guardarRegistroAlquiler(RegistroAlquiler registroAlquiler) {
		// TODO Auto-generated method stub
		em =  emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.persist(registroAlquiler);
	
		etx.commit();
		em.close();
	}

	@Override
	public void modificarRegistroAlquiler(RegistroAlquiler registroAlquiler) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.merge(registroAlquiler);
		
		etx.commit();
		em.close();
	}

	@Override
	public void eliminarRegistroAlquiler(RegistroAlquiler registroAlquiler) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.remove(em.merge(registroAlquiler));		
		
		etx.commit();
		em.close();
	}
	
	@Override
	public void eliminarRegistroAlquilerLogica(RegistroAlquiler reg) {		
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		reg.setEliminado(true);
		em.merge(reg);
		//em.remove(em.merge(usuario));		
		
		etx.commit();
		em.close();
	}
	
	
	@Override
	public RegistroAlquiler recuperarRegistroAlquiler(Long id) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		RegistroAlquiler reg = em.find(RegistroAlquiler.class, id); 
		em.close();
		return reg;
	}
	
	@Override
	public List<RegistroAlquiler> recuperarAlquilerUsuario(Usuario usr){
		em = emf.createEntityManager();
		List<RegistroAlquiler> resultList = new ArrayList<RegistroAlquiler>();
		Query q = em.createQuery("from RegistroAlquiler r where r.fechaEntrada ="+null) ;
		System.out.println("Buscando registros por usuarios");
		@SuppressWarnings("unchecked")
		List<RegistroAlquiler> listAux = Collections.checkedList(q.getResultList(), RegistroAlquiler.class);
		for (RegistroAlquiler ra : listAux){
			if (ra.getUsuarioUso().getEmail().equals(usr.getEmail())){
				resultList.add(ra);
				System.out.println("Usuario: "+ra.getUsuarioUso().getNombre());
			}
		}
		
		em.close();
		return resultList;
	}

}
