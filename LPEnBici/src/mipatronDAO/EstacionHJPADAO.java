package mipatronDAO;



import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import misclases.Bicicleta;
import misclases.Estacion;


public class EstacionHJPADAO implements IEstacionDAO {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
	private static EntityManager em;
		
	@Override
	public void guardarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		em =  emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.persist(estacion);
		
		etx.commit();
		em.close();
	}

	@Override
	public void modificarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.merge(estacion);
		
		etx.commit();
		em.close();
	}

	@Override
	public void eliminarEstacion(Estacion estacion) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		em.remove(em.merge(estacion));		
		
		etx.commit();
		em.close();
	}
	
	@Override
	public void eliminarEstacionLogica(Estacion estacion) {
		
		em = emf.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		
		estacion.setEliminado(true);
		em.merge(estacion);
		//em.remove(em.merge(usuario));		
		
		etx.commit();
		em.close();
	}

	@Override
	public Estacion recuperarEstacion(Long id) {
		// TODO Auto-generated method stub
		em = emf.createEntityManager();
		Estacion est = em.find(Estacion.class, id);
		em.close();
		return est;
	}
	
	@Override
	public List<Estacion> recuperarEstacionesNoEliminadas(){
		em = emf.createEntityManager();
		Query q = em.createQuery("from Estacion e where e.eliminado = '"+false+"'") ;
		@SuppressWarnings("unchecked")
		List<Estacion> resultList = Collections.checkedList(q.getResultList(), Estacion.class);
		System.out.println("Busque estaciones");
		for (Estacion est : resultList){
			System.out.println("Estacion: "+est.getNombre()+"\n");
		}
		return resultList;
	}
	
	@Override
	public Estacion recuperarEstacionNombre(String nombre){
		em = emf.createEntityManager();
		Estacion est = null;
		Query q ;
		try {
			q = em.createQuery("FROM Estacion e WHERE e.nombre ='"+nombre+"'");
			est = (Estacion) q.getSingleResult();
			System.out.println("Nombre Estacion: "+est.getNombre());
		} catch (Exception e) {
			est = null;
			//e.printStackTrace();
		}
		em.close();
		return est;
	}
	
	
	@Override
	public List<Bicicleta> listarBicicletasEstacion(Long idEstacion){
		Query q = em.createQuery("from Bicicleta b where b.eliminado = '"+false+"'"
				+ "and b.id_Estacion='"+idEstacion+"'");
		
		@SuppressWarnings("unchecked")
		List<Bicicleta> auxList = Collections.checkedList(q.getResultList(), Bicicleta.class);
//		List<Bicicleta> finalList = new ArrayList<Bicicleta>();
		for (Bicicleta bi : auxList){
//			if (bi.get)
			System.out.println("Bicicleta: "+bi.getPatente()+"\n");
		}
		return auxList; 
	}
	

}
