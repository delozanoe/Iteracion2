package uniandes.isis2304.parranderos.persistencia;

import java.util.List;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ConsumoHabitacionServicio;




public class SQLConsumoHabitacioServicio
{
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLConsumoHabitacioServicio(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	public long adicionarConsumoPorHabitacionServicio(PersistenceManager pm, Integer idConsumoHabitacion, Integer idServicio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlConsumoHabitacionServicio() + "(idConsumoHabitacion, idServicio) values (?, ?)");
        q.setParameters(idConsumoHabitacion, idServicio);
        return (long) q.executeUnique();
	}
	
	public List<ConsumoHabitacionServicio> darConsumoHabitacionServicio (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlConsumoHabitacionServicio ());
		q.setResultClass(ConsumoHabitacionServicio.class);
		return (List<ConsumoHabitacionServicio>) q.execute();
	}
}
