package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.persistencia.PersistenciaCadenaHotelera;




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
	
	public Cliente nuevoCliente(char pazYSalvo, Integer idHabitacion, String tipoDocumento, Long numeroDocumento, String nombre, String correo)
	{
		log.info("Agregando nuevo cliente: " + nombre );
		Cliente nuevoCliente = pha.adicionarCliente(pazYSalvo, idHabitacion, tipoDocumento, numeroDocumento, nombre, correo);
		log.info("Adicionando nuevo cliente" + numeroDocumento);
		return nuevoCliente;
	}
	
	public ArrayList<Cliente> darClientes()
	{
		return pha.darClientes();
	}
	
	public Cliente darClientePorId(Integer idCliente)
	{
		return pha.darClientePorId(idCliente);
	}
	
	//------------------------------------------------------
	//Consumo Habitacion servicio
	//-----------------------------------------------------
	
	public ConsumoHabitacionServicio nuevoConsumoPorHabitacionServicio(Integer idConsumoHabitacion, Integer idServicio)
	{
		ConsumoHabitacionServicio nuevoConsumo = pha.adicionarConsumoPorHabitacionServicio(idConsumoHabitacion, idServicio);
		return nuevoConsumo ;
	}
	
	public ArrayList<ConsumoHabitacionServicio> darCosnumosHabitacionServicio()
	{
		return pha.darConsumoHabitacionServicio();
	}
	
	
	//-------------------------------------------------
	//Consunmo Habitacion 
	//------------------------------------------------
	
	public ConsumoHabitacion nuevoConsumoPorHabitacion(Double valorTotal, Integer idHabitacion)
	{
		return pha.adicionarConsumoHabitacion(valorTotal, idHabitacion);
	}
	
	public ArrayList<ConsumoHabitacion> darConsumosHabitacion()
	{
		return pha.darConsumosHabitacion();
	}
	
	public ConsumoHabitacion darConsumoHabitacionPorId(Integer idConsumoPorHabitacion)
	{
		return pha.darConsumoHabitacionPorId(idConsumoPorHabitacion);
	}
	//--------------------------------
	//Empleado
	//--------------------------------
	
	public Empleado adicionarEmpleado(Integer idHotel, Integer idTipoEmpleado, String tipoDocumento, Long numeroDocumento, String nombre, String correo)
	{
		Empleado nuevoEmpleado = pha.adicionarEmpleado(idHotel, idTipoEmpleado, tipoDocumento, numeroDocumento, nombre, correo);
		return nuevoEmpleado;
	}
	
	public ArrayList<Empleado> darEmpleados()
	{
		return pha.darEmpleados();
	}
	
	public Empleado darEmpleadoPorId(Integer idEmpleado)
	{
		return pha.darEmpleadoPorId(idEmpleado);
	}
	
	//------------------------------------------------
	//Habitacion
	//------------------------------------------------
	public Habitacion adicionarHabitacion(Integer idHotel, Integer capacidad, Double costoPorNoche, Double cuenta, String numero, Integer idPlanConsumo, Integer idConsumoHabitacion, Integer idTipoHabitacion)
	{
		Habitacion nuevaHabitacion = pha.adicionarHabitacion(idHotel, capacidad, costoPorNoche, cuenta, numero, idPlanConsumo, idConsumoHabitacion, idTipoHabitacion);
		return nuevaHabitacion;
	}
	
	public ArrayList<Habitacion> darHabitaciones()
	{
		return pha.darHabitaciones();
	}
	
	public Habitacion darHabitacionesPorId(Integer idHabitacion)
	{
		return pha.darHabitacionPorId(idHabitacion);
	}
	
	//--------------------------------------------------
	//Hotel
	//-------------------------------------------------
	
	public Hotel adicionarHotel(String ciudad, String pais, Integer ofertaHabitacional)
	{
		Hotel nuevoHotel = pha.adicionarHotel(ciudad, pais, ofertaHabitacional);
		return nuevoHotel;
	}
	
	public ArrayList<Hotel> darHoteles()
	{
		return pha.darHoteles();
	}
	
	public Hotel darHotelPorId(Integer idHotel)
	{
		return pha.darHotelPorId(idHotel);
	}
	
	//-----------------------------------------------
	//Plan Consumo
	//-----------------------------------------------
	
	public PlanConsumo adicionarPlanConsumo(Integer idHotel, String descripcion)
	{
		PlanConsumo nuevoPlanConsumo = pha.adicionarPlanConsumo(idHotel, descripcion);
		return nuevoPlanConsumo;
	}
	
	public ArrayList<PlanConsumo> darPlanesConsumo()
	{
		return pha.darPlanesConsumo();
	}
	
	public PlanConsumo darPlanCosumoPorId(Integer idPlanConsumo)
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
	
	public ArrayList<Producto> darProductos()
	{
		return pha.darProductos();
	}
	
	public Producto darProductoPorId (Integer idProducto)
	{
		return pha.darProductoPorId(idProducto);
	}
	
	//----------------------------------------------
	//Producto consumo por habitacion
	//----------------------------------------------
	
	public ProductoConsumoPorHabitacion adicionarProcutoConsummidoPorHabitacion(Integer idProducto, Integer idConsumoHabitacion)
	{
		ProductoConsumoPorHabitacion nuevoConsumo = pha.adicionarProductoConsumoPorHabitacion(idProducto, idConsumoHabitacion);
		return nuevoConsumo; 
	}
	
	public ArrayList<ConsumoHabitacionServicio> darProductoConsumoPorHabitacionServicio()
	{
		return pha.darConsumoHabitacionServicio();
	}
	
	//-------------------------------------------------------
	//Reserva Habitacion
	//-----------------------------------------------------
	public ReservaHabitacion adicionarReservaHabitacion(String fechaEntrada, String fechaSalida, Integer numeroPersonas, Integer idHotel, Integer idCliente)
	{
		ReservaHabitacion nuevaReserva = pha.adicionarReservaHabitacion(fechaEntrada, fechaSalida, numeroPersonas, idHotel, idCliente);
		return nuevaReserva;
	}
	
	public ArrayList<ReservaHabitacion> darReservasHabitaciones()
	{
		return pha.darReservasHabitaciones();
	}
	
	
	public ReservaHabitacion darReservaHabitacion(Integer idReserva)
	{
		return pha.darReservaHabitacion(idReserva);
	}
	
	//------------------------------------------------------------
	//Reserva Servicio
	//----------------------------------------------------------
	
	public ReservaServicio adicionarReservaServicio(String horaInicio, Integer duracion, String dia, String lugar, Integer idCliente, Integer idServicio)
	{
		ReservaServicio nuevaRservaServ = pha.adicionarReservaServicio(horaInicio, duracion, dia, lugar, idCliente, idServicio);
		return nuevaRservaServ;
	}
	
	public ArrayList<ReservaServicio> darReservasServicios()
	{
		return pha.darReservasServicios();
	}
	
	public ReservaServicio darReservaServicioId(Integer idReserva)
	{
		return pha.darReservaServicio(idReserva);
	}
	
	//----------------------------------------------------------
	//Servicio
	//----------------------------------------------------------
	
	public Servicio adicionarServicio(String nombre, String descripcion, String horaApertura, String horaCierre, Integer capacidad, Double costo, char costoIncluido, Integer idHotel, Integer idTipoServicio)
	{
		Servicio nuevoSerivcio = pha.adicionarServicio(nombre, descripcion, horaApertura, horaCierre, capacidad, costo, costoIncluido, idHotel, idTipoServicio);
		return  nuevoSerivcio; 
	}
	
	public ArrayList<Servicio> darServicios()
	{
		return pha.darServicios();
	}
	
	public Servicio darServicio(Integer idServicio)
	{
		return pha.darServicio(idServicio);
	
	}
	
	//--------------------------------------------------------
	//Servicio Consumo
	//--------------------------------------------------------
	
	public ServicioConsumo adicionarServicioConsumo(Integer idServicio, Integer idProducto)
	{
		ServicioConsumo nuevoConsumo = pha.adicionarServicioConsumo(idServicio, idProducto); 
		return nuevoConsumo;
	}
	
	public ArrayList<ServicioConsumo> darServiciosConsumo()
	{
		return pha.darServicioConsumo();
	}
	
	//-----------------------------------------
	//Servicio Producto
	//------------------------------------------
	public ServicioProducto adicionarServicioProducto(Integer idServicio, Integer idProducto)
	{
		ServicioProducto nuevoServProduc = pha.adicionarServicioProducto(idServicio, idProducto);
		return nuevoServProduc;
	}
	
	public ArrayList<ServicioProducto> darServiciosProducto()
	{
		return pha.darServicioProducto();
	}
	
	//------------------------------------------------
	//TipoEmpleado
	//------------------------------------------------
	
	public TipoEmpleado adicionarTipoEmpleado(String nombre, Integer id) 
	{
		TipoEmpleado nuevoTipoEmpleado = pha.adicionarTipoEmpleado(nombre);
		return nuevoTipoEmpleado;
	}
	
	//--------------------------------------------
	//Tipo Habitacion
	//-------------------------------------------
	
	public TipoHabitacion adicionarTipoHabitacion(String nombre, Integer id)
	{
		TipoHabitacion nuevoTipoHabitacion = pha.adicionarTipoHabitacion(nombre);
		return nuevoTipoHabitacion;
	}
	
	public ArrayList<TipoHabitacion> darTiposHabitacion()
	{
		return pha.darTiposHabitacion();
	}
	
	public TipoHabitacion darTipoHabitacion(Integer idTipo)
	{
		return pha.darTipoHabitacion(idTipo);
	}
	
	//......................................................
	//Tipos Servicio
	//-----------------------------------------------------
	
	public TipoServicio adicionarTipoServicio(String nombre, Integer id)
	{
		TipoServicio nuevoServicio = pha.adicionarTipoServicio(nombre);
		return nuevoServicio;
	}
	
	public ArrayList<TipoServicio> darTiposServicio()
	{
		return pha.darTiposServicio();
	}
	
	public TipoServicio darTipoServicio(Integer idTipo)
	{
		return pha.darTipoServicio(idTipo);
	
	}	
	
	//----------------------------------------------------
	//Usuario
	//----------------------------------------------------
	
	public Usuario adicionarUsuario(String nombre, Integer id, String tipoDocumento, Long numeroDocumento, String correo)
	{
		Usuario nuevoUsuario = pha.adicionarUsuario(nombre, tipoDocumento, numeroDocumento, correo);
		return nuevoUsuario;
	}
	
	public ArrayList<Usuario> darUsuarios()
	{
		return pha.darUsuarios();
	}
	
	public Usuario darUsuario(Integer idUsuario)
	{
		return pha.darUsuario(idUsuario);
	
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


