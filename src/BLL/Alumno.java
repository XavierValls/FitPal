package BLL;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Alumno extends Persona {

	private double peso;
	private int altura;
	private String nivel;
	private Objetivo objPersonal;
	
	private LinkedList<PersonalTrainer> contratado = new LinkedList<PersonalTrainer>();
	private static LinkedList<Alumno> nuevoAlu = new LinkedList<Alumno>();

	public Alumno(int id,String nombre, String apellido, String email, String contra, int rol) {
		super(id,nombre, apellido, email, contra, rol);
		this.peso = peso;
		this.altura = altura;
		this.nivel = nivel;
	}
	
	public Alumno(String nombre, String apellido, String email, String contra, int rol) {
		super(nombre, apellido, email, contra, rol);
		this.peso = peso;
		this.altura = altura;
		this.nivel = nivel;
	}
	
	public Alumno(int id) {
		super(id);
		this.peso = peso;
		this.altura = altura;
		this.nivel = nivel;
	}

	public Alumno() {
		super("nombre","apellido","email","contra",2);
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Objetivo getObjPersonal() {
		return objPersonal;
	}

	public void setObjPersonal(Objetivo objPersonal) {
		this.objPersonal = objPersonal;
	}

	public LinkedList<PersonalTrainer> getContratado() {
		return contratado;
	}

	public void setContratado(LinkedList<PersonalTrainer> contratado) {
		this.contratado = contratado;
	}
	

	public static LinkedList<Alumno> getNuevoAlu() {
		return nuevoAlu;
	}

	public void setNuevoAlu(LinkedList<Alumno> nuevoAlu) {
		this.nuevoAlu = nuevoAlu;
	}

	@Override
	public String toString() {
		return "\nAlumno [peso=" + peso + ", altura=" + altura + ", nivel=" + nivel + ", objPersonal=" + objPersonal
				+ ", Nombre=" + getNombre() + ", Apellido=" + getApellido() + ", Email=" + getEmail()
				+ ", Contra=" + getContra() + "]";
	}

	
	public void menuAlu() {
		JOptionPane.showMessageDialog(null, "Bienvenido de nuevo " +this.getNombre() + "!");
		int opcion,opcion2,opcion3,opcion4,opcion5,opcion6;
		
		String[] opciones = {
				"Rutinas", "Objetivo","PersonalTrainer","Desafios","Mi Perfil","Cerrar Sesion"
		};
		
		do {
			opcion = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones, opciones[0]); 
		
		switch (opcion) {
		case 0:
			do {
				String[] opciones2 = {
						"Mis Rutinas", "Rutinas Predeterminadas","Salir"
				};
				opcion2 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones2, opciones2[0]);
				switch (opcion2) {
				case 0:
					JOptionPane.showMessageDialog(null, "Veo mis rutinas aqui");
					break;

				case 1:
					JOptionPane.showMessageDialog(null, "Veo las rutinas predeterminadas aqui");
					break;
				}
			} while (opcion2!=2);
			break;

		case 1:
			do {
				String[] opciones3 = {
						"Mi Objetivo", "Establecer Objetivo","Editar Objetivo","Salir"
				};
				opcion3 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones3, opciones3[0]);
				switch (opcion3) {
				case 0:
					JOptionPane.showMessageDialog(null, "Veo mi Objetivo aqui");
					break;

				case 1:
					JOptionPane.showMessageDialog(null, "Establezco mi Objetivo aqui");
					break;
					
				case 2:
					JOptionPane.showMessageDialog(null, "Edito mi Objetivo aqui");
					break;
				}
			} while (opcion3!=3);
			break;
		case 2:
			do {
				String[] opciones4 = {
						"Contratados", "Contratar","Dejar Review","Salir"
				};
				opcion4 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones4, opciones4[0]);
				switch (opcion4) {
				case 0:
					JOptionPane.showMessageDialog(null, "Aqui veo los personal trainer que contrate");
					break;

				case 1:
					JOptionPane.showMessageDialog(null, "Aqui contrato personal trainers");
					break;
					
				case 2:
					JOptionPane.showMessageDialog(null, "Aqui puedo dejar una review");
					break;
				}
			} while (opcion4!=3);
			break;
		case 3:
			do {
				String[] opciones5 = {
						"Ver Desafios Disponibles", "Anotarse a un desafio","Desafios Activos","Salir"
				};
				opcion5 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones5, opciones5[0]);
				switch (opcion5) {
				case 0:
					JOptionPane.showMessageDialog(null, "Aqui veo los desafios disponibles");
					break;

				case 1:
					JOptionPane.showMessageDialog(null, "Aqui me anoto a un desafio");
					break;
					
				case 2:
					JOptionPane.showMessageDialog(null, "Aqui veo los desafios a los que me anote");
					break;
				}
			} while (opcion5!=3);
			break;
		case 4:
			do {
				String[] opciones6 = {
						"Ver Perfil", "Editar Perfil","Salir"
				};
				opcion6 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones6, opciones6[0]);
				switch (opcion6) {
				case 0:
					JOptionPane.showMessageDialog(null, this.toString());
					break;

				case 1:
					JOptionPane.showMessageDialog(null, "Aqui edito mi perfil");
					break;
				}
			} while (opcion6!=2);
		}
		} while (opcion!=5);
		
	}

	

	

	
	
}
