package BLL;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.ControllerAlumno;
import DLL.ControllerPersona;
import DLL.ControllerPersonalTrainer;

public class Persona {
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String contra;
	private int rol;
	private static LinkedList<Persona> usuarios = new LinkedList<Persona>();
	
	public Persona(int id, String nombre, String apellido, String email, String contra, int rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contra = contra;
		this.rol = rol;
	}
	public Persona(String nombre, String apellido, String email, String contra, int rol) {
		super();
		this.id = 0;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contra = contra;
		this.rol = rol;
	}
	public Persona(int id) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contra = contra;
		this.rol = rol;
	}
	public Persona(int id, String nombre, String apellido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contra = contra;
		this.rol = rol;
	}
	public Persona(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;

	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContra() {
		return contra;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}
	
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}
	public static LinkedList<Persona> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(LinkedList<Persona> usuarios) {
		Persona.usuarios = usuarios;
	}

	
	
	
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", contra="
				+ contra + ", rol=" + rol + "]";
	}
	public static Object Loguearse(String email, String contra) {
		//String email = JOptionPane.showInputDialog("Ingrese su Email");
		//String contra = JOptionPane.showInputDialog("Ingrese su Contraseña");
		for (Persona persona : ControllerPersona.MostrarUsuarios()) {
			if (persona.getEmail().equals(email) && persona.getContra().equals(contra)) {
				JOptionPane.showMessageDialog(null, "Usuario Encontrado");
				if (persona.getRol()==1) {
					int idPersonal = (int) ControllerPersonalTrainer.ObtenerIdPersonal(persona.getId());
					PersonalTrainer	nuevo = new PersonalTrainer(persona.getId(), persona.getNombre(), persona.getApellido(), persona.getEmail(), persona.getContra(), persona.getRol(), idPersonal);
					//nuevo.menuPer();	
					return nuevo;
					
				} else {
					int idAlumno = (int) ControllerAlumno.ObtenerIdAlumno(persona.getId());
					Alumno nuevo2 = new Alumno(persona.getId(), persona.getNombre(), persona.getApellido(), persona.getEmail(), persona.getContra(), persona.getRol(), idAlumno);
					//nuevo2.menuAlu();
					return nuevo2;
				}
				
			}
		}
		JOptionPane.showMessageDialog(null, "No se encontro ningun Usuario");
		return null;
		
	}
	
	public static void Registrarse(String nombre, String apellido, String email, String contra, int elegido) {
		//String nombre = JOptionPane.showInputDialog("Ingrese su Nombre");
		//String apellido = JOptionPane.showInputDialog("Ingrese su Apellido");
		boolean flag;
		//String email;
		do {
			flag = false;
//			email= JOptionPane.showInputDialog("Ingrese su Email");
			for (Persona usuario : ControllerPersona.MostrarUsuarios()) {
				if (usuario.getEmail().equals(email)) {
					JOptionPane.showMessageDialog(null, "El email ya se encuentra registrado");
					flag = true;
				}
			}
		} while (flag);
//		contra = JOptionPane.showInputDialog("Ingrese su Contraseña");
//		elegido = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 1 si es PersonalTrainer, Elija 2 si es Alumno"));
		if (elegido==1) {
			PersonalTrainer nuevo = new PersonalTrainer(nombre,apellido,email,contra,elegido);
			int idUsuario = (int) ControllerPersona.agregarUsuario(nuevo);
			int idPersonal = (int)ControllerPersonalTrainer.agregarPersonal(new PersonalTrainer(idUsuario));
			nuevo.setIdPersonalTrainer(idPersonal);
			
			JOptionPane.showMessageDialog(null, "Registro Completado con Exito");
			JOptionPane.showMessageDialog(null, nuevo.getNuevoPer());
		} else {
			Alumno nuevoAlu = new Alumno(nombre,apellido,email,contra,elegido);
			int idUsuario = (int) ControllerPersona.agregarUsuario(nuevoAlu);
			ControllerAlumno.agregarAlumno(new Alumno(idUsuario));
			JOptionPane.showMessageDialog(null, "Registro Completado con Exito");
			JOptionPane.showMessageDialog(null, nuevoAlu.getNuevoAlu());
			
			
		}
	}
	
	
	
}
