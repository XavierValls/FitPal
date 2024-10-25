package BLL;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.ControllerPersonalTrainer;

public class PersonalTrainer extends Persona{
	private int idPersonalTrainer;
	private double calificacion;
	private boolean disponiblidad;
	private LinkedList<PersonalTrainer> aCargo = new LinkedList<PersonalTrainer>();
	private LinkedList<Review> reviews = new LinkedList<Review>();
	private LinkedList<PersonalTrainer> horarios = new LinkedList<PersonalTrainer>();
	private static LinkedList<PersonalTrainer> nuevoPer = new LinkedList<PersonalTrainer>();
	
	public PersonalTrainer(int id, String nombre, String apellido, String email, String contra,
			int rol) {
		super(id, nombre, apellido, email, contra, rol);
		this.calificacion = 0;
		this.disponiblidad = true;
		this.idPersonalTrainer = 0;
	}
	public PersonalTrainer(int id, String nombre, String apellido, String email, String contra,
			int rol,int idPersonalTrainer) {
		super(id, nombre, apellido, email, contra, rol);
		this.calificacion = 0;
		this.disponiblidad = true;
		this.idPersonalTrainer = idPersonalTrainer;
	}
	public PersonalTrainer( String nombre, String apellido, String email, String contra,
			int rol) {
		super(nombre, apellido, email, contra, rol);
		this.calificacion = 0;
		this.disponiblidad = true;
		this.idPersonalTrainer = 0;
	}
	public PersonalTrainer(int id) {
		super(id);
		this.calificacion = 0;
		this.disponiblidad = true;
		this.idPersonalTrainer = 0;
	}
	public PersonalTrainer(int id, int id2) {
		super(id);
		this.calificacion = 0;
		this.disponiblidad = true;
		this.idPersonalTrainer = 0;
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
	public LinkedList<PersonalTrainer> getaCargo() {
		return aCargo;
	}
	public void setaCargo(LinkedList<PersonalTrainer> aCargo) {
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
	
	public static LinkedList<PersonalTrainer> getNuevoPer() {
		return nuevoPer;
	}
	public void setNuevoPer(LinkedList<PersonalTrainer> nuevoPer) {
		this.nuevoPer = nuevoPer;
	}
	
	
	public int getIdPersonalTrainer() {
		return idPersonalTrainer;
	}
	public void setIdPersonalTrainer(int idPersonalTrainer) {
		this.idPersonalTrainer = idPersonalTrainer;
	}
	

	
	@Override
	public String toString() {
		return "PersonalTrainer [idPersonalTrainer=" + idPersonalTrainer + ", calificacion=" + calificacion
				+ ", disponiblidad=" + disponiblidad + ", aCargo=" + aCargo + ", reviews=" + reviews + ", horarios="
				+ horarios + "]";
	}
	public void menuPer() {
		JOptionPane.showMessageDialog(null, "Bienvenido de nuevo " +this.getNombre() + "!");
		int opcion,opcion2,opcion3,opcion4,opcion5,opcion6;
		
		String[] opciones = {
				"Rutinas", "Alumnos","Desafios","Mi Perfil","Cerrar Sesion"
		};
		
		do {
			opcion = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones, opciones[0]); 
		
		switch (opcion) {
		case 0:
			do {
				String[] opciones2 = {
						"Mis Rutinas", "Crear Rutina","Editar Rutina","Salir"
				};
				opcion2 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones2, opciones2[0]);
				switch (opcion2) {
				case 0:
					JOptionPane.showMessageDialog(null, "Veo mis rutinas creadas aqui");
					break;

				case 1:
					String[] tipoRut = {"Personalizada","Predeterminada"};
					String eleccion = (String)JOptionPane.showInputDialog(null, "Que tipo de rutina creara?", 
							null, JOptionPane.QUESTION_MESSAGE, null, tipoRut, tipoRut[0]);
					if (eleccion.equals("Personalizada")) {
						JOptionPane.showMessageDialog(null, this.getIdPersonalTrainer());
						this.aCargo = ControllerPersonalTrainer.MostrarAlumnos(this.getIdPersonalTrainer());
						String[] alumnos = new String[(this.aCargo.size())];
				        for (int i = 0; i < this.aCargo.size(); i++) {
				            alumnos[i] = this.aCargo.get(i).getNombre();
				        }
					} else {
						
					}
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "Edito las rutinas aqui");
					break;
				}
			} while (opcion2!=3);
			break;

		case 1:
			do {
				String[] opciones3 = {
						"Mis Alumnos","Salir"
				};
				opcion3 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones3, opciones3[0]);
				switch (opcion3) {
				case 0:
					JOptionPane.showMessageDialog(null, "Veo los alumnos a cargo");
					break;
				}
			} while (opcion3!=1);
			break;
		case 2:
			do {
				String[] opciones4 = {
						"Desafios Disponibles", "Crear Desafio","Editar Desafio","Mis Desafios","Salir"
				};
				opcion4 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones4, opciones4[0]);
				switch (opcion4) {
				case 0:
					JOptionPane.showMessageDialog(null, "Aqui veo los desafios disponibles");
					break;

				case 1:
					JOptionPane.showMessageDialog(null, "Aqui creo desafios");
					break;
					
				case 2:
					JOptionPane.showMessageDialog(null, "Aqui puedo eliminar desafios");
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Aqui veo los desafios que cree yo");
					break;
				}
			} while (opcion4!=4);
			break;
		case 3:
			do {
				String[] opciones5 = {
						"Ver Perfil", "Editar Perfil","Salir"
				};
				opcion5 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones5, opciones5[0]);
				switch (opcion5) {
				case 0:
					JOptionPane.showMessageDialog(null, this.toString());
					break;

				case 1:
					JOptionPane.showMessageDialog(null, "Aqui edito mi perfil");
					break;
				}
			} while (opcion5!=2);
		}
		} while (opcion!=4);
		
	}
	
	
	
	

	
	
}
