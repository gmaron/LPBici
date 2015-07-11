package aspecto;



import java.util.ArrayList;
import java.util.List;

import mipatronDAO.MyFactoryDAO;

public class AuditoriaService {
	MyFactoryDAO f = new MyFactoryDAO();

	public AuditoriaService() {
		
	}
		
	public List<AuditoriaRest> getAuditoriaAsList(String entidad) {
		List<AuditoriaRest> auditoriaList = new ArrayList<AuditoriaRest>();
		AuditoriaRest aud = f.getAuditoriaDAO().recuperarAuditoriaRest("Guardar"+entidad);
		auditoriaList.add(aud);
		aud = f.getAuditoriaDAO().recuperarAuditoriaRest("Modificar"+entidad);
		auditoriaList.add(aud);
		aud = f.getAuditoriaDAO().recuperarAuditoriaRest("Eliminar"+entidad);
		auditoriaList.add(aud);
		
		return auditoriaList;		
	}
}
