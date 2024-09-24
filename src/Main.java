import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		int elegido;
		Alumno temporal = new Alumno();
		PersonalTrainer nuevo = new PersonalTrainer("Xavier","Valls","personal@gmail.com","12345","PersonalTrainer");
		PersonalTrainer.getNuevoPer().add(nuevo);
		Alumno nuevo2 = new Alumno("Xavier","Valls","alumno@gmail.com","12345","Alumno", 1, 1, "asd");
		Alumno.getNuevoAlu().add(nuevo2);
		String[] opcion = {
				"Login", "Registrarse","Salir"
		};
		
		do {
			elegido = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opcion, opcion[0]); 
		
		switch (elegido) {
		case 0:
			temporal.Loguearse();
			break;

		case 1:
			temporal.Registrarse();
			break;
		}
		} while (elegido!=2);
	}

}
