package BLL;
import java.util.LinkedList;

public class Rutina {
	private int idRutina;
	private int idPersonalTrainer;
	private PersonalTrainer personalTrainer;
	private int idAlumno;
	private String titulo;
	private String descripcion;
	private int tipoRutina;
	private LinkedList<Comentario>comentarios = new LinkedList<Comentario>();
	
	public Rutina(int idRutina,int idPersonalTrainer,PersonalTrainer personalTrainer, int idAlumno,String titulo,String descripcion, int tipoRutina,
			LinkedList<Comentario> comentarios) {
		super();
		this.idRutina = idRutina;
		this.idPersonalTrainer = idPersonalTrainer;
		this.personalTrainer = personalTrainer;
		this.idAlumno = idAlumno;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tipoRutina = tipoRutina;
		this.comentarios = comentarios;
	}
	public Rutina(String descripcion,String titulo,int tipoRutina, int idAlumno, int idPersonalTrainer) {
		super();
		this.idPersonalTrainer = idPersonalTrainer;
		this.titulo = titulo;
		this.tipoRutina = tipoRutina;
		this.idAlumno = idAlumno;
		this.descripcion = descripcion;

	}
	public Rutina(String descripcion,String titulo,int tipoRutina, int idPersonalTrainer) {
		super();
		this.idPersonalTrainer = idPersonalTrainer;
		this.titulo = titulo;
		this.tipoRutina = tipoRutina;
		this.descripcion = descripcion;

	}
	public Rutina(String descripcion, String titulo, PersonalTrainer personalTrainer, int idRutina) {
		super();
		this.descripcion = descripcion;
		this.titulo = titulo;
		this.personalTrainer = personalTrainer;
		this.idRutina = idRutina;
	}
	public Rutina(int idRutina, String descripcion, String titulo) {
		super();
		this.idRutina = idRutina;
		this.descripcion = descripcion;
		this.titulo = titulo;
	}
	
	public Rutina(String descripcion, String nombrePT, String apellidoPT) {
		super();
		this.descripcion = descripcion;
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
	
	public int getIdRutina() {
		return idRutina;
	}
	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}
	
	
	public int getIdPersonalTrainer() {
		return idPersonalTrainer;
	}
	public void setIdPersonalTrainer(int idPersonalTrainer) {
		this.idPersonalTrainer = idPersonalTrainer;
	}
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	
	public PersonalTrainer getPersonalTrainer() {
		return personalTrainer;
	}
	public void setPersonalTrainer(PersonalTrainer personalTrainer) {
		this.personalTrainer = personalTrainer;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@Override
	public String toString() {
		return "Rutina Creada por "+ personalTrainer + ", \n Titulo: " + titulo + ",\n Rutina:\n" + descripcion
				+"";
	}
	
	
	
	

	
}
