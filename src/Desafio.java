import java.time.LocalDate;

public class Desafio {

	private PersonalTrainer personalTrainer;
	private int anotados;
	private String descripcion;
	private LocalDate fechaExp;
	private String recompensa;
	
	public Desafio(PersonalTrainer personalTrainer, int anotados, String descripcion, LocalDate fechaExp,
			String recompensa) {
		super();
		this.personalTrainer = personalTrainer;
		this.anotados = anotados;
		this.descripcion = descripcion;
		this.fechaExp = fechaExp;
		this.recompensa = recompensa;
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
	public LocalDate getFechaExp() {
		return fechaExp;
	}
	public void setFechaExp(LocalDate fechaExp) {
		this.fechaExp = fechaExp;
	}
	public String getRecompensa() {
		return recompensa;
	}
	public void setRecompensa(String recompensa) {
		this.recompensa = recompensa;
	}
	@Override
	public String toString() {
		return "Desafio [personalTrainer=" + personalTrainer + ", anotados=" + anotados + ", descripcion=" + descripcion
				+ ", fechaExp=" + fechaExp + ", recompensa=" + recompensa + "]";
	}
	
	


}
