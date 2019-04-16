package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;

class SQLConvencion 
{
	private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha; 
	
	public SQLConvencion(PersistenciaCadenaHotelera pha)
	{
		this.pha= pha; 
	}
	
	public long adicionarConvencion(PersistenceManager pm )
	{
		
	}
}
