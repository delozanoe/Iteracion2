package uniandes.isis2304.parranderos.persistencia;

import java.util.List;
import java.sql.Date;
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
	public long adicionarReserva(PersistenceManager pm, Integer id, String horaInicio, double duracion, Date dia, String lugar, Integer idCliente, Integer idServicio, Integer idConvencion)  
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlReservaServicio() + "(id, horaInicio, duracion, dia, lugar, idCLiente, idServicio, idConvencion) values (?, ?, ?,?,?,?,?)");
        q.setParameters(id, horaInicio, duracion, lugar, idCliente, idServicio, idConvencion);
        return (long) q.executeUnique();
	}
	
	public ReservaServicio darReservaServicioPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlReservaServicio() + " WHERE id = ?");
		q.setResultClass(ReservaServicio.class);
		q.setParameters(id);
		return (ReservaServicio) q.executeUnique();
	}
	
	public List<ReservaServicio> darReservasServicios(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlReservaServicio());
		q.setResultClass(ReservaServicio.class);
		return (List<ReservaServicio>) q.executeList();
	}
	
	public long eliminarReservaPorIdConvencion (PersistenceManager pm, Integer idConvencion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pha.getSqlReservaServicio() + " WHERE idConvencion = ?");
        q.setParameters(idConvencion);
        return (long) q.executeUnique();
	}
	
	public long eliminarReservaPorId (PersistenceManager pm, Integer idReserva)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pha.getSqlReservaServicio() + " WHERE id = ?");
        q.setParameters(idReserva);
        return (long) q.executeUnique();
	}
	
	public long cambiarServicio (PersistenceManager pm, Integer idServicio, Integer idReserva)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pha.getSqlReservaServicio () + " SET idServicio = ? WHERE id = ?");
        q.setParameters(idServicio,idReserva);
        return (long) q.executeUnique();
	}
}
