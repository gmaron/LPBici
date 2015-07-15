package misclases;

import javax.persistence.*;

@Entity
public class Denuncia {
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Usuario usuarioDenuncia;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Bicicleta bicicleta;
	
	
	boolean eliminado;
	private String comentario; //el comentario puede venir vacio
	
	@Id @GeneratedValue
	private Long id;
		
	public Denuncia(Usuario usr, Bicicleta bicicleta, String comentario) {
		super();
		this.bicicleta = bicicleta;
		this.usuarioDenuncia = usr;
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

	
	
	
	public Bicicleta getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}

	@Override
	public String toString() {
		return "Denuncia";
	} 
	

}
