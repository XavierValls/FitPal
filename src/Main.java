import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		Alumno temporal = new Alumno();
		String[] opcion = {
			"Login", "Registrarse","Salir"
		};
		
		int elegido = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opcion, opcion[0]); 
		
		switch (elegido) {
		case 0:
			
			break;

		case 1:
			temporal.Registrarse();
			break;
		}
	}

}
