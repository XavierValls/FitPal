package DLL;

import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import BLL.Alumno;
import BLL.Contrato;
import BLL.Desafio;
import BLL.Objetivo;
import BLL.Persona;
import BLL.Rutina;

import java.sql.Date;
import java.time.LocalDate;


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



	
	public static long ObtenerIdAlumno(int id) {

		try {
			
			PreparedStatement statement = (PreparedStatement) 
					con.prepareStatement("SELECT `idAlumno` FROM `alumno` WHERE `idUsuario` = (?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				return resultSet.getLong("idAlumno");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			
			
		}
		return 0;
		
	}
	
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
	
