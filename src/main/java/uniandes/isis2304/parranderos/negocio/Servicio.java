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
	
	private BigDecimal id;

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
	
	private String horaApertura;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String horaCierre;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private BigDecimal capacidad;

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
	
	private String costoIncluido;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private BigDecimal estado, idHotel, idTipoServicio;
	
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
	
	
	public Servicio(BigDecimal id, String nombre, String descripcion, String horaApertura, String horaCierre, BigDecimal capacidad, BigDecimal costo, String costoIncluido, BigDecimal idHotel, BigDecimal idTipoServicio, BigDecimal estado)
	{
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
		this.id = new BigDecimal(0);
		this.nombre = "";
		this.descripcion = "";
		this.horaApertura = "";
		this.horaCierre =  "";
		this.capacidad =new BigDecimal(0);
		this.costo = new BigDecimal(0);
		this.costoIncluido = "";
		this.idHotel = new BigDecimal(0);
		this.idTipoServicio = new BigDecimal(0);
		this.estado = new BigDecimal(0);
		
		this.reservas = new LinkedList<>();
		this.productos = new LinkedList<>();
		this.comsumos = new LinkedList<>();
		this.mantenimientos = new LinkedList<>();
		this.planesDeConsumo = new LinkedList<>();
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
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

	public String getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(String horaApertura) {
		this.horaApertura = horaApertura;
	}

	public String getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}

	public BigDecimal getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(BigDecimal capacidad) {
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

	public String getCostoIncluido() {
		return costoIncluido;
	}

	public void setCostoIncluido(String costoIncluido) {
		this.costoIncluido = costoIncluido;
	}

	public BigDecimal getEstado() {
		return estado;
	}

	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}

	public BigDecimal getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(BigDecimal idHotel) {
		this.idHotel = idHotel;
	}

	public BigDecimal getIdTipoServicio() {
		return idTipoServicio;
	}

	public void setIdTipoServicio(BigDecimal idTipoServicio) {
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
