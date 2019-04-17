package uniandes.isis2304.parranderos.negocio;

public class ProductoConsumoPorHabitacion 
{
       private long idProducto;
       
       private long idConsumoPorHabitacion;

	public ProductoConsumoPorHabitacion(long idProducto, long idConsumoPorHabitacion) {
		super();
		this.idProducto = idProducto;
		this.idConsumoPorHabitacion = idConsumoPorHabitacion;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public long getIdConsumoPorHabitacion() {
		return idConsumoPorHabitacion;
	}

	public void setIdConsumoPorHabitacion(long idConsumoPorHabitacion) {
		this.idConsumoPorHabitacion = idConsumoPorHabitacion;
	}
       
       
}
