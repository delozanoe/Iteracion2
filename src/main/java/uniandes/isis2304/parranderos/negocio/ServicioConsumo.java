package uniandes.isis2304.parranderos.negocio;

public class ServicioConsumo
{
	private Integer idServicio;
	
	private Integer idProductor;

	public ServicioConsumo(Integer idServicio, Integer idProductor) {
		super();
		this.idServicio = idServicio;
		this.idProductor = idProductor;
	}

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	public Integer getIdProductor() {
		return idProductor;
	}

	public void setIdProductor(Integer idProductor) {
		this.idProductor = idProductor;
	}
	
	
}
