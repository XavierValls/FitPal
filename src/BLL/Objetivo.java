package BLL;
import java.time.LocalDate;

public class Objetivo {
	private int idObjetivo;
	private String descripcion;
	private LocalDate fechaCreacion;
	private LocalDate fechaObj;
	
	public Objetivo(String descripcion, LocalDate fechaCreacion, LocalDate fechaObj) {
		super();
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaObj = fechaObj;
	}
	public Objetivo(int idObjetivo,String descripcion, LocalDate fechaCreacion, LocalDate fechaObj) {
		super();
		this.idObjetivo = idObjetivo;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaObj = fechaObj;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public LocalDate getFechaObj() {
		return fechaObj;
	}
	public void setFechaObj(LocalDate fechaObj) {
		this.fechaObj = fechaObj;
	}
	
	
	public int getIdObjetivo() {
		return idObjetivo;
	}
	public void setIdObjetivo(int idObjetivo) {
		this.idObjetivo = idObjetivo;
	}
	@Override
	public String toString() {
		return "Objetivo [descripcion=" + descripcion + ", fechaCreacion=" + fechaCreacion + ", fechaObj=" + fechaObj
				+ "]";
	}
	
	
	

}
