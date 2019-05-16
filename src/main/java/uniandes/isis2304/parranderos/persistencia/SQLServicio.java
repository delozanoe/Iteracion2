package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
	public long adicionarServicio(PersistenceManager pm, long id, String nombre, String descripcion, Timestamp horaApertura, Timestamp horaCierre, long capacidad, BigDecimal costo, char costoIncluido, long idHotel, long idTipoServicio, long estado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlServicio() + "(id, nombre, descripcion, horaApertura, horaCierre, capacidad, costo, costoIncluido, idHotel, idTipoServicio,estado) values (?, ?, ?, ?, ? , ?, ?, ?, ?, ?,?)");
        q.setParameters(id, nombre, descripcion, horaApertura, horaCierre, capacidad, costo, costoIncluido, idHotel, idTipoServicio,estado);
        return (long) q.executeUnique();
	}
	
	public Servicio darServicioPorId (PersistenceManager pm, long id) 
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
	
	public long cambiarEstado (PersistenceManager pm, long estado, long idServicio)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pha.getSqlServicio () + " SET estado = ? WHERE id = ?");
        q.setParameters(estado, idServicio);
        return (long) q.executeUnique();
	}
	
	public List <Object[]> consultarFuncionamientoServicioMasConsumido (PersistenceManager pm)
	{
		String sql = "SELECT DISTINCT nombre,tipoServicio,consumos, semana"
				+ "FROM (SELECT MAX (cantidad) as consumos, to_char(fecha, 'IW') as semana"
				+ 		"FROM (SELECT DISTINCT COUNT(rs.idServicio AS id, s.nombre AS nombre, rs.dia AS fecha, ts.nombre AS tipoServicio) AS cantidad"
				+ 				"FROM RESERVASERVICIO rs, SERVICIO s, TIPOSERVICIO ts"
				+ 				"WHERE rs.estado = 1"
				+ 					"AND rs.dia BETWEEN '01/01/18' AND '31/12/18'"
				+ 					"AND rs.idServicio = s.id"
				+ 					"AND s.idTipoServicio = ts.id"
				+ 				"GROUP BY rs.idServicio, s.nombre, rs.dia, ts.nombre"
				+ 				"ORDER BY cantidad DESC)"
				+ 		"GROUP BY to_char(fecha, 'IW')"
				+ 		"ORDER BY semana ASC) t1,"
				+ 		"(SELECT DISTINCT COUNT(rs.idServicio AS id, s.nombre AS nombre, rs.dia AS fecha) AS cantidad"
				+ 			"FROM RESERVASERVICIO rs, SERVICIO s"
				+ 			"WHERE rs.estado = 1"
				+ 				"AND rs.dia BETWEEN '01/01/18' AND '31/12/18'"
				+ 				"AND rs.idServicio = s.id"
				+ 			"GROUP BY rs.idServicio, s.nombre, rs.dia"
				+ 			"ORDER BY cantidad DESC) t2"
				+ "WHERE t1.consumos = t2.cantidad"
				+ "ORDER BY semana ASC;";
		
		
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	public List <Object[]> consultarFuncionamientoServicioMenosConsumido (PersistenceManager pm)
	{
		String sql = "SELECT DISTINCT nombre,tipoServicio,consumos, semana"
				+ "FROM (SELECT MIN (cantidad) as consumos, to_char(fecha, 'IW') as semana"
				+ 		"FROM (SELECT DISTINCT COUNT(rs.idServicio AS id, s.nombre AS nombre, rs.dia AS fecha, ts.nombre AS tipoServicio) AS cantidad"
				+ 				"FROM RESERVASERVICIO rs, SERVICIO s, TIPOSERVICIO ts"
				+ 				"WHERE rs.estado = 1"
				+ 					"AND rs.dia BETWEEN '01/01/18' AND '31/12/18'"
				+ 					"AND rs.idServicio = s.id"
				+ 					"AND s.idTipoServicio = ts.id"
				+ 				"GROUP BY rs.idServicio, s.nombre, rs.dia, ts.nombre"
				+ 				"ORDER BY cantidad DESC)"
				+ 		"GROUP BY to_char(fecha, 'IW')"
				+ 		"ORDER BY semana ASC) t1,"
				+ 		"(SELECT DISTINCT COUNT(rs.idServicio AS id, s.nombre AS nombre, rs.dia AS fecha) AS cantidad"
				+ 			"FROM RESERVASERVICIO rs, SERVICIO s"
				+ 			"WHERE rs.estado = 1"
				+ 				"AND rs.dia BETWEEN '01/01/18' AND '31/12/18'"
				+ 				"AND rs.idServicio = s.id"
				+ 			"GROUP BY rs.idServicio, s.nombre, rs.dia"
				+ 			"ORDER BY cantidad DESC) t2"
				+ "WHERE t1.consumos = t2.cantidad"
				+ "ORDER BY semana ASC;";
		

		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
}
