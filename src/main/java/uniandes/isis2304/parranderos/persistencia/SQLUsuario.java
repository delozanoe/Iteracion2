package uniandes.isis2304.parranderos.persistencia;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Usuario;



class SQLUsuario {
private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLUsuario(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	public long adicionarUsuario(PersistenceManager pm, Integer id, String tipoDocumento, Long numeroDocumento, String nombre, String correo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlUsuario()+ "(id, tipoDocumento, numeroDocumento, nombre, correo) values (?, ?, ?,?,?)");
        q.setParameters(id, tipoDocumento,numeroDocumento,nombre, correo);
        return (long) q.executeUnique();
	}
	
	public Usuario darUsuarioPorId (PersistenceManager pm, Integer id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlUsuario () + " WHERE id = ?");
		q.setResultClass(Usuario.class);
		q.setParameters(id);
		return (Usuario) q.executeUnique();
	}
	
	public ArrayList<Usuario> darUsuarios(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlUsuario());
		q.setResultClass(Usuario.class);
		return (ArrayList<Usuario>) q.executeList();
	}
}
