package aspecto;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.util.Map;

import mipatronDAO.MyFactoryDAO;

public class AuditoriaService {
	MyFactoryDAO f = new MyFactoryDAO();

	public AuditoriaService() {
		
	}
		
	public List<AuditoriaRest> getAuditoriaAsList() {
		List<AuditoriaRest> auditoriaList = new ArrayList<AuditoriaRest>();
		AuditoriaRest aud = f.getAuditoriaDAO().recuperarAuditoriaRest("GuardarUsuario");
		auditoriaList.add(aud);
		aud = f.getAuditoriaDAO().recuperarAuditoriaRest("ModificarUsuario");
		auditoriaList.add(aud);
		
		return auditoriaList;		
	}
}
