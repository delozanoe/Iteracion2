package uniandes.isis2304.parranderos.negocio;

public class ServicioProducto 
{
	private long idServicio;
	
	private long idProducto;

	public ServicioProducto(long idServicio, long idProducto) {
		super();
		this.idServicio = idServicio;
		this.idProducto = idProducto;
	}

	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	} 
	
	
}
