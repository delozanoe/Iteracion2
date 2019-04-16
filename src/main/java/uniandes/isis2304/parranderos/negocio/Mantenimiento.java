package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;

public class Mantenimiento 
{
	private String estados;
	
	private Date fechaInicio; 
	
	private Date fechaFin; 
	
	private String descripcion;

	public Mantenimiento(String estados, Date fechaInicio, Date fechaFin, String descripcion) {
		super();
		this.estados = estados;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
	}

	public String getEstados() {
		return estados;
	}

	public void setEstados(String estados) {
		this.estados = estados;
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
