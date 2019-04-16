package uniandes.isis2304.parranderos.persistencia;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ConvencionHotel;

public class SQLConvencionHotel 
{
	private final static String SQL = PersistenciaCadenaHotelera.SQL;

	private PersistenciaCadenaHotelera pha;

	public SQLConvencionHotel(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}

	public long adicionarConvencionHotel(PersistenceManager pm, Integer idConvencion, Integer idHotel) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlConvencionHotel() + "(idConvencion, idHotel) values (?, ?)");
		q.setParameters(idConvencion,idHotel);
		return (long) q.executeUnique();
	}

	public ArrayList<ConvencionHotel> darConvencionHotel (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlConvencionHotel ());
		q.setResultClass(ConvencionHotel.class);
		return (ArrayList<ConvencionHotel>) q.execute();
	}
}
