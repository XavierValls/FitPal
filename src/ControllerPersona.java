import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ControllerPersona {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static long agregarUsuario(PersonalTrainer personal) {
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("INSERT INTO `usuario`(`nombre`, `apellido`, `email`,`contra`,`idRol`) VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, personal.getNombre());
			statement.setString(2, personal.getApellido());
			statement.setString(3, personal.getEmail());
			statement.setString(4, personal.getContra());
			statement.setString(5, personal.getRol());
			
			int filas = statement.executeUpdate();
			if (filas>0) {
				try(ResultSet id = statement.getGeneratedKeys()) {
					if (id.next()) {
						JOptionPane.showMessageDialog(null, "Se agrego, id " + id.getLong(1));
						return id.getLong(1);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				JOptionPane.showMessageDialog(null, "Se agrego");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}		
	}
	
	public static LinkedList<Persona> MostrarUsuarios() {
		LinkedList<Persona> personas = new LinkedList<Persona>();
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM `persona` WHERE 1");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				personas.add(new Persona(resultSet.getString("nombre"),
						resultSet.getString("rol"),resultSet.getString("password"),resultSet.getInt("id")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se agrego");
			
			
		}
		return personas;
		
	}

	
	public static Persona BuscarUsuarios(int id) {
		Persona nuevo = null;
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM `usuario` WHERE id= ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();	
			if (resultSet.next()) {
				nuevo = new PersonalTrainer(resultSet.getString("nombre"),
						resultSet.getString("rol"),resultSet.getString("password"),resultSet.getInt("id"));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se agrego");
			
			
		}
		return nuevo;
	
		
	}
}
