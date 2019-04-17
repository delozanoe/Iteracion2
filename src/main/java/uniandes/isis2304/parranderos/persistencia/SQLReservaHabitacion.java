package uniandes.isis2304.parranderos.persistencia;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ReservaHabitacion;



class SQLReservaHabitacion {
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLReservaHabitacion(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	public long adicionarReservaHabitacion(PersistenceManager pm, Integer id, Timestamp fechaEntrada, Timestamp fechaSalida, Integer numeroPersonas, Integer idHotel, Integer idCliente, Integer idConvencion, Integer idTipoHabitacion) 
	{
		
		
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlReservaHabitacion()+ "(id, fechaEntrada, fechaSalida, numeroPersonas, idHotel, idCliente, idConvencion, idTipoHabitacion) values (?, ?, ?,?,?,?)");
        q.setParameters(id, fechaEntrada,fechaSalida,numeroPersonas, idHotel,idCliente, idConvencion, idTipoHabitacion);
        return (long) q.executeUnique();
	}
	
	public ReservaHabitacion darReservaHabitacionPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlReservaHabitacion() + " WHERE id = ?");
		q.setResultClass(ReservaHabitacion.class);
		q.setParameters(id);
		return (ReservaHabitacion) q.executeUnique();
	}
	
	public List<ReservaHabitacion> darReservasHabitaciones(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlReservaHabitacion());
		q.setResultClass(ReservaHabitacion.class);
		return (List<ReservaHabitacion>) q.executeList();
	}
	
	public long eliminarReservaPorIdConvencion (PersistenceManager pm, Integer idConvencion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pha.getSqlReservaHabitacion() + " WHERE idConvencion = ?");
        q.setParameters(idConvencion);
        return (long) q.executeUnique();
	}
	
	public long eliminarReservaPorId (PersistenceManager pm, Integer idReserva)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pha.getSqlReservaHabitacion() + " WHERE id = ?");
        q.setParameters(idReserva);
        return (long) q.executeUnique();
	}
	
}
