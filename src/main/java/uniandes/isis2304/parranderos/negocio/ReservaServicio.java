package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;

import java.sql.Timestamp;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class ReservaServicio
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Timestamp horaInicio;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private long duracion;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Timestamp dia;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private long id, idServicio, idConvencion, idCliente;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private String lugar;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Cliente cliente;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Servicio servicio;

	private Convencion convencion;

	public ReservaServicio(long id, Timestamp horaInicio, Timestamp dia, long duracion, String lugar, long idCliente, long idServicio, long idConvencion) {
		super();
		this.horaInicio = horaInicio;
		this.duracion = duracion;
		this.dia = dia;
		this.duracion = duracion;
		this.lugar = lugar;
		this.idCliente= idCliente;
		this.idServicio= idServicio;
		this.idConvencion =idConvencion;
	}
	
	public ReservaServicio() {
		super();
		this.horaInicio = new Timestamp(0);
		this.duracion = 0;
		this.dia = new Timestamp(0);
		this.lugar = " ";
		this.id= 0;
		this.idCliente= 0;
		this.idServicio= 0;
		this.idConvencion =0;
	}

	public Timestamp getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}

	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	public Timestamp getDia() {
		return dia;
	}

	public void setDia(Timestamp dia) {
		this.dia = dia;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	public long getIdConvencion() {
		return idConvencion;
	}

	public void setIdConvencion(long idConvencion) {
		this.idConvencion = idConvencion;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Convencion getConvencion() {
		return convencion;
	}

	public void setConvencion(Convencion convencion) {
		this.convencion = convencion;
	}
	
	
	
}
