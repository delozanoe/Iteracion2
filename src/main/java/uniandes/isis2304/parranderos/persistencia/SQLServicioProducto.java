package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ServicioProducto;


class SQLServicioProducto 
{
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLServicioProducto(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	public long adicionarServicioProducto (PersistenceManager pm, long idServicio, long idProducto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlServicioProducto() + "(idServicio, idProdcuto) values (?, ?)");
        q.setParameters(idServicio, idProducto);
        return (long) q.executeUnique();
	}
	
	public List<ServicioProducto> darServicioProducto (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlServicioProducto ());
		q.setResultClass(ServicioProducto.class);
		return (List<ServicioProducto>) q.execute();
	}
}
