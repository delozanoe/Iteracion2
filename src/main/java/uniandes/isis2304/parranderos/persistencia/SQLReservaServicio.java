package uniandes.isis2304.parranderos.persistencia;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ReservaServicio;


class SQLReservaServicio {
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLReservaServicio(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	public long adicionarReserva(PersistenceManager pm, Integer id, String horaInicio, double duracion, String dia, String lugar, Integer idCliente, Integer idServicio)  
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlReservaServicio() + "(id, horaInicio, duracion, dia, lugar, idCLiente, idServicio) values (?, ?, ?,?,?,?,?)");
        q.setParameters(id, horaInicio, duracion, lugar, idCliente, idServicio);
        return (long) q.executeUnique();
	}
	
	public ReservaServicio darReservaServicioPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlReservaServicio() + " WHERE id = ?");
		q.setResultClass(ReservaServicio.class);
		q.setParameters(id);
		return (ReservaServicio) q.executeUnique();
	}
	
	public ArrayList<ReservaServicio> darReservasServicios(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlReservaServicio());
		q.setResultClass(ReservaServicio.class);
		return (ArrayList<ReservaServicio>) q.executeList();
	}
}
