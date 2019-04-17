package uniandes.isis2304.parranderos.persistencia;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

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
		tablas.add("CadenaHotelera_sequence");
		tablas.add("CLIENTE");
		tablas.add("CONSUMOPORHABITACION");
		tablas.add("EMPLEADO");
		tablas.add("HABITACION");
		tablas.add("HOTEL");
		tablas.add("PLANCONSUMO");
		tablas.add("PRODUCTO");
		tablas.add("PRODUCTOCONSUMOPORHABITACION");
		tablas.add("RESERVASERVICIO	");
		tablas.add("RESERVAHABITACION");
		tablas.add("SERVICIO");
		tablas.add("SERVICIOCONSUMO");
		tablas.add("SERVICIOPRODUCTO");
		tablas.add("USUARIO");
		tablas.add("CONSUMOHABITACIOSERVICIO");
		tablas.add("TIPOEMPLEADO");
		tablas.add("TIPOSERVICIO");
		tablas.add("TIPOHABITACION");
		tablas.add("CONVENCION");
		tablas.add("MANTENIMIENTO");
		tablas.add("CONVENCIONCLIENTE");
		tablas.add("CONVENCIONHOTEL");
		tablas.add("PLANCONSUMOSERVICIO");

		
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
		return tablas.get(3);
	}

	public String getSqlHabitacion() {
		return tablas.get(4);
	}

	public String getSqlHotel() {
		return tablas.get(5);
	}

	public String getSqlPlanConsumo() {
		return tablas.get(6);
	}

	public String getSqlProducto() {
		return tablas.get(7);
	}

	public String getSqlProductoConsumoPorHabitacion() {
		return tablas.get(8);
	}

	public String getSqlReservaServicio() {
		return tablas.get(9);
	}

	public String getSqlReservaHabitacion() {
		return tablas.get(10);
	}

	public String getSqlServicio() {
		return tablas.get(11);
	}

	public String getSqlServicioConsumo() {
		return tablas.get(12);
	}
	
	public String getSqlServicioProducto()
	{
		return tablas.get(13);
	}

	public String getSqlUsuario() {
		return tablas.get(14);
	}

	public String getSqlUtil() {
		return tablas.get(15);
	}
	
	public String getSqlConsumoHabitacionServicio()
	{
		return tablas.get(16);
	}
	
	public String getSqlTipoEmpleado()
	{
		return tablas.get(17);
	}

	
	public String getSqlTipoServicio()
	{
		return tablas.get(18);
	}
	
	public String getSqlTipoHabitacion()
	{
		return tablas.get(19);
	}
	
	public String getSqlConvencion()
	{
		return tablas.get(20);
	}
	
	public String getSqlMantenimiento()
	{
		return tablas.get(21);
	}
	
	public String getSqlConvencionCliente()
	{
		return tablas.get(22);
	}
	
	public String getSqlConvencionHotel()
	{
		return tablas.get(23);
	}
	
	public String getSqlPlanConsumoServicio()
	{
		return tablas.get(24);
	}

	private Integer nextval ()
	{
        Integer resp = sqlUtil.nextval (pmf.getPersistenceManager());
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
	public Cliente adicionarCliente(char pazYSalvo, Integer idHabitacion, String tipoDocumento, Long numeroDocumento, String nombre, String correo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            Integer id = this.nextval();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, id, pazYSalvo, idHabitacion);
            long tuplasInsertadasUsu = sqlUsuario.adicionarUsuario(pm, id, tipoDocumento, numeroDocumento, nombre, correo);
            tx.commit();
            
            log.trace ("Inserción de cliente: " + nombre + ": " + tuplasInsertadas + tuplasInsertadasUsu +" tuplas insertadas");
            
            return new Cliente (id, pazYSalvo,null, sqlHabitacion.darHabitacionPorId(pm, idHabitacion),null,  null, nombre, tipoDocumento, numeroDocumento, correo,null);
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
	
	public ArrayList<Cliente> darClientes ()
	{
		return sqlCliente.darClientes(pmf.getPersistenceManager());
	}
	
	public Cliente darClientePorId (Integer idCliente)
	{
		return sqlCliente.darClientePorId (pmf.getPersistenceManager(), idCliente);
	}
 
	public ConsumoHabitacionServicio adicionarConsumoPorHabitacionServicio(Integer idConsumoHabitacion, Integer idServicio)
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
	
	
	public ArrayList<ConsumoHabitacionServicio> darConsumoHabitacionServicio ()
	{
		return sqlConsumoHabitacioServicio.darConsumoHabitacionServicio(pmf.getPersistenceManager());
	}
	
	public ConsumoHabitacion adicionarConsumoHabitacion(Double valorTotal, Integer idHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            Integer id = nextval();
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
	
	public ArrayList<ConsumoHabitacion> darConsumosHabitacion ()
	{
		return sqlConsumoPorHabitacion.darConsumosHabitacion(pmf.getPersistenceManager());
	}
	
	public ConsumoHabitacion darConsumoHabitacionPorId (Integer idConsumoPorHabitacion)
	{
		return sqlConsumoPorHabitacion.darConsumoHabitacionPorId(pmf.getPersistenceManager(), idConsumoPorHabitacion);
	}
	
	public Empleado adicionarEmpleado(Integer idHotel, Integer idTipoEmpleado, String tipoDocumento, Long numeroDocumento, String nombre, String correo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            Integer id = this.nextval();
            long tuplasInsertadas = sqlEmpleado.adicionarEmpleado(pm, id, idHotel, idTipoEmpleado);
            long tuplasInsertadasUsu = sqlUsuario.adicionarUsuario(pm, id, tipoDocumento, numeroDocumento, nombre, correo);
            tx.commit();
            
            log.trace ("Inserción bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Empleado(sqlTipoEmpleado.darTipoEmpleadoPorId(pm, idTipoEmpleado), sqlHotel.darHotelPorId(pm, idHotel), nombre, tipoDocumento, numeroDocumento, correo, id);
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
	
	public ArrayList<Empleado> darEmpleados ()
	{
		return sqlEmpleado.darEmpleados(pmf.getPersistenceManager());
	}
	
	public Empleado darEmpleadoPorId (Integer idEmpleado)
	{
		return sqlEmpleado.darEmpleadoPorId (pmf.getPersistenceManager(), idEmpleado);
	}
	
	public Habitacion adicionarHabitacion(Integer idHotel, Integer capacidad, Double costoPorNoche, Double cuenta, String numero, Integer idPlanConsumo, Integer idConsumoHabitacion, Integer idTipoHabitacion, char estado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            Integer id = nextval();
            long tuplasInsertadas = sqlHabitacion.adicionarHabitacion(pm, id, capacidad, costoPorNoche, cuenta, numero, idHotel, idConsumoHabitacion, idTipoHabitacion, idPlanConsumo, estado);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Habitacion(capacidad, costoPorNoche, null, cuenta, sqlHotel.darHotelPorId(pm, idHotel), null, sqlConsumoPorHabitacion.darConsumoHabitacionPorId(pm, idConsumoHabitacion), sqlPlanConsumo.darPlanConsumoPorId(pm, idPlanConsumo), id, sqlTipoHabitacion.darTipoHabitacionPorId(pm, idTipoHabitacion),estado);
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
	
	public ArrayList<Habitacion> darHabitaciones ()
	{
		return sqlHabitacion.darHabitaciones(pmf.getPersistenceManager());
	}
	
	public Habitacion darHabitacionPorId (Integer idHabitacion)
	{
		return sqlHabitacion.darHabitacionPorId(pmf.getPersistenceManager(), idHabitacion);
	}
	public long cambiarHabitacionAMantenimiento (Integer idHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacion.cambiarEstadoAMantenimiento(pm, idHabitacion);
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
	 
	
	public Hotel adicionarHotel(String ciudad, String pais, Integer ofertaHabitacional) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            Integer id = nextval ();
            long tuplasInsertadas = sqlHotel.adicionarHotel(pm, id, pais, ciudad, ofertaHabitacional);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Hotel(id, pais, ciudad, ofertaHabitacional, null, null, null, null, null, null, null);
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
	
	public ArrayList<Hotel> darHoteles()
	{
		return sqlHotel.darHoteles(pmf.getPersistenceManager());
	}
	
	public Hotel darHotelPorId (Integer idHotel)
	{
		return sqlHotel.darHotelPorId(pmf.getPersistenceManager(), idHotel);
	}
	
	public PlanConsumo adicionarPlanConsumo(Integer idHotel, String descripcion,ArrayList<Convencion> convenciones, ArrayList<Servicio> servicios) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            Integer id = nextval ();
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
	
	public ArrayList<PlanConsumo> darPlanesConsumo()
	{
		return sqlPlanConsumo.darPlanesConsumo(pmf.getPersistenceManager());
	}
	
	public PlanConsumo darPlanConsumoPorId (Integer idPlanConsumo)
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

            Integer id = nextval ();
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
	
	public ArrayList<Producto> darProductos()
	{
		return sqlProducto.darProductos(pmf.getPersistenceManager());
	}
	
	public Producto darProductoPorId (Integer idProducto)
	{
		return sqlProducto.darProductoPorId(pmf.getPersistenceManager(), idProducto);
	}
	
	public ProductoConsumoPorHabitacion adicionarProductoConsumoPorHabitacion(Integer idProducto, Integer idConsumoHabitacion)
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
	
	
	public ArrayList<ConsumoHabitacionServicio> darProductoConsumoPorHabitacion ()
	{
		return sqlConsumoHabitacioServicio.darConsumoHabitacionServicio(pmf.getPersistenceManager());
	}
	
	public ReservaHabitacion adicionarReservaHabitacion(Date fechaEntrada, Date fechaSalida, Integer numeroPersonas, Integer idHotel, Integer idCliente, Integer idConvencion, Integer idTipoHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            Integer id = nextval ();
            long tuplasInsertadas = sqlReservaHabitacion.adicionarReservaHabitacion(pm, id, fechaEntrada, fechaSalida, numeroPersonas, idHotel, idCliente, idConvencion, idTipoHabitacion);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ReservaHabitacion(fechaEntrada, fechaSalida, numeroPersonas, sqlHotel.darHotelPorId(pm, idHotel), sqlCliente.darClientePorId(pm, idCliente),id, sqlConvencion.darConvencionPorId(pm, idConvencion), sqlTipoHabitacion.darTipoHabitacionPorId(pm, idTipoHabitacion));
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
	
	public long eliminarReservaHabitacionPorIdConvencion (Integer idConvencion) 
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
	
	public long eliminarReservaHabitacionPorId(Integer idReserva) 
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
	
	public long eliminarReservaServicioPorIdConvencion (Integer idConvencion) 
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
	
	public long eliminarReservaServicioPorId(Integer idReserva) 
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
	
	
	public ArrayList<ReservaHabitacion> darReservasHabitaciones()
	{
		return sqlReservaHabitacion.darReservasHabitaciones(pmf.getPersistenceManager());
	}
	
	public ReservaHabitacion darReservaHabitacion(Integer idReserva)
	{
		return sqlReservaHabitacion.darReservaHabitacionPorId(pmf.getPersistenceManager(), idReserva);
	
	}
	
	public ReservaServicio adicionarReservaServicio(String horaInicio, Integer duracion, String dia, String lugar, Integer idCliente, Integer idServicio, Integer idConvencion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            Integer id = nextval ();
            long tuplasInsertadas = sqlReservaServicio.adicionarReserva(pm, id, horaInicio, duracion, dia, lugar, idCliente, idServicio, idConvencion);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ReservaServicio(horaInicio, duracion, dia, id, lugar, sqlCliente.darClientePorId(pm, idCliente), sqlServicio.darServicioPorId(pm, idServicio), sqlConvencion.darConvencionPorId(pm, idConvencion));
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
	
	public ArrayList<ReservaServicio> darReservasServicios()
	{
		return sqlReservaServicio.darReservasServicios(pmf.getPersistenceManager());
	}
	
	public ReservaServicio darReservaServicio(Integer idReserva)
	{
		return sqlReservaServicio.darReservaServicioPorId(pmf.getPersistenceManager(), idReserva);
	
	}
	
	public Servicio adicionarServicio(String nombre, String descripcion, String horaApertura, String horaCierre, Integer capacidad, Double costo, char costoIncluido, Integer idHotel, Integer idTipoServicio, Integer estado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            Integer id = nextval ();
            long tuplasInsertadas = sqlServicio.adicionarServicio(pm, id, nombre, descripcion, horaApertura, horaCierre, capacidad, costo, costoIncluido, idHotel, idTipoServicio, estado);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Servicio(id, nombre, descripcion, horaApertura, horaCierre, capacidad, costo, sqlTipoServicio.darTipoServicioPorId(pm, idTipoServicio), costoIncluido, null, sqlHotel.darHotelPorId(pm, idHotel), null, null,null,null,estado);
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
	
	public ArrayList<Servicio> darServicios()
	{
		return sqlServicio.darServicios(pmf.getPersistenceManager());
	}
	
	public Servicio darServicio(Integer idServicio)
	{
		return sqlServicio.darServicioPorId(pmf.getPersistenceManager(), idServicio);
	
	}
	
	public ServicioConsumo adicionarServicioConsumo(Integer idServicio, Integer idProducto)
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
	
	
	public ArrayList<ServicioConsumo> darServicioConsumo ()
	{
		return sqlServicioConsumo.darServicioConsumo(pmf.getPersistenceManager());
	}
	
	public ServicioProducto adicionarServicioProducto(Integer idServicio, Integer idProducto)
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
	
	
	public ArrayList<ServicioProducto> darServicioProducto ()
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

            Integer id = nextval ();
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
	
	public ArrayList<TipoEmpleado> darTiposEmpleado()
	{
		return sqlTipoEmpleado.darTipoEmpleado(pmf.getPersistenceManager());
	}
	
	public TipoEmpleado darTipoEmpleado(Integer idTipo)
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

            Integer id = nextval ();
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
	
	public ArrayList<TipoHabitacion> darTiposHabitacion()
	{
		return sqlTipoHabitacion.darTipoHabitacion(pmf.getPersistenceManager());
	}
	
	public TipoHabitacion darTipoHabitacion(Integer idTipo)
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

            Integer id = nextval ();
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
	
	public ArrayList<TipoServicio> darTiposServicio()
	{
		return sqlTipoServicio.darTipoServicio(pmf.getPersistenceManager());
	}
	
	public TipoServicio darTipoServicio(Integer idTipo)
	{
		return sqlTipoServicio.darTipoServicioPorId(pmf.getPersistenceManager(), idTipo);
	
	}
	
	public long cambiarServicioAMantenimiento (Integer idServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.cambiarEstadoAMantenimiento(pm, idServicio);
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
	
	public Usuario adicionarUsuario(String nombre, String tipoDocumento, Long numeroDocumento, String correo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();

            Integer id = nextval();
            long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, id, tipoDocumento, numeroDocumento, nombre, correo);
            tx.commit();

            log.trace ("Inserción de consumo: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario(nombre, tipoDocumento, numeroDocumento, correo, id);
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
	
	public ArrayList<Usuario> darUsuarios()
	{
		return sqlUsuario.darUsuarios(pmf.getPersistenceManager());
	}
	
	public Usuario darUsuario(Integer idUsuario)
	{
		return sqlUsuario.darUsuarioPorId(pmf.getPersistenceManager(), idUsuario);
	
	}
	
	
	public Convencion adicionarConvencion(String tematica, Integer numeroParticipantes, Date fechaInicio, Date fechaFin,
			double cuenta, char pazYSalvo, char estado, Integer idPlanConsumo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            Integer id = this.nextval();
            long tuplasInsertadas = sqlConvencion.adicionarConvencion(pm, id, idPlanConsumo,estado,pazYSalvo, cuenta, fechaInicio, fechaFin, numeroParticipantes,tematica);
            tx.commit();
            
            log.trace ("Inserción de convencion: " + fechaInicio + fechaFin + ": " + tuplasInsertadas+" tuplas insertadas");
            
            return new Convencion (id,tematica ,numeroParticipantes,fechaInicio,fechaFin,cuenta,pazYSalvo, estado, null, null, null, sqlPlanConsumo.darPlanConsumoPorId(pm, idPlanConsumo) ,null);
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
	
	public ArrayList<Convencion> darConvenciones ()
	{
		return sqlConvencion.darConvenciones(pmf.getPersistenceManager());
	}
	
	public Convencion darConvencionPorId (Integer idConvencion)
	{
		return sqlConvencion.darConvencionPorId (pmf.getPersistenceManager(), idConvencion);
	}
 
	
	public Mantenimiento adicionarMantenimiento(char estado, Date fechaInicio, Date fechaFin, String descripcion, Integer idHabitacion, Integer idServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            Integer id = this.nextval();
            long tuplasInsertadas = sqlMantenimiento.adicionarMantenimiento(pm, id, estado, fechaInicio,fechaFin,descripcion,idHabitacion,idServicio);
            tx.commit();
            
            log.trace ("Inserción de mantenimiento: " + descripcion + ": " + tuplasInsertadas+" tuplas insertadas");
            
            return new Mantenimiento (id, estado, fechaInicio,fechaFin,descripcion,sqlHabitacion.darHabitacionPorId(pm, idHabitacion),sqlServicio.darServicioPorId(pm, idServicio));
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
	
	public ArrayList<Mantenimiento> darMantenimientos ()
	{
		return sqlMantenimiento.darMantenimientos(pmf.getPersistenceManager());
	}
	
	public Cliente darMantenimientoPorId (Integer idMantenimiento)
	{
		return sqlCliente.darClientePorId (pmf.getPersistenceManager(), idMantenimiento);
	}
 
	public PlanConsumoServicio adicionarPlanConsumoServicio(Integer idPlanConsumo, Integer idServicio)
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
	
	
	public ArrayList<PlanConsumoServicio> darPlanConsumoServicio()
	{
		return sqlPlanConsumoServicio.darPlanConsumoServicio(pmf.getPersistenceManager());
	}
	
	public ConvencionCliente adicionarConvencionCliente(Integer idConvencion, Integer idCliente)
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
	
	
	public ArrayList<ConvencionCliente> darConvencionCliente ()
	{
		return sqlConvencionCliente.darConvencionCliente(pmf.getPersistenceManager());
	}
	
	public ConvencionHotel adicionarConvencionHotel(Integer idConvencion, Integer idHotel)
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
	
	
	public ArrayList<ConvencionHotel> darConvencionHotel ()
	{
		return sqlConvencionHotel.darConvencionHotel(pmf.getPersistenceManager());
	}
	

	public void checkin(Integer idReserva, Integer idEmpleado, char pazYSalvo, String tipoDocumento, long numeroDocumento, String correo, String nombre)
	{
		ArrayList<ReservaHabitacion> reservasHab = darReservasHabitaciones();
		ArrayList<Empleado> empleados = darEmpleados();
		Empleado empleadoActual = null;
		boolean esRecep = false;
		boolean hayReserva = false;
		boolean habitacionesDisp = false;
		
		Integer idHabitacion = null;
		for (int j = 0; j < empleados.size() && !esRecep; j++) 
		{
			empleadoActual = empleados.get(j);
			if(empleadoActual.getTipo().getId() == 1)
			{
				esRecep = true;
			}
		}
			
		for (int i = 0; i < reservasHab.size() && !hayReserva; i++) 
		{
			ReservaHabitacion reservaHab = reservasHab.get(i);
			if(reservaHab.getId() == idReserva)
			{
				hayReserva = true; 
				
			}
		}
		
		for (int i = 0; i < darHabitaciones().size(); i++) 
		{
			if(darHabitaciones().get(i).getClientes() == null)
			{
				habitacionesDisp = true;
				idHabitacion =  darHabitaciones().get(i).getId();
			}
		}
		if(hayReserva && hayReserva && habitacionesDisp)
		{
			adicionarCliente(pazYSalvo, idHabitacion, tipoDocumento, numeroDocumento, nombre, correo);
		}
	}
	
	public void registrarReservaAlojamineto(Integer idCliente)
	{
		for (int i = 0; i < darHabitaciones().size(); i++) 
		{
			Habitacion hab = darHabitaciones().get(i);
			
		}
	}
	
//	public void registrarReservaServicio(int idServicio, String horaInicio, String horaCierre, Integer duracion, String dia, String lugar, Integer idCliente)
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
	
	
	public void checkOut(Integer idCliente)
	{
		if(darClientePorId(idCliente).isPazYSalvo() == 'T')
		{
			darClientePorId(idCliente).setHabitacion(null);
		}
	}
}
