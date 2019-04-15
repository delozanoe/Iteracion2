package uniandes.isis2304.parranderos.negocio;

public class ProductoConsumoPorHabitacion 
{
       private Integer idProducto;
       
       private Integer idConsumoPorHabitacion;

	public ProductoConsumoPorHabitacion(Integer idProducto, Integer idConsumoPorHabitacion) {
		super();
		this.idProducto = idProducto;
		this.idConsumoPorHabitacion = idConsumoPorHabitacion;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getIdConsumoPorHabitacion() {
		return idConsumoPorHabitacion;
	}

	public void setIdConsumoPorHabitacion(Integer idConsumoPorHabitacion) {
		this.idConsumoPorHabitacion = idConsumoPorHabitacion;
	}
       
       
}
