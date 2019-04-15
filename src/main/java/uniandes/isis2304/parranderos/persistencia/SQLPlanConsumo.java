package uniandes.isis2304.parranderos.persistencia;

import java.util.ArrayList;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.PlanConsumo;




class SQLPlanConsumo {
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLPlanConsumo(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	public long adicionarPlanConsumo(PersistenceManager pm, Integer id, Integer idHotel, String descripcion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlPlanConsumo()+ "(id, idHotel, descripcion) values (?, ?, ?)");
        q.setParameters(id, idHotel, descripcion);
        return (long) q.executeUnique();
	}
	
	public PlanConsumo darPlanConsumoPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlPlanConsumo() + " WHERE id = ?");
		q.setResultClass(PlanConsumo.class);
		q.setParameters(id);
		return (PlanConsumo) q.executeUnique();
	}
	
	public ArrayList<PlanConsumo> darPlanesConsumo (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlPlanConsumo());
		q.setResultClass(PlanConsumo.class);
		return (ArrayList<PlanConsumo>) q.executeList();
	}
}
