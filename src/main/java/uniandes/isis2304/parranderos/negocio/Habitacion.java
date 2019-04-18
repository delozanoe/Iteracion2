package uniandes.isis2304.parranderos.negocio;
import java.math.BigDecimal;
import java.util.LinkedList;
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
	
	private long capacidad;

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
	
	private BigDecimal costoPorNoche;

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
	
	private BigDecimal cuenta;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Hotel hotel;
	
	private long idHotel,  idPlanConsumo,  idConsumoHabitacion,  idTipoHabitacion;

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
	
	private String estado;
	
	private String numero; 
	
	private PlanConsumo planConsumo;
	
	private TipoHabitacion tipoHabitacion;
	
	private List<Mantenimiento> mantenimientos ;

	public Habitacion() {
		super();
		this.id = 0;
		this.capacidad = 0;
		this.costoPorNoche = new BigDecimal(0);
		this.cuenta = new BigDecimal(0);
		this.numero = "";
		this.idHotel = 0;
		this.idPlanConsumo = 0; 
		this.idConsumoHabitacion = 0;
		this.estado = "";
		
		clientes = new LinkedList<>();
		mantenimientos = new LinkedList<>();
		servicios = new LinkedList<>();

	}
	
	public Habitacion(long id, long capacidad, BigDecimal costoPorNoche, BigDecimal cuenta, String numero, long idHotel, long idPlanConsumo, long idConsumoHabitacion, long idTipoHabitacion, String estado ) 
	{
		super();
		this.id = id;
		this.capacidad = capacidad;
		this.costoPorNoche = costoPorNoche;
		this.cuenta = cuenta;
		this.numero = numero;
		this.idHotel = idHotel;
		this.idPlanConsumo = idPlanConsumo; 
		this.idConsumoHabitacion = idPlanConsumo;
		this.estado = estado;
		
		clientes = new LinkedList<>();
		mantenimientos = new LinkedList<>();
		servicios = new LinkedList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(long capacidad) {
		this.capacidad = capacidad;
	}

	public BigDecimal getCostoPorNoche() {
		return costoPorNoche;
	}

	public void setCostoPorNoche(BigDecimal costoPorNoche) {
		this.costoPorNoche = costoPorNoche;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public BigDecimal getCuenta() {
		return cuenta;
	}

	public void setCuenta(BigDecimal cuenta) {
		this.cuenta = cuenta;
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

	public long getIdPlanConsumo() {
		return idPlanConsumo;
	}

	public void setIdPlanConsumo(long idPlanConsumo) {
		this.idPlanConsumo = idPlanConsumo;
	}

	public long getIdConsumoHabitacion() {
		return idConsumoHabitacion;
	}

	public void setIdConsumoHabitacion(long idConsumoHabitacion) {
		this.idConsumoHabitacion = idConsumoHabitacion;
	}

	public long getIdTipoHabitacion() {
		return idTipoHabitacion;
	}

	public void setIdTipoHabitacion(long idTipoHabitacion) {
		this.idTipoHabitacion = idTipoHabitacion;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public PlanConsumo getPlanConsumo() {
		return planConsumo;
	}

	public void setPlanConsumo(PlanConsumo planConsumo) {
		this.planConsumo = planConsumo;
	}

	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public List<Mantenimiento> getMantenimientos() {
		return mantenimientos;
	}

	public void setMantenimientos(List<Mantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}
	
	
	
	
}

