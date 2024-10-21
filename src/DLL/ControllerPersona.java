package DLL;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BLL.Persona;
import BLL.PersonalTrainer;

public class ControllerPersona {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static long agregarUsuario(Persona persona) {
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("INSERT INTO `usuario`(`nombre`, `apellido`, `email`,`contra`,`idRol`) VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getEmail());
			statement.setString(4, persona.getContra());
			if (persona.getRol()==1) {
				statement.setInt(5, 1);
			} else {
				statement.setInt(5, 2);
			}
			
			int filas = statement.executeUpdate();
			if (filas>0) {
				try(ResultSet id = statement.getGeneratedKeys()) {
					if (id.next()) {
						JOptionPane.showMessageDialog(null, "Se agrego, id " + id.getLong(1));
						return id.getLong(1);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se agrego");
			return 0;		
		}
		return 0;		

	}
	
	public static LinkedList<Persona> MostrarUsuarios() {
		LinkedList<Persona> usuarios = new LinkedList<Persona>();
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM `usuario` WHERE 1");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				
				usuarios.add(new Persona(resultSet.getInt("idUsuario"),
						resultSet.getString("nombre"),resultSet.getString("apellido"),resultSet.getString("email"),resultSet.getString("contra"),resultSet.getInt("idRol")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se agrego");
			
			
		}
		return usuarios;
		
	}

	
	public static Persona BuscarUsuarios(int id) {
		Persona nuevo = null;
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM `usuario` WHERE id= ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();	
			if (resultSet.next()) {
				nuevo = new Persona(resultSet.getString("nombre"),resultSet.getString("apellido"),
						resultSet.getString("rol"),resultSet.getString("password"),resultSet.getInt("idRol"));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se agrego");
			
			
		}
		return nuevo;
	
		
	}
}
