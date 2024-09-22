import java.util.LinkedList;

public class Rutina {

	private PersonalTrainer personalTrainer;
	private String descripcion;
	private int tipoRutina;
	private LinkedList<Comentario>comentarios = new LinkedList<Comentario>();
	public Rutina(PersonalTrainer personalTrainer, String descripcion, int tipoRutina,
			LinkedList<Comentario> comentarios) {
		super();
		this.personalTrainer = personalTrainer;
		this.descripcion = descripcion;
		this.tipoRutina = tipoRutina;
		this.comentarios = comentarios;
	}
	public PersonalTrainer getPersonalTrainer() {
		return personalTrainer;
	}
	public void setPersonalTrainer(PersonalTrainer personalTrainer) {
		this.personalTrainer = personalTrainer;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getTipoRutina() {
		return tipoRutina;
	}
	public void setTipoRutina(int tipoRutina) {
		this.tipoRutina = tipoRutina;
	}
	public LinkedList<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(LinkedList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	@Override
	public String toString() {
		return "Rutina [personalTrainer=" + personalTrainer + ", descripcion=" + descripcion + ", tipoRutina="
				+ tipoRutina + ", comentarios=" + comentarios + "]";
	}

	
}
