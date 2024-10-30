package DLL;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BLL.Contrato;

public class ControllerContrato {
	private static Connection con = Conexion.getInstance().getConnection();

	public static long agregarContrato(int idPersonal, int idAlumno) {
	    try {
	        PreparedStatement checkStatement = (PreparedStatement) con.prepareStatement(
	            "SELECT COUNT(*) FROM `personaltrainer_alumno` " +
	            "WHERE `idPersonalTrainer` = ? AND `idAlumno` = ? AND `fechaFin` > CURRENT_DATE"
	        );
	        checkStatement.setInt(1, idPersonal);
	        checkStatement.setInt(2, idAlumno);
	        
	        ResultSet resultSet = checkStatement.executeQuery();
	        if (resultSet.next() && resultSet.getInt(1) > 0) {
	            JOptionPane.showMessageDialog(null, "Ya existe un contrato vigente entre el personal trainer y el alumno.");
	            return 0;
	        }
	        
	        LocalDate fechaFin = LocalDate.now().plusMonths(1);
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement(
	            "INSERT INTO `personaltrainer_alumno`(`idPersonalTrainer`, `idAlumno`, `fechaInicio`, `fechaFin`) " +
	            "VALUES (?, ?, CURRENT_TIMESTAMP, ?)", 
	            PreparedStatement.RETURN_GENERATED_KEYS
	        );

	        statement.setInt(1, idPersonal);
	        statement.setInt(2, idAlumno);
	        statement.setDate(3, Date.valueOf(fechaFin));

	        int filas = statement.executeUpdate();
	        if (filas > 0) {
	            try (ResultSet id = statement.getGeneratedKeys()) {
	                if (id.next()) {
	                    JOptionPane.showMessageDialog(null, "El contrato se realizo con exito, ID: " + id.getLong(1));
	                    return id.getLong(1);
	                }
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, "Error al obtener el ID del contrato.");
	            }
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
	    }
	    return 0;
	}
	
	public static LinkedList<Contrato> mostrarContratosPorAlumno(int idAlumno) {
	    LinkedList<Contrato> contratos = new LinkedList<>();
	    try {
	        PreparedStatement statement = (PreparedStatement) con.prepareStatement(
	            "SELECT pt.idPersonalTrainer, u.nombre, u.apellido, pta.fechaInicio, pta.fechaFin " +
	            "FROM personaltrainer_alumno pta " +
	            "JOIN personaltrainer pt ON pta.idPersonalTrainer = pt.idPersonalTrainer " +
	            "JOIN usuario u ON pt.idUsuario = u.idUsuario " +
	            "WHERE pta.idAlumno = ?"
	        );
	        statement.setInt(1, idAlumno);
	        
	        ResultSet resultSet = statement.executeQuery();
	        
	        while (resultSet.next()) {
	            int idPersonalTrainer = resultSet.getInt("idPersonalTrainer");
	            String nombrePT = resultSet.getString("nombre");
	            String apellidoPT = resultSet.getString("apellido");
	            Date fechaInicio = resultSet.getDate("fechaInicio");
	            Date fechaFin = resultSet.getDate("fechaFin");
	            
	            Contrato contrato = new Contrato(idPersonalTrainer, nombrePT, apellidoPT, fechaInicio, fechaFin);
	            contratos.add(contrato);
	        }
	        
	        if (contratos.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "No se encontraron contratos para este alumno.");
	        }
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener los contratos: " + e.getMessage());
	    }
	    return contratos;
	}
}
