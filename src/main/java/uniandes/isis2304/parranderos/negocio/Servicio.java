package uniandes.isis2304.parranderos.negocio;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Servicio
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private long id;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String nombre;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String descripcion;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Timestamp horaApertura;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Timestamp horaCierre;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private double capacidad;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private BigDecimal costo;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private TipoServicio tipo;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private char costoIncluido;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private long estado, idHotel, idTipoServicio;
	
	private List<ReservaServicio> reservas;

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
	
	private List<Producto> productos;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private List<Producto> comsumos;
	
	private List<Mantenimiento> mantenimientos;
	
	private List<PlanConsumo> planesDeConsumo;
	

	public Servicio(long id, String nombre, String descripcion, Timestamp horaApertura, Timestamp horaCierre, long capacidad, BigDecimal costo, char costoIncluido, long idHotel, long idTipoServicio, long estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.capacidad = capacidad;
		this.costo = costo;
		this.costoIncluido = costoIncluido;
		this.idHotel = idHotel;
		this.idTipoServicio = idTipoServicio;
		this.estado = estado;
		
		this.reservas = new LinkedList<>();
		this.productos = new LinkedList<>();
		this.comsumos = new LinkedList<>();
		this.mantenimientos = new LinkedList<>();
		this.planesDeConsumo = new LinkedList<>();
	}
	
	public Servicio()
	{
		super();
		this.id = 0;
		this.nombre = "";
		this.descripcion = "";
		this.horaApertura = new Timestamp(0);
		this.horaCierre =  new Timestamp(0);
		this.capacidad = 0;
		this.costo = new BigDecimal(0);
		this.costoIncluido = 0;
		this.idHotel = 0;
		this.idTipoServicio = 0;
		this.estado = 0;
		
		this.reservas = new LinkedList<>();
		this.productos = new LinkedList<>();
		this.comsumos = new LinkedList<>();
		this.mantenimientos = new LinkedList<>();
		this.planesDeConsumo = new LinkedList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(Timestamp horaApertura) {
		this.horaApertura = horaApertura;
	}

	public Timestamp getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(Timestamp horaCierre) {
		this.horaCierre = horaCierre;
	}

	public double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public TipoServicio getTipo() {
		return tipo;
	}

	public void setTipo(TipoServicio tipo) {
		this.tipo = tipo;
	}

	public char getCostoIncluido() {
		return costoIncluido;
	}

	public void setCostoIncluido(char costoIncluido) {
		this.costoIncluido = costoIncluido;
	}

	public long getEstado() {
		return estado;
	}

	public void setEstado(long estado) {
		this.estado = estado;
	}

	public long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}

	public long getIdTipoServicio() {
		return idTipoServicio;
	}

	public void setIdTipoServicio(long idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}

	public List<ReservaServicio> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservaServicio> reservas) {
		this.reservas = reservas;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Producto> getComsumos() {
		return comsumos;
	}

	public void setComsumos(List<Producto> comsumos) {
		this.comsumos = comsumos;
	}

	public List<Mantenimiento> getMantenimientos() {
		return mantenimientos;
	}

	public void setMantenimientos(List<Mantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}

	public List<PlanConsumo> getPlanesDeConsumo() {
		return planesDeConsumo;
	}

	public void setPlanesDeConsumo(List<PlanConsumo> planesDeConsumo) {
		this.planesDeConsumo = planesDeConsumo;
	}
	
	
	

}
