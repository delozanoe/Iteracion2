package uniandes.isis2304.parranderos.persistencia;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.TipoServicio;

public class SQLTipoServicio 
{
	private final static String SQL = PersistenciaCadenaHotelera.SQL;

	private PersistenciaCadenaHotelera pha;

	public SQLTipoServicio(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}

	public long adicionarTipoServicio(PersistenceManager pm, Integer id, String nombre) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlTipoServicio()+ "(id, nombre) values (?, ?)");
		q.setParameters(id, nombre);
		return (long) q.executeUnique();
	}


	public TipoServicio darTipoServicioPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlTipoServicio () + " WHERE id = ?");
		q.setResultClass(TipoServicio.class);
		q.setParameters(id);
		return (TipoServicio) q.executeUnique();
	}

	public ArrayList<TipoServicio> darTipoServicio (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlTipoServicio());
		q.setResultClass(TipoServicio.class);
		return (ArrayList<TipoServicio>) q.executeList();
	}
}
