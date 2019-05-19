package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.List;

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
	
	public long adicionarUsuario(PersistenceManager pm, BigDecimal id, String tipoDocumento, String numeroDocumento, String nombre, String correo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pha.getSqlUsuario()+ "(id, tipoDocumento, numeroDocumento, nombre, correo) values (?, ?, ?,?,?)");
        q.setParameters(id, tipoDocumento,numeroDocumento,nombre, correo);
        return (long) q.executeUnique();
	}
	
	public Usuario darUsuarioPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlUsuario () + " WHERE id = ?");
		q.setResultClass(Usuario.class);
		q.setParameters(id);
		return (Usuario) q.executeUnique();
	}
	
	public List<Usuario> darUsuarios(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pha.getSqlUsuario());
		q.setResultClass(Usuario.class);
		return (List<Usuario>) q.executeList();
	}
}
