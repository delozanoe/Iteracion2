package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class PlanConsumo
{
	private Integer id;
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

	private ArrayList<Habitacion> habitacion;

	private ArrayList<Convencion> convenciones;

	public PlanConsumo(String descripcion, Hotel hotel, ArrayList<Habitacion> habitacion, Integer id, ArrayList<Convencion> convenciones) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.convenciones = convenciones;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
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

	public ArrayList<Habitacion> getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(ArrayList<Habitacion> habitacion) {
		this.habitacion = habitacion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	 public ArrayList<Convencion> getConvencion()
	 {
		return convenciones;
	 }

	 public void setConvenciones(ArrayList<Convencion> convenciones)
	 {
		 this.convenciones = convenciones; 
	 }

}
