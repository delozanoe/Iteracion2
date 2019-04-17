package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.List;

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
	
	public long adicionarConvencion(PersistenceManager pm, long id, long idPlanConsumo, String estado, String pazYSalvo, BigDecimal cuenta, Timestamp fechaInicio, Timestamp fechaFin, long numeroParticipantes, String tematica ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlConvencion() + "(id, idPlanConsumo, estado, pazYSalvo, cuenta, fechaInicio, fechaFin, numeroParticipantes, tematica) values (?, ?, ?, ? , ?, ?, ?, ?, ?)");
        q.setParameters(id, idPlanConsumo, estado, pazYSalvo, cuenta, fechaInicio, fechaFin, numeroParticipantes, tematica);
        return (long) q.executeUnique();
	}
	
	public Convencion darConvencionPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlConvencion() + " WHERE id = ?");
		q.setResultClass(Convencion.class);
		q.setParameters(id);
		return (Convencion) q.executeUnique();
	}
	
	public List<Convencion> darConvenciones(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlConvencion());
		q.setResultClass(Convencion.class);

		return (List<Convencion>) q.executeList();
	}
	
	
	public long eliminarConvencionPorId (PersistenceManager pm, long idConvencion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pha.getSqlConvencion() + " WHERE id = ?");
        q.setParameters(idConvencion);
        return (long) q.executeUnique();
	}
}
