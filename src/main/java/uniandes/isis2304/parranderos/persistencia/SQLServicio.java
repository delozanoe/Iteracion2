package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Servicio;


class SQLServicio
{
	private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLServicio(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	public long adicionarServicio(PersistenceManager pm, Integer id, String nombre, String descripcion, String horaApertura, String horaCierre, int capacidad, double costo, char costoIncluido, Integer idHotel, Integer idTipoServicio, Integer estado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlServicio() + "(id, nombre, descripcion, horaApertura, horaCierre, capacidad, costo, costoIncluido, idHotel, idTipoServicio,estado) values (?, ?, ?, ?, ? , ?, ?, ?, ?, ?,?)");
        q.setParameters(id, nombre, descripcion, horaApertura, horaCierre, capacidad, costo, costoIncluido, idHotel, idTipoServicio,estado);
        return (long) q.executeUnique();
	}
	
	public Servicio darServicioPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlServicio() + " WHERE id = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(id);
		return (Servicio) q.executeUnique();
	}
	
	public List<Servicio> darServicios(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlServicio());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}
	
	public long cambiarEstado (PersistenceManager pm, Integer estado, Integer idServicio)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pha.getSqlServicio () + " SET estado = ? WHERE id = ?");
        q.setParameters(estado, idServicio);
        return (long) q.executeUnique();
	}
}
