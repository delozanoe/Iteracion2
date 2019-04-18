package uniandes.isis2304.parranderos.negocio;


import java.sql.Timestamp;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class ReservaHabitacion
{
	private long id;
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Timestamp fechaEntrada;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Timestamp fechaSalida;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private long numeroPersonas;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Hotel hotel;
	
	private long idHotel, idCliente, idConvencion, idTipoHabitacion;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Cliente cliente;

	private Convencion convencion;
	
	private TipoHabitacion tipoHabitacion;
	
	public ReservaHabitacion() throws Exception 
	{
		super();
		this.id= 0;
		this.fechaEntrada = new Timestamp(0);
		this.fechaSalida = new Timestamp(0);
		this.numeroPersonas = 0;
		this.idHotel = 0;
		this.idCliente = 0;
		this.idConvencion = 0;
		this.idTipoHabitacion = 0;

	}
	

	public ReservaHabitacion(long id, Timestamp fechaEntrada, Timestamp fechaSalida, long numeroPersonas, long idHotel, long idCliente, long idConvencion, long idTipoHabitacion) throws Exception {
		super();
		this.id= id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.numeroPersonas = numeroPersonas;
		this.idHotel = idHotel;
		this.idCliente = idCliente;
		this.idConvencion = idConvencion;
		this.idTipoHabitacion = idTipoHabitacion;

	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Timestamp getFechaEntrada() {
		return fechaEntrada;
	}


	public void setFechaEntrada(Timestamp fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}


	public Timestamp getFechaSalida() {
		return fechaSalida;
	}


	public void setFechaSalida(Timestamp fechaSalida) {
		this.fechaSalida = fechaSalida;
	}


	public long getNumeroPersonas() {
		return numeroPersonas;
	}


	public void setNumeroPersonas(long numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}


	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	public long getIdHotel() {
		return idHotel;
	}


	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}


	public long getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}


	public long getIdConvencion() {
		return idConvencion;
	}


	public void setIdConvencion(long idConvencion) {
		this.idConvencion = idConvencion;
	}


	public long getIdTipoHabitacion() {
		return idTipoHabitacion;
	}


	public void setIdTipoHabitacion(long idTipoHabitacion) {
		this.idTipoHabitacion = idTipoHabitacion;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Convencion getConvencion() {
		return convencion;
	}


	public void setConvencion(Convencion convencion) {
		this.convencion = convencion;
	}


	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}


	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	
	

}