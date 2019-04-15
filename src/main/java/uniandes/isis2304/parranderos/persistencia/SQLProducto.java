package uniandes.isis2304.parranderos.persistencia;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Producto;





class SQLProducto
{
	private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLProducto(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	public long adicionarProducto(PersistenceManager pm, Integer id, String nombre, Double costo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlProducto()+ "(id, nombre, costo) values (?, ?, ?)");
        q.setParameters(id, nombre, costo);
        return (long) q.executeUnique();
	}
	
	public Producto darProductoPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlProducto() + " WHERE id = ?");
		q.setResultClass(Producto.class);
		q.setParameters(id);
		return (Producto) q.executeUnique();
	}
	
	public ArrayList<Producto> darProductos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlProducto());
		q.setResultClass(Producto.class);
		return (ArrayList<Producto>) q.executeList();
	}
}
