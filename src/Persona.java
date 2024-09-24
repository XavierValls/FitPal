import java.util.LinkedList;

import javax.swing.JOptionPane;

public abstract class Persona {
	private String nombre;
	private String apellido;
	private String email;
	private String contra;
	private String rol;
	
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
	

	@Override
	public String toString() {
		return "\nPersona [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", contra=" + contra + ", rol=" + rol + "]";
	}
	
	
	public void Loguearse() {
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
	
	public void Registrarse() {
		String nombre = JOptionPane.showInputDialog("Ingrese su Nombre");
		String apellido = JOptionPane.showInputDialog("Ingrese su Apellido");
		String email = JOptionPane.showInputDialog("Ingrese su Email");
		String contra = JOptionPane.showInputDialog("Ingrese su Contraseña");
		String[] rol = {"PersonalTrainer","Alumno"};
		String elegido = (String) JOptionPane.showInputDialog(null, email, contra, 0, null, rol, rol);
		if (elegido.equals("PersonalTrainer")) {
			PersonalTrainer nuevoPer = new PersonalTrainer(nombre,apellido,email,contra,elegido);
			nuevoPer.getNuevoPer().add(nuevoPer);
			JOptionPane.showMessageDialog(null, "Registro Completado con Exito");
			JOptionPane.showMessageDialog(null, nuevoPer.getNuevoPer());
			
		} else {
			double peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese su Peso en KG"));
			int altura = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su Altura en CM"));
			String[] nivel = {"Principiante","Intermedio","Avanzado"};
			String elegido2 = (String) JOptionPane.showInputDialog(null, email, contra, 0, null, nivel, nivel);
			Alumno nuevoAlu = new Alumno(nombre,apellido,email,contra,elegido,peso,altura,elegido2);
			nuevoAlu.getNuevoAlu().add(nuevoAlu);
			JOptionPane.showMessageDialog(null, "Registro Completado con Exito");
			JOptionPane.showMessageDialog(null, nuevoAlu.getNuevoAlu());
			
			
		}
	}
	
}
