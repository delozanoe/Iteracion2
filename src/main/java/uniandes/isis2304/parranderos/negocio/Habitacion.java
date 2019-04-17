package uniandes.isis2304.parranderos.negocio;
import java.util.List;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Habitacion
{
	
	private long id;
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int capacidad;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private double costoPorNoche;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private List<Servicio> servicios;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private double cuenta;

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
	
	private List<Cliente> clientes;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ConsumoHabitacion consumoHabitacion;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 * 
	 */
	
	private char estado;
	
	private PlanConsumo planConsumo;
	
	private TipoHabitacion tipoHabitacion;
	
	private List<Mantenimiento> mantenimientos ;

	public Habitacion(int capacidad, double costoPorNoche, List<Servicio> servicios,
			double cuenta, Hotel hotel, List<Cliente> clientes, ConsumoHabitacion consumoHabitacion,
			PlanConsumo planConsumo, long id, TipoHabitacion tipoHabitacion, char estado) {
		super();
		this.id = id;
		this.capacidad = capacidad;
		this.costoPorNoche = costoPorNoche;
		this.servicios = servicios;
		this.cuenta = cuenta;
		this.hotel = hotel;
		this.clientes = clientes;
		this.consumoHabitacion = consumoHabitacion;
		this.planConsumo = planConsumo;
		this.tipoHabitacion = tipoHabitacion;
		this.estado = estado;
	}
	
	public List<Mantenimiento> getMantenimientos()
	{
		return mantenimientos;
	}
	
	public void setMantenimientos(List<Mantenimiento> mantenimientos)
	{
		this.mantenimientos=mantenimientos;
	}
	
	public char getEstado()
	{
		return estado;
	}
	
	public void setEstado (char estado)
	{
		this.estado = estado;
	}
	

	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}



	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public double getCostoPorNoche() {
		return costoPorNoche;
	}

	public void setCostoPorNoche(double costoPorNoche) {
		this.costoPorNoche = costoPorNoche;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public double getCuenta() {
		return cuenta;
	}

	public void setCuenta(double cuenta) {
		this.cuenta = cuenta;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ConsumoHabitacion getConsumoHabitacion() {
		return consumoHabitacion;
	}

	public void setConsumoHabitacion(ConsumoHabitacion consumoHabitacion) {
		this.consumoHabitacion = consumoHabitacion;
	}

	public PlanConsumo getPlanConsumo() {
		return planConsumo;
	}

	public void setPlanConsumo(PlanConsumo planConsumo) {
		this.planConsumo = planConsumo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	
	
	
}

