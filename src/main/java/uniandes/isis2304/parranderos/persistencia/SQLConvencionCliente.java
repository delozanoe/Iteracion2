package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ConvencionCliente;

public class SQLConvencionCliente 
{
	private final static String SQL = PersistenciaCadenaHotelera.SQL;

	private PersistenciaCadenaHotelera pha;

	public SQLConvencionCliente(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}

	public long adicionarConvencionCliente(PersistenceManager pm, long idConvencion, long idCliente) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlConvencionCliente() + "(idConvencion, idCliente) values (?, ?)");
		q.setParameters(idConvencion,idCliente);
		return (long) q.executeUnique();
	}

	public List<ConvencionCliente> darConvencionCliente (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlConvencionCliente ());
		q.setResultClass(ConvencionCliente.class);
		return (List<ConvencionCliente>) q.execute();
	}
}
