package DLL;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BLL.Desafio;
import BLL.Persona;
import BLL.PersonalTrainer;

public class ControllerPersonalTrainer {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static long agregarPersonal(PersonalTrainer personal) {
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("INSERT INTO `personaltrainer`(`idUsuario`) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setInt(1, personal.getId());
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
	
	public static LinkedList<PersonalTrainer> MostrarUsuarios() {
		LinkedList<PersonalTrainer> personals = new LinkedList<PersonalTrainer>();
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM `personaltrainer` ");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				personals.add(new PersonalTrainer(resultSet.getInt("idPersonalTrainer"), resultSet.getInt("idUsuario")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se agrego");
			
			
		}
		return personals;
		
	}
	
	public static LinkedList<PersonalTrainer> MostrarAlumnos(int id) {
	    LinkedList<PersonalTrainer> alumnos = new LinkedList<>();
	    try {
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement(
	        		"SELECT u.nombre, u.apellido, a.idAlumno " +
	        		"FROM personaltrainer_alumno pa " +
	        		"JOIN alumno a ON pa.idAlumno = a.idAlumno " +
	        		"JOIN usuario u ON a.idUsuario = u.idUsuario " +
	        		"WHERE pa.idPersonalTrainer = ?"
	        );
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (!resultSet.isBeforeFirst()) {
	            JOptionPane.showMessageDialog(null, "No se encontraron alumnos.");
	        }
	        
	        while (resultSet.next()) {
	            PersonalTrainer alumno = new PersonalTrainer(
	                resultSet.getInt("idAlumno"),
	                resultSet.getString("nombre"), 
	                resultSet.getString("apellido")
	            );
	            alumnos.add(alumno);
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e);
	    }
	    return alumnos;
	}
	
	public static LinkedList<PersonalTrainer> MostrarPersonalTrainers() {
	    LinkedList<PersonalTrainer> personalTrainers = new LinkedList<>();
	    try {
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement(
	            "SELECT pt.idPersonalTrainer, u.nombre, u.apellido " +
	            "FROM personaltrainer pt " +
	            "JOIN usuario u ON pt.idUsuario = u.idUsuario"
	        );

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            PersonalTrainer personalTrainer = new PersonalTrainer(
	                resultSet.getInt("idPersonalTrainer"),
	                resultSet.getString("nombre"),
	                resultSet.getString("apellido")
	            );
	            personalTrainers.add(personalTrainer);
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e);
	    }
	    return personalTrainers;
	}

	
	public static long ObtenerIdPersonal(int id) {

		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT `idPersonalTrainer` FROM `personaltrainer` WHERE `idUsuario` = (?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				return resultSet.getLong("idPersonalTrainer");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			
			
		}
		return 0;
		
	}
	
	

	
	
	
	
	
	
}
