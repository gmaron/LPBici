package mipatronDAO;




import rest.AuditoriaRest;
import misclases.Auditoria;

public interface IAuditoriaDAO {
	
	public void guardarAuditoria(Auditoria aud);
	public AuditoriaRest recuperarAuditoriaRest(String op);
	
}
