import java.util.LinkedList;

public class Alumno {

	double peso;
	double altura;
	int nivel;
	private Objetivo objPersonal;
	LinkedList<PersonalTrainer> contratado = new LinkedList<PersonalTrainer>();
	public Alumno(double peso, double altura, int nivel, Objetivo objPersonal, LinkedList<PersonalTrainer> contratado) {
		super();
		this.peso = peso;
		this.altura = altura;
		this.nivel = nivel;
		this.objPersonal = objPersonal;
		this.contratado = contratado;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
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
	@Override
	public String toString() {
		return "Alumno [peso=" + peso + ", altura=" + altura + ", nivel=" + nivel + ", objPersonal=" + objPersonal
				+ ", contratado=" + contratado + "]";
	}
	
	
}
