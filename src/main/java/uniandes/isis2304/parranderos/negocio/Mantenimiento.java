package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;
import java.util.List;

public class Mantenimiento 
{
	
	private long id;
	
	
	private char estado;
	
	private Date fechaInicio; 
	
	private Date fechaFin; 
	
	private String descripcion;
	
	private Habitacion habitacion;
	
	private Servicio servicio;

	public Mantenimiento(long id, char estado, Date fechaInicio, Date fechaFin, String descripcion, Habitacion habitacion, Servicio servicio) {
		super();
		this.id = id;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.habitacion = habitacion;
		this.servicio = servicio;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}


	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public char getEstados() {
		return estado;
	}

	public void setEstados(char estados) {
		this.estado = estados;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	} 
	
	
	
}
