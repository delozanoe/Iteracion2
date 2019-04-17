package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ServicioConsumo;


class SQLServicioConsumo 
{
      private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLServicioConsumo(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	public long adicionarServicioConsumo (PersistenceManager pm, long idServicio, long idProducto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlServicioConsumo() + "(idServicio,idProducto) values (?, ?)");
        q.setParameters(idServicio, idProducto);
        return (long) q.executeUnique();
	}
	
	public List<ServicioConsumo> darServicioConsumo(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlServicioConsumo ());
		q.setResultClass(ServicioConsumo.class);
		return (List<ServicioConsumo>) q.execute();
	}
}
