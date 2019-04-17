package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ProductoConsumoPorHabitacion;


class SQLProductoConsumoPorHabitacion {
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLProductoConsumoPorHabitacion(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	
	public long adicionarProductoConsumoPorHabitacion(PersistenceManager pm, Integer idProducto, Integer idConsumoHabitacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlProductoConsumoPorHabitacion() + "(idProducto, idConsumoHabitacion) values (?, ?)");
        q.setParameters(idProducto, idConsumoHabitacion);
        return (long) q.executeUnique();
	}
	
	public List<ProductoConsumoPorHabitacion> darProductoConsumoHabitacion (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlProductoConsumoPorHabitacion ());
		q.setResultClass(ProductoConsumoPorHabitacion.class);
		return (List<ProductoConsumoPorHabitacion>) q.execute();
	}
}
