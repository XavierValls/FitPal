package BLL;

import java.sql.Date;

public class Contrato {
    private int idPersonalTrainer;
    private String nombre;
    private String apellido;
    private Date fechaInicio;
    private Date fechaFin;

    public Contrato(int idPersonalTrainer, String nombre, String apellido, Date fechaInicio, Date fechaFin) {
        this.idPersonalTrainer = idPersonalTrainer;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    

    public int getIdPersonalTrainer() {
		return idPersonalTrainer;
	}



	public void setIdPersonalTrainer(int idPersonalTrainer) {
		this.idPersonalTrainer = idPersonalTrainer;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public Date getFechaInicio() {
		return fechaInicio;
	}



	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	public Date getFechaFin() {
		return fechaFin;
	}



	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}



	@Override
    public String toString() {
        return "\nPersonal Trainer: " + nombre + " " + apellido + 
               ", Fecha Inicio: " + fechaInicio + 
               ", Fecha Fin: " + fechaFin;
    }
}

