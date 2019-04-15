package uniandes.isis2304.parranderos.persistencia;

import java.util.ArrayList;


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
	
	public long adicionarCliente(PersistenceManager pm, Integer idBar, char pazySalvo, Integer idHabitacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlCliente()+ "(id, pazySalvo, idHotel) values (?, ?, ?)");
        q.setParameters(idBar, pazySalvo, idHabitacion);
        return (long) q.executeUnique();
	}
	
	
	public Cliente darClientePorId (PersistenceManager pm, Integer idUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlCliente () + " WHERE id = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(idUsuario);
		return (Cliente) q.executeUnique();
	}
	
	public ArrayList<Cliente> darClientes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlCliente());
		q.setResultClass(Cliente.class);
		return (ArrayList<Cliente>) q.executeList();
	}
	
}
