package BLL;
import java.util.Date;

public class Desafio {
	private int idDesafio;
	private PersonalTrainer personalTrainer;
	private String titulo;
	private int anotados;
	private String descripcion;
	private Date fechaExp;
	private String recompensa;
	
	public Desafio(PersonalTrainer personalTrainer, int anotados, String descripcion, Date fechaExp,
			String recompensa) {
		super();
		this.personalTrainer = personalTrainer;
		this.anotados = anotados;
		this.descripcion = descripcion;
		this.fechaExp = fechaExp;
		this.recompensa = recompensa;
	}
	public Desafio(String titulo, String descripcion, Date fechaExp, PersonalTrainer personalTrainer) {
		super();
		this.titulo=titulo;
		this.descripcion = descripcion;
		this.fechaExp = fechaExp;
		this.personalTrainer = personalTrainer;
	}
	public Desafio(int idDesafio, String titulo, String descripcion, Date fechaExp, int idPersonalTrainer) {
		super();
		this.idDesafio = idDesafio;
		this.titulo=titulo;
		this.descripcion = descripcion;
		this.fechaExp = fechaExp;
		this.personalTrainer = new PersonalTrainer(idPersonalTrainer);
	}
	public Desafio(String titulo, String descripcion, Date fechaExp, int idPersonalTrainer) {
		super();
		this.titulo=titulo;
		this.descripcion = descripcion;
		this.fechaExp = fechaExp;
		this.personalTrainer = new PersonalTrainer(idPersonalTrainer);
	}
	
	
	

	
	
	public PersonalTrainer getPersonalTrainer() {
		return personalTrainer;
	}
	public void setPersonalTrainer(PersonalTrainer personalTrainer) {
		this.personalTrainer = personalTrainer;
	}
	public int getAnotados() {
		return anotados;
	}
	public void setAnotados(int anotados) {
		this.anotados = anotados;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Date getFechaExp() {
		return fechaExp;
	}
	public void setFechaExp(Date fechaExp) {
		this.fechaExp = fechaExp;
	}
	public String getRecompensa() {
		return recompensa;
	}
	public void setRecompensa(String recompensa) {
		this.recompensa = recompensa;
	}
	
	public int getIdDesafio() {
		return idDesafio;
	}
	public void setIdDesafio(int idDesafio) {
		this.idDesafio = idDesafio;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@Override
	public String toString() {
		return "Desafio creado por " + personalTrainer+ "\nanotados=" + anotados + "\n descripcion=" + descripcion
				+ "\n fechaExp=" + fechaExp;
	}
	
	


}
