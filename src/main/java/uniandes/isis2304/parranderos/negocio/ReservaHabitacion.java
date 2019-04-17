package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;
import java.util.List;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class ReservaHabitacion
{
	private Integer id;
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Date fechaEntrada;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Date fechaSalida;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private int numeroPersonas;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Hotel hotel;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Cliente cliente;

	private Convencion convencion;
	
	private TipoHabitacion tipoHabitacion;

	public ReservaHabitacion(Date fechaEntrada, Date fechaSalida, Integer numeroPersonas, Hotel hotel,
			Cliente cliente, Integer id, Convencion convencion, TipoHabitacion tipoHabitacion) throws Exception {
		super();
		this.id= id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.numeroPersonas = numeroPersonas;
		this.hotel = hotel;
		this.cliente = cliente;
		this.convencion = convencion;
		this.tipoHabitacion = tipoHabitacion;

	}


	public TipoHabitacion getTipoHabitacion()
	{
		return tipoHabitacion;
	}
	
	public void setTipoHabitacion(TipoHabitacion tipoHabitacion)
	{
		this.tipoHabitacion = tipoHabitacion;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getNumeroPersonas() {
		return numeroPersonas;
	}

	public void setNumeroPersonas(int numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Convencion getConvencion()
	{
		return convencion;
	}

	public void setConvencion(Convencion convencion)
	{
		this.convencion = convencion;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */



}
