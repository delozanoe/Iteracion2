package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ConsumoHabitacion;



public class SQLConsumoPorHabitacion {

	private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLConsumoPorHabitacion(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	public long adicionarConsumoPorHabitacion(PersistenceManager pm, long id, Double valorTotal, long idHabitacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlConsumoPorHabitacion() + "(id, valorTotal, idHabitacion) values (?, ?, ?)");
        q.setParameters(id, valorTotal, idHabitacion);
        return (long) q.executeUnique();
	}
	
	public ConsumoHabitacion darConsumoHabitacionPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlConsumoPorHabitacion() + " WHERE id = ?");
		q.setResultClass(ConsumoHabitacion.class);
		q.setParameters(id);
		return (ConsumoHabitacion) q.executeUnique();
	}
	
	public List<ConsumoHabitacion> darConsumosHabitacion (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlConsumoPorHabitacion());
		q.setResultClass(ConsumoHabitacion.class);
		return (List<ConsumoHabitacion>) q.executeList();
	}
	
}
