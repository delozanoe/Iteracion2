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
	public long adicionarServicio(PersistenceManager pm, BigDecimal id, String nombre, String descripcion, String horaApertura, String horaCierre, BigDecimal capacidad, BigDecimal costo, String costoIncluido, BigDecimal idHotel, BigDecimal idTipoServicio, BigDecimal estado) 
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
//		String sql ="SELEC * FROM SERVICIO1";
		String sql = "SELECT DISTINCT nombre,id,tipoServicio,consumos, semana\n" + 
				"				 FROM (SELECT MAX (cantidad) as consumos, to_char(fecha, 'IW') as semana\n" + 
				"						FROM (SELECT DISTINCT COUNT(*) AS cantidad, rs.idServicio AS id, s.nombre AS nombre, rs.dia AS fecha, ts.nombre AS tipoServicio\n" + 
				"				 				FROM RESERVASERVICIO rs, SERVICIO s, TIPOSERVICIO ts\n" + 
				"								WHERE rs.estado = 1\n" + 
				"				 					AND rs.dia BETWEEN '01/01/18' AND '31/12/18'\n" + 
				"				 					AND rs.idServicio = s.id\n" + 
				"				 					AND s.idTipoServicio = ts.id\n" + 
				"				 				GROUP BY rs.idServicio, s.nombre, rs.dia, ts.nombre\n" + 
				"				 				ORDER BY cantidad DESC)\n" + 
				"				 		GROUP BY to_char(fecha, 'IW')\n" + 
				"				 		ORDER BY semana ASC) t1,\n" + 
				"				 		(SELECT DISTINCT COUNT(*) AS cantidad, rs.idServicio AS id, s.nombre AS nombre, rs.dia AS fecha, ts.nombre AS tipoServicio\n" + 
				"				 			FROM RESERVASERVICIO rs, SERVICIO s, TIPOSERVICIO ts\n" + 
				"				 			WHERE rs.estado = 1\n" + 
				"				 				AND rs.dia BETWEEN '01/01/18' AND '31/12/18'\n" + 
				"				 				AND rs.idServicio = s.id\n" + 
				"                                AND s.idTipoServicio = ts.id\n" + 
				"				 			GROUP BY rs.idServicio, s.nombre, rs.dia, ts.nombre\n" + 
				"				 			ORDER BY cantidad DESC) t2\n" + 
				"				 WHERE t1.consumos = t2.cantidad\n" + 
				"				 ORDER BY semana ASC\n" + 
				"";
		
		
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	public List <Object[]> consultarFuncionamientoServicioMenosConsumido (PersistenceManager pm)
	{
//		String sql ="SELEC * FROM SERVICIO";
		String sql = "SELECT DISTINCT nombre,id,tipoServicio,consumos, semana\n" + 
				"				 FROM (SELECT MIN (cantidad) as consumos, to_char(fecha, 'IW') as semana\n" + 
				"						FROM (SELECT DISTINCT COUNT(*) AS cantidad, rs.idServicio AS id, s.nombre AS nombre, rs.dia AS fecha, ts.nombre AS tipoServicio\n" + 
				"				 				FROM RESERVASERVICIO rs, SERVICIO s, TIPOSERVICIO ts\n" + 
				"								WHERE rs.estado = 1\n" + 
				"				 					AND rs.dia BETWEEN '01/01/18' AND '31/12/18'\n" + 
				"				 					AND rs.idServicio = s.id\n" + 
				"				 					AND s.idTipoServicio = ts.id\n" + 
				"				 				GROUP BY rs.idServicio, s.nombre, rs.dia, ts.nombre\n" + 
				"				 				ORDER BY cantidad DESC)\n" + 
				"				 		GROUP BY to_char(fecha, 'IW')\n" + 
				"				 		ORDER BY semana ASC) t1,\n" + 
				"				 		(SELECT DISTINCT COUNT(*) AS cantidad, rs.idServicio AS id, s.nombre AS nombre, rs.dia AS fecha, ts.nombre AS tipoServicio\n" + 
				"				 			FROM RESERVASERVICIO rs, SERVICIO s, TIPOSERVICIO ts\n" + 
				"				 			WHERE rs.estado = 1\n" + 
				"				 				AND rs.dia BETWEEN '01/01/18' AND '31/12/18'\n" + 
				"				 				AND rs.idServicio = s.id\n" + 
				"                                AND s.idTipoServicio = ts.id\n" + 
				"				 			GROUP BY rs.idServicio, s.nombre, rs.dia, ts.nombre\n" + 
				"				 			ORDER BY cantidad DESC) t2\n" + 
				"				 WHERE t1.consumos = t2.cantidad\n" + 
				"				 ORDER BY semana ASC\n" + 
				"";
		

		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
}
