package uniandes.isis2304.parranderos.persistencia;

import java.util.ArrayList;

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

	public long adicionarPlanConsumoServicio(PersistenceManager pm, Integer idPlanConsumo, Integer idServicio) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlConvencionHotel() + "(idPlanConsumo, idServicio) values (?, ?)");
		q.setParameters(idPlanConsumo,idServicio);
		return (long) q.executeUnique();
	}

	public ArrayList<PlanConsumoServicio> darPlanConsumoServicio (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlPlanConsumoServicio ());
		q.setResultClass(PlanConsumoServicio.class);
		return (ArrayList<PlanConsumoServicio>) q.execute();
	}
}