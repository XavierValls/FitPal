package DLL;

import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BLL.Alumno;
import BLL.Persona;
import BLL.PersonalTrainer;
import BLL.Rutina;


public class ControllerRutina {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static long agregarRutina(Rutina rutina) {
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("INSERT INTO `rutina`(`descripcion`,`titulo`,`tipoRutina`,`idAlumno`,`idPersonalTrainer`) VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, rutina.getDescripcion());
			statement.setString(2, rutina.getTitulo());
			statement.setInt(3, rutina.getTipoRutina());
			statement.setInt(4, rutina.getIdAlumno());
			statement.setInt(5, rutina.getIdPersonalTrainer());
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
			JOptionPane.showMessageDialog(null, e);
			return 0;		
		}
		return 0;
	}
	
	public static long agregarRutinaPredeterminada(Rutina rutina) {
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("INSERT INTO `rutina`(`descripcion`,`titulo`,`tipoRutina`,`idPersonalTrainer`) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, rutina.getDescripcion());
			statement.setString(2, rutina.getTitulo());
			statement.setInt(3, rutina.getTipoRutina());
			statement.setInt(4, rutina.getIdPersonalTrainer());
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
			JOptionPane.showMessageDialog(null, e);
			return 0;		
		}
		return 0;
	}
	
	public static LinkedList<Rutina> MostrarRutinas(int id) {
	    LinkedList<Rutina> rutinas = new LinkedList<>();
	    try {
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement(
	            "SELECT r.descripcion, r.titulo, r.idRutina, u.nombre, u.apellido " +
	            "FROM rutina r " +
	            "JOIN personaltrainer pt ON r.idPersonalTrainer = pt.idPersonalTrainer " +
	            "JOIN usuario u ON pt.idUsuario = u.idUsuario " +
	            "WHERE r.idAlumno = ?"
	        );
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String descripcion = resultSet.getString("descripcion");
	            String titulo = resultSet.getString("titulo");
	            int idRutina = resultSet.getInt("idRutina");
	            String nombrePT = resultSet.getString("nombre");
	            String apellidoPT = resultSet.getString("apellido");

	            PersonalTrainer personalTrainer = new PersonalTrainer(nombrePT, apellidoPT);
	            Rutina rutina = new Rutina(descripcion,titulo,personalTrainer,idRutina);

	            rutinas.add(rutina);
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e);
	    }
	    return rutinas;
	}
	
	public static LinkedList<Rutina> MostrarRutinasPredeterminadas() {
	    LinkedList<Rutina> rutinas = new LinkedList<>();
	    try {
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement(
	        		"SELECT r.descripcion, r.titulo,r.idRutina, u.nombre, u.apellido " +
	        	            "FROM rutina r " +
	        	            "JOIN personaltrainer pt ON r.idPersonalTrainer = pt.idPersonalTrainer " +
	        	            "JOIN usuario u ON pt.idUsuario = u.idUsuario " +
	        	            "WHERE r.tipoRutina = 1");
	        
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String descripcion = resultSet.getString("descripcion");
	            String titulo = resultSet.getString("titulo");
	            int idRutina = resultSet.getInt("idRutina");
	            String nombrePT = resultSet.getString("nombre");
	            String apellidoPT = resultSet.getString("apellido");

	            PersonalTrainer personalTrainer = new PersonalTrainer(nombrePT, apellidoPT);
	            Rutina rutina = new Rutina(descripcion,titulo, personalTrainer,idRutina);

	            rutinas.add(rutina);
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e);
	    }
	    return rutinas;
	}
	
	public static long actualizarRutina(Rutina rutina) {
	    try {
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement("UPDATE `rutina` SET `descripcion` = ?, `titulo` = ? WHERE `idRutina` = ?");
	        
	        statement.setString(1, rutina.getDescripcion());
	        statement.setString(2, rutina.getTitulo());
	        statement.setInt(3, rutina.getIdRutina()); 

	        int filas = statement.executeUpdate();

	        if (filas > 0) {
	            JOptionPane.showMessageDialog(null, "Rutina actualizada correctamente.");
	            return rutina.getIdRutina(); 
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontro la rutina para actualizar.");
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Ocurrio un error: " + e.getMessage());
	        return 0;
	    }
	    return 0;
	}





}
