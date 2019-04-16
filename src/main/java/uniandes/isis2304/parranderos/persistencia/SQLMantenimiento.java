package uniandes.isis2304.parranderos.persistencia;

class SQLMantenimiento 
{
	
	private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	
	private PersistenciaCadenaHotelera pha; 
	
	public SQLMantenimiento(PersistenciaCadenaHotelera pha)
	{
		this.pha=pha; 
	}
}
