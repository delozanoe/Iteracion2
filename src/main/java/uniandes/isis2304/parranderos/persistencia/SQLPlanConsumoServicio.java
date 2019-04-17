package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.parranderos.negocio.PlanConsumoServicio;

public class SQLPlanConsumoServicio 
{
	private final static String SQL = PersistenciaCadenaHotelera.SQL;

	private PersistenciaCadenaHotelera pha;

	public SQLPlanConsumoServicio(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}

	public long adicionarPlanConsumoServicio(PersistenceManager pm, long idPlanConsumo, long idServicio) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlConvencionHotel() + "(idPlanConsumo, idServicio) values (?, ?)");
		q.setParameters(idPlanConsumo,idServicio);
		return (long) q.executeUnique();
	}

	public List<PlanConsumoServicio> darPlanConsumoServicio (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlPlanConsumoServicio ());
		q.setResultClass(PlanConsumoServicio.class);
		return (List<PlanConsumoServicio>) q.execute();
	}
}
