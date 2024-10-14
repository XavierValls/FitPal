import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Conexion.getInstance();
		
		int elegido;
		String[] opcion = {
				"Login", "Registrarse","Salir"
		};
		
		do {
			elegido = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opcion, opcion[0]); 
		
		switch (elegido) {
		case 0:
			Persona.Loguearse();
			break;

		case 1:
			Persona.Registrarse();
			break;
		}
		} while (elegido!=2);
	}

}
