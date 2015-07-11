package misclases;

import javax.persistence.*;

@Entity
public class Denuncia {
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Usuario usuarioDenuncia;
	
	boolean eliminado;
	private String comentario; //el comentario puede venir vacio
	
	@Id @GeneratedValue
	private Long id;
		
	public Denuncia(Usuario usuarioDenuncia, String comentario) {
		super();
		this.usuarioDenuncia = usuarioDenuncia;
		this.comentario = comentario;
		this.eliminado = false;
	}
	
	public Denuncia(){
		
	}
	
	public Usuario getUsuarioDenuncia() {
		return usuarioDenuncia;
	}
	public void setUsuarioDenuncia(Usuario usuarioDenuncia) {
		this.usuarioDenuncia = usuarioDenuncia;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Override
	public String toString() {
		return "Denuncia";
	} 
	

}
