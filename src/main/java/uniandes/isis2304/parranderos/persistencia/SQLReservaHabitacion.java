package uniandes.isis2304.parranderos.persistencia;

import java.util.ArrayList;

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
	
	public long adicionarReservaHabitacion(PersistenceManager pm, Integer id, String fechaEntrada, String fechaSalida, Integer numeroPersonas, Integer idHotel, Integer idCliente) 
	{
		
		
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlReservaHabitacion()+ "(id, fechaEntrada, fechaSalida, numeroPersonas, idHotel, idCliente) values (?, ?, ?,?,?,?)");
        q.setParameters(id, fechaEntrada,fechaSalida,numeroPersonas, idHotel,idCliente);
        return (long) q.executeUnique();
	}
	
	public ReservaHabitacion darReservaHabitacionPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlReservaHabitacion() + " WHERE id = ?");
		q.setResultClass(ReservaHabitacion.class);
		q.setParameters(id);
		return (ReservaHabitacion) q.executeUnique();
	}
	
	public ArrayList<ReservaHabitacion> darReservasHabitaciones(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlReservaHabitacion());
		q.setResultClass(ReservaHabitacion.class);
		return (ArrayList<ReservaHabitacion>) q.executeList();
	}
}
