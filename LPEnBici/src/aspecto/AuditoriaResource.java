package aspecto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.primefaces.json.JSONObject;

@Path("/auditoria")
public class AuditoriaResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	AuditoriaService auditoriaService;

	public AuditoriaResource() {
		auditoriaService = new AuditoriaService();
	}

	//Map<String, Integer>
	@GET
	@Path("datos")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public  List<AuditoriaRest> getAuditorias() {
//		Map <String , Integer> aud = auditoriaService.getAuditoriaAsList();
		List <AuditoriaRest> list = new ArrayList<AuditoriaRest>();
//		list.add(new AuditoriaRest("Guardar",10));
//		list.add(new AuditoriaRest("Modificar",20));
		list = auditoriaService.getAuditoriaAsList();
		return list;
	}
}
