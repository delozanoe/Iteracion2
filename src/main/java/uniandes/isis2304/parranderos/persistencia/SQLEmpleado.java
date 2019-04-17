package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Cliente;
import uniandes.isis2304.parranderos.negocio.Empleado;




class SQLEmpleado {
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLEmpleado(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	public long adicionarEmpleado(PersistenceManager pm, long id,long idHotel, long idTipoEmpleado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlEmpleado()+ "(id, idHotel, idTipoEmpleado) values (?, ?,?)");
        q.setParameters(id, idHotel, idTipoEmpleado);
        return (long) q.executeUnique();
	}
	
	public Empleado darEmpleadoPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlEmpleado () + " WHERE id = ?");
		q.setResultClass(Empleado.class);
		q.setParameters(id);
		return (Empleado) q.executeUnique();
	}
	
	public List<Empleado> darEmpleados (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlEmpleado());
		q.setResultClass(Cliente.class);
		return (List<Empleado>) q.executeList();
	}
}
