package mipatronDAO;




import aspecto.Auditoria;
import aspecto.AuditoriaRest;

public interface IAuditoriaDAO {
	
	public void guardarAuditoria(Auditoria aud);
	public AuditoriaRest recuperarAuditoriaRest(String op);
	
}
