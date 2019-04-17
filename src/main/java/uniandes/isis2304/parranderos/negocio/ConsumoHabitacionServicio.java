package uniandes.isis2304.parranderos.negocio;

public class ConsumoHabitacionServicio 
{
	private long idConsumoHabitacion; 
	
	private long idServicio;

	public ConsumoHabitacionServicio(long idConsumoHabitacion, long idServicio) {
		super();
		this.idConsumoHabitacion = idConsumoHabitacion;
		this.idServicio = idServicio;
	}

	public long getIdConsumoHabitacion() {
		return idConsumoHabitacion;
	}

	public void setIdConsumoHabitacion(long idConsumoHabitacion) {
		this.idConsumoHabitacion = idConsumoHabitacion;
	}

	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}
	
	
}
