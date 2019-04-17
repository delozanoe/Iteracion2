package uniandes.isis2304.parranderos.negocio;

public class ServicioConsumo
{
	private long idServicio;
	
	private long idProductor;

	public ServicioConsumo(long idServicio, long idProductor) {
		super();
		this.idServicio = idServicio;
		this.idProductor = idProductor;
	}

	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	public long getIdProductor() {
		return idProductor;
	}

	public void setIdProductor(long idProductor) {
		this.idProductor = idProductor;
	}
	
	
}
