import java.util.LinkedList;

public class Alumno extends Persona{

	private double peso;
	private int altura;
	private String nivel;
	private Objetivo objPersonal;
	
	private LinkedList<PersonalTrainer> contratado = new LinkedList<PersonalTrainer>();
	private LinkedList<Alumno> nuevoAlu = new LinkedList<Alumno>();

	public Alumno(String nombre, String apellido, String email, String contra, String rol,
			double peso, int altura, String nivel) {
		super(nombre, apellido, email, contra, rol);
		this.peso = peso;
		this.altura = altura;
		this.nivel = nivel;
	}

	public Alumno() {
		super("nombre","apellido","email","contra","rol");
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
	

	public LinkedList<Alumno> getNuevoAlu() {
		return nuevoAlu;
	}

	public void setNuevoAlu(LinkedList<Alumno> nuevoAlu) {
		this.nuevoAlu = nuevoAlu;
	}

	@Override
	public String toString() {
		return "Alumno [peso=" + peso + ", altura=" + altura + ", nivel=" + nivel + ", objPersonal=" + objPersonal
				+ ", Nombre=" + getNombre() + ", Apellido=" + getApellido() + ", Email=" + getEmail()
				+ ", Contra=" + getContra() + "]";
	}

	

	

	
	
}
