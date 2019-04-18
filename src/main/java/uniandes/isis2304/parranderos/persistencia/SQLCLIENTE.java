package uniandes.isis2304.parranderos.persistencia;

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
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlCliente()+ "(id, pazySalvo, idHotel) values (?, ?, ?)");
        q.setParameters(idBar, pazySalvo, idHabitacion);
        return (long) q.executeUnique();
	}
	
	
	public Cliente darClientePorId (PersistenceManager pm, long idUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlCliente () + " WHERE id = ?");
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
        Query q = pm.newQuery(SQL, "UPDATE " + pha.getSqlCliente () + " SET idHabitacion = ? WHERE id = ?");
        q.setParameters(idHabitacion,idCliente);
        return (long) q.executeUnique();
	}
	
}
