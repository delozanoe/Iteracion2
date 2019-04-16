package uniandes.isis2304.parranderos.persistencia;

import java.sql.Date;
import java.util.ArrayList;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Convencion;

class SQLConvencion 
{
	private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha; 
	
	public SQLConvencion(PersistenciaCadenaHotelera pha)
	{
		this.pha= pha; 
	}
	
	public long adicionarConvencion(PersistenceManager pm, Integer id, Integer idPlanConsumo, char estado, char pazYSalvo, Double cuenta, Date fechaInicio, Date fechaFin, Integer numeroParticipantes, String tematica ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlConvencion() + "(id, idPlanConsumo, estado, pazYSalvo, cuenta, fechaInicio, fechaFin, numeroParticipantes, tematica) values (?, ?, ?, ? , ?, ?, ?, ?, ?)");
        q.setParameters(id, idPlanConsumo, estado, pazYSalvo, cuenta, fechaInicio, fechaFin, numeroParticipantes, tematica);
        return (long) q.executeUnique();
	}
	
	public Convencion darConvencionPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlConvencion() + " WHERE id = ?");
		q.setResultClass(Convencion.class);
		q.setParameters(id);
		return (Convencion) q.executeUnique();
	}
	
	public ArrayList<Convencion> darConvenciones(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlConvencion());
		q.setResultClass(Convencion.class);
		return (ArrayList<Convencion>) q.executeList();
	}
}
