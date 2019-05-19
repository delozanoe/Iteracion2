package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Habitacion;



class SQLHabitacion {
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLHabitacion(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	public long adicionarHabitacion(PersistenceManager pm, long id, long capacidad, BigDecimal costoPorNoche, BigDecimal cuenta, String numero, long idHotel, long idConsumoHabitacion, long idTipoHabitacion, long idPlanConsumo, String estado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlHabitacion()+ "(id, capacidad, costoPorNoche, cuenta, numero, idHotel, idConsumoPorHabitacion, idTipoHabitacion, idPlanConsumo, estado) values (?, ?, ?,?,?,?,?,?,?)");
        q.setParameters(id, capacidad, costoPorNoche, cuenta, numero, idHotel, idConsumoHabitacion, idTipoHabitacion, idPlanConsumo, estado);
        return (long) q.executeUnique();
	}
	
	public Habitacion darHabitacionPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlHabitacion () + " WHERE id = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(id);
		return (Habitacion) q.executeUnique();
	}
	
	public List<Habitacion> darHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlHabitacion());
		q.setResultClass(Habitacion.class);
		return (List<Habitacion>) q.executeList();
	}
	
	public long cambiarEstado (PersistenceManager pm,String estado, long idHabitacion)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pha.getSqlHabitacion () + " SET estado = ? WHERE id = ?");
        q.setParameters(estado, idHabitacion);
        return (long) q.executeUnique();
	}
	
	public long moverConsumos (PersistenceManager pm, long idHabitacion, long idHabitacionNueva, long idConsumoHabitacion)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pha.getSqlHabitacion () + " SET idConsumoHabitacion = ? WHERE id = ?");
        q.setParameters(idConsumoHabitacion,idHabitacionNueva);
        Query q2 = pm.newQuery(SQL, "UPDATE " + pha.getSqlHabitacion () + " SET idConsumoHabitacion = ? WHERE id = ?");
        q2.setParameters(null, idHabitacion);
        return (long) q.executeUnique();
	}
	
	public long darNumeroHabitacionesTipo(PersistenceManager pm, long idTipoHabitacion)
	{
		Query q = pm.newQuery(SQL, "SELECT COUNT(*) FROM " + pha.getSqlHabitacion () + "WHERE idTipoHabitacion = ?");
        q.setParameters(idTipoHabitacion);
        return (int) q.executeUnique();
	}
	
	public List <Object[]> consultarFuncionamientoHabitacionMasSolicitada (PersistenceManager pm)
	{
		String sql = "SELECT DISTINCT nombre, solicitudes, semana\n" + 
				"				 FROM (SELECT MAX (cantidad) as solicitudes, to_char(to_date(fecha), 'IW') as semana\n" + 
				"				 		FROM (SELECT DISTINCT COUNT(*) AS cantidad, rh.idTipoHabitacion AS id, th.nombre AS nombre, rh.fechaEntrada AS fecha \n" + 
				"				 				FROM RESERVAHABITACION rh, TIPOHABITACION th\n" + 
				"				 				WHERE rh.estado = 1\n" + 
				"				 					AND rh.fechaEntrada BETWEEN '01/01/18' AND '31/12/18'\n" + 
				"				 					AND rh.idTipoHabitacion = th.id\n" + 
				"				 				GROUP BY rh.idTipoHabitacion, th.nombre, rh.fechaEntrada\n" + 
				"				 				ORDER BY cantidad DESC)\n" + 
				"				 		GROUP BY to_char(to_date(fecha), 'IW')\n" + 
				"				 		ORDER BY semana ASC) t1,\n" + 
				"				 		(SELECT DISTINCT COUNT(*) AS cantidad, rh.idTipoHabitacion AS id, th.nombre AS nombre, rh.fechaEntrada AS fecha\n" + 
				"				 			FROM RESERVAHABITACION rh, TIPOHABITACION th\n" + 
				"				 			WHERE rh.estado = 1\n" + 
				"				 				AND rh.fechaEntrada BETWEEN '01/01/18' AND '31/12/18'\n" + 
				"				 				AND rh.idTipoHabitacion = th.id\n" + 
				"				 			GROUP BY rh.idTipoHabitacion, th.nombre, rh.fechaEntrada\n" + 
				"				 			ORDER BY cantidad DESC) t2\n" + 
				"				 WHERE t1.solicitudes = t2.cantidad\n" + 
				"				 ORDER BY semana ASC";
		
		
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	public List <Object[]> consultarFuncionamientoHabitacionMenosSolicitada (PersistenceManager pm)
	{
		String sql = "SELECT DISTINCT nombre, solicitudes, semana\n" + 
				"				 FROM (SELECT MIN (cantidad) as solicitudes, to_char(to_date(fecha), 'IW') as semana\n" + 
				"				 		FROM (SELECT DISTINCT COUNT(*) AS cantidad, rh.idTipoHabitacion AS id, th.nombre AS nombre, rh.fechaEntrada AS fecha \n" + 
				"				 				FROM RESERVAHABITACION rh, TIPOHABITACION th\n" + 
				"				 				WHERE rh.estado = 1\n" + 
				"				 					AND rh.fechaEntrada BETWEEN '01/01/18' AND '31/12/18'\n" + 
				"				 					AND rh.idTipoHabitacion = th.id\n" + 
				"				 				GROUP BY rh.idTipoHabitacion, th.nombre, rh.fechaEntrada\n" + 
				"				 				ORDER BY cantidad DESC)\n" + 
				"				 		GROUP BY to_char(to_date(fecha), 'IW')\n" + 
				"				 		ORDER BY semana ASC) t1,\n" + 
				"				 		(SELECT DISTINCT COUNT(*) AS cantidad, rh.idTipoHabitacion AS id, th.nombre AS nombre, rh.fechaEntrada AS fecha\n" + 
				"				 			FROM RESERVAHABITACION rh, TIPOHABITACION th\n" + 
				"				 			WHERE rh.estado = 1\n" + 
				"				 				AND rh.fechaEntrada BETWEEN '01/01/18' AND '31/12/18'\n" + 
				"				 				AND rh.idTipoHabitacion = th.id\n" + 
				"				 			GROUP BY rh.idTipoHabitacion, th.nombre, rh.fechaEntrada\n" + 
				"				 			ORDER BY cantidad DESC) t2\n" + 
				"				 WHERE t1.solicitudes = t2.cantidad\n" + 
				"				 ORDER BY semana ASC";
		
		
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
}
