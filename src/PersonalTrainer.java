import java.util.LinkedList;

public class PersonalTrainer extends Persona{

	private double calificacion;
	private boolean disponiblidad;
	private LinkedList<Alumno> aCargo = new LinkedList<Alumno>();
	private LinkedList<Review> reviews = new LinkedList<Review>();
	private LinkedList<PersonalTrainer> horarios = new LinkedList<PersonalTrainer>();
	private LinkedList<PersonalTrainer> nuevoPer = new LinkedList<PersonalTrainer>();
	
	public PersonalTrainer(String nombre, String apellido, String email, String contra,
			String rol) {
		super(nombre, apellido, email, contra, rol);
		this.calificacion = 0;
		this.disponiblidad = true;
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
	
	public LinkedList<PersonalTrainer> getNuevoPer() {
		return nuevoPer;
	}
	public void setNuevoPer(LinkedList<PersonalTrainer> nuevoPer) {
		this.nuevoPer = nuevoPer;
	}
	@Override
	public String toString() {
		return "PersonalTrainer [calificacion=" + calificacion + ", disponiblidad=" + disponiblidad + ", aCargo="
				+ aCargo + ", reviews=" + reviews + ", horarios=" + horarios + ", Nombre=" + getNombre()
				+ ", Apellido=" + getApellido() + ", Email=" + getEmail() + ", Contra=" + getContra()
				+ "]";
	}
	
	
	
	
	
	

	
	
}
