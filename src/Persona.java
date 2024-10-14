import java.util.LinkedList;

import javax.swing.JOptionPane;

public abstract class Persona {
	private String nombre;
	private String apellido;
	private String email;
	private String contra;
	private String rol;
	private static LinkedList<Persona> usuarios = new LinkedList<Persona>();
	
	public Persona(String nombre, String apellido, String email, String contra, String rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contra = contra;
		this.rol = rol;
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
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
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
		return "\nPersona [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", contra=" + contra + ", rol=" + rol + "]";
	}
	
	
	public static void Loguearse() {
		String email = JOptionPane.showInputDialog("Ingrese su Email");
		String contra = JOptionPane.showInputDialog("Ingrese su Contraseña");
		for (Alumno alumno : Alumno.getNuevoAlu()) {
			if (alumno.getEmail().equals(email) && alumno.getContra().equals(contra)) {
				JOptionPane.showMessageDialog(null, "Usuario Encontrado");
				alumno.menuAlu();
				return;
			}
		}
		for (PersonalTrainer PersonalTrainer : PersonalTrainer.getNuevoPer()) {
			if (PersonalTrainer.getEmail().equals(email) && PersonalTrainer.getContra().equals(contra)) {
				JOptionPane.showMessageDialog(null, "Usuario Encontrado");
				PersonalTrainer.menuPer();
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "No se encontro ningun Usuario");
		
	}
	
	public static void Registrarse() {
		String nombre = JOptionPane.showInputDialog("Ingrese su Nombre");
		String apellido = JOptionPane.showInputDialog("Ingrese su Apellido");
		boolean flag;
		String email;
		do {
			flag = false;
			email= JOptionPane.showInputDialog("Ingrese su Email");
			for (Persona usuario : usuarios) {
				if (usuario.getEmail().equals(email)) {
					JOptionPane.showMessageDialog(null, "El email ya se encuentra registrado");
					flag = true;
				}
			}
		} while (flag);
		String contra = JOptionPane.showInputDialog("Ingrese su Contraseña");
		String[] rol = {"PersonalTrainer","Alumno"};
		String elegido = (String) JOptionPane.showInputDialog(null, email, contra, 0, null, rol, rol);
		if (elegido.equals("PersonalTrainer")) {
			PersonalTrainer nuevo = new PersonalTrainer(nombre,apellido,email,contra,elegido);
			ControllerPersona.agregarUsuario(nuevo);
			JOptionPane.showMessageDialog(null, "Registro Completado con Exito");
			JOptionPane.showMessageDialog(null, nuevo.getNuevoPer());
		} else {
			Alumno nuevoAlu = new Alumno(nombre,apellido,email,contra,elegido);
			nuevoAlu.getNuevoAlu().add(nuevoAlu);
			JOptionPane.showMessageDialog(null, "Registro Completado con Exito");
			JOptionPane.showMessageDialog(null, nuevoAlu.getNuevoAlu());
			
			
		}
	}
	
}
