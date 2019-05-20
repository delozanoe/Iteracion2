package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import org.datanucleus.FetchPlanForClass;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Cliente;
import uniandes.isis2304.parranderos.negocio.ConsumoHabitacion;
import uniandes.isis2304.parranderos.negocio.ConsumoHabitacionServicio;
import uniandes.isis2304.parranderos.negocio.Convencion;
import uniandes.isis2304.parranderos.negocio.ConvencionCliente;
import uniandes.isis2304.parranderos.negocio.ConvencionHotel;
import uniandes.isis2304.parranderos.negocio.Empleado;
import uniandes.isis2304.parranderos.negocio.Habitacion;
import uniandes.isis2304.parranderos.negocio.Hotel;
import uniandes.isis2304.parranderos.negocio.Mantenimiento;
import uniandes.isis2304.parranderos.negocio.PlanConsumo;
import uniandes.isis2304.parranderos.negocio.PlanConsumoServicio;
import uniandes.isis2304.parranderos.negocio.Producto;
import uniandes.isis2304.parranderos.negocio.ProductoConsumoPorHabitacion;
import uniandes.isis2304.parranderos.negocio.ReservaHabitacion;
import uniandes.isis2304.parranderos.negocio.ReservaServicio;
import uniandes.isis2304.parranderos.negocio.Servicio;
import uniandes.isis2304.parranderos.negocio.ServicioConsumo;
import uniandes.isis2304.parranderos.negocio.ServicioProducto;
import uniandes.isis2304.parranderos.negocio.TipoEmpleado;
import uniandes.isis2304.parranderos.negocio.TipoHabitacion;
import uniandes.isis2304.parranderos.negocio.TipoServicio;
import uniandes.isis2304.parranderos.negocio.Usuario;






public class PersistenciaCadenaHotelera 
{
	private static Logger log = Logger.getLogger(PersistenciaCadenaHotelera.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	private static PersistenciaCadenaHotelera instance;

	private PersistenceManagerFactory pmf;

	private List <String> tablas;

	private SQLCLIENTE sqlCliente; 

	private SQLConsumoPorHabitacion sqlConsumoPorHabitacion;

	private SQLEmpleado sqlEmpleado;

	private SQLHabitacion sqlHabitacion;

	private SQLHotel sqlHotel;

	private SQLPlanConsumo sqlPlanConsumo; 

	private SQLProducto sqlProducto;

	private SQLProductoConsumoPorHabitacion sqlProductoConsumoPorHabitacion;

	private SQLReservaServicio sqlReservaServicio; 

	private SQLReservaHabitacion sqlReservaHabitacion;

	private SQLServicio sqlServicio;

	private SQLServicioConsumo sqlServicioConsumo;

	private SQLServicioProducto sqlServicioProducto;

	private SQLUsuario sqlUsuario;

	private SQLUtil sqlUtil;

	private SQLConsumoHabitacioServicio sqlConsumoHabitacioServicio;

	private SQLTipoEmpleado sqlTipoEmpleado;

	private SQLTipoServicio sqlTipoServicio;

	private SQLTipoHabitacion sqlTipoHabitacion;

	private SQLConvencion sqlConvencion; 

	private SQLMantenimiento sqlMantenimiento;

	private SQLConvencionCliente sqlConvencionCliente;

	private SQLConvencionHotel sqlConvencionHotel;

	private SQLPlanConsumoServicio sqlPlanConsumoServicio;


	private PersistenciaCadenaHotelera()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("CadenaHotelera");
		crearClasesSQL();

		tablas = new LinkedList<String>();
		//0
		tablas.add("CadenaHotelera_sequence");
		//1
		tablas.add("CLIENTE");
		//2
		tablas.add("CONSUMOPORHABITACION");
		//03
		tablas.add("CONSUMOHABITACIONSERVICIO");
		//04
		tablas.add("CONVENCION");
		//5
		tablas.add("CONVENCIONCLIENTE");
		//6
		tablas.add("CONVENCIONHOTEL");
		//7
		tablas.add("EMPLEADO");
		//8
		tablas.add("HABITACION");
		//9
		tablas.add("HOTEL");
		//10
		tablas.add("MANTENIMIENTO");
		//11
		tablas.add("PLANCONSUMO");
		//12
		tablas.add("PLANCONSUMOSERVICIO");
		//13
		tablas.add("PRODUCTO");
		//14
		tablas.add("PRODUCTOCONSUMOHABITACION");
		//15
		tablas.add("RESERVAHABITACION");
		//16
		tablas.add("RESERVASERVICIO");
		
		tablas.add("SERVICIO");
		tablas.add("SERVICIOCONSUMO");
		tablas.add("SERVICIOPRODUCTO");
		
		
		tablas.add("TIPOEMPLEADO");
		tablas.add("TIPOSERVICIO");
		tablas.add("USUARIO");
		tablas.add("TIPOHABITACION");
		tablas.add("UTIL");
		
		



	}

