package uniandes.isis2304.parranderos.negocio;
import java.util.ArrayList;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Habitacion
{
	
	private Integer id;
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
	
	private ArrayList<Servicio> servicios;

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
	
	private ArrayList<Cliente> clientes;

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
	 */
	
	private PlanConsumo planConsumo;
	
	private TipoHabitacion tipoHabitacion;

	public Habitacion(int capacidad, double costoPorNoche, ArrayList<Servicio> servicios,
			double cuenta, Hotel hotel, ArrayList<Cliente> clientes, ConsumoHabitacion consumoHabitacion,
			PlanConsumo planConsumo, Integer id, TipoHabitacion tipoHabitacion) {
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
	}
	
	

	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}



	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
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

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
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

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
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

