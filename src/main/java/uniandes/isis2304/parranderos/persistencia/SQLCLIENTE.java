package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Cliente;



class SQLCLIENTE {
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLCLIENTE(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	public long adicionarCliente(PersistenceManager pm, long idBar, String pazySalvo, long idHabitacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlCliente()+ "(idUsuario, pazySalvo, idHotel) values (?, ?, ?)");
        q.setParameters(idBar, pazySalvo, idHabitacion);
        return (long) q.executeUnique();
	}
	
	
	public Cliente darClientePorId (PersistenceManager pm, long idUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlCliente () + " WHERE idUsuario = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(idUsuario);
		return (Cliente) q.executeUnique();
	}
	
	public List<Cliente> darClientes(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlCliente());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	
	public long cambiarHabitacion (PersistenceManager pm, long idCliente, long idHabitacion)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pha.getSqlCliente () + " SET idHabitacion = ? WHERE idUsuario = ?");
        q.setParameters(idHabitacion,idCliente);
        return (long) q.executeUnique();
	}
	
	public List<Cliente> darClientesHanConsumido(PersistenceManager pm, long idServicio, Timestamp fechaInicio, Timestamp fechaFin, String criterio, String criterioOrden )
	{
		String sql = "";
		String adicionar1 = "";
		String adicionar2 = "";
		
		if(criterio.equals("id"))
		{
			criterio = "u.id";
		}
		
		else if (criterio.equals("fecha"))
		{
			criterio = "rs.dia";
		}
		
		else if(criterio.equals("cantidad"))
		{
			adicionar1 = ", COUNT(*) AS CantidadConsumos";
			adicionar2 = "GROUP BY u.nombre, u.id, i.tipoDocumento, u.numeroDocumento, u.correo";
			criterio = "COUNT(*)";
		}
		
		sql = "SELECT u.nombre, u.id, u.tipoDocumento, u.numeroDocumento, u.correo";
		sql += adicionar1;
		sql+= "FROM" + pha.getSqlCliente() + " c, " + pha.getSqlUsuario() + " u, " + pha.getSqlReservaServicio() + " rs";
		sql+= "WHERE c.idUsuario = u.id AND u.id = rs.idCliente AND rs.idServicio = ? AND rs.dia BETWEEN (?) AND (?)";
		sql += adicionar2;
		sql+= "ORDER BY ? ?";
		
		Query q = pm.newQuery(SQL,sql);
		q.setResultClass(Cliente.class);
		q.setParameters(idServicio, fechaInicio, fechaFin, criterio,criterioOrden);
		return (List<Cliente>) q.executeList();
	}
	
	public List<Cliente> darClientesNoHanConsumido(PersistenceManager pm, long idServicio, Timestamp fechaInicio, Timestamp fechaFin, String criterio, String criterioOrden)
	{
		String sql = "";
		
		if(criterio.equals("id"))
		{
			criterio = "u.id";
		}
		else if(criterio.equals("fecha"))
		{
			criterio = "rs.dia";
		}
		
		sql = "SELECT u.nombre, u.id, u.tipoDocumento, u.numeroDocumento, u.correo";
		sql+= "FROM" + pha.getSqlCliente() + " c, " + pha.getSqlUsuario() + " u";
		sql+= "LEFT JOIN" + pha.getSqlReservaServicio()+ "rs ON c.idUsuario = rs.idClient";
		sql+= "WHERE c.idUsuario = u.id AND rs.idServicio != ? AND rs.estado = 1 AND rs.dia BETWEEN (?) AND (?)";
		sql+= "ORDER BY ? ?";
		
		Query q = pm.newQuery(SQL,sql);
		q.setResultClass(Cliente.class);
		q.setParameters(idServicio, fechaInicio, fechaFin, criterio,criterioOrden);
		return (List<Cliente>) q.executeList();
	}
	
	public List<Object[]> buenosClientes1(PersistenceManager pm)
	{
		String sql = "SELECT (*)"
				+ 	"FROM (SELECT c.idUsuario as clienteBueno"
				+ 			"FROM Cliente c, ReservaHabitacion rh"
				+ 			"WHERE c.idUsuario = rh.idCliente"
				+ 				"AND rh.fechaSalida < '29/05/19'"
				+ 				"AND rh.estado = 1"
				+ 				"AND EXTRACT (MONTH FROM rh.fechaEntrada) BETWEEN 1 AND 4) t1,"
				+ 		"(SELECT c.idUsuario"
				+ 			"FROM Cliente c, ReservaHabitacion rh"
				+ 			"WHERE c.idUsuario = rh.idCliente"
				+ 				"AND rh.fechaSalida < '29/05/19'"
				+ 				"AND rh.estado = 1"
				+ 				"AND EXTRACT (MONTH FROM rh.fechaEntrada) BETWEEN  5 AND 8) t2,"
				+  		"(SELECT c.idUsuario"
				+ 			"FROM Cliente c, ReservaHabitacion rh"
				+ 			"WHERE c.idUsuario = rh.idCliente"
				+ 				"AND rh.fechaSalida < '29/05/19'"
				+ 				"AND rh.estado = 1"
				+ 				"AND EXTRACT (MONTH FROM rh.fechaEntrada) BETWEEN 9 AND 12) t3"
				+ 	"WHERE t1.idCliente = t2.idCliente"
				+ 		"AND t2.idCliente = t3.idCliente;";
		
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	public List<Object[]> buenosClientes2 (PersistenceManager pm)
	{
		String sql = "SELECT c.idUsuario"
				+ 	"FROM Cliente c, Habitacion h, ConsumoHabitacion ch, ConsumoHabitacionServicio chs, Servicio s"
				+ 	"WHERE c.idHabitacion = h.id"
				+ 		"AND ch.idHabitacion =h.id"
				+ 		"AND ch.id = chs.idConsumoHabitacion"
				+ 		"AND chs.idServicio = s.id"
				+ 		"AND s.costo >300000";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();	
	}
	
	public List<Object[]> buenosClientes3 (PersistenceManager pm)
	{
		String sql = "SELECT c.idUsuario"
				+ 	"FROM Cliente c, Habitacion h, ConsumoHabitacion ch, ConsumoHabitacionServicio chs, Servicio s, TipoServicio ts"
				+ 	"WHERE c.idHabitacion = h.id"
				+ 		"AND ch.idHabitacion =h.id"
				+ 		"AND ch.id = chs.idConsumoHabitacion"
				+ 		"AND chs.idServicio = s.id"
				+ 		"AND ts.id = s.idTipoServicio"
				+ 		"AND ts.id = 8"
				+ 		"OR ts.id = 11"
				+ 		"AND rs.duracion > 240";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();	
	}
	
}
