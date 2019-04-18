package uniandes.isis2304.parranderos.negocio;


import java.sql.Timestamp;
import java.util.List;

public class Mantenimiento 
{
	
	private long id;
	
	
	private String estado;
	
	private Timestamp fechaInicio; 
	
	private Timestamp fechaFin; 
	
	private String descripcion;
	
	private Habitacion habitacion;
	
	private Servicio servicio;
	
	private long idHabitacion, idServicio; 

	public Mantenimiento(String estado, long id, Timestamp fechaInicio, Timestamp fechaFin, String descripcion, long idHabitacion, long idServicio ) 
	{
		super();
		this.id = id;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.idHabitacion = idHabitacion;
		this.idServicio = idServicio;
		
	}
	
	public Mantenimiento() 
	{
		super();
		this.id = 0;
		this.estado = "";
		this.fechaInicio = new Timestamp(0);
		this.fechaFin = new Timestamp(0);
		this.descripcion = "";
		this.idHabitacion = 0;
		this.idServicio = 0;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Timestamp getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public long getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}
	
	

	
	
	
}
