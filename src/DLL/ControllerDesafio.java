package DLL;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BLL.Desafio;

public class ControllerDesafio {
	private static Connection con = Conexion.getInstance().getConnection();

	public static long agregarDesafio(Desafio desafio) {
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("INSERT INTO `desafio`(`titulo`,`descripcion`,`fechaExp`,`idPersonalTrainer`) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1,desafio.getTitulo());
			statement.setString(2,desafio.getDescripcion());
			statement.setDate(3, (Date) desafio.getFechaExp());
			statement.setInt(4, desafio.getPersonalTrainer().getIdPersonalTrainer());
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
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 0;		
		}
		return 0;
	}
	
	public static LinkedList<Desafio> desafiosDispo(){
		LinkedList<Desafio> desafios = new LinkedList<Desafio>();
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM `desafio` ");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				desafios.add(new Desafio(resultSet.getInt("idDesafio"), resultSet.getString("titulo"), resultSet.getString("descripcion"), resultSet.getDate("fechaExp"),resultSet.getInt("idPersonalTrainer")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se agrego");
			
			
		}
		return desafios;
		
	}
	
	public static LinkedList<Desafio> misDesafios(int idPersonalTrainer){
		LinkedList<Desafio> desafios = new LinkedList<Desafio>();
		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT * FROM `desafio` WHERE idPersonalTrainer = ?");
			statement.setInt(1, idPersonalTrainer);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				desafios.add(new Desafio(resultSet.getInt("idDesafio"), resultSet.getString("titulo"), resultSet.getString("descripcion"), resultSet.getDate("fechaExp"),resultSet.getInt("idPersonalTrainer")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se agrego");
			
			
		}
		return desafios;
		
	}
	
	public static long eliminarDesafio(int idDesafio) {
	    try {
	        PreparedStatement checkStatement = (PreparedStatement) con.prepareStatement(
	            "SELECT COUNT(*) AS total FROM desafio_alumno WHERE idDesafio = ?"
	        );
	        checkStatement.setInt(1, idDesafio);
	        ResultSet resultSet = checkStatement.executeQuery();

	        int filas = 0;
	        if (resultSet.next()) {
	            filas = resultSet.getInt("total");
	        }
	        if (filas > 0) {
	            JOptionPane.showMessageDialog(null, "No se puede eliminar el desafio, hay alumnos anotados.");
	            return 0;
	        }
	        PreparedStatement deleteStatement = (PreparedStatement) con.prepareStatement(
	            "DELETE FROM desafio WHERE idDesafio = ?"
	        );
	        deleteStatement.setInt(1, idDesafio);
	        int filasEliminadas = deleteStatement.executeUpdate();
	        if (filasEliminadas > 0) {
	            JOptionPane.showMessageDialog(null, "El desafío se elimino correctamente.");
	            return idDesafio;
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error al intentar eliminar el desafio: " + e.getMessage());
	    }

	    return 0;
	}
	
	
	public static long inscribirseEnDesafio(int idAlumno, int idDesafio) {
	    try {
	        PreparedStatement checkStatement = (PreparedStatement) con.prepareStatement(
	            "SELECT COUNT(*) AS total FROM desafio_alumno WHERE idAlumno = ? AND idDesafio = ?"
	        );
	        checkStatement.setInt(1, idAlumno);
	        checkStatement.setInt(2, idDesafio);
	        ResultSet resultSet = checkStatement.executeQuery();
	        if (resultSet.next() && resultSet.getInt("total") > 0) {
	            JOptionPane.showMessageDialog(null, "Ya estas inscripto en este desafio.");
	            return 0;
	        }
	        PreparedStatement insertStatement = (PreparedStatement) con.prepareStatement(
	            "INSERT INTO desafio_alumno (idAlumno, idDesafio) VALUES (?, ?)", 
	            PreparedStatement.RETURN_GENERATED_KEYS
	        );
	        insertStatement.setInt(1, idAlumno);
	        insertStatement.setInt(2, idDesafio);
	        int filas = insertStatement.executeUpdate();
	        if (filas > 0) {
	            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    long idGenerado = generatedKeys.getLong(1);
	                    JOptionPane.showMessageDialog(null, "La inscripcion fue un exito. ID de inscripcion: " + idGenerado);
	                    return idGenerado;
	                }
	            }
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error al intentar inscribir al alumno: " + e.getMessage());
	    }
	    return 0;
	}

	
	public static LinkedList<Desafio> MisDesafios(int idAlumno) {
	    LinkedList<Desafio> desafios = new LinkedList<Desafio>();
	    try {
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement(
	            "SELECT d.idDesafio, d.titulo, d.descripcion, d.fechaExp, d.idPersonalTrainer " +
	            "FROM desafio d " +
	            "JOIN desafio_alumno da ON d.idDesafio = da.idDesafio " +
	            "WHERE da.idAlumno = ?"
	        );
	        statement.setInt(1, idAlumno);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            desafios.add(new Desafio(
	                resultSet.getInt("idDesafio"),
	                resultSet.getString("titulo"),
	                resultSet.getString("descripcion"),
	                resultSet.getDate("fechaExp"),
	                resultSet.getInt("idPersonalTrainer")));
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener los desafíos: " + e.getMessage());
	    }
	    
	    return desafios;
	}
}
