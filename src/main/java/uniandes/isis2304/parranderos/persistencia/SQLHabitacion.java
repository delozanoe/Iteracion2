package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Habitacion;



class SQLHabitacion {
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLHabitacion(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	public long adicionarHabitacion(PersistenceManager pm, Integer id, Integer capacidad, Double costoPorNoche, Double cuenta, String numero, Integer idHotel, Integer idConsumoHabitacion, Integer idTipoHabitacion, Integer idPlanConsumo, char estado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlHabitacion()+ "(id, capacidad, costoPorNoche, cuenta, numero, idHotel, idConsumoPorHabitacion, idTipoHabitacion, idPlanConsumo, estado) values (?, ?, ?,?,?,?,?,?,?)");
        q.setParameters(id, capacidad, costoPorNoche, cuenta, numero, idHotel, idConsumoHabitacion, idTipoHabitacion, idPlanConsumo, estado);
        return (long) q.executeUnique();
	}
	
	public Habitacion darHabitacionPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlHabitacion () + " WHERE id = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(id);
		return (Habitacion) q.executeUnique();
	}
	
	public List<Habitacion> darHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlHabitacion());
		q.setResultClass(Habitacion.class);
		return (List<Habitacion>) q.executeList();
	}
	
	public long cambiarEstado (PersistenceManager pm,char estado, Integer idHabitacion)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pha.getSqlHabitacion () + " SET estado = ? WHERE id = ?");
        q.setParameters(estado, idHabitacion);
        return (long) q.executeUnique();
	}
	
	public long moverConsumos (PersistenceManager pm, Integer idHabitacion, Integer idHabitacionNueva, Integer idConsumoHabitacion)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pha.getSqlHabitacion () + " SET idConsumoHabitacion = ? WHERE id = ?");
        q.setParameters(idConsumoHabitacion,idHabitacionNueva);
        Query q2 = pm.newQuery(SQL, "UPDATE " + pha.getSqlHabitacion () + " SET idConsumoHabitacion = ? WHERE id = ?");
        q2.setParameters(null, idHabitacion);
        return (long) q.executeUnique();
	}
	
	public Integer darNumeroHabitacionesTipo(PersistenceManager pm, Integer idTipoHabitacion)
	{
		Query q = pm.newQuery(SQL, "SELECT COUNT(*) FROM " + pha.getSqlHabitacion () + "WHERE idTipoHabitacion = ?");
        q.setParameters(idTipoHabitacion);
        return (int) q.executeUnique();
	}
}
