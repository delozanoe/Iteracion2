package uniandes.isis2304.parranderos.negocio;

public class ConsumoHabitacionServicio 
{
	private Integer idConsumoHabitacion; 
	
	private Integer idServicio;

	public ConsumoHabitacionServicio(Integer idConsumoHabitacion, Integer idServicio) {
		super();
		this.idConsumoHabitacion = idConsumoHabitacion;
		this.idServicio = idServicio;
	}

	public Integer getIdConsumoHabitacion() {
		return idConsumoHabitacion;
	}

	public void setIdConsumoHabitacion(Integer idConsumoHabitacion) {
		this.idConsumoHabitacion = idConsumoHabitacion;
	}

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}
	
	
}
