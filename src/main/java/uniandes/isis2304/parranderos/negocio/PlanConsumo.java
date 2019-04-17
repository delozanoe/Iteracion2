package uniandes.isis2304.parranderos.negocio;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class PlanConsumo
{
	private long id;
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

	private Hotel hotel;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private List<Habitacion> habitacion;

	private List<Convencion> convenciones;
	
	private List<Servicio> servicios ;

	public PlanConsumo(String descripcion, Hotel hotel, List<Habitacion> habitacion, long id, List<Convencion> convenciones, List<Servicio> servicios) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.convenciones = convenciones;
		this.servicios = servicios;
	}

	public List<Servicio> getServicios()
	{
		return servicios;
	}
	
	public void setServicios(List<Servicio> servicios)
	{
		this.servicios = servicios;
	}


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Habitacion> getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(List<Habitacion> habitacion) {
		this.habitacion = habitacion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	 public List<Convencion> getConvencion()
	 {
		return convenciones;
	 }

	 public void setConvenciones(List<Convencion> convenciones)
	 {
		 this.convenciones = convenciones; 
	 }

}
