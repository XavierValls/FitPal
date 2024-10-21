package DLL;

import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BLL.Alumno;
import BLL.Persona;


public class ControllerAlumno {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static long agregarAlumno(Alumno alumno) {
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("INSERT INTO `alumno`(`idUsuario`) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setInt(1, alumno.getId());
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
	
	public static LinkedList<Alumno> MostrarUsuarios() {
		LinkedList<Alumno> alumnos = new LinkedList<Alumno>();
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM `alumno` ");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				alumnos.add(new Alumno(resultSet.getInt("idAlumno")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se agrego");
			
			
		}
		return alumnos;
		
	}
}
