package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hotel;




public class SQLHotel {
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLHotel(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	public long adicionarHotel(PersistenceManager pm, Integer id, String pais, String ciudad, Integer ofertaHabitacionl) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlHotel()+ "(id, pais, ciudad, ofertaHabitacional) values (?, ?, ?,?)");
        q.setParameters(id, pais, ciudad, ofertaHabitacionl);
        return (long) q.executeUnique();
	}
	
	public Hotel darHotelPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlHotel () + " WHERE id = ?");
		q.setResultClass(Hotel.class);
		q.setParameters(id);
		return (Hotel) q.executeUnique();
	}
	
	public List<Hotel> darHoteles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlHotel());
		q.setResultClass(Hotel.class);
		return (List<Hotel>) q.executeList();
	}
}
