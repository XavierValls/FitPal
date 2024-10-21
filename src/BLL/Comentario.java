package BLL;
import java.time.LocalDate;

public class Comentario {

	private String comentario;
	private LocalDate fechaCom;
	
	public Comentario(String comentario, LocalDate fechaCom) {
		super();
		this.comentario = comentario;
		this.fechaCom = fechaCom;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public LocalDate getFechaCom() {
		return fechaCom;
	}
	public void setFechaCom(LocalDate fechaCom) {
		this.fechaCom = fechaCom;
	}
	@Override
	public String toString() {
		return "Comentario [comentario=" + comentario + ", fechaCom=" + fechaCom + "]";
	}
	
	
}
