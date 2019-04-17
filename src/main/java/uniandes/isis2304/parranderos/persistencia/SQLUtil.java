package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLUtil 
{
	private final static String SQL = PersistenciaCadenaHotelera.SQL;
	
	private PersistenciaCadenaHotelera pha;
	
	public SQLUtil(PersistenciaCadenaHotelera pha)
	{
		this.pha = pha;
	}
	
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pha.darSeqCadenaHotelera() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarCadenaHotelera (PersistenceManager pm)
	{
		return null;
//		Query qConsumoHabitacionServicio = pm.newQuery(SQL, "DELETE FROM " + pha.getSqlConsumoHabitacioServicio ());
//		Query qProductoConsumoPorHabitacion = pm.newQuery(SQL, "DELETE FROM " + pha.getSqlProductoConsumoPorHabitacion ());
//		Query qServicioConsumo = pm.newQuery(SQL, "DELETE FROM " + pha.getSqlServicioConsumo());
//		Query qServicioProducto = pm.newQuery(SQL, "DELETE FROM " + pha.getSqlServicioProducto());
//		Query qServicioConsumo = pm.newQuery(SQL, "DELETE FROM " + pha.getSqlServicioConsumo());
//		
//
//        long gustanEliminados = (long) qGustan.executeUnique ();
//        long sirvenEliminados = (long) qSirven.executeUnique ();
//        long visitanEliminadas = (long) qVisitan.executeUnique ();
//        long bebidasEliminadas = (long) qBebida.executeUnique ();
//        long tiposBebidaEliminados = (long) qTipoBebida.executeUnique ();
//        long bebedoresEliminados = (long) qBebedor.executeUnique ();
//        long baresEliminados = (long) qBar.executeUnique ();
//        return new long[] {gustanEliminados, sirvenEliminados, visitanEliminadas, bebidasEliminadas, 
//        		tiposBebidaEliminados, bebedoresEliminados, baresEliminados};
	}
}
