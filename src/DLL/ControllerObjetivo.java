package DLL;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BLL.Objetivo;

public class ControllerObjetivo {

	private static Connection con = Conexion.getInstance().getConnection();
	
	public static long AgregarObjetivo(String obj, LocalDate fechaObj, int idAlumno) {
	    long idObjetivo = 0;
	    try {
	    	 PreparedStatement checkStatement = (PreparedStatement) con.prepareStatement(
	    	            "SELECT `idObjetivo` FROM `alumno` WHERE `idAlumno` = ?"
	    	        );
	    	        checkStatement.setInt(1, idAlumno);
	    	        ResultSet resultSet = checkStatement.executeQuery();
	    	        
	    	        if (resultSet.next() && resultSet.getLong("idObjetivo") != 0) {
	    	            JOptionPane.showMessageDialog(null, "El alumno ya tiene un objetivo asignado.");
	    	            return 0;
	    	        }
	    	
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement(
	            "INSERT INTO `objetivo`(`descripcion`, `fechaCreacion`, `fechaObj`) " +
	            "VALUES (?, CURRENT_TIMESTAMP, ?)", 
	            PreparedStatement.RETURN_GENERATED_KEYS
	        );
	        statement.setString(1, obj);
	        statement.setDate(2, Date.valueOf(fechaObj));

	        int filas = statement.executeUpdate();
	        if (filas > 0) {
	            try (ResultSet id = statement.getGeneratedKeys()) {
	                if (id.next()) {
	                    idObjetivo = id.getLong(1);
	                    JOptionPane.showMessageDialog(null, "El Objetivo se establecio con exito, ID: " + idObjetivo);
	                }
	            }
	        }
	        if (idObjetivo != 0) {
	            PreparedStatement updateStatement = (PreparedStatement) con.prepareStatement(
	                "UPDATE `alumno` SET `idObjetivo` = ? WHERE `idAlumno` = ?"
	            );
	            updateStatement.setLong(1, idObjetivo);
	            updateStatement.setInt(2, idAlumno);
	            updateStatement.executeUpdate();
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	    return idObjetivo;
	}
	
	public static Objetivo mostrarObjetivoDelAlumno(int idAlumno) {
	    Objetivo objetivo = null;
	    try {
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement(
	            "SELECT o.idObjetivo, o.descripcion, o.fechaCreacion, o.fechaObj " +
	            "FROM alumno a " +
	            "JOIN objetivo o ON a.idObjetivo = o.idObjetivo " +
	            "WHERE a.idAlumno = ?"
	        );
	        statement.setInt(1, idAlumno);
	        
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            int idObjetivo = resultSet.getInt("idObjetivo");
	            String descripcion = resultSet.getString("descripcion");
	            LocalDate fechaCreacion = resultSet.getDate("fechaCreacion").toLocalDate();
	            LocalDate fechaObj = resultSet.getDate("fechaObj").toLocalDate();
	            objetivo = new Objetivo(idObjetivo, descripcion, fechaCreacion, fechaObj);
	        } else {
	            JOptionPane.showMessageDialog(null, "No estableciste un objetivo aun.");
	        }
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error al mostrar el objetivo: " + e.getMessage());
	    }
	    
	    return objetivo;
	}
	
	public static long actualizarObjetivo(Objetivo objetivo) {
	    try {
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement("UPDATE `objetivo` SET `descripcion` = ?, `fechaCreacion` = ?, `fechaObj` =? WHERE `idObjetivo` = ?");
	        
	        statement.setString(1, objetivo.getDescripcion());
	        statement.setDate(2, Date.valueOf(objetivo.getFechaCreacion()));
	        statement.setDate(3, Date.valueOf(objetivo.getFechaObj())); 
	        statement.setInt(4, objetivo.getIdObjetivo()); 
	        

	        int filas = statement.executeUpdate();

	        if (filas > 0) {
	            JOptionPane.showMessageDialog(null, "Objetivo actualizado correctamente.");
	            return objetivo.getIdObjetivo(); 
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontro el objetivo para actualizar.");
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Ocurrio un error: " + e.getMessage());
	        return 0;
	    }
	    return 0;
	}
}
