package aspecto;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;


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

	@GET
	@Path("datosUsuario")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public  List<AuditoriaRest> getAuditoriasUsuario() {
		List <AuditoriaRest> list = new ArrayList<AuditoriaRest>();
		list = auditoriaService.getAuditoriaAsList("Usuario");
		return list;
	}
	
	
	
	@GET
	@Path("datosAdministrador")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public  List<AuditoriaRest> getAuditoriasAdministrador() {
		List <AuditoriaRest> list = new ArrayList<AuditoriaRest>();
		list = auditoriaService.getAuditoriaAsList("Administrador");
		return list;
	}
}
