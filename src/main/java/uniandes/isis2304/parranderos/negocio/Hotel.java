package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Hotel
{
	private Integer id;
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

	private int ofertaHabitacional;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private ArrayList<Habitacion> habitaciones;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private ArrayList<ReservaHabitacion> reservas;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private ArrayList<Empleado> empleados;

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

	private ArrayList<PlanConsumo> planesConsumo;

	private ArrayList<TipoEmpleado> tiposEmpleados;

	private ArrayList<Convencion> convenciones; 

	public Hotel(Integer id, String pais, String ciudad, int ofertaHabitacional, ArrayList<Habitacion> habitaciones,
			ArrayList<ReservaHabitacion> reservas, ArrayList<Empleado> empleado, ArrayList<Servicio> servicios,
			ArrayList<PlanConsumo> planesConsumo, ArrayList<TipoEmpleado> tiposEmpleados, ArrayList<Convencion> convenciones) {
		super();
		this.id= id;
		this.pais = pais;
		this.ciudad = ciudad;
		this.ofertaHabitacional = ofertaHabitacional;
		this.habitaciones = habitaciones;
		this.reservas = reservas;
		this.empleados = empleado;
		this.servicios = servicios;
		this.planesConsumo = planesConsumo;
		this.tiposEmpleados = tiposEmpleados;
		this.convenciones = convenciones;
	}



	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}



	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}



	public ArrayList<PlanConsumo> getPlanesConsumo() {
		return planesConsumo;
	}



	public void setPlanesConsumo(ArrayList<PlanConsumo> planesConsumo) {
		this.planesConsumo = planesConsumo;
	}



	public ArrayList<TipoEmpleado> getTiposEmpleados() {
		return tiposEmpleados;
	}



	public void setTiposEmpleados(ArrayList<TipoEmpleado> tiposEmpleados) {
		this.tiposEmpleados = tiposEmpleados;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
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

	public int getOfertaHabitacional() {
		return ofertaHabitacional;
	}

	public void setOfertaHabitacional(int ofertaHabitacional) {
		this.ofertaHabitacional = ofertaHabitacional;
	}

	public ArrayList<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public ArrayList<ReservaHabitacion> getReservas() {
		return reservas;
	}

	public void setReservas(ArrayList<ReservaHabitacion> reservas) {
		this.reservas = reservas;
	}

	public ArrayList<Empleado> getEmpleado() {
		return empleados;
	}

	public void setEmpleado(ArrayList<Empleado> empleado) {
		this.empleados = empleado;
	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}

	public ArrayList<PlanConsumo> getPlanConsumo() {
		return planesConsumo;
	}

	public void setPlanConsumo(ArrayList<PlanConsumo> planConsumo) {
		this.planesConsumo = planConsumo;
	}

	public ArrayList<Convencion> getConvencion()
	{
		return convenciones;
	}

	public void setConvenciones(ArrayList<Convencion> concenciones)
	{
		this.convenciones = concenciones;
	}
	

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */



}
