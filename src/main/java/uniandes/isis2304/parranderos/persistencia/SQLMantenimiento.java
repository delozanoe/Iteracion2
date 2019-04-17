package uniandes.isis2304.parranderos.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Mantenimiento;

class SQLMantenimiento 
{
	
	private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	
	private PersistenciaCadenaHotelera pha; 
	
	public SQLMantenimiento(PersistenciaCadenaHotelera pha)
	{
		this.pha=pha; 
	}
	
	public long adicionarMantenimiento(PersistenceManager pm, Integer id, char estado, Date fechaInicio, Date fechaFin, String descripcion, Integer idHabitacion, Integer idServicio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlMantenimiento() + "(id,estado,fechaInicio, fechaFin, descripcion, idHabitacion, idServicio) values (?, ?, ?, ? , ?, ?, ?)");
        q.setParameters(id,estado,fechaInicio, fechaFin, descripcion, idHabitacion, idServicio);
        return (long) q.executeUnique();
	}
	
	public Mantenimiento darMantenimientoPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlMantenimiento() + " WHERE id = ?");
		q.setResultClass(Mantenimiento.class);
		q.setParameters(id);
		return (Mantenimiento) q.executeUnique();
	}
	
	public List<Mantenimiento> darMantenimientos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlMantenimiento());
		q.setResultClass(Mantenimiento.class);
		return (List<Mantenimiento>) q.executeList();
	}
}
