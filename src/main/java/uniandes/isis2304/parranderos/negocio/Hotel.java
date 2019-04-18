package uniandes.isis2304.parranderos.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Hotel
{
	private long id;
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private String pais;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private String ciudad;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private long ofertaHabitacional;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private List<Habitacion> habitaciones;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private List<ReservaHabitacion> reservas;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private List<Empleado> empleados;

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

	private List<PlanConsumo> planesConsumo;

	private List<TipoEmpleado> tiposEmpleados;

	private List<Convencion> convenciones; 
	
	public Hotel()
	{
		this.id= 0;
		this.pais = " ";
		this.ciudad = " ";
		this.ofertaHabitacional = 0;
		this.habitaciones = new LinkedList<>();
		this.reservas = new LinkedList<>();
		this.empleados = new LinkedList<>();
		this.servicios = new LinkedList<>();
		this.planesConsumo = new LinkedList<>();
		this.tiposEmpleados = new LinkedList<>();
		this.convenciones = new LinkedList<>();
	}

	public Hotel(long id, String pais, String ciudad, long ofertaHabitacional) {
		super();
		this.id= id;
		this.pais = pais;
		this.ciudad = ciudad;
		this.ofertaHabitacional = ofertaHabitacional;
		this.habitaciones = new LinkedList<>();
		this.reservas = new LinkedList<>();
		this.empleados = new LinkedList<>();
		this.servicios = new LinkedList<>();
		this.planesConsumo = new LinkedList<>();
		this.tiposEmpleados = new LinkedList<>();
		this.convenciones = new LinkedList<>();
	}



	public List<Empleado> getEmpleados() {
		return empleados;
	}



	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}



	public List<PlanConsumo> getPlanesConsumo() {
		return planesConsumo;
	}



	public void setPlanesConsumo(List<PlanConsumo> planesConsumo) {
		this.planesConsumo = planesConsumo;
	}



	public List<TipoEmpleado> getTiposEmpleados() {
		return tiposEmpleados;
	}



	public void setTiposEmpleados(List<TipoEmpleado> tiposEmpleados) {
		this.tiposEmpleados = tiposEmpleados;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public long getOfertaHabitacional() {
		return ofertaHabitacional;
	}

	public void setOfertaHabitacional(long ofertaHabitacional) {
		this.ofertaHabitacional = ofertaHabitacional;
	}

	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public List<ReservaHabitacion> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservaHabitacion> reservas) {
		this.reservas = reservas;
	}

	public List<Empleado> getEmpleado() {
		return empleados;
	}

	public void setEmpleado(List<Empleado> empleado) {
		this.empleados = empleado;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public List<PlanConsumo> getPlanConsumo() {
		return planesConsumo;
	}

	public void setPlanConsumo(List<PlanConsumo> planConsumo) {
		this.planesConsumo = planConsumo;
	}

	public List<Convencion> getConvencion()
	{
		return convenciones;
	}

	public void setConvenciones(List<Convencion> concenciones)
	{
		this.convenciones = concenciones;
	}
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */



}
