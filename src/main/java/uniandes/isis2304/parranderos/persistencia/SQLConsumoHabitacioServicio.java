package uniandes.isis2304.parranderos.persistencia;

import java.util.ArrayList;


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
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlConsumoPorHabitacion() + "(idConsumoHabitacion, idServicio) values (?, ?)");
        q.setParameters(idConsumoHabitacion, idServicio);
        return (long) q.executeUnique();
	}
	
	public ArrayList<ConsumoHabitacionServicio> darConsumoHabitacionServicio (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlConsumoHabitacioServicio ());
		q.setResultClass(ConsumoHabitacionServicio.class);
		return (ArrayList<ConsumoHabitacionServicio>) q.execute();
	}
}
