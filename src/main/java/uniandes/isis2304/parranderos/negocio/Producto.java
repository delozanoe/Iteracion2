package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Producto
{
	
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
	
	private double costo;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	
	private Integer  id;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList<Servicio> serviciosProductos;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList<Servicio> serviciosConsumos;

	public Producto(String nombre, Double costo,Integer id,
			ArrayList<Servicio> serviciosProductos, ArrayList<Servicio> serviciosConsumos) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.id = id;
		this.serviciosProductos = serviciosProductos;
		this.serviciosConsumos = serviciosConsumos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer  id) {
		this.id = id;
	}

	public ArrayList<Servicio> getServiciosProductos() {
		return serviciosProductos;
	}

	public void setServiciosProductos(ArrayList<Servicio> serviciosProductos) {
		this.serviciosProductos = serviciosProductos;
	}

	public ArrayList<Servicio> getServiciosConsumos() {
		return serviciosConsumos;
	}

	public void setServiciosConsumos(ArrayList<Servicio> serviciosConsumos) {
		this.serviciosConsumos = serviciosConsumos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	

}