	private PersistenciaCadenaHotelera(JsonObject tableConfig)
	{
		crearClasesSQL();
		tablas = leerNombreTablas(tableConfig);

		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);

	}

	public static PersistenciaCadenaHotelera getInstance()
	{
		if(instance == null)
		{
			instance = new PersistenciaCadenaHotelera();
		}
		return instance;
	}

	public static PersistenciaCadenaHotelera getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaCadenaHotelera (tableConfig);
		}
		return instance;
	}

	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	private List<String> leerNombreTablas(JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}

	private void crearClasesSQL()
	{


		sqlCliente = new SQLCLIENTE(this);
		sqlConsumoPorHabitacion = new SQLConsumoPorHabitacion(this);

		sqlEmpleado = new SQLEmpleado(this);



		sqlHabitacion = new SQLHabitacion(this);
		sqlHotel = new SQLHotel(this);

		sqlPlanConsumo = new SQLPlanConsumo(this);
		sqlProductoConsumoPorHabitacion = new SQLProductoConsumoPorHabitacion(this);

		sqlReservaHabitacion = new SQLReservaHabitacion(this); 
		sqlReservaServicio = new SQLReservaServicio(this);

		sqlServicio = new SQLServicio(this);
		sqlServicioConsumo = new SQLServicioConsumo(this);
		sqlServicioProducto = new SQLServicioProducto(this);

		sqlUsuario = new SQLUsuario(this);

		sqlUtil = new SQLUtil(this);
		sqlConsumoHabitacioServicio = new SQLConsumoHabitacioServicio(this);

		sqlTipoEmpleado = new SQLTipoEmpleado(this);
		sqlTipoHabitacion = new SQLTipoHabitacion(this);
		sqlTipoServicio = new SQLTipoServicio(this);

		sqlMantenimiento = new SQLMantenimiento(this);
		sqlConvencion = new SQLConvencion(this);

		sqlConvencionCliente = new SQLConvencionCliente(this);
		sqlConvencionHotel = new SQLConvencionHotel(this);
		sqlPlanConsumoServicio = new SQLPlanConsumoServicio(this);
	}




	public List<String> getTablas() {
		return tablas;
	}



	public PersistenceManagerFactory getPmf() {
		return pmf;
	}

	public String darSeqCadenaHotelera()
	{
		return tablas.get(0);
	}

	public String getSqlCliente() {
		return tablas.get(1);
	}

	public String getSqlConsumoPorHabitacion() {
		return tablas.get(2);
	}

	public String getSqlEmpleado() {
		return tablas.get(7);
	}

	public String getSqlHabitacion() {
		return tablas.get(8);
	}

	public String getSqlHotel() {
		return tablas.get(9);
	}

	public String getSqlPlanConsumo() {
		return tablas.get(11);
	}

	public String getSqlProducto() {
		return tablas.get(13);
	}

	public String getSqlProductoConsumoPorHabitacion() {
		return tablas.get(14);
	}

	public String getSqlReservaServicio() {
		return tablas.get(16);
	}

	public String getSqlReservaHabitacion() {
		return tablas.get(15);
	}

	public String getSqlServicio() {
		return tablas.get(16);
	}

	public String getSqlServicioConsumo() {
		return tablas.get(18);
	}

	public String getSqlServicioProducto()
	{
		return tablas.get(19);
	}

	public String getSqlUsuario() {
		return tablas.get(21);
	}

	public String getSqlUtil() {
		return tablas.get(24);
	}

	public String getSqlConsumoHabitacionServicio()
	{
		return tablas.get(3);
	}

	public String getSqlTipoEmpleado()
	{
		return tablas.get(20);
	}


	public String getSqlTipoServicio()
	{
		return tablas.get(21);
	}

	public String getSqlTipoHabitacion()
	{
		return tablas.get(23);
	}

	public String getSqlConvencion()
	{
		return tablas.get(4);
	}

	public String getSqlMantenimiento()
	{
		return tablas.get(10);
	}

	public String getSqlConvencionCliente()
	{
		return tablas.get(5);
	}

	public String getSqlConvencionHotel()
	{
		return tablas.get(6);
	}

	public String getSqlPlanConsumoServicio()
	{
		return tablas.get(12);
	}

	private long nextval ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia: " + resp);
		return resp;
	}

	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	public long [] limpiarCadenaHotelera()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long [] resp = sqlUtil.limpiarCadenaHotelera(pm);
			tx.commit ();
			log.info ("Borrada la base de datos");
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1, -1, -1, -1, -1, -1, -1};
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Cliente adicionarCliente(String pazYSalvo, long idHabitacion, String tipoDocumento, String numeroDocumento, String nombre, String correo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = this.nextval();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, id, pazYSalvo, idHabitacion);
            long tuplasInsertadasUsu = sqlUsuario.adicionarUsuario(pm, new BigDecimal(id), tipoDocumento, numeroDocumento, nombre, correo);
            tx.commit();
            
            log.trace ("Inserción de cliente: " + nombre + ": " + tuplasInsertadas + tuplasInsertadasUsu +" tuplas insertadas");
            
            return new Cliente (nombre,new BigDecimal(id), tipoDocumento, numeroDocumento, correo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Cliente> darClientes ()
	{
		return sqlCliente.darClientes(pmf.getPersistenceManager());
	}

	public Cliente darClientePorId (long idCliente)
	{
		return sqlCliente.darClientePorId (pmf.getPersistenceManager(), idCliente);
	}

	public long cambiarHabitacionCliente (long idCliente, long idHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlCliente.cambiarHabitacion(pm, idCliente, idHabitacion);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	} 


	public ConsumoHabitacionServicio adicionarConsumoPorHabitacionServicio(long idConsumoHabitacion, long idServicio)
	{
			PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long tuplasInsertadas = sqlConsumoHabitacioServicio.adicionarConsumoPorHabitacionServicio(pm, idConsumoHabitacion, idServicio);
	            tx.commit();

	            log.trace ("Inserción de gustan: [" + idConsumoHabitacion + ", " + idServicio + "]. " + tuplasInsertadas + " tuplas insertadas");

	            return new ConsumoHabitacionServicio(idConsumoHabitacion, idServicio);
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return null;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
		
	}
	
	
	public List<ConsumoHabitacionServicio> darConsumoHabitacionServicio ()
	{
		return sqlConsumoHabitacioServicio.darConsumoHabitacionServicio(pmf.getPersistenceManager());
	}

	public ConsumoHabitacion adicionarConsumoHabitacion(Double valorTotal, long idHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long id = nextval();
            long tuplasInsertadas = sqlConsumoPorHabitacion.adicionarConsumoPorHabitacion(pmf.getPersistenceManager(), id, valorTotal, idHabitacion);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ConsumoHabitacion(valorTotal, sqlHabitacion.darHabitacionPorId(pm, idHabitacion),null, id);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<ConsumoHabitacion> darConsumosHabitacion ()
	{
		return sqlConsumoPorHabitacion.darConsumosHabitacion(pmf.getPersistenceManager());
	}

	public ConsumoHabitacion darConsumoHabitacionPorId (long idConsumoPorHabitacion)
	{
		return sqlConsumoPorHabitacion.darConsumoHabitacionPorId(pmf.getPersistenceManager(), idConsumoPorHabitacion);
	}

	public Empleado adicionarEmpleado(long idHotel, long idTipoEmpleado, String tipoDocumento, BigDecimal numeroDocumento, String nombre, String correo) 
	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//        Transaction tx=pm.currentTransaction();
//        try
//        {
//            tx.begin();            
//            long id = this.nextval();
//            long tuplasInsertadas = sqlEmpleado.adicionarEmpleado(pm, id, idHotel, idTipoEmpleado);
//            long tuplasInsertadasUsu = sqlUsuario.adicionarUsuario(pm, new BigDecimal(id), tipoDocumento, numeroDocumento, nombre, correo);
//            tx.commit();
//            
//            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
//            return new Empleado(sqlTipoEmpleado.darTipoEmpleadoPorId(pm, idTipoEmpleado), sqlHotel.darHotelPorId(pm, idHotel), nombre, tipoDocumento, numeroDocumento, correo, id);
//        }
//        catch (Exception e)
//        {
////        	e.printStackTrace();
//        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
//        	return null;
//        }
//        finally
//        {
//            if (tx.isActive())
//            {
//                tx.rollback();
//            }
//            pm.close();
//        }
		//TODO EMPLEADO
		return null;
	}
	
	public List<Empleado> darEmpleados ()
	{
		return sqlEmpleado.darEmpleados(pmf.getPersistenceManager());
	}

	public Empleado darEmpleadoPorId (long idEmpleado)
	{
		return sqlEmpleado.darEmpleadoPorId (pmf.getPersistenceManager(), idEmpleado);
	}

	public Habitacion adicionarHabitacion(long idHotel, long capacidad, BigDecimal costoPorNoche, BigDecimal cuenta, String numero, long idPlanConsumo, long idConsumoHabitacion, long idTipoHabitacion, String estado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long id = nextval();
            long tuplasInsertadas = sqlHabitacion.adicionarHabitacion(pm, id, capacidad, costoPorNoche, cuenta, numero, idHotel, idConsumoHabitacion, idTipoHabitacion, idPlanConsumo, estado);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Habitacion(id, capacidad, costoPorNoche, cuenta, numero, idHotel, idPlanConsumo, idConsumoHabitacion, idTipoHabitacion, estado);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Habitacion> darHabitaciones ()
	{
		return sqlHabitacion.darHabitaciones(pmf.getPersistenceManager());
	}

	public Habitacion darHabitacionPorId (long idHabitacion)
	{
		return sqlHabitacion.darHabitacionPorId(pmf.getPersistenceManager(), idHabitacion);
	}
	public long cambiarEstadoHabitacion (String estado,long idHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlHabitacion.cambiarEstado(pm, estado,idHabitacion);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	} 

	public long moverConsumos (long idHabitacion, long idHabitacionNueva, long idConsumoHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlHabitacion.moverConsumos(pm, idHabitacion, idHabitacionNueva, idConsumoHabitacion);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	} 


	public Hotel adicionarHotel(String ciudad, String pais, long ofertaHabitacional) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long id = nextval ();
            long tuplasInsertadas = sqlHotel.adicionarHotel(pm, id, pais, ciudad, ofertaHabitacional);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Hotel(id, pais, ciudad, ofertaHabitacional);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Hotel> darHoteles()
	{
		return sqlHotel.darHoteles(pmf.getPersistenceManager());
	}

	public Hotel darHotelPorId (long idHotel)
	{
		return sqlHotel.darHotelPorId(pmf.getPersistenceManager(), idHotel);
	}
	
	public PlanConsumo adicionarPlanConsumo(long idHotel, String descripcion,List<Convencion> convenciones, List<Servicio> servicios) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long id = nextval ();
            long tuplasInsertadas = sqlPlanConsumo.adicionarPlanConsumo(pm, id, idHotel, descripcion);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PlanConsumo(descripcion, sqlHotel.darHotelPorId(pm, idHotel), null, id, convenciones, servicios);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<PlanConsumo> darPlanesConsumo()
	{
		return sqlPlanConsumo.darPlanesConsumo(pmf.getPersistenceManager());
	}

	public PlanConsumo darPlanConsumoPorId (long idPlanConsumo)
	{
		return sqlPlanConsumo.darPlanConsumoPorId(pmf.getPersistenceManager(), idPlanConsumo);
	}

	public Producto adicionarProducto(String nombre, Double costo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long id = nextval ();
            long tuplasInsertadas = sqlProducto.adicionarProducto(pm, id, nombre, costo);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Producto(nombre, costo, id, null, null);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Producto> darProductos()
	{
		return sqlProducto.darProductos(pmf.getPersistenceManager());
	}

	public Producto darProductoPorId (long idProducto)
	{
		return sqlProducto.darProductoPorId(pmf.getPersistenceManager(), idProducto);
	}

	public ProductoConsumoPorHabitacion adicionarProductoConsumoPorHabitacion(long idProducto, long idConsumoHabitacion)
	{
			PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long tuplasInsertadas = sqlProductoConsumoPorHabitacion.adicionarProductoConsumoPorHabitacion(pm, idProducto, idConsumoHabitacion);
	            tx.commit();

	            log.trace ("Inserción de gustan: [" + idConsumoHabitacion + ", " + idProducto + "]. " + tuplasInsertadas + " tuplas insertadas");

	            return new ProductoConsumoPorHabitacion(idProducto, idConsumoHabitacion);
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return null;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
		
	}
	
	
	public List<ConsumoHabitacionServicio> darProductoConsumoPorHabitacion ()
	{
		return sqlConsumoHabitacioServicio.darConsumoHabitacionServicio(pmf.getPersistenceManager());
	}

	public ReservaHabitacion adicionarReservaHabitacion(Timestamp fechaEntrada, Timestamp fechaSalida, long numeroPersonas, long idHotel, long idCliente, long idConvencion, long idTipoHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			long id = nextval ();
			long tuplasInsertadas = sqlReservaHabitacion.adicionarReservaHabitacion(pm, id, fechaEntrada, fechaSalida, numeroPersonas, idHotel, idCliente, idConvencion, idTipoHabitacion);
			tx.commit();

			log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");

			return new ReservaHabitacion(id, fechaEntrada, fechaSalida, numeroPersonas, idHotel, idCliente, idConvencion, idTipoHabitacion);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarReservaHabitacionPorIdConvencion (long idConvencion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlReservaHabitacion.eliminarReservaPorIdConvencion (pm, idConvencion);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarReservaHabitacionPorId(long idReserva) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlReservaHabitacion.eliminarReservaPorId (pm, idReserva);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarReservaServicioPorIdConvencion (long idConvencion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlReservaServicio.eliminarReservaPorIdConvencion (pm, idConvencion);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarReservaServicioPorId(long idReserva) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReservaServicio.eliminarReservaPorId (pm, idReserva);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public List<ReservaHabitacion> darReservasHabitaciones()
	{
		return sqlReservaHabitacion.darReservasHabitaciones(pmf.getPersistenceManager());
	}

	public ReservaHabitacion darReservaHabitacion(long idReserva)
	{
		return sqlReservaHabitacion.darReservaHabitacionPorId(pmf.getPersistenceManager(), idReserva);

	}

	public ReservaServicio adicionarReservaServicio(Timestamp horaInicio, long duracion, Timestamp dia, String lugar, long idCliente, long idServicio, long idConvencion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long id = nextval ();
            long tuplasInsertadas = sqlReservaServicio.adicionarReserva(pm, id, horaInicio, duracion, dia, lugar, idCliente, idServicio, idConvencion);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ReservaServicio(id, horaInicio, dia, duracion, lugar, idCliente, idServicio, idConvencion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<ReservaServicio> darReservasServicios()
	{
		return sqlReservaServicio.darReservasServicios(pmf.getPersistenceManager());
	}

	public ReservaServicio darReservaServicio(long idReserva)
	{
		return sqlReservaServicio.darReservaServicioPorId(pmf.getPersistenceManager(), idReserva);

	}

	public long cambiarServicioReserva (long idServicio, long idReservaServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlReservaServicio.cambiarServicio(pm, idServicio, idReservaServicio);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	} 

	public Servicio adicionarServicio(String nombre, String descripcion, String horaApertura, String horaCierre, BigDecimal capacidad, BigDecimal costo, String costoIncluido, BigDecimal idHotel, BigDecimal idTipoServicio, BigDecimal estado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long id = nextval ();
            long tuplasInsertadas = sqlServicio.adicionarServicio(pm, new BigDecimal(id), nombre, descripcion, horaApertura, horaCierre, capacidad, costo, costoIncluido, idHotel, idTipoServicio, estado);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Servicio(new BigDecimal(id), nombre, descripcion, horaApertura, horaCierre, capacidad, costo, costoIncluido, idHotel, idTipoServicio, estado);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Servicio> darServicios()
	{
		return sqlServicio.darServicios(pmf.getPersistenceManager());
	}

	public Servicio darServicio(long idServicio)
	{
		return sqlServicio.darServicioPorId(pmf.getPersistenceManager(), idServicio);

	}

	public ServicioConsumo adicionarServicioConsumo(long idServicio, long idProducto)
	{
			PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long tuplasInsertadas = sqlServicioConsumo.adicionarServicioConsumo(pm, idServicio, idProducto);
	            tx.commit();

	            log.trace ("Inserción de gustan: [" + idServicio + ", " + idProducto + "]. " + tuplasInsertadas + " tuplas insertadas");

	            return new ServicioConsumo(idServicio, idProducto);
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return null;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
		
	}
	
	
	public List<ServicioConsumo> darServicioConsumo ()
	{
		return sqlServicioConsumo.darServicioConsumo(pmf.getPersistenceManager());
	}

	public ServicioProducto adicionarServicioProducto(long idServicio, long idProducto)
	{
			PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long tuplasInsertadas = sqlServicioProducto.adicionarServicioProducto(pm, idServicio, idProducto);
	            tx.commit();

	            log.trace ("Inserción de gustan: [" + idServicio + ", " + idProducto + "]. " + tuplasInsertadas + " tuplas insertadas");

	            return new ServicioProducto(idServicio, idProducto);
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return null;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
		
	}
	
	
	public List<ServicioProducto> darServicioProducto ()
	{
		return sqlServicioProducto.darServicioProducto(pmf.getPersistenceManager());
	}

	public TipoEmpleado adicionarTipoEmpleado(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long id = nextval ();
            long tuplasInsertadas = sqlTipoEmpleado.adicionarTipoEmpleado(pm, id, nombre);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new TipoEmpleado(id, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<TipoEmpleado> darTiposEmpleado()
	{
		return sqlTipoEmpleado.darTipoEmpleado(pmf.getPersistenceManager());
	}

	public TipoEmpleado darTipoEmpleado(long idTipo)
	{
		return sqlTipoEmpleado.darTipoEmpleadoPorId(pmf.getPersistenceManager(), idTipo);

	}

	public TipoHabitacion adicionarTipoHabitacion(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long id = nextval ();
            long tuplasInsertadas = sqlTipoHabitacion.adicionarTipoHabitacion(pm, id, nombre);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new TipoHabitacion(id, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<TipoHabitacion> darTiposHabitacion()
	{
		return sqlTipoHabitacion.darTipoHabitacion(pmf.getPersistenceManager());
	}

	public TipoHabitacion darTipoHabitacion(long idTipo)
	{
		return sqlTipoHabitacion.darTipoHabitacionPorId(pmf.getPersistenceManager(), idTipo);

	}


	public TipoServicio adicionarTipoServicio(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long id = nextval ();
            long tuplasInsertadas = sqlTipoServicio.adicionarTipoServicio(pm, id, nombre);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new TipoServicio(id, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<TipoServicio> darTiposServicio()
	{
		return sqlTipoServicio.darTipoServicio(pmf.getPersistenceManager());
	}

	public TipoServicio darTipoServicio(long idTipo)
	{
		return sqlTipoServicio.darTipoServicioPorId(pmf.getPersistenceManager(), idTipo);

	}

	public long cambiarEstadoServicio (long estado,long idServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlServicio.cambiarEstado(pm, estado,idServicio);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Usuario adicionarUsuario(String nombre, String tipoDocumento, String numeroDocumento, String correo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            long id = nextval();
            long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, new BigDecimal(id), tipoDocumento, numeroDocumento, nombre, correo);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario(nombre, tipoDocumento, numeroDocumento, correo, new BigDecimal(id));
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Usuario> darUsuarios()
	{
		return sqlUsuario.darUsuarios(pmf.getPersistenceManager());
	}

	public Usuario darUsuario(long idUsuario)
	{
		return sqlUsuario.darUsuarioPorId(pmf.getPersistenceManager(), idUsuario);

	}


	public Convencion adicionarConvencion(String tematica, long numeroParticipantes, Timestamp fechaInicio, Timestamp fechaFin,
			BigDecimal cuenta, String  pazYSalvo, String estado, long idPlanConsumo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = this.nextval();
            long tuplasInsertadas = sqlConvencion.adicionarConvencion(pm, id, idPlanConsumo,estado,pazYSalvo, cuenta, fechaInicio, fechaFin, numeroParticipantes,tematica);
            tx.commit();
            
            log.trace ("Inserción de convencion: " + fechaInicio + fechaFin + ": " + tuplasInsertadas+" tuplas insertadas");
            
            return new Convencion (tematica, numeroParticipantes, fechaInicio, fechaFin, cuenta, pazYSalvo, estado, id, idPlanConsumo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Convencion> darConvenciones ()
	{
		return sqlConvencion.darConvenciones(pmf.getPersistenceManager());
	}

	public Convencion darConvencionPorId (long idConvencion)
	{
		return sqlConvencion.darConvencionPorId (pmf.getPersistenceManager(), idConvencion);
	}


	public Mantenimiento adicionarMantenimiento(String estado, Timestamp fechaInicio, Timestamp fechaFin, String descripcion, long idHabitacion, long idServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = this.nextval();
            long tuplasInsertadas = sqlMantenimiento.adicionarMantenimiento(pm, id, estado, fechaInicio,fechaFin,descripcion,idHabitacion,idServicio);
            tx.commit();
            
            log.trace ("Inserción de mantenimiento: " + descripcion + ": " + tuplasInsertadas+" tuplas insertadas");
            
            return new Mantenimiento (estado, id, fechaInicio, fechaFin, descripcion, idHabitacion, idServicio);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarConvencionPorId (long idConvencion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlConvencion.eliminarConvencionPorId(pm, idConvencion);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public List<Mantenimiento> darMantenimientos ()
	{
		return sqlMantenimiento.darMantenimientos(pmf.getPersistenceManager());
	}

	public Cliente darMantenimientoPorId (long idMantenimiento)
	{
		return sqlCliente.darClientePorId (pmf.getPersistenceManager(), idMantenimiento);
	}

	public PlanConsumoServicio adicionarPlanConsumoServicio(long idPlanConsumo, long idServicio)
	{
			PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long tuplasInsertadas = sqlPlanConsumoServicio.adicionarPlanConsumoServicio(pm, idPlanConsumo, idServicio);
	            tx.commit();

	            log.trace ("Inserción de gustan: [" + idPlanConsumo + ", " + idServicio + "]. " + tuplasInsertadas + " tuplas insertadas");

	            return new PlanConsumoServicio(idPlanConsumo, idServicio);
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return null;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
		
	}
	
	
	public List<PlanConsumoServicio> darPlanConsumoServicio()
	{
		return sqlPlanConsumoServicio.darPlanConsumoServicio(pmf.getPersistenceManager());
	}

	public ConvencionCliente adicionarConvencionCliente(long idConvencion, long idCliente)
	{
			PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long tuplasInsertadas = sqlConvencionCliente.adicionarConvencionCliente(pm, idConvencion, idCliente);
	            tx.commit();

	            log.trace ("Inserción de gustan: [" + idConvencion + ", " + idCliente + "]. " + tuplasInsertadas + " tuplas insertadas");

	            return new ConvencionCliente(idConvencion, idCliente);
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return null;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
		
	}
	
	
	public List<ConvencionCliente> darConvencionCliente ()
	{
		return sqlConvencionCliente.darConvencionCliente(pmf.getPersistenceManager());
	}

	public ConvencionHotel adicionarConvencionHotel(long idConvencion, long idHotel)
	{
			PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long tuplasInsertadas = sqlConvencionHotel.adicionarConvencionHotel(pm, idConvencion, idHotel);
	            tx.commit();

	            log.trace ("Inserción de gustan: [" + idConvencion + ", " + idHotel + "]. " + tuplasInsertadas + " tuplas insertadas");

	            return new ConvencionHotel(idConvencion, idHotel);
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return null;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
		
	}
	
	
	public List<ConvencionHotel> darConvencionHotel ()
	{
		return sqlConvencionHotel.darConvencionHotel(pmf.getPersistenceManager());
	}


//	public void checkin(long idReserva, long idEmpleado, String pazYSalvo, String tipoDocumento, long numeroDocumento, String correo, String nombre)
//	{
//		List<ReservaHabitacion> reservasHab = darReservasHabitaciones();
//		List<Empleado> empleados = darEmpleados();
//		Empleado empleadoActual = null;
//		boolean esRecep = false;
//		boolean hayReserva = false;
//		boolean habitacionesDisp = false;
//
//		long idHabitacion = null;
//		for (int j = 0; j < empleados.size() && !esRecep; j++) 
//		{
//			empleadoActual = empleados.get(j);
//			if(empleadoActual.getTipo().getId() == 1)
//			{
//				esRecep = true; 
//			}
//		}
//
//		for (int i = 0; i < reservasHab.size() && !hayReserva; i++) 
//		{
//			ReservaHabitacion reservaHab = reservasHab.get(i);
//			if(reservaHab.getId() == idReserva)
//			{
//				hayReserva = true; 
//
//			}
//		}
//
//		for (int i = 0; i < darHabitaciones().size(); i++) 
//		{
//			if(darHabitaciones().get(i).getClientes() == null)
//			{
//				habitacionesDisp = true;
//				idHabitacion =  darHabitaciones().get(i).getId();
//			}
//		}
//		if(hayReserva && hayReserva && habitacionesDisp)
//		{
//			adicionarCliente(pazYSalvo, idHabitacion, tipoDocumento, numeroDocumento, nombre, correo);
//		}
//	}
//
//	public void registrarReservaAlojamineto(long idCliente)
//	{
//		for (int i = 0; i < darHabitaciones().size(); i++) 
//		{
//			Habitacion hab = darHabitaciones().get(i);
//
//		}
//	}

	//	public void registrarReservaServicio(int idServicio, String horaInicio, String horaCierre, long duracion, String dia, String lugar, long idCliente)
	//	{
	//		int contador =0;
	//		
	//		for (int i = 0; i <darServicios().size(); i++) 
	//		{
	//			Servicio actual = darServicios().get(i);
	//			if(actual.getTipo().getId() == idServicio)
	//			{
	//				contador++;
	//			}
	//					
	//		}
	//		if(darServicio(idServicio).getReservas().size() < contador)
	//		{
	//			if(darServicio(idServicio).getHoraApertura().equals(horaInicio) && darServicio(idServicio).getHoraCierre().equals(horaCierre))
	//			{
	//				adicionarReservaServicio(horaInicio, duracion, dia, lugar, idCliente, idServicio);
	//			}	
	//		}
	//		
	////		for (int i = 0; i < reservs.size(); i++) 
	////		{
	////			ReservaServicio actual = reservs.get(i);
	////			if(actual.getServicio().getTipo().getId() == idServicio )
	////			{
	////				if()
	////			}
	////		}
	//	}

//
//	public void checkOut(long idCliente)
//	{
//		if(darClientePorId(idCliente).isPazYSalvo() == 'T')
//		{
//			darClientePorId(idCliente).setHabitacion(null);
//		}
//	}

	/**
	 * Este requerimiento permite hacer la reserva del numero de habitaciones y servicios de cada tipo solicitadas por una convencion
	 * @param tiposHabitacion hashtable de los tipos de habitacion con la cantidad que se desea reservas
	 * @param tiposServicio lista de los servicios que se desean reservar
	 * @param tematica tematica de la convencion
	 * @param numeroParticipantes numero de participantes de la convenvion (de los que se sacara el numero requerido de cada servicio)
	 * @param fechaInicio fecha de inicio de la convencion
	 * @param fechaFin fecha final de la convencion
	 * @param cuenta cuenta de la convencion
	 * @param pazYSalvo estado de paz y salvo de la convencion
	 * @param estado estado de la convencion (A de actica, I de inactiva)
	 * @param idPlanConsumo plan de consumo de la convencion
	 * @param idHotel id del hotel donde esta la convencion
	 * El metodo unicamente permite hacer la reserva de los recursos si todos tienen disponibilidad, en caso de que al menos uno no la tenga, no se procesa la transaccion
	 * La base de datod queda en estado de readcommited y el autocommit apagado
	 */
	public void registrarReservaConvenvion(Hashtable<Long, Long> tiposHabitacion, ArrayList<Long> tiposServicio, String tematica, long numeroParticipantes, Timestamp fechaInicio, Timestamp fechaFin, BigDecimal cuenta, String pazYSalvo, String estado, long  idPlanConsumo, long idHotel)
	{
		Convencion convencion= this.adicionarConvencion(tematica, numeroParticipantes, fechaInicio, fechaFin, cuenta, pazYSalvo, estado, idPlanConsumo);
		PersistenceManager pm= pmf.getPersistenceManager();
		boolean esPosible =false;
		ArrayList<Long> idServicios = new ArrayList<Long>();

		Iterator<Long> iter = (Iterator<Long>) tiposHabitacion.keys();
		while(iter.hasNext())
		{
			Long actual = iter.next();
			Long cantidadDeseada = tiposHabitacion.get(actual);

			Query q = pm.newQuery(SQL, "SELECT COUNT(*) FROM " + this.getSqlHabitacion () + "WHERE idTipoHabitacion = ?");
			q.setParameters(actual);
			long cant= (long) q.executeUnique();

			Query q2 = pm.newQuery(SQL, "SELECT COUNT(*) FROM " + this.getSqlReservaHabitacion () + "WHERE (fechaEntrada <=? OR fechaSalida >=?)  AND idTipoHabitacion = ?");
			q2.setParameters(fechaFin,fechaInicio, actual);
			long cantOcupadas= (long) q2.executeUnique();

			if(cant>=cantidadDeseada &&(cant - cantOcupadas >=cantidadDeseada))
			{
				esPosible = true;
			}	

			else
			{
				esPosible = false;
			}

		}

		for(int i =0; i<tiposServicio.size(); i++)
		{
			Long servicio= tiposServicio.get(i);

			Query q3 = pm.newQuery(SQL, "SELECT COUNT(*) FROM " + this.getSqlServicio () + "WHERE idTipoServicio = ? AND capacidad>?");
			q3.setParameters(servicio, numeroParticipantes);
			long cantServiciosCumplen= (long) q3.executeUnique();

			Query q4 = pm.newQuery(SQL, "SELECT DISTINCT id FROM " + this.getSqlServicio () + "WHERE idTipoServicio = ? AND capacidad>?");
			q4.setParameters(servicio, numeroParticipantes);
			idServicios.add((Long) q4.executeUnique());

			Query q5 = pm.newQuery(SQL, "SELECT COUNT(*) FROM " + this.getSqlReservaServicio () + "WHERE dia >=? AND dia<= ? AND idServicio =?");
			q5.setParameters(fechaInicio,fechaFin, (long) q4.executeUnique());
			long cantidadReservas= (long) q5.executeUnique();


//			if(cantServiciosCumplen>0 && sqlServicio.darServicioPorId(pm, (long) q4.executeUnique()).getCapacidad()>cantidadReservas + numeroParticipantes )
//			{
//				esPosible = true;
//			}
//
//			else
//			{
//				esPosible = false;
//			}

		}

		if(esPosible = true) 
		{
			while(iter.hasNext())
			{
				Long actual = iter.next();
				Long cantidad = tiposHabitacion.get(actual);

				for(int i =0; i < cantidad;i++)
				{
					this.adicionarReservaHabitacion(fechaInicio, fechaFin, 1, idHotel, 0, convencion.getId(), actual);
				}

			}

			for(int i =0; i < idServicios.size();i++)
			{

				for(int j = 0; j<numeroParticipantes;j++)
				{
					Servicio servicio = sqlServicio.darServicioPorId(pm, idServicios.get(i));
					this.adicionarReservaServicio(new Timestamp(120), new Long(180), fechaInicio, "" + servicio, new Long(0), idServicios.get(i), convencion.getId());
				}
			}
		}
	}

	/**
	 * Cancela la reserva de tipos de habitacion y de servicios
	 * @param tiposHabitacion hashtable con los tipos de habitacion y la cantidad que se desean cancelar
	 * @param tiposServicio tipos de los servicios que se desean cancelar
	 * @param idConvencion id de la convencion de la cual se quieren cancelar las reservas
	 */
	public void desReservar(Hashtable<TipoHabitacion, Long> tiposHabitacion, ArrayList<TipoServicio> tiposServicio, long idConvencion)
	{
		List<ReservaHabitacion> reservasHabitacion = this.darReservasHabitaciones();
		long numeroPersonas = 0;

		Iterator<TipoHabitacion> iter = (Iterator<TipoHabitacion>) tiposHabitacion.keys();
		while(iter.hasNext())
		{
			TipoHabitacion actual = iter.next();
			long cantidadDeseada = tiposHabitacion.get(actual);
			numeroPersonas += cantidadDeseada;
			
			long cant = 0;

			for(int i = 0; i < reservasHabitacion.size() && cant<=cantidadDeseada ;i++)
			{
				if(reservasHabitacion.get(i).getTipoHabitacion().getId() == (actual.getId()) && reservasHabitacion.get(i).getConvencion().getId() == idConvencion)
				{
					this.eliminarReservaHabitacionPorId(reservasHabitacion.get(i).getId());
					cant ++;
				}
			}

		}
		
		List<ReservaServicio> reservasServicio = this.darReservasServicios();
		
		for(int i =0; i <tiposServicio.size();i++)
		{
			long idTipoServicio = tiposServicio.get(i).getId();
			long cantidadE = 0;
			
			for(int j = 0; j<reservasServicio.size() && cantidadE <= numeroPersonas ; j++)
			{
				if(reservasServicio.get(j).getConvencion().getId() == idConvencion && reservasServicio.get(j).getServicio().getTipo().getId() == idTipoServicio )
				{
					this.eliminarReservaServicioPorId(reservasServicio.get(j).getId());
					cantidadE++;
				}
			}
		}
		
	}
	
	
	/**
	 * Finaliza la convencion, cierra y permite terminar la convenvion si esta esta a paz y salvo con el hotel
	 * @param idConvencion convencion que se quiere finalizar
	 */
	public void finConvencion (long idConvencion)
	{
		Convencion convencion =sqlConvencion.darConvencionPorId(pmf.getPersistenceManager(), idConvencion);
		BigDecimal cero = new BigDecimal(0);
		
		if(convencion.getCuenta() ==cero && convencion.getPazYSalvo() == "T" )
		{
			this.eliminarConvencionPorId(idConvencion);
		}
		
	}
	
	/**
	 * Pone en mantenimiento una lista de habitaciones y servicios
	 * @param habitaciones lista de habitaciones que se quiere poner en mantenimiento
	 * @param servicios lista de servicios a poner en mantenimiento
	 * @param fechaInicio fecha de inicio del mantenimiento
	 * @param fechaFin fecha de fin del mantenimiento
	 * 
	 * Para poner en mantenimiento todas las habitaciones tienen que existir, si alguna no se puede se hace rollback de la aplicacion ya que o se hace una o se hacen todas
	 * 
	 */
	public void ponerEnMantenimiento(List<Habitacion> habitaciones, List<Servicio> servicios, Timestamp fechaInicio, Timestamp fechaFin)
	{
//		PersistenceManager pm = pmf.getPersistenceManager();
//		
//		for(int i =0; i <habitaciones.size();i++)
//		{
//			if(habitaciones.get(i).getEstado() == "D")				
//			{
//				this.adicionarMantenimiento("P", fechaInicio, fechaFin, "Habitacion en mantenimiento", habitaciones.get(i).getId(), 0);
//				habitaciones.get(i).setEstado("M");
//				this.cambiarEstadoHabitacion("M", habitaciones.get(i).getId());
//			}
//			
//			else if(habitaciones.get(i).getEstado() == "O")
//			{
//				this.adicionarMantenimiento("P", fechaInicio, fechaFin, "Habitacion en mantenimiento", habitaciones.get(i).getId(), 0);
//				habitaciones.get(i).setEstado("M");
//				this.cambiarEstadoHabitacion("M", habitaciones.get(i).getId());
//				
//				Query q1 = pm.newQuery(SQL, "SELECT DISTINCT id FROM " + this.getSqlCliente() + "WHERE idHabitacion =?");
//				q1.setParameters(habitaciones.get(i).getId());
//				long idCliente= (long) q1.executeUnique();
//
//				
//				List<Habitacion> habitacionesRevisar = this.darHabitaciones();
//				long idHabitacionNueva = 0;
//				
//				for(int j=0; j<habitacionesRevisar.size();j++)
//				{
//					Habitacion nueva = habitaciones.get(j);
//					String estadoNueva = habitaciones.get(j).getEstado();
//					if(estadoNueva=="D" && nueva.getTipoHabitacion() == (habitaciones.get(i).getTipoHabitacion()))
//					{
//						 idHabitacionNueva = nueva.getId();
//					}
//				}
//				
//				
//				this.cambiarHabitacionCliente(idCliente, idHabitacionNueva);
//				this.moverConsumos(habitaciones.get(i).getId(), idHabitacionNueva, habitaciones.get(i).getConsumoHabitacion().getId());
//			}
//		}
//		
//		for(int i =0; i <servicios.size();i++)
//		{
//			this.cambiarEstadoServicio(1, servicios.get(i).getId());
//			
//			Query q1 = pm.newQuery(SQL, "SELECT id FROM " + this.getSqlReservaServicio() + "WHERE idServicio =? AND dia>= ? AND dia <= ?");
//			q1.setParameters(servicios.get(i).getId(), fechaInicio, fechaFin);
//			List <Long>idsReservaServicios= (List<Long>) q1.executeUnique();
//			
//			for(int j =0; j <idsReservaServicios.size();j++)
//			{
//				long idReservaServicio = idsReservaServicios.get(j);
//				long idTipoServicio = sqlReservaServicio.darReservaServicioPorId(pm, idReservaServicio).getServicio().getTipo().getId();
//				
//				Query q2 = pm.newQuery(SQL, "SELECT DISTINCT id FROM " + this.getSqlServicio() + "WHERE idTipoServicio =? AND estado=0");
//				q2.setParameters(idTipoServicio);
//				long idServicioNuevo= (long) q2.executeUnique();
//				
//				this.cambiarServicioReserva(idServicioNuevo, idReservaServicio);
//				
//			}
//			
//		}
	}

	/**
	 * Finaliza el mantenimiento. Cambia los recursos que entran como parametro a estado disponible para que se puedan volver a reservar
	 * @param habitaciones habitaciones a sacar de mantenimiento
	 * @param servicios servicios a sacar de mantenimiento
	 */
	public void sacarDeMantenimiento(List<Habitacion> habitaciones, List<Servicio> servicios)
	{
		for (int i =0; i<habitaciones.size();i++)
		{
			this.cambiarEstadoHabitacion("D", habitaciones.get(i).getId());
		}
		
//		for (int i =0; i <servicios.size(); i++)
//		{
//			this.cambiarEstadoServicio(0, servicios.get(i).getId());
//		}
	}
	
	/**
	 * Muestra el dinero recolectado por cada habitacion
	 * @param fechaInicio de consulta
	 * @param fechaFin de consulta
	 * @return 
	 */
	public Hashtable<Habitacion, Long> mostrarDineroRecolectado(Timestamp fechaInicio, Timestamp fechaFin)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Hashtable<Habitacion, Long> hs = new Hashtable<Habitacion,Long>();
		
		Query q1 = pm.newQuery(SQL, "SELECT id FROM " + this.getSqlReservaHabitacion() + "WHERE fechaEntrada >=? AND fechaSalida<=?");
		q1.setParameters(fechaInicio,fechaFin);
		List<Long> idReservasDentro= (List<Long>) q1.executeUnique();
		//TODO RESIVAR 
//		
//		for(int i =0; i<idReservasDentro.size();i++)
//		{
//			ReservaHabitacion reserva = sqlReservaHabitacion.darReservaHabitacionPorId(pm, idReservasDentro.get(i));
//			Habitacion habitacion = reserva.getCliente().getHabitacion();
//			
//			Long idConsumoHabitacionClienteDeReserva = habitacion.getConsumoHabitacion().getId();
//			
//			Query q2 = pm.newQuery(SQL, "SELECT valorTotal FROM " + this.getSqlConsumoPorHabitacion() + "WHERE id =?");
//			q2.setParameters(idConsumoHabitacionClienteDeReserva);
//			Long valorTotal= (Long) q2.executeUnique();
//			
//			hs.put(habitacion, valorTotal);
//			
//		}
		
		return hs;
	}
	
	/**
	 * Da los 20 servicios mas populares
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public List<Servicio> serviciosMasPopulares(Timestamp fechaInicio, Timestamp fechaFin)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Servicio> servicios= new ArrayList<Servicio>();
		
		Query q1 = pm.newQuery(SQL, "SELECT idServicio FROM (SELECT idServicio FROM " + this.getSqlReservaServicio() + "WHERE dia >=? AND dia<=? GROUP BY idServicio ORDER BY COUNT(*) DESC) WHERE rownum<=20 ");
		q1.setParameters(fechaInicio,fechaFin);
		List<Long> ids=(List<Long>) q1.executeUnique();
		
		for (int i =0; i<ids.size();i++)
		{
			servicios.add(sqlServicio.darServicioPorId(pm, ids.get(i)));
		}
		
		return servicios;
		
	}
	
	/**
	 * Da los servicios en tre el rango de precio ingresadl
	 * @param min
	 * @param max
	 * @return
	 */
	public List<Servicio> serviciosEntreRangoDePrecio(Long min,Long max)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Servicio> servicios= new ArrayList<Servicio>();
		
		Query q1 = pm.newQuery(SQL, "SELECT id FROM " + this.getSqlServicio() + "WHERE costo >=? AND costo<=?");
		q1.setParameters(min,max);
		List<Long> ids= (List<Long>) q1.executeUnique();
		
		for (int i =0; i<ids.size();i++)
		{
			servicios.add(sqlServicio.darServicioPorId(pm, ids.get(i)));
		}
		
		return servicios;
		
	}
	
	/**
	 * Muestra el consumo de un cliente entre fechas
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idCliente
	 * @return
	 */
	public BigDecimal mostrarConsumoCliente(Timestamp fechaInicio, Timestamp fechaFin, Long idCliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		BigDecimal consumoTotal = new BigDecimal (0);
		
		Query q1 = pm.newQuery(SQL, "SELECT id FROM " + this.getSqlReservaHabitacion() + "WHERE fechaEntreda >=? AND fechaSalida<=? AND idCliente = ?");
		q1.setParameters(fechaInicio,fechaFin,idCliente);
		List<Long> idReservasCliente= (List<Long>) q1.executeUnique();
		
//		for (int i =0; i<idReservasCliente.size();i++)
//		{
//			ReservaHabitacion rh = sqlReservaHabitacion.darReservaHabitacionPorId(pm, idReservasCliente.get(i));
//			consumoTotal.add(rh.getCliente().getHabitacion().getCuenta()) ;	
//		}
		//TODO REVISAR
		
		return consumoTotal;	
	}
	
	/**
	 * Metodo que revisa el consumo de HotelAndes, devolviendo la lista de clientes que han consumido cierto servicio 
	 * @param servicio el servicio que se desea consultar
	 * @param fechaInicio fecha de inicio de la consulta
	 * @param fechaFin fecha final de la consulta
	 * @param criterio el criterio de busqueda (puede ser por cliente, fecha, cantidad de consumo del servicio)
	 * @param criterioOrden el criterio de ordenamiento (ASC o DESC)
	 * @return la lista de clientes que han consumido el servicio
	 */
	public List<Cliente> darClientesHanConsumido(long idServicio, String fechaInicio, String fechaFin, String criterio, String criterioOrden)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try
		{
			tx.begin();
			log.trace("Se quiere obtener los clientes que han utilizado el servicio con id: " + idServicio);
			System.out.println("VE A PERSISTENCIA HOTELERA");
			List<Cliente> registros = sqlCliente.darClientesHanConsumido(pm, idServicio, fechaInicio, fechaFin, criterio, criterioOrden);
			System.out.println("-------------------------------------------------------------------------------");
			tx.commit();
			return registros;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public List<Cliente> darClientesNoHanConsumido(long idServicio, String fechaInicio, String fechaFin, String criterio, String criterioOrden)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try
		{
			tx.begin();
			log.trace("Se quiere obtener los clientes que no han utilizado el servicio con id:" + idServicio);
			List<Cliente> registros = sqlCliente.darClientesNoHanConsumido(pm, idServicio, fechaInicio, fechaFin, criterio, criterioOrden);
			tx.commit();
			return registros;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if(tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public List<String[]> consultarFuncionamientoServicioMasConsumido()
	{
		List<String []> resp = new LinkedList<String []> ();
		long startTime = System.nanoTime();

		List<Object []> tuplas = sqlServicio.consultarFuncionamientoServicioMasConsumido(pmf.getPersistenceManager());
		long endTime = System.nanoTime();

		long duration = (endTime - startTime); 

		//dividir entre 1000000 para que de en milisegundos
		System.out.println(duration/1000000);
		for ( Object [] tupla : tuplas)
		{
			String [] datosResp = new String[5];

			datosResp [0] = tupla [0].toString(); // nombre servicio
			datosResp [1] = tupla [2].toString(); // nombre tipo de servicio
			datosResp [2] = tupla [3].toString(); // consumos
			datosResp [3] = tupla [4].toString(); // semana
			resp.add(datosResp);
		}
		return resp;
	}
	
	public List<String[]> consultarFuncionamientoServicioMenosConsumido()
	{
		List<String []> resp = new LinkedList<String []> ();
		long startTime = System.nanoTime();

		List<Object []> tuplas = sqlServicio.consultarFuncionamientoServicioMenosConsumido(pmf.getPersistenceManager());
		long endTime = System.nanoTime();

		long duration = (endTime - startTime); 

		//dividir entre 1000000 para que de en milisegundos
		System.out.println(duration/1000000);
		for ( Object [] tupla : tuplas)
		{
			String [] datosResp = new String[5];

			datosResp [0] = tupla [0].toString(); // nombre servicio
			datosResp [1] = tupla [2].toString(); // nombre tipo de servicio
			datosResp [2] = tupla [3].toString(); // consumos
			datosResp [3] = tupla [4].toString(); // semana
			resp.add(datosResp);
		}
		return resp;
	}
	
	public List<String[]> consultarFuncionamientoHabitacionMasSolicitada()
	{
		List<String []> resp = new LinkedList<String []> ();
		long startTime = System.nanoTime();

		List<Object []> tuplas = sqlHabitacion.consultarFuncionamientoHabitacionMasSolicitada(pmf.getPersistenceManager());
		long endTime = System.nanoTime();

		long duration = (endTime - startTime); 

		//dividir entre 1000000 para que de en milisegundos
		System.out.println(duration/1000000);
		for ( Object [] tupla : tuplas)
		{
			String [] datosResp = new String[4];

			datosResp [0] = tupla [0].toString(); // nombre tipo habitacion
			datosResp [1] = tupla [1].toString(); // solicitudes
			datosResp [2] = tupla [2].toString(); // semana
			resp.add(datosResp);
		}
		return resp;
	}
	
	public List<String[]> consultarFuncionamientoHabitacionMenosSolicitada()
	{
		List<String []> resp = new LinkedList<String []> ();
		long startTime = System.nanoTime();

		List<Object []> tuplas = sqlHabitacion.consultarFuncionamientoHabitacionMenosSolicitada(pmf.getPersistenceManager());
		long endTime = System.nanoTime();

		long duration = (endTime - startTime); 

		//dividir entre 1000000 para que de en milisegundos
		System.out.println(duration/1000000);
		for ( Object [] tupla : tuplas)
		{
			String [] datosResp = new String[4];

			datosResp [0] = tupla [0].toString(); // nombre tipo habitacion
			datosResp [1] = tupla [1].toString(); // solicitudes
			datosResp [2] = tupla [2].toString(); // semana
			resp.add(datosResp);
		}
		return resp;
	}
	
	public List<String []> buenosClientes1()
	{
		List<String[]> resp = new LinkedList <String[]>();
		List<Object[]> tuplas = sqlCliente.buenosClientes1(pmf.getPersistenceManager());
		
		for(Object [] tupla: tuplas)
		{
			String[] datosResp = new String [5];
			
			datosResp[0] = tupla[0].toString(); //id del cliente	
			datosResp[1] = tupla[1].toString(); // nombre del cliente
			datosResp[2] = tupla[2].toString(); // correo del cliente
			datosResp[3] = tupla[3].toString(); // tipo de documento del cliente
			datosResp[4] = "Por hospedarse en 3 trimestres"; // razon
			resp.add(datosResp);
		}
		
		return resp;
	}
	
	public List<String []> buenosClientes2()
	{
		List<String[]> resp = new LinkedList <String[]>();
		List<Object[]> tuplas = sqlCliente.buenosClientes2(pmf.getPersistenceManager());
		
		for(Object [] tupla: tuplas)
		{
			String[] datosResp = new String [5];
			
			datosResp[0] = tupla[0].toString(); //id del cliente	
			datosResp[1] = tupla[1].toString(); // nombre del cliente
			datosResp[2] = tupla[2].toString(); // correo del cliente
			datosResp[3] = tupla[3].toString(); // tipo de documento del cliente
			datosResp[4] = "Por usar servicios con costo mayores a 300"; // razon
			resp.add(datosResp);
		}
		
		return resp;
	}
	
	public List<String []> buenosClientes3()
	{
		List<String[]> resp = new LinkedList <String[]>();
		List<Object[]> tuplas = sqlCliente.buenosClientes3(pmf.getPersistenceManager());
		
		for(Object [] tupla: tuplas)
		{
			String[] datosResp = new String [5];
			
			datosResp[0] = tupla[0].toString(); //id del cliente	
			datosResp[1] = tupla[1].toString(); // nombre del cliente
			datosResp[2] = tupla[2].toString(); // correo del cliente
			datosResp[3] = tupla[3].toString(); // tipo de documento del cliente
			datosResp[4] = "Por consumo de spa o salones de reuniones por mas de 4 horas"; // razon
			resp.add(datosResp);
		}
		
		return resp;
	}

}
