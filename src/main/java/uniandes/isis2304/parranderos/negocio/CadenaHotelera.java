package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.persistencia.PersistenciaCadenaHotelera;
import uniandes.isis2304.parranderos.test.ConexionTest;




public class CadenaHotelera 
{
	private static Logger log = Logger.getLogger(CadenaHotelera.class.getName());
	
	private PersistenciaCadenaHotelera pha;
	
	public CadenaHotelera()
	{
		pha = PersistenciaCadenaHotelera.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public CadenaHotelera (JsonObject tableConfig)
	{
		pha = PersistenciaCadenaHotelera.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexi�n con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pha.cerrarUnidadPersistencia ();
	}
	/* ****************************************************************
	 * 			M�todos para manejar los clientes
	 *****************************************************************/
	
	public Cliente nuevoCliente(String pazYSalvo, long idHabitacion, String tipoDocumento, String numeroDocumento, String nombre, String correo)
	{
		log.info("Agregando nuevo cliente: " + nombre );
		Cliente nuevoCliente = pha.adicionarCliente(pazYSalvo, idHabitacion, tipoDocumento, numeroDocumento, nombre, correo);
		log.info("Adicionando nuevo cliente" + numeroDocumento);
		return nuevoCliente;
	}
	
	public List<Cliente> darClientes()
	{
		return pha.darClientes();
	}
	
	public Cliente darClientePorId(long idCliente)
	{
		return pha.darClientePorId(idCliente);
	}
	
	//------------------------------------------------------
	//Consumo Habitacion servicio
	//-----------------------------------------------------
	
	public ConsumoHabitacionServicio nuevoConsumoPorHabitacionServicio(long idConsumoHabitacion, long idServicio)
	{
		ConsumoHabitacionServicio nuevoConsumo = pha.adicionarConsumoPorHabitacionServicio(idConsumoHabitacion, idServicio);
		return nuevoConsumo ;
	}
	
	public List<ConsumoHabitacionServicio> darCosnumosHabitacionServicio()
	{
		return pha.darConsumoHabitacionServicio(); 
	}
	
	
	
	//-------------------------------------------------
	//Consunmo Habitacion 
	//------------------------------------------------
	
	public ConsumoHabitacion nuevoConsumoPorHabitacion(Double valorTotal, long idHabitacion)
	{
		return pha.adicionarConsumoHabitacion(valorTotal, idHabitacion);
	}
	
	public List<ConsumoHabitacion> darConsumosHabitacion()
	{
		return pha.darConsumosHabitacion();
	}
	
	public ConsumoHabitacion darConsumoHabitacionPorId(long idConsumoPorHabitacion)
	{
		return pha.darConsumoHabitacionPorId(idConsumoPorHabitacion);
	}
	//--------------------------------
	//Empleado
	//--------------------------------
	
	public Empleado adicionarEmpleado(long idHotel, long idTipoEmpleado, String tipoDocumento, Long numeroDocumento, String nombre, String correo)
	{
//		Empleado nuevoEmpleado = pha.adicionarEmpleado(idHotel, idTipoEmpleado, tipoDocumento, numeroDocumento, nombre, correo);
//		return nuevoEmpleado;
		return null;
	}
	
	public List<Empleado> darEmpleados()
	{
		return pha.darEmpleados();
	}
	
	public Empleado darEmpleadoPorId(long idEmpleado)
	{
		return pha.darEmpleadoPorId(idEmpleado);
	}
	
	//------------------------------------------------
	//Habitacion
	//------------------------------------------------
	public Habitacion adicionarHabitacion(long idHotel, long capacidad, BigDecimal costoPorNoche, BigDecimal cuenta, String numero, long idPlanConsumo, long idConsumoHabitacion, long idTipoHabitacion, String estado)
	{
		Habitacion nuevaHabitacion = pha.adicionarHabitacion(idHotel, capacidad, costoPorNoche, cuenta, numero, idPlanConsumo, idConsumoHabitacion, idTipoHabitacion, estado);
		return nuevaHabitacion;
	}
	
	public List<Habitacion> darHabitaciones()
	{
		return pha.darHabitaciones();
	}
	
	public Habitacion darHabitacionesPorId(long idHabitacion)
	{
		return pha.darHabitacionPorId(idHabitacion);
	}
	
	//--------------------------------------------------
	//Hotel
	//-------------------------------------------------
	
	public Hotel adicionarHotel(String ciudad, String pais, long ofertaHabitacional)
	{
		Hotel nuevoHotel = pha.adicionarHotel(ciudad, pais, ofertaHabitacional);
		return nuevoHotel;
	}
	
	public List<Hotel> darHoteles()
	{
		return pha.darHoteles();
	}
	
	public Hotel darHotelPorId(long idHotel)
	{
		return pha.darHotelPorId(idHotel);
	}
	
	//-----------------------------------------------
	//Plan Consumo
	//-----------------------------------------------
	
	public PlanConsumo adicionarPlanConsumo(long idHotel, String descripcion, List<Servicio> servicios)
	{
		PlanConsumo nuevoPlanConsumo = pha.adicionarPlanConsumo(idHotel, descripcion, null,servicios);
		return nuevoPlanConsumo;
	}
	
	public List<PlanConsumo> darPlanesConsumo()
	{
		return pha.darPlanesConsumo();
	}
	
	public PlanConsumo darPlanCosumoPorId(long idPlanConsumo)
	{
		return pha.darPlanConsumoPorId(idPlanConsumo);
	}
	
	//-----------------------------------------------------
	//Producto
	//-------------------------------------------
	
	public Producto adicionarProducto(String nombre, Double costo)
	{
		Producto nuevoProducto = pha.adicionarProducto(nombre, costo);
		return nuevoProducto;
	}
	
	public List<Producto> darProductos()
	{
		return pha.darProductos();
	}
	
	public Producto darProductoPorId (long idProducto)
	{
		return pha.darProductoPorId(idProducto);
	}
	
	//----------------------------------------------
	//Producto consumo por habitacion
	//----------------------------------------------
	
	public ProductoConsumoPorHabitacion adicionarProcutoConsummidoPorHabitacion(long idProducto, long idConsumoHabitacion)
	{
		ProductoConsumoPorHabitacion nuevoConsumo = pha.adicionarProductoConsumoPorHabitacion(idProducto, idConsumoHabitacion);
		return nuevoConsumo; 
	}
	
	public List<ConsumoHabitacionServicio> darProductoConsumoPorHabitacionServicio()
	{
		return pha.darConsumoHabitacionServicio();
	}
	
	//-------------------------------------------------------
	//Reserva Habitacion
	//-----------------------------------------------------
	public ReservaHabitacion adicionarReservaHabitacion(Timestamp fechaEntrada, Timestamp fechaSalida, long numeroPersonas, long idHotel, long idCliente,long id, long idConvencion, long idTipoHabitacion )
	{
		ReservaHabitacion nuevaReserva = pha.adicionarReservaHabitacion(fechaEntrada, fechaSalida, numeroPersonas, idHotel, idCliente,idConvencion, idTipoHabitacion );
		return nuevaReserva;
	}
	
	public List<ReservaHabitacion> darReservasHabitaciones()
	{
		return pha.darReservasHabitaciones();
	}
	
	
	public ReservaHabitacion darReservaHabitacion(long idReserva)
	{
		return pha.darReservaHabitacion(idReserva);
	}
	
	
	
	//------------------------------------------------------------
	//Reserva Servicio
	//----------------------------------------------------------
	
	public ReservaServicio adicionarReservaServicio(Timestamp horaInicio, long duracion, Timestamp dia, String lugar, long idCliente, long idServicio, long idConvencion)
	{
		ReservaServicio nuevaRservaServ = pha.adicionarReservaServicio(horaInicio, duracion, dia, lugar, idCliente, idServicio, idConvencion);
		return nuevaRservaServ;
	}
	
	public List<ReservaServicio> darReservasServicios()
	{
		return pha.darReservasServicios();
	}
	
	public ReservaServicio darReservaServicioId(long idReserva)
	{
		return pha.darReservaServicio(idReserva);
	}
	
	//----------------------------------------------------------
	//Servicio
	//----------------------------------------------------------
	
	public Servicio adicionarServicio(String nombre, String descripcion, String horaApertura, String horaCierre, BigDecimal capacidad, BigDecimal costo, String costoIncluido, BigDecimal idHotel, BigDecimal idTipoServicio, BigDecimal estado)
	{
		Servicio nuevoSerivcio = pha.adicionarServicio(nombre, descripcion, horaApertura, horaCierre, capacidad, costo, costoIncluido, idHotel, idTipoServicio, estado);
		return  nuevoSerivcio; 
	}
	
	public List<Servicio> darServicios()
	{
		return pha.darServicios();
	}
	
	public Servicio darServicio(long idServicio)
	{
		return pha.darServicio(idServicio);
	
	}
	
	//--------------------------------------------------------
	//Servicio Consumo
	//--------------------------------------------------------
	
	public ServicioConsumo adicionarServicioConsumo(long idServicio, long idProducto)
	{
		ServicioConsumo nuevoConsumo = pha.adicionarServicioConsumo(idServicio, idProducto); 
		return nuevoConsumo;
	}
	
	public List<ServicioConsumo> darServiciosConsumo()
	{
		return pha.darServicioConsumo();
	}
	
	//-----------------------------------------
	//Servicio Producto
	//------------------------------------------
	public ServicioProducto adicionarServicioProducto(long idServicio, long idProducto)
	{
		ServicioProducto nuevoServProduc = pha.adicionarServicioProducto(idServicio, idProducto);
		return nuevoServProduc;
	}
	
	public List<ServicioProducto> darServiciosProducto()
	{
		return pha.darServicioProducto();
	}
	
	//------------------------------------------------
	//TipoEmpleado
	//------------------------------------------------
	
	public TipoEmpleado adicionarTipoEmpleado(String nombre, long id) 
	{
		TipoEmpleado nuevoTipoEmpleado = pha.adicionarTipoEmpleado(nombre);
		return nuevoTipoEmpleado;
	}
	
	//--------------------------------------------
	//Tipo Habitacion
	//-------------------------------------------
	
	public TipoHabitacion adicionarTipoHabitacion(String nombre, long id)
	{
		TipoHabitacion nuevoTipoHabitacion = pha.adicionarTipoHabitacion(nombre);
		return nuevoTipoHabitacion;
	}
	
	public List<TipoHabitacion> darTiposHabitacion()
	{
		return pha.darTiposHabitacion();
	}
	
	public TipoHabitacion darTipoHabitacion(long idTipo)
	{
		return pha.darTipoHabitacion(idTipo);
	}
	
	//......................................................
	//Tipos Servicio
	//-----------------------------------------------------
	
	public TipoServicio adicionarTipoServicio(String nombre, long id)
	{
		TipoServicio nuevoServicio = pha.adicionarTipoServicio(nombre);
		return nuevoServicio;
	}
	
	public List<TipoServicio> darTiposServicio()
	{
		return pha.darTiposServicio();
	}
	
	public TipoServicio darTipoServicio(long idTipo)
	{
		return pha.darTipoServicio(idTipo);
	
	}	
	
	//----------------------------------------------------
	//Usuario
	//----------------------------------------------------
	
	public Usuario adicionarUsuario(String nombre, long id, String tipoDocumento, String numeroDocumento, String correo)
	{
		Usuario nuevoUsuario = pha.adicionarUsuario(nombre, tipoDocumento, numeroDocumento, correo);
		return nuevoUsuario;
	}
	
	public List<Usuario> darUsuarios()
	{
		return pha.darUsuarios();
	}
	
	public Usuario darUsuario(long idUsuario)
	{
		return pha.darUsuario(idUsuario);
	
	}
	
	public Convencion adicionarConvencion(String tematica, int numeroParticipantes, Timestamp fechaInicio, Timestamp fechaFin, BigDecimal cuenta, String pazYSalvo, String estado, int idPlanConsumo )
	{
		Convencion nuevaConvencion = pha.adicionarConvencion(tematica, numeroParticipantes, fechaInicio, fechaFin, cuenta, pazYSalvo, estado, idPlanConsumo);	
		return nuevaConvencion;
	}

	public void registrarConvencion(Hashtable<Long, Long> tiposHabitacion, ArrayList<Long> tiposServicio, String tematica, long numeroParticipantes, Timestamp fechaInicio, Timestamp fechaFin, BigDecimal cuenta, String pazYSalvo, String estado, long  idPlanConsumo, long idHotel)
	{
		pha.registrarReservaConvenvion(tiposHabitacion, tiposServicio, tematica, numeroParticipantes, fechaInicio, fechaFin, cuenta, pazYSalvo, estado, idPlanConsumo, idHotel);
	}
	
	public void adicionarHabitacionConvencion()
	{
		
	}
	
	public Mantenimiento adicionarMantenimiento()
	{
		return null; 
	}
	
	public List<Convencion> darConvenciones()
	{
		return pha.darConvenciones();
	}
	
	//---------------------------------------------------------------
	//Requerimientos de la iteracion 3
	//----------------------------------------------------
	public List<Cliente> darClientesHanConsumido (long idServicio, String fechaInicio, String fechaFin, String criterio, String criterioOrden)
	{
		System.out.println("CADENA HOTELERA");
		return pha.darClientesHanConsumido(idServicio, fechaInicio, fechaFin, criterio, criterioOrden);
		
		
	}
	
	
	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * @return Un arreglo con 7 n�meros que indican el n�mero de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarCadenaHotelera ()
	{
        log.info ("Limpiando la BD de Parranderos");
        long [] borrrados = pha.limpiarCadenaHotelera();	
        log.info ("Limpiando la BD de Parranderos: Listo!");
        return borrrados;
	}
}


