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

	public List<Cliente> darClientesHanConsumido(PersistenceManager pm, long idServicio, String fechaInicio, String fechaFin, String criterio, String criterioOrden )
	{
		System.out.println("ESTA EN EL QUERY");
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
			adicionar2 = " GROUP BY u.nombre, u.id, i.tipoDocumento, u.numeroDocumento, u.correo";
			criterio = " COUNT(*)";
		}
		if(criterioOrden.equals("asc"))
		{
			sql = "SELECT u.nombre, u.id, u.tipoDocumento, u.numeroDocumento, u.correo";
			sql += adicionar1;
			sql+= " FROM " + pha.getSqlCliente() + " c, " + pha.getSqlUsuario() + " u, " + pha.getSqlReservaServicio() + " rs";
			sql+= " WHERE c.idUsuario = u.id "
					+ 	" AND u.id = rs.idCliente"
					+ 	" AND rs.estado = 1"
					+ 	" AND rs.idServicio = ? "
					+ 	" AND rs.dia BETWEEN (?) AND (?)";
			sql += adicionar2;
			sql+= " ORDER BY ? ASC";
		}
		else
		{
			sql = "SELECT u.nombre, u.id, u.tipoDocumento, u.numeroDocumento, u.correo";
			sql += adicionar1;
			sql+= " FROM " + pha.getSqlCliente() + " c, " + pha.getSqlUsuario() + " u, " + pha.getSqlReservaServicio() + " rs";
			sql+= " WHERE c.idUsuario = u.id "
					+ 	" AND u.id = rs.idCliente"
					+ 	" AND rs.estado = 1"
					+ 	" AND rs.idServicio = ? "
					+ 	" AND rs.dia BETWEEN (?) AND (?)";
			sql += adicionar2;
			sql+= " ORDER BY ? DESC";
		}

		Query q = pm.newQuery(SQL,sql);

		q.setResultClass(Cliente.class);
		q.setParameters(idServicio, fechaInicio, fechaFin, criterio,criterioOrden);
		System.out.println(sql);
		return (List<Cliente>) q.executeList();

	}

	public List<Cliente> darClientesNoHanConsumido(PersistenceManager pm, long idServicio, String fechaInicio, String fechaFin, String criterio, String criterioOrden)
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

		if(criterioOrden.equals("asc"))
		{
			sql = "SELECT u.nombre, u.id, u.tipoDocumento, u.numeroDocumento, u.correo\n" + 
					" FROM" + pha.getSqlUsuario()+" u, "+ pha.getSqlCliente() + " c, " + pha.getSqlReservaServicio()+" rs\n" + 
					" WHERE u.id = c.idUsuario\n " + 
					"    AND c.idUsuario = rs.idCliente\n" + 
					"    AND rs.estado = 1\n" + 
					"    AND rs.dia BETWEEN (?) AND (?)\n" + 
					"    AND (SELECT COUNT (*)\n " + 
					"        FROM Cliente cli, ReservaServicio rese\n " + 
					"        WHERE cli.idusuario = rese.idCliente\n" + 
					"            AND c.idUsuario = cli.idUsuario\n" + 
					"            AND rese.idServicio = ?) =0\n" + 
					" ORDER BY ? ASC;";
		}
		else
		{
			sql = "SELECT u.nombre, u.id, u.tipoDocumento, u.numeroDocumento, u.correo\n" + 
					" FROM" + pha.getSqlUsuario()+" u, "+ pha.getSqlCliente() + " c, " + pha.getSqlReservaServicio()+" rs\n" + 
					" WHERE u.id = c.idUsuario\n " + 
					"    AND c.idUsuario = rs.idCliente\n" + 
					"    AND rs.estado = 1\n" + 
					"    AND rs.dia BETWEEN (?) AND (?)\n" + 
					"    AND (SELECT COUNT (*)\n " + 
					"        FROM Cliente cli, ReservaServicio rese\n " + 
					"        WHERE cli.idusuario = rese.idCliente\n" + 
					"            AND c.idUsuario = cli.idUsuario\n" + 
					"            AND rese.idServicio = ?) =0\n" + 
					" ORDER BY ? DESC;";
		}

		Query q = pm.newQuery(SQL,sql);
		q.setResultClass(Cliente.class);
		q.setParameters(fechaInicio, fechaFin,idServicio, criterio,criterioOrden);
		return (List<Cliente>) q.executeList();
	}

	public List<Object[]> buenosClientes1(PersistenceManager pm)
	{
		String sql = "SELECT clienteBueno1 AS BuenosClientes\n" + 
				"FROM (SELECT c.idUsuario as clienteBueno1\n" + 
				"    FROM Cliente c, ReservaHabitacion rh\n" + 
				"    WHERE c.idUsuario = rh.idCliente\n" + 
				"        AND rh.fechaSalida < '29/05/19'	\n" + 
				"        AND rh.estado = 1\n" + 
				"        AND (EXTRACT (MONTH FROM to_date(rh.fechaEntrada, 'dd/mm/yyyy')))  BETWEEN 1 AND 4) t1,\n" + 
				"    (SELECT c.idUsuario as clienteBueno2\n" + 
				"    FROM Cliente c, ReservaHabitacion rh\n" + 
				"    WHERE c.idUsuario = rh.idCliente\n" + 
				"        AND rh.fechaSalida < '29/05/19'\n" + 
				"        AND rh.estado = 1\n" + 
				"        AND (EXTRACT (MONTH FROM to_date(rh.fechaEntrada, 'dd/mm/yyyy')))  BETWEEN 5 AND 8) t2,\n" + 
				"    (SELECT c.idUsuario as clienteBueno3\n" + 
				"    FROM Cliente c, ReservaHabitacion rh\n" + 
				"    WHERE c.idUsuario = rh.idCliente\n" + 
				"        AND rh.fechaSalida < '29/05/19'\n" + 
				"        AND rh.estado = 1\n" + 
				"        AND (EXTRACT (MONTH FROM to_date(rh.fechaEntrada, 'dd/mm/yyyy')))  BETWEEN 9 AND 12) t3\n" + 
				"WHERE clienteBueno1 = clienteBueno2\n" + 
				"AND clienteBueno2 = clienteBueno3;";

		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	public List<Object[]> buenosClientes2 (PersistenceManager pm)
	{
		String sql = "SELECT c.idUsuario\n" + 
				"FROM Cliente c, Habitacion h, ConsumoHabitacion ch, ConsumoHabitacionServicio chs, Servicio s\n" + 
				"WHERE c.idHabitacion = h.id\n" + 
				"    AND ch.id =h.idConsumoHabitacion\n" + 
				"	AND ch.id = chs.idConsumoHabitacion\n" + 
				"    AND chs.idServicio = s.id\n" + 
				"    AND s.costo >100;";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();	
	}

	public List<Object[]> buenosClientes3 (PersistenceManager pm)
	{
		String sql = "SELECT c.idUsuario"
				+ 	"FROM Cliente c, Habitacion h, ConsumoHabitacion ch, ConsumoHabitacionServicio chs, Servicio s, TipoServicio ts, ReservaServicio rs"
				+ 	"WHERE c.id = h.idConsumoHabitacion"
				+ 		"AND ch.idHabitacion =h.id"
				+ 		"AND ch.id = chs.idConsumoHabitacion"
				+ 		"AND chs.idServicio = s.id"
				+ 		"AND ts.id = s.idTipoServicio"
				+ 		"AND rs.idCliente = c.idUsuario"
				+ 		"AND rs.idServicio = s.id"
				+ 		"AND ts.id = 8"
				+ 		"OR ts.id = 11"
				+ 		"AND rs.duracion > 240";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();	
	}

}
