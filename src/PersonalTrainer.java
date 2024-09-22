import java.util.LinkedList;

public class PersonalTrainer {

	private double calificacion;
	private boolean disponiblidad;
	LinkedList<Alumno> aCargo = new LinkedList<Alumno>();
	LinkedList<Review> reviews = new LinkedList<Review>();
	LinkedList<PersonalTrainer> horarios = new LinkedList<PersonalTrainer>();
	public PersonalTrainer(double calificacion, boolean disponiblidad, LinkedList<Alumno> aCargo,
			LinkedList<Review> reviews, LinkedList<PersonalTrainer> horarios) {
		super();
		this.calificacion = calificacion;
		this.disponiblidad = disponiblidad;
		this.aCargo = aCargo;
		this.reviews = reviews;
		this.horarios = horarios;
	}
	public double getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
	public boolean isDisponiblidad() {
		return disponiblidad;
	}
	public void setDisponiblidad(boolean disponiblidad) {
		this.disponiblidad = disponiblidad;
	}
	public LinkedList<Alumno> getaCargo() {
		return aCargo;
	}
	public void setaCargo(LinkedList<Alumno> aCargo) {
		this.aCargo = aCargo;
	}
	public LinkedList<Review> getReviews() {
		return reviews;
	}
	public void setReviews(LinkedList<Review> reviews) {
		this.reviews = reviews;
	}
	public LinkedList<PersonalTrainer> getHorarios() {
		return horarios;
	}
	public void setHorarios(LinkedList<PersonalTrainer> horarios) {
		this.horarios = horarios;
	}
	@Override
	public String toString() {
		return "PersonalTrainer [calificacion=" + calificacion + ", disponiblidad=" + disponiblidad + ", aCargo="
				+ aCargo + ", reviews=" + reviews + ", horarios=" + horarios + "]";
	}
	
	
}
