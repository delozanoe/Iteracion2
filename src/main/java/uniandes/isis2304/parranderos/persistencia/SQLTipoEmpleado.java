package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.TipoEmpleado;





public class SQLTipoEmpleado 
{
	private final static String SQL = PersistenciaCadenaHotelera.SQL;

	private PersistenciaCadenaHotelera pha;

	public SQLTipoEmpleado(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}

	public long adicionarTipoEmpleado(PersistenceManager pm, long id, String nombre) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlTipoEmpleado()+ "(id, nombre) values (?, ?)");
		q.setParameters(id, nombre);
		return (long) q.executeUnique();
	}


	public TipoEmpleado darTipoEmpleadoPorId (PersistenceManager pm, long idUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlTipoEmpleado () + " WHERE id = ?");
		q.setResultClass(TipoEmpleado.class);
		q.setParameters(idUsuario);
		return (TipoEmpleado) q.executeUnique();
	}

	public List<TipoEmpleado> darTipoEmpleado (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlTipoEmpleado());
		q.setResultClass(TipoEmpleado.class);
		return (List<TipoEmpleado>) q.executeList();
	}
}
