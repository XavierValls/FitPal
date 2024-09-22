
public abstract class Persona {
	private String nombre;
	private String apellido;
	private String email;
	private String nombreUsuario;
	private String contra;
	private String rol;
	
	public Persona(String nombre, String apellido, String email, String nombreUsuario, String contra, String rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
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
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", nombreUsuario="
				+ nombreUsuario + ", contra=" + contra + ", rol=" + rol + "]";
	}
	
	

	
}
